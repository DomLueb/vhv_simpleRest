package de.lmis.vhv.simplerest.api.jackson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DateTimeTestController.class)
@Import(DeserializersConfiguration.class)
class TimeSerializationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DateTimeTestController dateTimeTestController;

    @Test
    void localDate_serialization() throws Exception {
        this.mockMvc.perform(
                get("/localDate"))
                .andExpect(status().isOk())
                .andExpect(content().json("\"2020-05-15\""));
    }

    @Test
    void localDateTime_serialization() throws Exception {
        this.mockMvc.perform(
                get("/localDateTime"))
                .andExpect(status().isOk())
                .andExpect(content().json("\"2020-05-15T14:30:15\""));
    }

    @Test
    void localTime_serialization() throws Exception {
        this.mockMvc.perform(
                        get("/localTime"))
                .andExpect(status().isOk())
                .andExpect(content().json("\"17:53:00\""));
    }

    @Test
    void instant_serialization() throws Exception {
        this.mockMvc.perform(
                get("/instant"))
                .andExpect(status().isOk())
                .andExpect(content().json("\"1973-11-29T21:33:09Z\""));
    }

    @Test
    void localDate_isoLocalDate_deserialization() throws Exception {
        this.mockMvc.perform(
                post("/localDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2020-05-15\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalDate.of(2020,5,15), dateTimeTestController.acceptedLocalDate);
    }

    @Test
    void localDate_withTimeAndOffset_deserialization() throws Exception {
        this.mockMvc.perform(
                post("/localDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2021-12-25T13:50:22.000+01:00\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalDate.of(2021,12,25), dateTimeTestController.acceptedLocalDate);
    }

    @Test
    void localDateTime_isoLocalDateTime_deserialization() throws Exception {
        this.mockMvc.perform(
                post("/localDateTime")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2020-05-15T19:30\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalDateTime.of(2020,5,15,19,30), dateTimeTestController.acceptedLocalDateTime);
    }

    @Test
    void localDateTime_withZoneOffset_deserialization() throws Exception {
        this.mockMvc.perform(
                post("/localDateTime")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2021-12-25T13:50:22.000+01:00\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalDateTime.of(2021,12,25,13,50,22), dateTimeTestController.acceptedLocalDateTime);
    }

    @Test
    void localTime_isoLocalTime_deserialization() throws Exception {
        this.mockMvc.perform(
                        post("/localTime")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("\"19:15:03\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalTime.of(19,15,3), dateTimeTestController.acceptedLocalTime);
    }

    @Test
    void localTime_withDateAndOffset_deserialization() throws Exception {
        this.mockMvc.perform(
                        post("/localTime")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("\"2021-12-25T13:50:22.000+01:00\""))
                .andExpect(status().isOk());

        Assertions.assertEquals(LocalTime.of(13,50,22), dateTimeTestController.acceptedLocalTime);
    }

    @Test
    void instant_utcTime_deserialization() throws Exception {
        this.mockMvc.perform(
                post("/instant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2021-12-25T21:06:13Z\""))
                .andExpect(status().isOk());

        var expectedInstant= LocalDateTime.of(2021, 12, 25, 21, 6, 13).toInstant(ZoneOffset.UTC);
        Assertions.assertEquals(expectedInstant, dateTimeTestController.acceptedInstant);
    }
}

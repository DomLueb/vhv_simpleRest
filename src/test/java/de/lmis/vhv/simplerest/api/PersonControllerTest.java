package de.lmis.vhv.simplerest.api;

import de.lmis.vhv.simplerest.business.PersonService;
import de.lmis.vhv.simplerest.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void getAllPersons() throws Exception{
        when(this.personService.getAllPersons())
                .thenReturn(List.of(getDemoPerson()));

        this.mockMvc.perform(
                        get("/persons"))
                .andDo(print())
                .andExpect(status().isOk());
        // TODO: compare result with expectation
    }

    private Person getDemoPerson() {
        return Person.builder().firstName("FN").lastName("LN").build();
    }
}
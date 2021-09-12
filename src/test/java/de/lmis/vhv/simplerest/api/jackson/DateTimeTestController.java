package de.lmis.vhv.simplerest.api.jackson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
class DateTimeTestController {
    LocalDate acceptedLocalDate;
    LocalDateTime acceptedLocalDateTime;
    LocalTime acceptedLocalTime;
    Instant acceptedInstant;

    @GetMapping(path = "/localDate")
    public LocalDate getLocalDate() {
        return LocalDate.of(2020, 5, 15);
    }

    @GetMapping(path = "/localDateTime")
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.of(2020, 5, 15, 14, 30, 15);
    }

    @GetMapping(path = "/localTime")
    public LocalTime getLocalTime() {
        return LocalTime.of(17, 53);
    }

    @GetMapping(path = "/instant")
    public Instant getInstant() {
        return Instant.ofEpochSecond(123_456_789);
    }

    @PostMapping(path = "/localDate")
    public void postLocalDate(@RequestBody LocalDate ld) {
        this.acceptedLocalDate = ld;
    }

    @PostMapping(path = "/localDateTime")
    public void postLocalDateTime(@RequestBody LocalDateTime ldt) {
        this.acceptedLocalDateTime = ldt;
    }

    @PostMapping(path = "/localTime")
    public void postLocalTime(@RequestBody LocalTime lt) {
        this.acceptedLocalTime = lt;
    }

    @PostMapping(path = "/instant")
    public void postInstant(@RequestBody Instant instant) {
        this.acceptedInstant = instant;
    }
}

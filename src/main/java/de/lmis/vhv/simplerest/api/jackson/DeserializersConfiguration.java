package de.lmis.vhv.simplerest.api.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Configuration
public class DeserializersConfiguration {
    @Bean
    public com.fasterxml.jackson.databind.Module createDeserializeModule() {
        var module = new SimpleModule();

        module.addDeserializer(LocalDate.class, new TimeZoneTolerantLocalDateDeserializer());
        module.addDeserializer(LocalDateTime.class, new TimeZoneTolerantLocalDateTimeDeserializer());
        module.addDeserializer(LocalTime.class, new DateAndTimeZoneTolerantLocalTimeDeserializer());
        return module;
    }
}

package de.lmis.vhv.simplerest.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneTolerantLocalDateTimeDeserializer extends StdScalarDeserializer<LocalDateTime> {

    public TimeZoneTolerantLocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        try {
            return LocalDateTimeDeserializer.INSTANCE.deserialize(parser, ctxt);
        } catch (InvalidFormatException ife) {
            try {
                var parseAsOffsetDateTime = OffsetDateTime.parse(parser.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                return parseAsOffsetDateTime.toLocalDateTime();
            } catch (Exception e) {
                throw ife; // throw original format exception
            }
        }
    }
}

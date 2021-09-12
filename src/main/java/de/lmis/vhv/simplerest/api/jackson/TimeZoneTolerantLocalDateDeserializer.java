package de.lmis.vhv.simplerest.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneTolerantLocalDateDeserializer extends StdScalarDeserializer<LocalDate> {

    public TimeZoneTolerantLocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        try {
            return LocalDateDeserializer.INSTANCE.deserialize(parser, ctxt);
        } catch (InvalidFormatException ife) {
            try {
                var parseAsOffsetDateTime = OffsetDateTime.parse(parser.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                return parseAsOffsetDateTime.toLocalDate();
            } catch (Exception e) {
                throw ife; // throw original format exception
            }
        }
    }
}

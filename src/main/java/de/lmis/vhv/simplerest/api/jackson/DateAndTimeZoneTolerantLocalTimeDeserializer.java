package de.lmis.vhv.simplerest.api.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeZoneTolerantLocalTimeDeserializer extends StdScalarDeserializer<LocalTime> {

    public DateAndTimeZoneTolerantLocalTimeDeserializer() {
        super(LocalTime.class);
    }

    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        try {
            return LocalTimeDeserializer.INSTANCE.deserialize(parser, ctxt);
        } catch (InvalidFormatException ife) {
            try {
                var parseAsDateTimeWithZoneOffset = OffsetDateTime.parse(parser.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                return parseAsDateTimeWithZoneOffset.toLocalTime();
            } catch (Exception e) {
                throw ife; // throw original format exception
            }
        }
    }
}

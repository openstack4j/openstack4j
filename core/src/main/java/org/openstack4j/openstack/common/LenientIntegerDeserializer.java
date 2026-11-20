package org.openstack4j.openstack.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Reads a 32-bit whole number that a driver may report as text: JSON numbers keep Jackson's default handling (a fraction is
 * truncated); a string is trimmed and rounded half-up ({@code "1230.75"} gives 1231); an empty string, a keyword such as
 * {@code "infinite"} or {@code "unknown"}, or a value out of range gives {@code null}, so one back end does not fail
 * the whole list.
 */
public class LenientIntegerDeserializer extends JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.currentToken() == JsonToken.VALUE_STRING) {
            BigDecimal value = LenientLongDeserializer.decimal(parser.getText());
            try {
                return value == null ? null : value.setScale(0, RoundingMode.HALF_UP).intValueExact();
            } catch (ArithmeticException e) {
                return null;
            }
        }
        return context.readValue(parser, Integer.class);
    }
}

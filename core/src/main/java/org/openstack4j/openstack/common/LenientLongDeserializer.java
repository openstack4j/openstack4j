package org.openstack4j.openstack.common;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Reads a whole number that a driver may report as text: JSON numbers keep Jackson's default handling (a fraction is
 * truncated); a string is trimmed and rounded half-up ({@code "1230.75"} gives 1231); an empty string, a keyword such as
 * {@code "infinite"} or {@code "unknown"}, or a value out of range gives {@code null}, so one back end does not fail
 * the whole list.
 */
public class LenientLongDeserializer extends JsonDeserializer<Long> {

    @Override
    public Long deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.currentToken() == JsonToken.VALUE_STRING) {
            BigDecimal value = decimal(parser.getText());
            try {
                return value == null ? null : value.setScale(0, RoundingMode.HALF_UP).longValueExact();
            } catch (ArithmeticException e) {
                return null;
            }
        }
        return context.readValue(parser, Long.class);
    }

    /** @return the number in the text, or {@code null} when it is empty, not a finite number or has more than 19 integer digits */
    static BigDecimal decimal(String text) {
        String trimmed = text == null ? "" : text.trim();
        if (trimmed.isEmpty())
            return null;
        try {
            BigDecimal value = new BigDecimal(trimmed);
            // Avoid rounding values whose exponent is too large in either direction.
            long integerDigits = (long) value.precision() - value.scale();
            if (integerDigits > 19) {
                return null;
            }
            return integerDigits < 0 ? BigDecimal.ZERO : value;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

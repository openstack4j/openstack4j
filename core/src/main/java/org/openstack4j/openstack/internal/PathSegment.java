package org.openstack4j.openstack.internal;

import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.regex.Pattern;

/** Turns a caller-supplied identifier into exactly one URL path segment. */
public final class PathSegment {

    private static final char[] HEX = "0123456789ABCDEF".toCharArray();
    private static final Pattern DOT_SEGMENT = Pattern.compile("(?:\\.|%2[eE]){1,2}");
    private static final Pattern ENCODED_SLASH = Pattern.compile("%2[fF]");

    private PathSegment() {
    }

    /**
     * Validates (see {@link #validate}) and percent-encodes value as one RFC 3986 path segment: ALPHA, DIGIT, "-", ".", "_", "~",
     * ":" and "@" are kept, a valid %XX escape is kept as it is (an encoded value is not encoded twice), every other
     * character is percent-encoded from its UTF-8 bytes with upper case hex digits.
     */
    public static String encode(String value) {
        validate(value);
        ByteBuffer buffer;
        try {
            buffer = StandardCharsets.UTF_8.newEncoder()
                    .onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT)
                    .encode(CharBuffer.wrap(value));
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Not a valid identifier: '" + value + "'", e);
        }
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        StringBuilder out = new StringBuilder(bytes.length + 8);
        for (int i = 0; i < bytes.length; i++) {
            int b = bytes[i] & 0xFF;
            if (b == '%' && i + 2 < bytes.length && isHex(bytes[i + 1]) && isHex(bytes[i + 2])) {
                out.append('%').append((char) bytes[i + 1]).append((char) bytes[i + 2]);
                i += 2;
            } else if (isKept(b)) {
                out.append((char) b);
            } else {
                out.append('%').append(HEX[b >> 4]).append(HEX[b & 0xF]);
            }
        }
        return out.toString();
    }

    /**
     * @return value unchanged, for identifiers sent in a body or query
     * @throws NullPointerException value is null
     * @throws IllegalArgumentException blank, contains "/", "?" or "#", contains an encoded slash (%2F), is "." or ".." (also %2E
     *         forms), or (encode only) is not well-formed UTF-16
     */
    public static String validate(String value) {
        Objects.requireNonNull(value, "id");
        if (value.codePoints().allMatch(Character::isWhitespace) || value.indexOf('/') >= 0 || value.indexOf('?') >= 0 || value.indexOf('#') >= 0
                || ENCODED_SLASH.matcher(value).find() || DOT_SEGMENT.matcher(value).matches())
            throw new IllegalArgumentException("Not a valid identifier: '" + value + "'");
        return value;
    }

    private static boolean isHex(int b) {
        return (b >= '0' && b <= '9') || (b >= 'A' && b <= 'F') || (b >= 'a' && b <= 'f');
    }

    private static boolean isKept(int b) {
        return (b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z') || (b >= '0' && b <= '9')
                || b == '-' || b == '.' || b == '_' || b == '~' || b == ':' || b == '@';
    }
}

package org.openstack4j.model.storage.object.options;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Holds location information for an Object (Container and Object name including path)
 *
 * @author Jeremy Unruh
 */
public final class ObjectLocation {

    private static final char[] HEX = "0123456789ABCDEF".toCharArray();

    private String containerName;
    private String objectName;


    private ObjectLocation(String containerName, String objectName) {
        super();
        Objects.requireNonNull(containerName, "ContainerName cannot be null");
        Objects.requireNonNull(objectName, "ObjectName cannot be null");

        this.containerName = containerName;
        this.objectName = objectName;
    }

    public static ObjectLocation create(String containerName, String objectName) {
        return new ObjectLocation(containerName, objectName);
    }

    public String getContainerName() {
        return containerName;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getURI() {
        return String.format("/%s/%s", encodePath(containerName), encodePath(objectName));
    }

    /**
     * Percent-encodes a container or object name for safe inclusion in a request
     * URL path.  Swift names may contain arbitrary characters (e.g. {@code %},
     * {@code #}, {@code ?}, spaces, non-ASCII), all of which must be escaped so
     * the HTTP client does not misinterpret them.  RFC 3986 unreserved characters
     * are left intact, and {@code /} is preserved so pseudo-directory object names
     * keep their path structure.
     *
     * @param name the raw (unencoded) name
     * @return the percent-encoded path segment(s)
     */
    public static String encodePath(String name) {
        StringBuilder sb = new StringBuilder(name.length() + 16);
        for (byte b : name.getBytes(StandardCharsets.UTF_8)) {
            int c = b & 0xFF;
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
                    || (c >= '0' && c <= '9')
                    || c == '-' || c == '.' || c == '_' || c == '~' || c == '/') {
                sb.append((char) c);
            } else {
                sb.append('%').append(HEX[(c >> 4) & 0xF]).append(HEX[c & 0xF]);
            }
        }
        return sb.toString();
    }
}

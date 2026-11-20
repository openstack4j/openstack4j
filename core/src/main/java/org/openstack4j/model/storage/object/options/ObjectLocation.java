package org.openstack4j.model.storage.object.options;

import java.util.Objects;

import com.google.common.net.PercentEscaper;
/**
 * Holds location information for an Object (Container and Object name including path)
 *
 * @author Jeremy Unruh
 */
public final class ObjectLocation {

    private static final PercentEscaper PATH_ESCAPER = new PercentEscaper("-._~/", false);
    private static final PercentEscaper SEGMENT_ESCAPER = new PercentEscaper("-._~", false);

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
        if (containerName.isEmpty() || containerName.indexOf('/') >= 0 || ".".equals(containerName) || "..".equals(containerName)) {
            throw new IllegalArgumentException("Container name must be a single path segment");
        }
        for (String component : objectName.split("/", -1)) {
            if (component.isEmpty() || ".".equals(component) || "..".equals(component)) {
                return "/" + encodePath(containerName) + "/" + SEGMENT_ESCAPER.escape(objectName);
            }
        }
        return "/" + encodePath(containerName) + "/" + encodePath(objectName);
    }

    public static String encodePath(String path) {
        return PATH_ESCAPER.escape(path);
    }
}

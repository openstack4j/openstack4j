package org.openstack4j.model.storage.object.options;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Options used for copying objects
 *
 * @author Lukas Alt
 */
public final class ObjectCopyOptions {

    public static final ObjectCopyOptions NONE = new ObjectCopyOptions();
    private final ObjectPutOptions options = ObjectPutOptions.create();

    private ObjectCopyOptions() {
    }

    public static ObjectCopyOptions create() {
        return new ObjectCopyOptions();
    }

    /**
     * Specifies the MIME type/Content Type of the uploaded payload
     *
     * @param contentType the content type/mime type
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions contentType(String contentType) {
        options.contentType(contentType);
        return this;
    }

    @Nullable
    public String getContentType() {
        return options.getContentType();
    }

    /**
     * Additional metadata associated with the Object
     *
     * @param metadata the metadata
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions metadata(Map<String, String> metadata) {
        options.metadata(metadata);
        return this;
    }

    /**
     *
     * @param headers map containing headers to add
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions headers(Map<String, String> headers) {
        options.getOptions().putAll(headers);
        return this;
    }

    /**
     * Sets the specified header
     *
     * @param key The key of the header
     * @param value The value of the header
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions header(String key, String value) {
        options.header(key, value);
        return this;
    }

    /**
     * Sets a header which indicates when the copy should be deleted
     *
     * @param date The date after which the copy should be deleted
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions deleteAt(Date date) {
        options.deleteAt(date);
        return this;
    }

    /**
     * Sets an header which indicates after which period of time the copy should be deleted
     *
     * @param t the delay
     * @param unit the unit of the delay
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions deleteAfter(long t, TimeUnit unit) {
        options.deleteAfter(t, unit);
        return this;
    }

    public Map<String, String> getHeaders() {
        return options.getOptions();
    }

}

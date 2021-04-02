package org.openstack4j.model.storage.object.options;

import com.google.common.collect.Maps;
import org.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;

import javax.annotation.Nullable;
import java.util.Map;

import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_TYPE;
import static org.openstack4j.model.storage.object.SwiftHeaders.OBJECT_METADATA_PREFIX;

/**
 * Options used for copying objects
 *
 * @author Lukas Alt
 */
public class ObjectCopyOptions {
    private final Map<String, String> headers = Maps.newHashMap();

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
        headers.put(CONTENT_TYPE, contentType);
        return this;
    }

    @Nullable
    public String getContentType() {
        return headers.get(CONTENT_TYPE);
    }

    /**
     * Additional metadata associated with the Object
     *
     * @param metadata the metadata
     * @return ObjectPutOptions
     */
    public ObjectCopyOptions metadata(Map<String, String> metadata) {
        this.headers.putAll(MetadataToHeadersFunction.create(OBJECT_METADATA_PREFIX).apply(metadata));
        return this;
    }

    /**
     *
     * @param headers map containing headers to add
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions headers(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    /**
     * Sets the specified header
     * @param key The key of the header
     * @param value The value of the header
     * @return ObjectCopyOptions
     */
    public ObjectCopyOptions header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}

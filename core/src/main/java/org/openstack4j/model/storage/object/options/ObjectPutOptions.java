package org.openstack4j.model.storage.object.options;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Maps;
import org.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;

import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_TYPE;
import static org.openstack4j.model.storage.object.SwiftHeaders.OBJECT_METADATA_PREFIX;

/**
 * Options used for the creation and update of Objects
 *
 * @author Jeremy Unruh
 */
public final class ObjectPutOptions {

    public static final ObjectPutOptions NONE = new ObjectPutOptions();
    Map<String, String> headers = Maps.newHashMap();
    private Map<String, List<Object>> queryParams = Maps.newHashMap();
    private String path;

    private ObjectPutOptions() {
    }

    public static ObjectPutOptions create() {
        return new ObjectPutOptions();
    }

    /**
     * Specifies the MIME type/Content Type of the uploaded payload
     *
     * @param contentType the content type/mime type
     * @return ObjectPutOptions
     */
    public ObjectPutOptions contentType(String contentType) {
        headers.put(CONTENT_TYPE, contentType);
        return this;
    }

    public ObjectPutOptions path(String path) {
        if (path != null)
            this.path = (path.startsWith("/") ? path.substring(1): path);
        return this;
    }

    /**
     * Additional metadata associated with the Object
     *
     * @param metadata the metadata
     * @return ObjectPutOptions
     */
    public ObjectPutOptions metadata(Map<String, String> metadata) {
        this.headers.putAll(MetadataToHeadersFunction.create(OBJECT_METADATA_PREFIX).apply(metadata));
        return this;
    }

    public Map<String, String> getOptions() {
        return headers;
    }

    public String getContentType() {
        return headers.get(CONTENT_TYPE);
    }

    public String getPath() {
        return path;
    }

    public ObjectPutOptions queryParam(String key, Object value) {
        if (value == null)
            return this;

        if (queryParams.containsKey(key)) {
            List<Object> list = queryParams.get(key);
            list.add(value);
        } else {
            List<Object> list = new ArrayList<Object>();
            list.add(value);
            queryParams.put(key, list);
        }
        return this;
    }


    /**
     * Sets the specified header
     * @param key The key of the header
     * @param value The value of the header
     * @return ObjectCopyOptions
     */
    public ObjectPutOptions header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }


    /**
     * Sets a header which indicates when the copy should be deleted
     * @param date The date after which the copy should be deleted
     * @return ObjectCopyOptions
     */
    public ObjectPutOptions deleteAt(Date date) {
        return this.header("X-Delete-At", Long.toString(TimeUnit.MILLISECONDS.toSeconds(date.getTime())));
    }

    /**
     * Sets an header which indicates after which period of time the copy should be deleted
     * @param t the delay
     * @param unit the unit of the delay
     * @return ObjectCopyOptions
     */
    public ObjectPutOptions deleteAfter(long t, TimeUnit unit) {
        final long secs = unit.toSeconds(t);
        if(secs < 1) {
            throw new IllegalArgumentException("TTL has to be at least 1 seconds (" + t + " " + unit + ")");
        }
        return this.header("X-Delete-After", Long.toString(secs));
    }

    public Map<String, List<Object>> getQueryParams() {
        return queryParams;
    }
}

package org.openstack4j.openstack.storage.object.internal;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.openstack4j.api.storage.ObjectStorageObjectService;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.DLPayload;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.common.payloads.FilePayload;
import org.openstack4j.model.storage.block.options.DownloadOptions;
import org.openstack4j.model.storage.object.SwiftObject;
import org.openstack4j.model.storage.object.options.ObjectDeleteOptions;
import org.openstack4j.model.storage.object.options.ObjectListOptions;
import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.openstack4j.model.storage.object.options.ObjectPutOptions;
import org.openstack4j.model.storage.object.options.SLOSegment;
import org.openstack4j.openstack.common.DLPayloadEntity;
import org.openstack4j.openstack.common.functions.HeaderNameValuesToHeaderMap;
import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl;
import org.openstack4j.openstack.storage.object.domain.SwiftObjectImpl.SwiftObjects;
import org.openstack4j.openstack.storage.object.functions.ApplyContainerToObjectFunction;
import org.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;
import org.openstack4j.openstack.storage.object.functions.MetadataToHeadersFunction;
import org.openstack4j.openstack.storage.object.functions.ParseObjectFunction;

import static org.openstack4j.core.transport.HttpEntityHandler.closeQuietly;
import static org.openstack4j.model.storage.object.SwiftHeaders.CONTENT_LENGTH;
import static org.openstack4j.model.storage.object.SwiftHeaders.ETAG;
import static org.openstack4j.model.storage.object.SwiftHeaders.OBJECT_METADATA_PREFIX;
import static org.openstack4j.model.storage.object.SwiftHeaders.X_COPY_FROM;

/**
 * A service responsible for maintaining directory and file objects within containers for
 * an Object Service within OpenStack
 *
 * @author Jeremy Unruh
 */
public class ObjectStorageObjectServiceImpl extends BaseObjectStorageService implements ObjectStorageObjectService {

    @Override
    public List<? extends SwiftObject> list(String containerName) {
        Objects.requireNonNull(containerName);
        List<SwiftObjectImpl> objs = get(SwiftObjects.class, uri("/%s", ObjectLocation.encodePath(containerName))).param("format", "json").execute();

        if (objs == null) {
            return Collections.emptyList();
        }

        return objs.stream().map(ApplyContainerToObjectFunction.create(containerName)).collect(Collectors.toList());
    }

    @Override
    public List<? extends SwiftObject> list(String containerName, ObjectListOptions options) {
        if (options == null)
            return list(containerName);

        Objects.requireNonNull(containerName);

        List<SwiftObjectImpl> objs = get(SwiftObjects.class, uri("/%s", ObjectLocation.encodePath(containerName))).param("format", "json").params(options.getOptions()).execute();
        if (objs == null) {
            return Collections.emptyList();
        }
        return objs.stream().map(ApplyContainerToObjectFunction.create(containerName)).collect(Collectors.toList());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftObject get(ObjectLocation location) {
        Objects.requireNonNull(location);

        HttpResponse resp = head(Void.class, location.getURI()).executeWithResponse();
        try {
            if (resp.getStatus() == 404)
                return null;

            return ParseObjectFunction.create(location).apply(resp);
        } finally {
            closeQuietly(resp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SwiftObject get(String containerName, String name) {
        return get(ObjectLocation.create(containerName, name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String containerName, String name, Payload<?> payload) {
        return put(containerName, name, payload, ObjectPutOptions.NONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String put(String containerName, String name, Payload<?> payload, ObjectPutOptions options) {
        Objects.requireNonNull(containerName);
        Objects.requireNonNull(options);

        if (payload != null && FilePayload.class.isAssignableFrom(payload.getClass()) && name == null)
            name = FilePayload.class.cast(payload).getRaw().getName();
        else
            Objects.requireNonNull(name);


        if (options.getPath() != null && name.indexOf('/') == -1)
            name = options.getPath() + "/" + name;

        HttpResponse resp = put(Void.class, uri("/%s/%s", ObjectLocation.encodePath(containerName), ObjectLocation.encodePath(name)))
                .entity(payload)
                .headers(options.getOptions())
                .contentType(options.getContentType())
                .paramLists(options.getQueryParams())
                .executeWithResponse();
        try {
            return resp.header(ETAG);
        } finally {
            closeQuietly(resp);
        }
    }

    @Override
    public String createStaticLargeObject(String containerName, String name,
            List<? extends SLOSegment> segments) {
        return createStaticLargeObject(containerName, name, segments, ObjectPutOptions.NONE);
    }

    @Override
    public String createStaticLargeObject(String containerName, String name,
            List<? extends SLOSegment> segments, ObjectPutOptions options) {
        Objects.requireNonNull(containerName);
        Objects.requireNonNull(name);
        Objects.requireNonNull(segments);
        Objects.requireNonNull(options);

        Payload<InputStream> payload = Payloads.create(new ByteArrayInputStream(
                toManifestJson(segments).getBytes(StandardCharsets.UTF_8)));

        // The ?multipart-manifest=put query parameter marks the JSON body as an
        // SLO manifest; the request's Content-Type becomes the large object's.
        HttpResponse resp = put(Void.class, uri("/%s/%s", ObjectLocation.encodePath(containerName), ObjectLocation.encodePath(name)))
                .entity(payload)
                .headers(options.getOptions())
                .contentType(options.getContentType())
                .param("multipart-manifest", "put")
                .paramLists(options.getQueryParams())
                .executeWithResponse();
        try {
            return resp.header(ETAG);
        } finally {
            closeQuietly(resp);
        }
    }

    private static String toManifestJson(List<? extends SLOSegment> segments) {
        List<Map<String, Object>> manifest = new ArrayList<>(segments.size());
        for (SLOSegment segment : segments) {
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("path", segment.getPath());
            if (segment.getEtag() != null) {
                entry.put("etag", segment.getEtag());
            }
            entry.put("size_bytes", segment.getSizeBytes());
            manifest.add(entry);
        }
        try {
            return ObjectMapperSingleton.getContext(List.class).writer().writeValueAsString(manifest);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize SLO manifest", e);
        }
    }

    @Override
    public ActionResponse delete(String containerName, String name) {
        Objects.requireNonNull(containerName);
        Objects.requireNonNull(name);

        return delete(ObjectLocation.create(containerName, name));
    }

    @Override
    public ActionResponse delete(ObjectLocation location) {
        return delete(location, ObjectDeleteOptions.NONE);
    }

    @Override
    public ActionResponse delete(ObjectLocation location, ObjectDeleteOptions options) {
        Objects.requireNonNull(location);
        Objects.requireNonNull(options);
        return delete(ActionResponse.class, location.getURI())
                .paramLists(options.getQueryParams())
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String copy(ObjectLocation source, ObjectLocation dest) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(dest);

        HttpResponse resp = put(Void.class, dest.getURI())
                .header(X_COPY_FROM, source.getURI())
                .header(CONTENT_LENGTH, 0)
                .executeWithResponse();
        try {
            return resp.header(ETAG);
        } finally {
            closeQuietly(resp);
        }
    }

    @Override
    public Map<String, String> getMetadata(ObjectLocation location) {
        Objects.requireNonNull(location);

        HttpResponse resp = head(Void.class, location.getURI()).executeWithResponse();
        try {
            return MapWithoutMetaPrefixFunction.INSTANCE.apply(resp.headers());
        } finally {
            closeQuietly(resp);
        }
    }

    @Override
    public Map<String, String> getMetadata(String containerName, String name) {
        Objects.requireNonNull(containerName);
        Objects.requireNonNull(name);
        return getMetadata(ObjectLocation.create(containerName, name));
    }

    @Override
    public boolean updateMetadata(ObjectLocation location, Map<String, String> metadata) {
        Objects.requireNonNull(location);
        Objects.requireNonNull(metadata);

        //the successfull response state of updateMetadata is 202 instead of 204
        //I test it by curl and this api
        return isResponseSuccess(post(Void.class, location.getURI())
                .headers(MetadataToHeadersFunction.create(OBJECT_METADATA_PREFIX).apply(metadata))
                .executeWithResponse(), 202);
    }

    @Override
    public DLPayload download(String containerName, String name) {
        return download(ObjectLocation.create(containerName, name), DownloadOptions.create());
    }

    @Override
    public DLPayload download(String containerName, String name, DownloadOptions options) {
        Objects.requireNonNull(containerName);
        Objects.requireNonNull(name);
        Objects.requireNonNull(options);

        return download(ObjectLocation.create(containerName, name), options);
    }

    @Override
    public DLPayload download(ObjectLocation location, DownloadOptions options) {
        Objects.requireNonNull(location);
        Objects.requireNonNull(options);

        return DLPayloadEntity.create(
                get(Void.class, location.getURI())
                        .headers(HeaderNameValuesToHeaderMap.INSTANCE.apply(options.getHeaders()))
                        .executeWithResponse()
        );
    }
}

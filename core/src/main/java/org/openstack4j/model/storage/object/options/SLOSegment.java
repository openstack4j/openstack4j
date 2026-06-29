package org.openstack4j.model.storage.object.options;

/**
 * Describes a single segment of a Static Large Object (SLO) manifest.  Each
 * segment refers to a previously-uploaded object by its
 * {@code <container>/<object>} path together with its ETag and size, which the
 * server validates when the manifest is stored.
 */
public final class SLOSegment {

    private final String path;
    private final String etag;
    private final long sizeBytes;

    private SLOSegment(String path, String etag, long sizeBytes) {
        this.path = path;
        this.etag = etag;
        this.sizeBytes = sizeBytes;
    }

    /**
     * Creates a segment from a {@code <container>/<object>} path.
     *
     * @param path the segment object's path, e.g. {@code "segments/0001"}
     * @param etag the segment's ETag (MD5); may be null to skip server-side
     *             validation of the segment's content
     * @param sizeBytes the segment's size in bytes
     * @return the segment descriptor
     */
    public static SLOSegment create(String path, String etag, long sizeBytes) {
        return new SLOSegment(path, etag, sizeBytes);
    }

    /**
     * Creates a segment from separate container and object names.
     *
     * @param containerName the segment object's container
     * @param objectName the segment object's name
     * @param etag the segment's ETag (MD5); may be null to skip server-side
     *             validation of the segment's content
     * @param sizeBytes the segment's size in bytes
     * @return the segment descriptor
     */
    public static SLOSegment create(String containerName, String objectName, String etag, long sizeBytes) {
        return new SLOSegment(containerName + "/" + objectName, etag, sizeBytes);
    }

    /**
     * @return the segment object's {@code <container>/<object>} path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the segment's ETag (MD5), or null if unspecified
     */
    public String getEtag() {
        return etag;
    }

    /**
     * @return the segment's size in bytes
     */
    public long getSizeBytes() {
        return sizeBytes;
    }
}

package org.openstack4j.model.manila;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.Link;

/**
 * A share is a remote, mountable file system.
 * You can mount a share to and access a share from several hosts by several users at a time.
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface Share extends ModelEntity {
    /**
     * @return The UUID of the share
     */
    String getId();

    /**
     * @return The share status
     */
    Status getStatus();

    /**
     * @return The share links
     */
    List<? extends Link> getLinks();

    /**
     * @return The UUID of the project in which the share was created
     */
    String getProjectId();

    /**
     * @return The Shared File Systems protocol
     */
    Protocol getShareProto();

    /**
     * @return The share size, in GBs
     */
    Integer getSize();

    /**
     * @return The share name
     */
    String getName();

    /**
     * @return The share description
     */
    String getDescription();

    /**
     * @return The share name
     */
    String getDisplayName();

    /**
     * @return The share description.
     */
    String getDisplayDescription();

    /**
     * @return The UUID of the share type
     */
    String getShareType();

    /**
     * @return The share type name
     */
    String getShareTypeName();

    /**
     * @return The volume type
     */
    String getVolumeType();

    /**
     * @return The UUID of the snapshot from which to create the share
     */
    String getSnapshotId();

    /**
     * @return The level of visibility for the share
     */
    Boolean isPublic();

    /**
     * @return One or more metadata key and value pairs as a dictionary of strings
     */
    Map<String, String> getMetadata();

    /**
     * @return The UUID of the share network
     */
    String getShareNetworkId();

    /**
     * @return The availability zone
     */
    String getAvailabilityZone();

    /**
     * @return The export location
     */
    String getExportLocation();

    /**
     * @return A list of export locations
     */
    List<String> getExportLocations();

    /**
     * @return The share host name
     */
    String getHost();

    /**
     * @return For the share migration, the migration task state
     */
    TaskState getTaskState();

    /**
     * @return The UUID of the share server
     */
    String getShareServerId();

    /**
     * @return The UUID of the consistency group where the share was created
     */
    String getConsistencyGroupId();

    /**
     * @return An extra specification that filters back ends by whether they do or do not support share snapshots
     */
    Boolean getSnapshotSupport();

    /**
     * @return If the share was created with <code>consistency_group_id</code> attribute, the snapshot member ID
     */
    String getSourceCgsnapshotMemberId();

    /**
     * @return The date and time stamp when the share was created
     */
    String getCreatedAt();

    enum Status {
        CREATING,
        DELETING,
        ERROR,
        ERROR_DELETING,
        AVAILABLE,
        MANAGE_STARTING,
        UNMANAGE_STARTING,
        UNMANAGE_ERROR,
        UNMANAGED,
        EXTENDING,
        EXTENDING_ERROR,
        SHRINKING,
        SHRINKING_ERROR,
        SHRINKING_POSSIBLE_DATA_LOSS_ERROR,
        UNRECOGNIZED;

        @JsonCreator
        public static Status value(String v) {
            try {
                return valueOf(v.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                return UNRECOGNIZED;
            }
        }

        @JsonValue
        public String value() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

    enum Protocol {
        NFS, CIFS, GlusterFS, HDFS, CEPHFS, MAPRFS, LUSTRE, WEKAFS, UNRECOGNIZED;

        @JsonCreator
        public static Protocol value(String v) {
            for (Protocol protocol : values()) {
                if (protocol.name().equalsIgnoreCase(v)) {
                    return protocol;
                }
            }
            return UNRECOGNIZED;
        }
    }

    enum TaskState {
        NULL,
        MIGRATION_STARTING,
        MIGRATION_ERROR,
        MIGRATION_SUCCESS,
        MIGRATION_COMPLETING,
        MIGRATING,
        UNRECOGNIZED;

        @JsonCreator
        public static TaskState value(String v) {
            try {
                return valueOf(v.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                return UNRECOGNIZED;
            }
        }

        @JsonValue
        public String value() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
}

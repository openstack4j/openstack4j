package org.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.storage.block.VolumeBackupImport;

@JsonRootName("backup")
public class CinderVolumeBackupImport implements VolumeBackupImport {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String backupId;
    @JsonProperty("name")
    private String backupName;

    public CinderVolumeBackupImport() {

    }

    public CinderVolumeBackupImport(String backupId, String backupName) {
        this.backupId = backupId;
        this.backupName = backupName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBackupId() {
        return backupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBackupName() {
        return backupName;
    }
}

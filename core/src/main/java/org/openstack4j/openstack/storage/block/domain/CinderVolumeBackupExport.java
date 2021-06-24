package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.storage.block.VolumeBackupExport;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("backup-record")
public class CinderVolumeBackupExport implements VolumeBackupExport {

    private static final long serialVersionUID = 1L;

    @JsonProperty("backup_service")
    private String backupService;
    @JsonProperty("backup_url")
    private String backupMetadata;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBackupService() {
        return backupService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBackupMetadata() {
        return backupMetadata;
    }

    public CinderVolumeBackupExport() {

    }

    public CinderVolumeBackupExport(String backupService, String backupMetadata) {
        this.backupService = backupService;
        this.backupMetadata = backupMetadata;
    }
}

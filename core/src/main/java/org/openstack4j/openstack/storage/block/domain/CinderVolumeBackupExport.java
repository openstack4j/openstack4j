package org.openstack4j.openstack.storage.block.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.storage.block.VolumeBackupExport;

@JsonRootName("backup-record")
public class CinderVolumeBackupExport implements VolumeBackupExport {

    private static final long serialVersionUID = 1L;

    @JsonProperty("backup_service")
    private String backupService;
    @JsonProperty("backup_url")
    private String backupMetadata;

    public CinderVolumeBackupExport() {

    }

    public CinderVolumeBackupExport(String backupService, String backupMetadata) {
        this.backupService = backupService;
        this.backupMetadata = backupMetadata;
    }

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
    @JsonProperty("backup_url")
    public String getBackupMetadata() {
        return backupMetadata;
    }
}

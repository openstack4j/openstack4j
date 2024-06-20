package org.openstack4j.openstack.storage.block.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.api.storage.BlockVolumeBackupService;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.VolumeBackup;
import org.openstack4j.model.storage.block.VolumeBackupCreate;
import org.openstack4j.model.storage.block.VolumeBackupExport;
import org.openstack4j.model.storage.block.VolumeBackupImport;
import org.openstack4j.model.storage.block.VolumeBackupRestore;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackup;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackup.VolumeBackups;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackupExport;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackupImport;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeBackupRestore;

/**
 * OpenStack (Cinder) Volume Backup Operations API Implementation.
 *
 * @author Huang Yi
 */
public class BlockVolumeBackupServiceImpl extends BaseBlockStorageServices implements BlockVolumeBackupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends VolumeBackup> list() {
        return get(VolumeBackups.class, uri("/backups/detail")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends VolumeBackup> list(Map<String, String> filteringParams) {
        Invocation<VolumeBackups> invocation = buildInvocation(filteringParams);
        return invocation.execute().getList();
    }

    private Invocation<VolumeBackups> buildInvocation(Map<String, String> filteringParams) {
        Invocation<VolumeBackups> invocation = get(VolumeBackups.class, "/backups/detail");
        if (filteringParams == null) {
            return invocation;
        } else {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                invocation = invocation.param(entry.getKey(), entry.getValue());
            }
        }
        return invocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeBackup get(String backupId) {
        Objects.requireNonNull(backupId);
        return get(CinderVolumeBackup.class, uri("/backups/%s", backupId)).execute();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String backupId) {
        Objects.requireNonNull(backupId);
        return deleteWithResponse(uri("/backups/%s", backupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeBackup create(VolumeBackupCreate vbc) {
        Objects.requireNonNull(vbc);
        Objects.requireNonNull(vbc.getVolumeId());
        return post(CinderVolumeBackup.class, uri("/backups")).entity(vbc).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeBackupRestore restore(String backupId, String name, String volumeId) {
        _VolumeBackupRestore entity = new _VolumeBackupRestore(name, volumeId);
        return post(CinderVolumeBackupRestore.class, uri("/backups/%s/restore", backupId)).entity(entity).execute();
    }

    @JsonRootName("restore")
    private static class _VolumeBackupRestore implements ModelEntity {

        private static final long serialVersionUID = 1L;
        @JsonProperty("name")
        private String name;
        @JsonProperty("volume_id")
        private String volumeId;

        public _VolumeBackupRestore(String name, String volumeId) {
            this.name = name;
            this.volumeId = volumeId;
        }
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeBackupExport exportMetadata(String backupId) {
        return get(CinderVolumeBackupExport.class, uri("/backups/%s/export_record", backupId)).execute();
    }

    @JsonRootName("backup-record")
    private static class _VolumeBackupImport implements ModelEntity {

        private static final long serialVersionUID = 1L;
        @JsonProperty("backup_service")
        private String backupService;
        @JsonProperty("backup_url")
        private String backupMetadata;

        public _VolumeBackupImport(String backupService, String backupMetadata) {
            this.backupService = backupService;
            this.backupMetadata = backupMetadata;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeBackupImport importMetadata(String backupService, String backupMetadata) {
        _VolumeBackupImport entity = new _VolumeBackupImport(backupService, backupMetadata);
        return post(CinderVolumeBackupImport.class, uri("/backups/import_record")).entity(entity).execute();
    }

}


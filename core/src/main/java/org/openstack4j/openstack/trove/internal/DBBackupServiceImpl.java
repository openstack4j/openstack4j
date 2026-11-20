package org.openstack4j.openstack.trove.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.trove.BackupService;
import org.openstack4j.model.trove.Backup;
import org.openstack4j.openstack.trove.domain.TroveBackup;
import org.openstack4j.openstack.trove.domain.TroveBackup.DBBackups;

public class DBBackupServiceImpl extends BaseTroveServices implements BackupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Backup> list() {
        return list(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Backup> list(Map<String, String> filteringParams) {
        return get(DBBackups.class, "/backups").params(filteringParams).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Backup get(String backupId) {
        Objects.requireNonNull(backupId);
        return get(TroveBackup.class, uri("/backups/%s", pathSegment(backupId))).execute();
    }
}

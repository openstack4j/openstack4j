package org.openstack4j.openstack.trove.internal;

import java.util.List;
import java.util.Map;

import org.openstack4j.api.trove.BackupService;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.trove.Backup;
import org.openstack4j.openstack.compute.domain.NovaServer;
import org.openstack4j.openstack.trove.domain.TroveBackup;
import org.openstack4j.openstack.trove.domain.TroveBackup.DBBackups;

import static com.google.common.base.Preconditions.checkNotNull;

public class DBBackupServiceImpl extends BaseTroveServices implements BackupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Backup> list() {
        return get(DBBackups.class, uri("/backups")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Backup> list(Map<String, String> filteringParams) {
        Invocation<DBBackups> invocation = get(DBBackups.class, "/backups");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                invocation = invocation.param(entry.getKey(), entry.getValue());
            }
        }
        return invocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Backup get(String backupId) {
        checkNotNull(backupId);
        TroveBackup backup = get(TroveBackup.class, uri("/backups/%s", backupId)).execute();
        return backup;
    }
}

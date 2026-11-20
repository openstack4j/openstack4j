package org.openstack4j.api.trove;

import java.util.List;
import java.util.Map;

import org.openstack4j.model.trove.Backup;

public interface BackupService {

    List<? extends Backup> list();

    List<? extends Backup> list(Map<String, String> filteringParams);

    Backup get(String backupId);
}

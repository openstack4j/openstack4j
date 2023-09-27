package org.openstack4j.model.storage.block;

import org.openstack4j.model.ModelEntity;

public interface VolumeBackupImport extends ModelEntity{
	
    /**
     * @return the UUID of the backup
     */
    String getBackupId();

    /**
     * @return the name of the backup
     */
    String getBackupName();

}

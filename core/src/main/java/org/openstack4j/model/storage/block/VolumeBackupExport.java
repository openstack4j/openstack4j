package org.openstack4j.model.storage.block;

import org.openstack4j.model.ModelEntity;

public interface VolumeBackupExport extends ModelEntity{
	
    /**
     * @return the backup service containing the backup
     */
    String getBackupService();

    /**
     * @return the encoded backup metadata
     */
    String getBackupMetadata();

}

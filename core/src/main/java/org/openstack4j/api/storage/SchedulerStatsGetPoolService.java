package org.openstack4j.api.storage;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.openstack.storage.block.domain.VolumeBackendPool;

/**
 * Scheduler Stats Service for Cinder block storage.
 *
 * @author chenguofeng
 */
public interface SchedulerStatsGetPoolService extends RestService {
    /**
     * Lists all Volumes back-end storage pools.
     *
     * @return a list of all Volumes back-end storage pools
     */
    List<? extends VolumeBackendPool> pools();

    List<? extends VolumeBackendPool> poolsDetail();


}

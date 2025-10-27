package org.openstack4j.openstack.octavia.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.octavia.HealthMonitorV2Service;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.octavia.HealthMonitorV2;
import org.openstack4j.model.octavia.HealthMonitorV2Update;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.octavia.domain.OctaviaHealthMonitorV2;
import org.openstack4j.openstack.octavia.domain.OctaviaHealthMonitorV2.HealthMonitorsV2;

/**
 * OpenStack (Octavia) lbaas v2 health monitor operations
 *
 * @author wei
 */
public class HealthMonitorV2ServiceImpl extends BaseOctaviaServices implements HealthMonitorV2Service {
    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list() {
        return get(HealthMonitorsV2.class, uri("/lbaas/healthmonitors")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends HealthMonitorV2> list(Map<String, String> filteringParams) {
        Invocation<HealthMonitorsV2> req = get(HealthMonitorsV2.class, uri("/lbaas/healthmonitors"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 get(String healthMonitorId) {
        Objects.requireNonNull(healthMonitorId);
        return get(OctaviaHealthMonitorV2.class, uri("/lbaas/healthmonitors/%s", healthMonitorId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String healthMonitorId) {
        Objects.requireNonNull(healthMonitorId);
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/lbaas/healthmonitors/%s", healthMonitorId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 create(HealthMonitorV2 healthMonitor) {
        Objects.requireNonNull(healthMonitor);
        return post(OctaviaHealthMonitorV2.class, uri("/lbaas/healthmonitors")).entity(healthMonitor).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HealthMonitorV2 update(String healthMonitorId,
            HealthMonitorV2Update healthMonitor) {
        Objects.requireNonNull(healthMonitorId);
        Objects.requireNonNull(healthMonitor);
        return put(OctaviaHealthMonitorV2.class, uri("/lbaas/healthmonitors/%s", healthMonitorId)).entity(healthMonitor).execute();
    }
}

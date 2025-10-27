package org.openstack4j.openstack.telemetry.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.telemetry.ResourceService;
import org.openstack4j.model.telemetry.Resource;
import org.openstack4j.openstack.telemetry.domain.CeilometerResource;

/**
 * Provides Measurements for Telemetry resources within an OpenStack deployment
 *
 * @author Shital Patil
 */
public class ResourceServiceImpl extends BaseTelemetryServices implements ResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Resource> list() {
        CeilometerResource[] resources = get(CeilometerResource[].class, uri("/resources")).execute();
        return wrapList(resources);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource get(String resourceId) {
        Objects.requireNonNull(resourceId);
        CeilometerResource resource = get(CeilometerResource.class, uri("/resources/%s", resourceId)).execute();
        return resource;
    }

}

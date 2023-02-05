package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.networking.NeutronResourceTagService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.Resource;
import org.openstack4j.openstack.networking.domain.NeutronResourceTag;

import java.util.Objects;

/**
 * Allows users to set (Neutron) Resource tags on their resources.
 *
 * @author bboyHan
 */
public class NeutronResourceTagServiceImpl extends BaseNetworkingServices implements NeutronResourceTagService {

    @Override
    public ActionResponse check(Resource resource, String resourceId, String tag) {
        Objects.requireNonNull(resourceId);
        Objects.requireNonNull(tag);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return getWithResponse(uri("/%s/%s/tags/%s", resourceType, resourceId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NeutronResourceTag list(Resource resource, String securityGroupId) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return get(NeutronResourceTag.class, uri("/%s/%s/tags", resourceType, securityGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addSingle(Resource resource, String securityGroupId, String tag) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(tag);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return putWithResponse(uri("/%s/%s/tags/%s", resourceType, securityGroupId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NeutronResourceTag replace(Resource resource, String securityGroupId, NeutronResourceTag tags) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(tags);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return put(NeutronResourceTag.class, uri("/%s/%s/tags", resourceType, securityGroupId)).entity(tags).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(Resource resource, String securityGroupId, String tag) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(tag);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return deleteWithResponse(uri("/%s/%s/tags/%s", resourceType, securityGroupId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteAll(Resource resource, String securityGroupId) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(resource);
        String resourceType = Objects.requireNonNull(Resource.forValue(resource));
        return deleteWithResponse(uri("/%s/%s/tags", resourceType, securityGroupId)).execute();
    }

}

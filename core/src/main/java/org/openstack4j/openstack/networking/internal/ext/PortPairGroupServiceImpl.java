package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.ext.PortPairGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortPairGroup;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortPairGroup.PortPairGroups;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * {@inheritDoc}
 */
public class PortPairGroupServiceImpl extends BaseNetworkingServices implements PortPairGroupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PortPairGroup> list() {
        return get(PortPairGroups.class, uri("/sfc/port_pair_groups")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortPairGroup create(PortPairGroup portPairGroup) {
        Objects.requireNonNull(portPairGroup);
        return post(NeutronPortPairGroup.class, uri("/sfc/port_pair_groups")).entity(portPairGroup).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String portPairGroupId) {
        Objects.requireNonNull(portPairGroupId);
        return deleteWithResponse(uri("/sfc/port_pair_groups/%s", portPairGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortPairGroup get(String portPairGroupId) {
        Objects.requireNonNull(portPairGroupId);
        return get(NeutronPortPairGroup.class, uri("/sfc/port_pair_groups/%s", portPairGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortPairGroup update(String portPairGroupId, PortPairGroup portPairGroup) {
        Objects.requireNonNull(portPairGroupId);
        return put(NeutronPortPairGroup.class, uri("/sfc/port_pair_groups/%s", portPairGroupId)).entity(portPairGroup).execute();
    }
}

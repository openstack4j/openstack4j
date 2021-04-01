package org.openstack4j.openstack.networking.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.SubnetService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.networking.domain.NeutronSubnet;
import org.openstack4j.openstack.networking.domain.NeutronSubnet.Subnets;
import org.openstack4j.openstack.networking.domain.NeutronSubnetUpdate;

/**
 * OpenStack (Neutron) Subnet based Operations implementation
 *
 * @author Jeremy Unruh
 */
public class SubnetServiceImpl extends BaseNetworkingServices implements SubnetService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Subnet> list() {
        return get(Subnets.class, uri("/subnets")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subnet get(String subnetId) {
        Objects.requireNonNull(subnetId);
        return get(NeutronSubnet.class, uri("/subnets/%s", subnetId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String subnetId) {
        Objects.requireNonNull(subnetId);
        return deleteWithResponse(uri("/subnets/%s", subnetId)).execute();
    }

    @Override
    public Subnet create(Subnet subnet) {
        Objects.requireNonNull(subnet);
        return post(NeutronSubnet.class, uri("/subnets")).entity(subnet).execute();
    }

    public Subnet update(Subnet subnet) {
        Objects.requireNonNull(subnet);
        return update(subnet.getId(), subnet);
    }

    public Subnet update(String subnetId, Subnet subnet) {
        Objects.requireNonNull(subnetId);
        Objects.requireNonNull(subnet);
        return put(NeutronSubnet.class, uri("/subnets/%s", subnetId))
                .entity(NeutronSubnetUpdate.createFromSubnet(subnet))
                .execute();
    }
}

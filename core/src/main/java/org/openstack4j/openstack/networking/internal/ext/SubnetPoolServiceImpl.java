package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.ext.SubnetPoolService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.SubnetPool;
import org.openstack4j.openstack.networking.domain.ext.NeutronSubnetPool;
import org.openstack4j.openstack.networking.domain.ext.NeutronSubnetPool.SubnetPools;
import org.openstack4j.openstack.networking.domain.ext.NeutronSubnetPoolUpdate;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * OpenStack (Neutron) Subnet Pool based Operations implementation
 *
 * @author bboyHan
 */
public class SubnetPoolServiceImpl extends BaseNetworkingServices implements SubnetPoolService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SubnetPool> list() {
        return get(SubnetPools.class, uri("/subnetpools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubnetPool get(String subnetId) {
        Objects.requireNonNull(subnetId);
        return get(NeutronSubnetPool.class, uri("/subnetpools/%s", pathSegment(subnetId))).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String subnetId) {
        Objects.requireNonNull(subnetId);
        return deleteWithResponse(uri("/subnetpools/%s", pathSegment(subnetId))).execute();
    }

    @Override
    public SubnetPool create(SubnetPool subnet) {
        Objects.requireNonNull(subnet);
        return post(NeutronSubnetPool.class, uri("/subnetpools")).entity(subnet).execute();
    }

    public SubnetPool update(SubnetPool subnetPool) {
        Objects.requireNonNull(subnetPool);
        return update(subnetPool.getId(), subnetPool);
    }

    public SubnetPool update(String subnetId, SubnetPool subnetPool) {
        Objects.requireNonNull(subnetId);
        Objects.requireNonNull(subnetPool);
        return put(NeutronSubnetPool.class, uri("/subnetpools/%s", pathSegment(subnetId)))
                .entity(NeutronSubnetPoolUpdate.createFromSubnetPool(subnetPool))
                .execute();
    }
}

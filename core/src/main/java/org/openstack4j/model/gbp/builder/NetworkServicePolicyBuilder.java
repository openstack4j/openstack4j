package org.openstack4j.model.gbp.builder;

import java.util.List;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.gbp.NetworkServicePolicy;
import org.openstack4j.openstack.gbp.domain.GbpNetworkServiceParams;

/**
 * Created by sumigand on 7/7/2016.
 */
public interface NetworkServicePolicyBuilder extends Buildable.Builder<NetworkServicePolicyBuilder, NetworkServicePolicy> {
    NetworkServicePolicyBuilder name(String name);

    NetworkServicePolicyBuilder description(String description);

    NetworkServicePolicyBuilder isShared(boolean shared);

    NetworkServicePolicyBuilder gbpNetworkServiceParams(List<GbpNetworkServiceParams> gbpNetworkServiceParams);
}

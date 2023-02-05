package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.ext.NetQosPolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.NetQosPolicy;
import org.openstack4j.model.network.ext.NetQosPolicyUpdate;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicy;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicy.NeutronNetQosPolicies;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) Qos Policy Extension API
 *
 * @author bboyHan
 */
public class NetQosPolicyServiceImpl extends BaseNetworkingServices implements NetQosPolicyService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NetQosPolicy> list() {
        return get(NeutronNetQosPolicies.class, uri("/qos/policies")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy get(String policyId) {
        Objects.requireNonNull(policyId, "qos policyId must not be null");
        return get(NeutronNetQosPolicy.class, uri("/qos/policies/%s", policyId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy update(NetQosPolicyUpdate update) {
        Objects.requireNonNull(update, "netQosPolicy must not be null");
        Objects.requireNonNull(update.getId(), "netQosPolicy id must not be null");
        return put(NeutronNetQosPolicy.class, uri("/qos/policies/%s", getAndClearIdentifier(update))).entity(update).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetQosPolicy create(NetQosPolicy netQosPolicy) {
        Objects.requireNonNull(netQosPolicy, "netQosPolicy must not be null");
        return post(NeutronNetQosPolicy.class, uri("/qos/policies")).entity(netQosPolicy).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String policyId) {
        Objects.requireNonNull(policyId);
        return deleteWithResponse(uri("/qos/policies/%s", policyId)).execute();
    }

    private String getAndClearIdentifier(NetQosPolicyUpdate update) {
        String id = update.getId();
        update.setId(null);
        return id;
    }
}

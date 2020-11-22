package org.openstack4j.openstack.networking.internal.ext;

import org.openstack4j.api.networking.ext.NetQosPolicyBLRuleService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.NetQosPolicyBandwidthLimitRule;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicyBandwidthLimitRule;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicyBandwidthLimitRule.NeutronNetQosPolicyBLRules;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Networking (Neutron) Qos Policy Bandwidth Limit Rule Extension API
 *
 * @author bboyHan
 */
public class NetQosPolicyBLRuleServiceImpl extends BaseNetworkingServices implements NetQosPolicyBLRuleService {

    @Override
    public List<? extends NetQosPolicyBandwidthLimitRule> list(String policyId) {
        checkNotNull(policyId, "qos policyId must not be null");
        return get(NeutronNetQosPolicyBLRules.class, uri("/qos/policies/%s/bandwidth_limit_rules", policyId)).execute().getList();
    }

    @Override
    public NetQosPolicyBandwidthLimitRule get(String policyId, String ruleId) {
        checkNotNull(policyId, "qos policyId must not be null");
        checkNotNull(ruleId, "qos ruleId must not be null");
        return get(NeutronNetQosPolicyBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules/%s", policyId, ruleId)).execute();
    }

    @Override
    public NetQosPolicyBandwidthLimitRule update(String policyId, NetQosPolicyBandwidthLimitRule bandwidthLimitRule) {
        checkNotNull(policyId, "qos policyId must not be null");
        checkNotNull(bandwidthLimitRule);
        checkNotNull(bandwidthLimitRule.getId(), "netQosPolicy rule id must not be null");
        return put(NeutronNetQosPolicyBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules/%s",
                policyId, getAndClearIdentifier(bandwidthLimitRule)))
                .entity(bandwidthLimitRule).execute();
    }

    @Override
    public NetQosPolicyBandwidthLimitRule create(String policyId, NetQosPolicyBandwidthLimitRule bandwidthLimitRule) {
        checkNotNull(policyId, "qos policyId must not be null");
        checkNotNull(bandwidthLimitRule, "netQosPolicy ruleId must not be null");
        return post(NeutronNetQosPolicyBandwidthLimitRule.class, uri("/qos/policies/%s/bandwidth_limit_rules", policyId)).entity(bandwidthLimitRule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String policyId, String ruleId) {
        checkNotNull(policyId, "qos policyId must not be null");
        checkNotNull(ruleId, "netQosPolicy ruleId must not be null");
        return deleteWithResponse(uri("/qos/policies/%s/bandwidth_limit_rules/%s", policyId, ruleId)).execute();
    }

    private String getAndClearIdentifier(NetQosPolicyBandwidthLimitRule update) {
        String id = update.getId();
        update.setId(null);
        return id;
    }
}

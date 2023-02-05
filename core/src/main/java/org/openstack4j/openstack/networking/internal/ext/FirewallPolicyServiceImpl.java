package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.networking.ext.FirewallPolicyService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.FirewallPolicy;
import org.openstack4j.model.network.ext.FirewallPolicyUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.networking.domain.ext.AbstractNeutronFirewallPolicy.FirewallPolicies;
import org.openstack4j.openstack.networking.domain.ext.FirewallRuleStrategy;
import org.openstack4j.openstack.networking.domain.ext.FirewallRuleStrategy.RuleInsertStrategyType;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicy;
import org.openstack4j.openstack.networking.domain.ext.NeutronFirewallPolicyRule;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * Networking (Neutron) FwaaS FirewallPolicy Extension API
 *
 * @author Vishvesh Deshmukh
 */
public class FirewallPolicyServiceImpl extends BaseNetworkingServices implements FirewallPolicyService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends FirewallPolicy> list() {
        return get(FirewallPolicies.class, uri("/fw/firewall_policies")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends FirewallPolicy> list(Map<String, String> filteringParams) {
        Invocation<FirewallPolicies> req = get(FirewallPolicies.class, uri("/fw/firewall_policies"));
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
    public FirewallPolicy get(String firewallPolicyId) {
        Objects.requireNonNull(firewallPolicyId);
        return get(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String firewallPolicyId) {
        Objects.requireNonNull(firewallPolicyId);
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class,
                uri("/fw/firewall_policies/%s", firewallPolicyId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FirewallPolicy create(FirewallPolicy firewallPolicy) {
        return post(NeutronFirewallPolicy.class, uri("/fw/firewall_policies")).entity(firewallPolicy)
                .execute(ExecutionOptions.<NeutronFirewallPolicy>create(PropagateOnStatus.on(404)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FirewallPolicy update(String firewallPolicyId, FirewallPolicyUpdate firewallPolicyUpdate) {
        Objects.requireNonNull(firewallPolicyId);
        Objects.requireNonNull(firewallPolicyUpdate);
        return put(NeutronFirewallPolicy.class, uri("/fw/firewall_policies/%s", firewallPolicyId)).entity(firewallPolicyUpdate).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FirewallPolicy insertFirewallRuleInPolicy(
            String firewallPolicyId, String firewallRuleId, RuleInsertStrategyType type, String insertAfterOrBeforeRuleId) {
        Objects.requireNonNull(firewallPolicyId);
        Objects.requireNonNull(firewallRuleId);
        return put(NeutronFirewallPolicyRule.class, uri("/fw/firewall_policies/%s/insert_rule", firewallPolicyId))
                .entity(FirewallRuleStrategy.create(firewallRuleId, type, insertAfterOrBeforeRuleId))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FirewallPolicy removeFirewallRuleFromPolicy(String firewallPolicyId, String firewallRuleId) {
        Objects.requireNonNull(firewallPolicyId);
        Objects.requireNonNull(firewallRuleId);
        return put(NeutronFirewallPolicyRule.class, uri("/fw/firewall_policies/%s/remove_rule", firewallPolicyId))
                .entity(FirewallRuleStrategy.remove(firewallRuleId))
                .execute(ExecutionOptions.<NeutronFirewallPolicyRule>create(PropagateOnStatus.on(404)));
    }

}

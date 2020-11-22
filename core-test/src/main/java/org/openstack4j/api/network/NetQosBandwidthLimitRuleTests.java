package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.NetQosPolicyBandwidthLimitRule;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicyBandwidthLimitRule;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for (Neutron) qos policy bandwidth limit rule extension based Services
 *
 * @author bboyHan
 */
@Test(suiteName = "Network/qosPolicyBandwidthLimitRule")
public class NetQosBandwidthLimitRuleTests extends AbstractTest {

    private static final String QOS_POLICY_BANDWIDTH_LIMIT_RULE_JSON = "/network/qos_bandwidth_limit_rule.json";
    private static final String QOS_POLICY_BANDWIDTH_LIMIT_RULES_JSON = "/network/qos_bandwidth_limit_rules.json";

    public void list() throws IOException {
        respondWith(QOS_POLICY_BANDWIDTH_LIMIT_RULES_JSON);
        List<? extends NetQosPolicyBandwidthLimitRule> qosPolicyBandwidthLimitRules = osv3().networking().netQosPolicyBandWidthLimitRule().list("policyId");
        assertEquals(1, qosPolicyBandwidthLimitRules.size());
        assertEquals(10000, (int)qosPolicyBandwidthLimitRules.get(0).getMaxKbps());
    }

    public void get() throws IOException {
        respondWith(QOS_POLICY_BANDWIDTH_LIMIT_RULE_JSON);
        NetQosPolicyBandwidthLimitRule netQosPolicyBandwidthLimitRule = osv3().networking().netQosPolicyBandWidthLimitRule().get("networkId", "ruleId");
        assertEquals("5f126d84-551a-4dcf-bb01-0e9c0df0c793", netQosPolicyBandwidthLimitRule.getId());
        assertEquals("egress", netQosPolicyBandwidthLimitRule.getDirection());
    }

    public void delete() {
        respondWith(204);
        ActionResponse response = osv3().networking().netQosPolicyBandWidthLimitRule().delete("policyId", "ruleId");
        assertTrue(response.isSuccess());
    }

    public void create() throws IOException {
        respondWith(QOS_POLICY_BANDWIDTH_LIMIT_RULE_JSON);
        NetQosPolicyBandwidthLimitRule netQosPolicyBandwidthLimitRule = osv3().networking().netQosPolicyBandWidthLimitRule()
                .create("policyId", NeutronNetQosPolicyBandwidthLimitRule.builder().maxKbps(10000).build());
        assertEquals("5f126d84-551a-4dcf-bb01-0e9c0df0c793", netQosPolicyBandwidthLimitRule.getId());
        assertEquals("egress", netQosPolicyBandwidthLimitRule.getDirection());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}

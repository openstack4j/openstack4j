package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.NetQosPolicyBandwidthLimitRule;

/**
 * A Builder which creates a NetQosPolicyBandwidthLimitRule entity
 *
 * @author bboyHan
 * @author slankka
 */
public interface NetQosPolicyBandwidthLimitRuleBuilder extends Builder<NetQosPolicyBandwidthLimitRuleBuilder, NetQosPolicyBandwidthLimitRule> {

    /**
     * See {@link NetQosPolicyBandwidthLimitRule#getMaxKbps()} for details
     *
     * @param maxKbps maxKbps
     * @return NetQosPolicyBandwidthLimitRuleBuilder
     */
    NetQosPolicyBandwidthLimitRuleBuilder maxKbps(Integer maxKbps);

    /**
     * See {@link NetQosPolicyBandwidthLimitRule#getMaxBurstKbps()} for details
     *
     * @param maxBurstKbps maxBurstKbps
     * @return NetQosPolicyBandwidthLimitRuleBuilder
     */
    NetQosPolicyBandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps);

    /**
     * See {@link NetQosPolicyBandwidthLimitRule#getDirection()} for details
     *
     * @param direction direction
     * @return NetQosPolicyBandwidthLimitRuleBuilder
     */
    NetQosPolicyBandwidthLimitRuleBuilder direction(NetQosPolicyBandwidthLimitRule.Direction direction);

}

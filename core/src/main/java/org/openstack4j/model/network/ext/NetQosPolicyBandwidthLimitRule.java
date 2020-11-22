package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.ext.builder.NetQosPolicyBandwidthLimitRuleBuilder;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicyRuleTag;

/**
 * Network qos policy band-width-limit that are bound to a Tenant
 *
 * @author bboyHan
 */
public interface NetQosPolicyBandwidthLimitRule extends IdEntity, Buildable<NetQosPolicyBandwidthLimitRuleBuilder> {

    /**
     * The maximum KBPS (kilobits per second) value.
     * If you specify this value, must be greater than 0 otherwise max_kbps will have no value.
     *
     * @return maxKbps
     */
    Integer getMaxKbps();

    /**
     * The maximum burst size (in kilobits).
     *
     * @return maxBurstKbps
     */
    Integer getMaxBurstKbps();

    /**
     * The direction of the traffic to which the QoS rule is applied, as seen from the point of view of the port.
     * Valid values are egress and ingress. Default value is egress.
     *
     * @return direction
     */
    String getDirection();

    /**
     * The list of tags on the resource.
     *
     * @return tags
     */
    NeutronNetQosPolicyRuleTag getTags();

}

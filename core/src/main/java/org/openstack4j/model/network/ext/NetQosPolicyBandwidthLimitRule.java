package org.openstack4j.model.network.ext;

import javax.annotation.Nullable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.ext.builder.NetQosPolicyBandwidthLimitRuleBuilder;
import org.openstack4j.openstack.networking.domain.ext.NeutronNetQosPolicyRuleTag;

/**
 * Network qos policy band-width-limit that are bound to a Tenant
 *
 * @author bboyHan
 * @author slankka
 */
public interface NetQosPolicyBandwidthLimitRule extends IdEntity, Buildable<NetQosPolicyBandwidthLimitRuleBuilder> {

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String getId();

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
    Direction getDirection();

    String getDirectionValue();

    /**
     * The list of tags on the resource.
     *
     * @return tags
     */
    NeutronNetQosPolicyRuleTag getTags();


    enum Direction {
        ingress,
        egress;

        /**
         * @param direction nullable direction
         * @return Direction
         * @see <a href="https://www.baeldung.com/jackson-serialize-enums">jackson-serialize-enums</a>
         */
        @JsonCreator
        public static Direction forValues(@Nullable String direction) {
            if (Objects.isNull(direction) || Objects.equals(direction, "")) {
                return null;
            }
            return Direction.valueOf(direction);
        }
    }

}

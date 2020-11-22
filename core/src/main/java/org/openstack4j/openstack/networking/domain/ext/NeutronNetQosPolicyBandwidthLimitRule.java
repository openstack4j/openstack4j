package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.NetQosPolicyBandwidthLimitRule;
import org.openstack4j.model.network.ext.builder.NetQosPolicyBandwidthLimitRuleBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Network qos policy band-width-limit that are bound to a Tenant
 *
 * @author bboyHan
 */
@JsonRootName("bandwidth_limit_rule")
public class NeutronNetQosPolicyBandwidthLimitRule implements NetQosPolicyBandwidthLimitRule {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;
    @JsonProperty("max_kbps")
    private Integer maxKbps;
    @JsonProperty("max_burst_kbps")
    private Integer maxBurstKbps;
    @JsonProperty
    private String direction;
    @JsonProperty
    private NeutronNetQosPolicyRuleTag tags;

    public static NetQosPolicyBandwidthLimitRuleBuilder builder() {
        return new NetQosPolicyBandwidthLimitRuleConcreteBuilder();
    }

    @Override
    public NetQosPolicyBandwidthLimitRuleBuilder toBuilder() {
        return new NetQosPolicyBandwidthLimitRuleConcreteBuilder(this);
    }

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getMaxKbps() {
        return maxKbps;
    }

    public Integer getMaxBurstKbps() {
        return maxBurstKbps;
    }

    public String getDirection() {
        return direction;
    }

    public NeutronNetQosPolicyRuleTag getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("id", id).add("maxKbps", maxKbps).add("maxBurstKbps", maxBurstKbps)
                .add("direction", direction).add("tags", tags)
                .toString();
    }

    public static class NetQosPolicyBandwidthLimitRuleConcreteBuilder implements NetQosPolicyBandwidthLimitRuleBuilder {

        private NeutronNetQosPolicyBandwidthLimitRule model;

        public NetQosPolicyBandwidthLimitRuleConcreteBuilder() {
            model = new NeutronNetQosPolicyBandwidthLimitRule();
        }

        public NetQosPolicyBandwidthLimitRuleConcreteBuilder(NeutronNetQosPolicyBandwidthLimitRule model) {
            this.model = model;
        }

        @Override
        public NetQosPolicyBandwidthLimitRule build() {
            return model;
        }

        @Override
        public NetQosPolicyBandwidthLimitRuleBuilder from(NetQosPolicyBandwidthLimitRule in) {
            model = (NeutronNetQosPolicyBandwidthLimitRule) in;
            return this;
        }

        @Override
        public NetQosPolicyBandwidthLimitRuleBuilder maxKbps(Integer maxKbps) {
            model.maxKbps = maxKbps;
            return this;
        }

        @Override
        public NetQosPolicyBandwidthLimitRuleBuilder maxBurstKbps(Integer maxBurstKbps) {
            model.maxBurstKbps = maxBurstKbps;
            return this;
        }

        @Override
        public NetQosPolicyBandwidthLimitRuleBuilder direction(String direction) {
            model.direction = direction;
            return this;
        }

    }

    public static class NeutronNetQosPolicyBLRules extends ListResult<NeutronNetQosPolicyBandwidthLimitRule> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("bandwidth_limit_rules")
        private List<NeutronNetQosPolicyBandwidthLimitRule> bandwidthLimitRules;

        @Override
        protected List<NeutronNetQosPolicyBandwidthLimitRule> value() {
            return bandwidthLimitRules;
        }
    }

}

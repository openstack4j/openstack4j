package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.network.ext.NetQosPolicyUpdate;
import org.openstack4j.model.network.ext.builder.NetQosPolicyUpdateBuilder;
import org.openstack4j.util.ToStringHelper;

/**
 * An entity used to update a qos policy ({@code PUT /v2.0/qos/policies/{id}}). Only the fields that were set through
 * the builder are sent; the id goes into the URL, not the body.
 */
@JsonRootName("policy")
public class NeutronNetQosPolicyUpdate implements NetQosPolicyUpdate {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("shared")
    private Boolean shared;
    @JsonProperty("is_default")
    private Boolean isDefault;

    public static NetQosPolicyUpdateBuilder builder() {
        return new NetQosPolicyUpdateConcreteBuilder();
    }

    @Override
    public NetQosPolicyUpdateBuilder toBuilder() {
        return new NetQosPolicyUpdateConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    @Override
    public String getName() {
        return name;
    }

    @JsonIgnore
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @Override
    public String getDescription() {
        return description;
    }

    @JsonIgnore
    @Override
    public boolean isShared() {
        return shared != null && shared;
    }

    @JsonIgnore
    @Override
    public boolean isDefault() {
        return isDefault != null && isDefault;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id).add("name", name).add("description", description).add("shared", shared).add("isDefault", isDefault)
                .toString();
    }

    public static class NetQosPolicyUpdateConcreteBuilder implements NetQosPolicyUpdateBuilder {

        private NeutronNetQosPolicyUpdate model;

        public NetQosPolicyUpdateConcreteBuilder() {
            this.model = new NeutronNetQosPolicyUpdate();
        }

        public NetQosPolicyUpdateConcreteBuilder(NeutronNetQosPolicyUpdate model) {
            this.model = model;
        }

        @Override
        public NetQosPolicyUpdate build() {
            return model;
        }

        @Override
        public NetQosPolicyUpdateBuilder from(NetQosPolicyUpdate in) {
            model = (NeutronNetQosPolicyUpdate) in;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder description(String description) {
            model.description = description;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder shared(boolean shared) {
            model.shared = shared;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder isDefault(boolean isDefault) {
            model.isDefault = isDefault;
            return this;
        }

        @Override
        public NetQosPolicyUpdateBuilder name(String name) {
            model.name = name;
            return this;
        }
    }
}

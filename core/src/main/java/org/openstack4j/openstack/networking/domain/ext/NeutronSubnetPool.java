package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.ext.SubnetPool;
import org.openstack4j.model.network.ext.builder.SubnetPoolBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

/**
 * An OpenStack (Neutron) subnet pool
 *
 * @author bboyHan
 */
@JsonRootName("subnetpool")
public class NeutronSubnetPool implements SubnetPool {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    @JsonProperty("default_quota")
    private Integer defaultQuota;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("default_prefixlen")
    private Integer defaultPreFixLen;
    @JsonProperty("min_prefixlen")
    private Integer minPrefixLen;
    @JsonProperty("max_prefixlen")
    private Integer maxPrefixLen;
    private List<String> prefixes;
    @JsonProperty("address_scope_id")
    private String addressScopeId;
    @JsonProperty("is_default")
    private Boolean isDefault;
    @JsonProperty("ip_version")
    private IPVersionType ipVersion;
    private String description;
    private Boolean shared;
    @JsonProperty("created_at")
    private Date createdTime;
    @JsonProperty("updated_at")
    private Date updatedTime;
    @JsonProperty("revision_number")
    private Integer revisionNumber;
    private List<String> tags;

    public NeutronSubnetPool() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getDefaultQuota() {
        return defaultQuota;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getDefaultPreFixLen() {
        return defaultPreFixLen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTenantId() {
        return tenantId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMinPrefixLen() {
        return minPrefixLen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMaxPrefixLen() {
        return maxPrefixLen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPrefixes() {
        return prefixes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAddressScopeId() {
        return addressScopeId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDefault() {
        return isDefault != null && isDefault;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IPVersionType getIpVersion() {
        return ipVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShared() {
        return shared != null && shared;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getTags() {
        return tags;
    }

    public static SubnetPoolBuilder builder() {
        return new SubnetPoolConcreteBuilder();
    }

    @Override
    public SubnetPoolConcreteBuilder toBuilder() {
        return new SubnetPoolConcreteBuilder(this);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id).add("ipVersion", ipVersion)
                .add("name", name).add("tenantId", tenantId)
                .add("defaultQuota", defaultQuota).add("defaultPreFixLen", defaultPreFixLen)
                .add("ipVersion", ipVersion).add("minPrefixLen", minPrefixLen)
                .add("maxPrefixLen", maxPrefixLen).add("prefixes", prefixes)
                .add("addressScopeId", addressScopeId).add("isDefault", isDefault)
                .add("description", description).add("shared", shared)
                .add("createdTime", createdTime).add("updatedTime", updatedTime)
                .add("revisionNumber", revisionNumber).add("tags", tags)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, ipVersion, name, tenantId, defaultQuota, defaultPreFixLen,
                minPrefixLen, maxPrefixLen, prefixes, addressScopeId,
                isDefault, description, shared, ipVersion, tags, createdTime, updatedTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof NeutronSubnetPool) {
            NeutronSubnetPool that = (NeutronSubnetPool) obj;
            return java.util.Objects.equals(name, that.name) &&
                    java.util.Objects.equals(tenantId, that.tenantId) &&
                    java.util.Objects.equals(defaultQuota, that.defaultQuota) &&
                    java.util.Objects.equals(defaultPreFixLen, that.defaultPreFixLen) &&
                    java.util.Objects.equals(minPrefixLen, that.minPrefixLen) &&
                    java.util.Objects.equals(maxPrefixLen, that.maxPrefixLen) &&
                    java.util.Objects.equals(prefixes, that.prefixes) &&
                    java.util.Objects.equals(addressScopeId, that.addressScopeId) &&
                    java.util.Objects.equals(isDefault, that.isDefault) &&
                    java.util.Objects.equals(description, that.description) &&
                    java.util.Objects.equals(shared, that.shared) &&
                    java.util.Objects.equals(ipVersion, that.ipVersion) &&
                    java.util.Objects.equals(tags, that.tags) &&
                    java.util.Objects.equals(createdTime, that.createdTime) &&
                    java.util.Objects.equals(updatedTime, that.updatedTime);
        }
        return false;
    }

    public static class SubnetPools extends ListResult<NeutronSubnetPool> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("subnetpools")
        private List<NeutronSubnetPool> subnetpools;

        public List<NeutronSubnetPool> value() {
            return subnetpools;
        }
    }

    public static class SubnetPoolConcreteBuilder
            extends ResourceBuilder<SubnetPool, SubnetPoolConcreteBuilder> implements SubnetPoolBuilder {

        NeutronSubnetPool model;

        public SubnetPoolConcreteBuilder() {
            model = new NeutronSubnetPool();
        }

        public SubnetPoolConcreteBuilder(NeutronSubnetPool model) {
            this.model = model;
        }

        @Override
        public NeutronSubnetPool build() {
            return model;
        }

        @Override
        protected SubnetPool reference() {
            return model;
        }

        @Override
        public SubnetPoolConcreteBuilder from(SubnetPool in) {
            return new SubnetPoolConcreteBuilder((NeutronSubnetPool) in);
        }

        @Override
        public SubnetPoolConcreteBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder defaultQuota(Integer defaultQuota) {
            model.defaultQuota = defaultQuota;
            return this;
        }

        @Override
        public SubnetPoolBuilder defaultPreFixLen(Integer defaultPreFixLen) {
            model.defaultPreFixLen = defaultPreFixLen;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder minPrefixLen(Integer minPrefixLen) {
            model.minPrefixLen = minPrefixLen;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder maxPrefixLen(Integer maxPrefixLen) {
            model.maxPrefixLen = maxPrefixLen;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder prefixes(List<String> prefixes) {
            model.prefixes = prefixes;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder addressScopeId(String addressScopeId) {
            model.addressScopeId = addressScopeId;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder isDefault(Boolean isDefault) {
            model.isDefault = isDefault;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder shared(Boolean shared) {
            model.shared = shared;
            return this;
        }

        @Override
        public SubnetPoolConcreteBuilder description(String description) {
            model.description = description;
            return this;
        }
    }
}

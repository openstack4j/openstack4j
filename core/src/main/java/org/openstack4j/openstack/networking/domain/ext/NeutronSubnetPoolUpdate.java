package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.ext.SubnetPool;

import java.util.List;

/**
 * Encapsulates the updatable view for a Subnet Pool within Neutron
 * 
 * @author bboyHan
 */
@JsonRootName("subnetpool")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronSubnetPoolUpdate implements ModelEntity {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty
    private String name;
    @JsonProperty("default_quota")
    private Integer defaultQuota;
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
    private String description;

    public static NeutronSubnetPoolUpdate createFromSubnetPool(SubnetPool in) {
        NeutronSubnetPoolUpdate ns = new NeutronSubnetPoolUpdate();
        ns.name = in.getName();
        ns.defaultQuota = in.getDefaultQuota();
        ns.defaultPreFixLen = in.getDefaultPreFixLen();
        ns.minPrefixLen = in.getMinPrefixLen();
        ns.maxPrefixLen = in.getMaxPrefixLen();
        ns.addressScopeId = in.getAddressScopeId();
        ns.isDefault = in.isDefault();
        ns.description = in.getDescription();
        return ns;
    }

}

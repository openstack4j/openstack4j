package org.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.common.QuotaDetails;
import org.openstack4j.model.compute.ComputeQuotaDetail;
import org.openstack4j.openstack.common.QuotaDetailsEntity;

/**
 * The detail of quota for a project or a project and a user.
 *
 * @author Mingshan
 */
@JsonRootName("quota_set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NovaComputeQuotaDetail implements ComputeQuotaDetail {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;
    @JsonProperty
    private QuotaDetailsEntity cores;
    @JsonProperty("fixed_ips")
    private QuotaDetailsEntity fixedIps;
    @JsonProperty("floating_ips")
    private QuotaDetailsEntity floatingIps;
    @JsonProperty("injected_file_content_bytes")
    private QuotaDetailsEntity injectedFileContentBytes;
    @JsonProperty("injected_file_path_bytes")
    private QuotaDetailsEntity injectedFilePathBytes;
    @JsonProperty("injected_files")
    private QuotaDetailsEntity injectedFiles;
    @JsonProperty
    private QuotaDetailsEntity instances;
    @JsonProperty("key_pairs")
    private QuotaDetailsEntity keyPairs;
    @JsonProperty("metadata_items")
    private QuotaDetailsEntity metadataItems;
    private QuotaDetailsEntity ram;
    @JsonProperty("security_group_rules")
    private QuotaDetailsEntity securityGroupRules;
    @JsonProperty("security_groups")
    private QuotaDetailsEntity securityGroups;
    @JsonProperty("server_groups")
    private QuotaDetailsEntity serverGroups;
    @JsonProperty("server_group_members")
    private QuotaDetailsEntity serverGroupMembers;

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
    public QuotaDetails getCores() {
        return cores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getInstances() {
        return instances;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getRam() {
        return ram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getKeyPairs() {
        return keyPairs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getMetadataItems() {
        return metadataItems;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getServerGroupMembers() {
        return serverGroupMembers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetails getServerGroups() {
        return serverGroups;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getFixedIps() {
        return fixedIps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getFloatingIps() {
        return floatingIps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getInjectedFileContentBytes() {
        return injectedFileContentBytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getInjectedFilePathBytes() {
        return injectedFilePathBytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getInjectedFiles() {
        return injectedFiles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getSecurityGroupRules() {
        return securityGroupRules;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaDetailsEntity getSecurityGroups() {
        return securityGroups;
    }

    @Override
    public String toString() {
        return "NovaComputeQuotaUsage{" +
                "id='" + id + '\'' +
                ", cores=" + cores +
                ", fixedIps=" + fixedIps +
                ", floatingIps=" + floatingIps +
                ", injectedFileContentBytes=" + injectedFileContentBytes +
                ", injectedFilePathBytes=" + injectedFilePathBytes +
                ", injectedFiles=" + injectedFiles +
                ", instances=" + instances +
                ", keyPairs=" + keyPairs +
                ", metadataItems=" + metadataItems +
                ", ram=" + ram +
                ", securityGroupRules=" + securityGroupRules +
                ", securityGroups=" + securityGroups +
                ", serverGroups=" + serverGroups +
                ", serverGroupMembers=" + serverGroupMembers +
                '}';
    }
}

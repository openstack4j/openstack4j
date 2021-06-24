package org.openstack4j.model.network.ext.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ext.SubnetPool;

import java.util.List;

/**
 * A Builder which creates a Subnet Pool
 *
 * @author bboyHan
 */
public interface SubnetPoolBuilder extends Builder<SubnetPoolBuilder, SubnetPool> {

    /**
     * @see SubnetPool#getName()
     */
    SubnetPoolBuilder name(String name);

    /**
     * @see SubnetPool#getDefaultQuota()
     */
    SubnetPoolBuilder defaultQuota(Integer defaultQuota);

    /**
     * @see SubnetPool#getDefaultPreFixLen()
     */
    SubnetPoolBuilder defaultPreFixLen(Integer defaultQuota);

    /**
     * @see SubnetPool#getMinPrefixLen()
     */
    SubnetPoolBuilder minPrefixLen(Integer minPrefixLen);

    /**
     * @see SubnetPool#getMaxPrefixLen()
     */
    SubnetPoolBuilder maxPrefixLen(Integer maxPrefixLen);

    /**
     * @see SubnetPool#getTenantId()
     */
    SubnetPoolBuilder tenantId(String tenantId);

    /**
     * @see SubnetPool#getPrefixes()
     */
    SubnetPoolBuilder prefixes(List<String> prefixes);

    /**
     * @see SubnetPool#getAddressScopeId()
     */
    SubnetPoolBuilder addressScopeId(String addressScopeId);

    /**
     * @see SubnetPool#isDefault()
     */
    SubnetPoolBuilder isDefault(Boolean isDefault);

    /**
     * @see SubnetPool#isShared()
     */
    SubnetPoolBuilder shared(Boolean shared);

    /**
     * @see SubnetPool#getDescription()
     */
    SubnetPoolBuilder description(String description);

}

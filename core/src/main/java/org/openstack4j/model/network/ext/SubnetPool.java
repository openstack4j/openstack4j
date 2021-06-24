package org.openstack4j.model.network.ext;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.common.TimeEntity;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.ext.builder.SubnetPoolBuilder;

import java.util.List;

/**
 * An OpenStack (Neutron) subnet pool
 * 
 * @author bboyHan
 */
public interface SubnetPool extends Resource, TimeEntity, Buildable<SubnetPoolBuilder> {

	/**
	 * @return A per-project quota on the prefix space
	 * that can be allocated from the subnet pool for project subnets.
	 * Default is no quota is enforced on allocations from the subnet pool.
	 * For IPv4 subnet pools, default_quota is measured in units of /32.
	 * For IPv6 subnet pools, default_quota is measured units of /64.
	 * All projects that use the subnet pool have the same prefix quota applied.
	 */
	Integer getDefaultQuota();

	/**
	 * @return The size of the prefix to allocate when the cidr or prefixlen attributes are omitted
	 * when you create the subnet. Default is min_prefixlen.
	 *
	 */
	Integer getDefaultPreFixLen();

	/**
	 * @return A list of subnet prefixes to assign to the subnet pool.
	 * The API merges adjacent prefixes and treats them as a single prefix.
	 * Each subnet prefix must be unique among all subnet prefixes in all subnet pools
	 * that are associated with the address scope.
	 */
	List<String> getPrefixes();

	/**
	 * @return The smallest prefix that can be allocated from a subnet pool.
	 * For IPv4 subnet pools, default is 8. For IPv6 subnet pools, default is 64.
	 */
	Integer getMinPrefixLen();

	/**
	 * @return the sub-ranges of cidr available for dynamic allocation to ports
	 */
	Integer getMaxPrefixLen();

	/**
	 * @return An address scope to assign to the subnet pool.
	 */
	String getAddressScopeId();

	/**
	 * @return The subnet pool is default pool or not.
	 */
	boolean isDefault();

	/**
	 * @return the ip version used by this subnet pool
	 */
	IPVersionType getIpVersion();
	
	/**
	 * @return Indicates whether this resource is shared across all projects.
	 * By default, only administrative users can change this value.
	 */
	boolean isShared();
	
	/**
	 * @return A human-readable description for the resource.
	 */
	String getDescription();

	/**
	 * @return Revision number of a resource.
	 */
	Integer getRevisionNumber();

	/**
	 * @return List of tags associated with the subnet pool.
	 */
	List<String> getTags();

}

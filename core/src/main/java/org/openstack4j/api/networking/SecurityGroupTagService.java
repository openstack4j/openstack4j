package org.openstack4j.api.networking;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroupTag;

/**
 * @author bboyHan
 */
public interface SecurityGroupTagService extends RestService {

	/**
	 * Confirms a given tag is set on the resource.
	 *
	 * @param securityGroupId security group id
	 * @param tag tag
	 * @return the ActionResponse
	 */
	ActionResponse check(String securityGroupId, String tag);

	/**
	 * Obtains the security group tags for a resource.
	 *
	 * @param securityGroupId security group id
	 * @return security group tags
	 */
	NeutronSecurityGroupTag list(String securityGroupId);

	/**
	 * Add tag to security group.
	 *
	 * @param securityGroupId security group id
	 * @param tag tag
	 * @return the ActionResponse
	 */
	ActionResponse addSingle(String securityGroupId, String tag);

	/**
	 * reset all tags
	 *
	 * @param securityGroupId security group id
	 * @param tags tags
	 * @return the ActionResponse
	 */
	NeutronSecurityGroupTag replace(String securityGroupId, NeutronSecurityGroupTag tags);

	/**
	 * Delete tag from security group.
	 *
	 * @param securityGroupId security group id
	 * @param tag tag
	 * @return the ActionResponse
	 */
	ActionResponse delete(String securityGroupId, String tag);

	/**
	 * Remove all tags from security group
	 *
	 * @param securityGroupId security group id
	 * @return the ActionResponse
	 */
	ActionResponse deleteAll(String securityGroupId);

}

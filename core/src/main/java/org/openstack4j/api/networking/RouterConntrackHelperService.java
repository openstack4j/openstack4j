package org.openstack4j.api.networking;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.RouterConntrackHelper;

import java.util.List;

/**
 * Provides Neutron Router Conntrack Helper based Service Operations
 * 
 * @author bboyHan
 */
public interface RouterConntrackHelperService extends RestService {

	/**
	 * Lists router conntrack helpers associated with a router.
	 *
	 * @return List of Router Conntrack Helpers or empty list
	 */
	List<? extends RouterConntrackHelper> list(String routerId);
	
	/**
	 * Gets a Router Conntrack Helper by Conntrack Helper ID
	 *
	 * @param routerId router Id
	 * @param conntrackHelperId conntrack helper id
	 * @return RouterConntrackHelper
	 */
	RouterConntrackHelper get(String routerId, String conntrackHelperId);
	
	/**
	 * Deletes the specified Router Conntrack Helper by ID
	 *
	 * @param routerId router Id
	 * @param conntrackHelperId the router conntrack helper identifier to delete
	 * @return the action response
	 */
	ActionResponse delete(String routerId, String conntrackHelperId);

	/**
	 * Creates a Router Conntrack Helper
	 *
	 * @param routerConntrackHelper the routerConntrackHelper to create
	 * @return the newly created routerConntrackHelper
	 */
	RouterConntrackHelper create(String routerId, RouterConntrackHelper routerConntrackHelper);
	
	/**
	 * Updates a Router Conntrack Helper.
	 *
	 * @param routerId router Id
	 * @param routerConntrackHelper the routerConntrackHelper to update
	 * @return the updated routerConntrackHelper
	 */
	RouterConntrackHelper update(String routerId, RouterConntrackHelper routerConntrackHelper);
	
}

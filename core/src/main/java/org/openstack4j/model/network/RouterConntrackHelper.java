package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.IdEntity;
import org.openstack4j.model.network.builder.RouterConntrackHelperBuilder;

/**
 * Routers Conntrack Helper (CT) target rules
 * 
 * @author bboyHan
 */
public interface RouterConntrackHelper extends IdEntity, Buildable<RouterConntrackHelperBuilder> {

	/**
	 * Sets the identifier for this resource.
	 *
	 * @param routerId router ID
	 */
	void setRouterId(String routerId);

	/**
	 * The ID of the router.
	 *
	 * @return The ID of the router.
	 */
	String getRouterId();
	
	/**
	 * The netfilter conntrack helper module.
	 *
	 * @return helper module.
	 */
	String getHelper();
	
	/**
	 * The network protocol for the netfilter conntrack target rule.
	 *
	 * @return The network protocol
	 */
	String getProtocol();

	/**
	 * The network port for the netfilter conntrack target rule.
	 *
	 * @return The network port
	 */
	Integer getPort();

}

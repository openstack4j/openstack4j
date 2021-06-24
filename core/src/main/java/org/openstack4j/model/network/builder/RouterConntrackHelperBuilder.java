package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.RouterConntrackHelper;

/**
 * A Builder that creates a Router Conntrack Helper
 * 
 * @author bboyHan
 */
public interface RouterConntrackHelperBuilder extends Builder<RouterConntrackHelperBuilder, RouterConntrackHelper> {

	/**
	 * @see RouterConntrackHelper#getId()
	 */
	RouterConntrackHelperBuilder id(String id);

	/**
	 * @see RouterConntrackHelper#getRouterId()
	 */
	RouterConntrackHelperBuilder routerId(String routerId);
	
	/**
	 * @see RouterConntrackHelper#getHelper()
	 */
	RouterConntrackHelperBuilder helper(String helper);
	
	/**
	 * @see RouterConntrackHelper#getProtocol()
	 */
	RouterConntrackHelperBuilder protocol(String protocol);
	
	/**
	 * @see RouterConntrackHelper#getPort()
	 */
	RouterConntrackHelperBuilder port(Integer port);

}

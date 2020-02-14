package org.openstack4j.api.networking;

import java.util.List;
import java.util.Map;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.NetFloatingIP;

/**
 * Provides Neutron-based FloatingIP services.
 *
 * @author Nathan Anderson
 */
public interface NetFloatingIPService extends RestService {

  /**
   * Returns list of floating IPs.
   *
   * @return List of NetFloatingIPs or empty
   */
  List<? extends NetFloatingIP> list();
  
  /**
   * Returns list of floating IPs filtered by parameters.
   * 
   * @param filteringParams map (name, value) of filtering parameters
   */
  List<? extends NetFloatingIP> list(Map<String, String> filteringParams);
  
  /**
   * Gets a NetFloatingIP by id.
   *
   * @param id the floating-ip identifier
   * @return the NetFloatingIP
   */
  NetFloatingIP get(String id);

  /**
   * Deletes NetFloatingIP by id.
   *
   * @param id the id
   * @return the action response
   */
  ActionResponse delete(String id);

  /**
   * Creates a new Floating IP
   *
   * @param floatingIp the floating ip
   * @return the net floating ip
   */
  NetFloatingIP create(NetFloatingIP floatingIp);

  /**
   * Associates a Floating IP to a Port.
   *
   * @return the net floating ip
   */
  NetFloatingIP associateToPort(String id, String portId);
  
  
  /**
   * Disassociate from port.
   *
   * @return the net floating ip
   */
  NetFloatingIP disassociateFromPort(String id);
}

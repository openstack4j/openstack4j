package org.openstack4j.api.networking;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.Trunk;
import org.openstack4j.model.network.TrunkSubport;
import org.openstack4j.openstack.networking.domain.NeutronTrunkSubport;

import java.util.List;

/**
 * OpenStack Network Trunk operations
 *
 * @author Kashyap Jha
 */
public interface TrunkService extends RestService {

    /**
     * Adds a subport to the specified Trunk
     *
     * @param trunkId
     *            ID of the trunk
     * @param subPort
     *            subport object to add
     * @return the updated trunk object
     */
    Trunk addTrunkSubport(String trunkId, TrunkSubport subPort);

    /**
     * Creates a trunk
     *
     * @param trunk
     *            the trunk to create
     * @return the created trunk object
     */
    Trunk createTrunk(Trunk trunk);

    /**
     * Delete a trunk
     *
     * @param trunkId
     *            ID of the trunk
     * @return action response
     */
    ActionResponse deleteTrunk(String trunkId);

    /**
     * Get a trunk by ID
     *
     * @param trunkId
     *            the trunk ID
     * @return the trunk object
     */
    Trunk get(String trunkId);

    /**
     * Lists all trunks
     *
     * @return a list of trunks
     */
    List<? extends Trunk> list();

    /**
     * List the subports associated with the trunk
     *
     * @param trunkId
     *            trunk ID
     * @return a list of subports
     */
    List<NeutronTrunkSubport> listTrunkSubports(String trunkId);

    /**
     * Removes subport from the specified trunk
     *
     * @param trunkId
     *            trunk ID
     * @param portId
     *            the ID of the subport to remove
     * @return trunk object with the subport removed
     */
    Trunk removeTrunkSubport(String trunkId, String portId);

    /**
     * Updates a trunk object
     *
     * @param trunk
     *            the trunk object to update
     * @return
     *            the updated trunk object
     */
    Trunk updateTrunk(Trunk trunk);
}
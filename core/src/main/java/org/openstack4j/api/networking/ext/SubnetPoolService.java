package org.openstack4j.api.networking.ext;

import org.openstack4j.common.RestService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.SubnetPool;

import java.util.List;

/**
 * OpenStack (Neutron) Subnet Pool based Operations
 *
 * @author bboyHan
 */
public interface SubnetPoolService extends RestService {

    /**
     * List all the Subnet Pool which are authorized by the current Tenant
     *
     * @return the list of subnet pool or empty
     */
    List<? extends SubnetPool> list();

    /**
     * Gets a Subnet Pool by ID
     *
     * @param subnetPoolId the subnet pool identifier
     * @return the Subnet Pool or null if not found
     */
    SubnetPool get(String subnetPoolId);

    /**
     * Delete a Subnet Pool by ID
     *
     * @param subnetPoolId the subnet pool identifier to delete
     * @return the action response
     */
    ActionResponse delete(String subnetPoolId);

    /**
     * Creates a new Subnet Pool
     *
     * @param subnet the subnet pool to create
     * @return the newly created subnet pool
     */
    SubnetPool create(SubnetPool subnet);

    /**
     * Updates a Subnet Pool.
     * <p>
     * Example:<br>
     * SubnetPool update = update(existingSubnet.toBuilder().someChange(change).build());
     *
     * @param subnetPool the subnet pool to update
     * @return the new subnet pool info
     */
    SubnetPool update(SubnetPool subnetPool);

    /**
     * See example at {@linkplain #update(SubnetPool)}
     *
     * @param subnetPoolId the subnet pool identifier to update
     * @param subnetPool   the subnet to update
     * @return the updated subnet pool
     */
    SubnetPool update(String subnetPoolId, SubnetPool subnetPool);

}

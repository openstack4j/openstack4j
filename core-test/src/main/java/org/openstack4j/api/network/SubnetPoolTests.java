package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.SubnetPool;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/**
 * Test cases for (Neutron) Subnet Pool based Services
 * 
 * @author bboyHan
 */
@Test(suiteName = "Network/subnetPool")
public class SubnetPoolTests extends AbstractTest {

	private static final String JSON_GET_SUBNET_POOL = "/network/subnet_pool.json";
	private static final String JSON_GET_SUBNET_POOLS = "/network/subnet_pools.json";

	private static final String SUBNET_POOL_NAME = "my-subnet-pool";
	private static final String SUBNET_POOL_ID = "03f761e6-eee0-43fc-a921-8acf64c14988";

	@Test
	public void get() throws Exception {
		respondWith(JSON_GET_SUBNET_POOL);
		SubnetPool subnetPool = osv3().networking().subnetPool().get(SUBNET_POOL_ID);
		server.takeRequest();
		assertEquals(subnetPool.getName(), SUBNET_POOL_NAME);
		assertNotNull(subnetPool.getCreatedTime());
		assertNotNull(subnetPool.getUpdatedTime());
	}

	@Test
	public void list() throws Exception {
		respondWith(JSON_GET_SUBNET_POOLS);
		List<? extends SubnetPool> list = osv3().networking().subnetPool().list();
		server.takeRequest();
		assertEquals(list.size(), 2);
		assertEquals(list.get(0).getName(), "my-subnet-pool-ipv6");
		assertEquals(list.get(1).getName(), "my-subnet-pool-ipv4");
	}

	@Test
	public void create() throws Exception {
		respondWith(JSON_GET_SUBNET_POOL);
		SubnetPool subnetPool = osv3().networking().subnetPool().create(getSubnetPool());
		server.takeRequest();
		assertEquals(subnetPool.getName(), SUBNET_POOL_NAME);
	}

	@Test
	public void update() throws Exception {
		respondWith(JSON_GET_SUBNET_POOL);
		SubnetPool update = getSubnetPool();
		update.setId(SUBNET_POOL_ID);
		SubnetPool subnetPool = osv3().networking().subnetPool().update(update);
		server.takeRequest();
		assertEquals(subnetPool.getName(), SUBNET_POOL_NAME);
	}

	@Test
	public void delete() throws Exception {
		respondWith(204);
		ActionResponse actionResponse = osv3().networking().subnetPool().delete(SUBNET_POOL_ID);
		server.takeRequest();
		assertTrue(actionResponse.isSuccess());
	}

	private SubnetPool getSubnetPool() {
		return Builders.subnetPool().name(SUBNET_POOL_NAME).build();
	}

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
    
}

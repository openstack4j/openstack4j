package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.RouterConntrackHelper;
import org.openstack4j.openstack.networking.domain.NeutronRouterConntrackHelper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for (Neutron) Router Conntrack Helper based Services
 *
 * @author bboyHan
 */
@Test(suiteName = "Network/routerConntrackHelper")
public class RouterConntrackHelperTests extends AbstractTest {

    private static final String ROUTER_CONNTRACK_HELPER_JSON = "/network/router_conntrack_helper.json";
    private static final String ROUTER_CONNTRACK_HELPERS_JSON = "/network/router_conntrack_helpers.json";

    public void list() throws IOException {
        respondWith(ROUTER_CONNTRACK_HELPERS_JSON);
        List<? extends RouterConntrackHelper> routerConntrackHelpers = osv3().networking().routerConntrackHelper().list("routerId");
        assertEquals(2, routerConntrackHelpers.size());
        assertEquals("ftp", routerConntrackHelpers.get(1).getHelper());
    }

    public void get() throws IOException{
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper().get("routerId", "id");
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("udp", routerConntrackHelper.getProtocol());
    }

    public void delete() {
        respondWith(204);
        ActionResponse response = osv3().networking().routerConntrackHelper().delete("routerId", "id");
        assertTrue(response.isSuccess());
    }

    public void create() throws IOException {
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper()
                .create("routerId", NeutronRouterConntrackHelper.builder().id("id").helper("tftp").routerId("routerId").build());
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("tftp", routerConntrackHelper.getHelper());
    }

    public void update() throws IOException {
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper()
                .update("routerId", NeutronRouterConntrackHelper.builder().id("id").helper("tftp").routerId("routerId").build());
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("tftp", routerConntrackHelper.getHelper());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}

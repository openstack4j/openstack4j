package org.openstack4j.api.network;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.RouterConntrackHelper;
import org.testng.annotations.Test;

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

    public void list() throws Exception {
        respondWith(ROUTER_CONNTRACK_HELPERS_JSON);
        List<? extends RouterConntrackHelper> routerConntrackHelpers = osv3().networking().routerConntrackHelper().list("routerId");
        takeRequest();
        assertEquals(2, routerConntrackHelpers.size());
        assertEquals("ftp", routerConntrackHelpers.get(1).getHelper());
    }

    public void get() throws Exception {
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper().get("routerId", "id");
        takeRequest();
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("udp", routerConntrackHelper.getProtocol());
    }

    public void delete() throws InterruptedException {
        respondWith(204);
        ActionResponse response = osv3().networking().routerConntrackHelper().delete("routerId", "id");
        takeRequest();
        assertTrue(response.isSuccess());
    }

    public void create() throws Exception {
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper()
                .create("routerId", Builders.routerConntrackHelper().helper("tftp").port(69).protocol("udp").build());
        takeRequest();
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("tftp", routerConntrackHelper.getHelper());
    }

    public void update() throws Exception {
        respondWith(ROUTER_CONNTRACK_HELPER_JSON);
        RouterConntrackHelper changes = Builders.routerConntrackHelper().id("id").routerId("routerId")
                .helper("tftp").port(69).protocol("udp").build();
        RouterConntrackHelper routerConntrackHelper = osv3().networking().routerConntrackHelper().update("routerId", changes);
        JsonNode body = new ObjectMapper().readTree(takeRequest().getBody().readUtf8()).get("conntrack_helper");
        assertEquals(changes.getId(), "id");
        assertEquals(changes.getRouterId(), "routerId");
        assertEquals(body.size(), 3);
        assertEquals(body.get("port").asInt(), 69);
        assertEquals("2fc1eebb-e0fa-4c40-868a-7ace444717e1", routerConntrackHelper.getId());
        assertEquals("tftp", routerConntrackHelper.getHelper());
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}

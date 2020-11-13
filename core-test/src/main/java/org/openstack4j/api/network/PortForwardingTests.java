package org.openstack4j.api.network;

import java.io.IOException;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortForwarding;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author zjluo
 */
@Test(suiteName = "Network/FloatingIp/portForwarding")
public class PortForwardingTests extends AbstractTest {
    private static final String JSON_RESPONSE_CREATE = "/network/portforwarding/create.json";
    private static final String JSON_RESPONSE_GET = "/network/portforwarding/get.json";
    private static final String JSON_RESPONSE_LIST = "/network/portforwarding/list.json";
    private static final String FLOATINGIP_ID = "2f245a7b-796b-4f26-9cf9-9e82d248fda7";

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    public void testCreate() throws IOException, InterruptedException {
        respondWithCodeAndResource(201, JSON_RESPONSE_CREATE);
        String protocol = "tcp";
        String internalPortId = "1238be08-a2a8-4b8d-addf-fb5e2250e480";
        String internalIp = "10.0.0.11";
        int internalPort = 25;
        int externalPort = 2230;
        String description = "Some description";
        PortForwarding portForwarding = osv3().networking().floatingip().portForwarding().create(FLOATINGIP_ID,
                Builders.portForwarding()
                        .protocol(protocol)
                        .internalPortId(internalPortId)
                        .internalIpAddress(internalIp)
                        .internalPort(internalPort)
                        .externalPort(externalPort)
                        .description(description)
                        .build());
        assertEquals(internalPortId, portForwarding.getInternalPortId());
        assertEquals(protocol, portForwarding.getProtocol());
        assertEquals(internalIp, portForwarding.getInternalIpAddress());
        assertEquals(internalPort, portForwarding.getInternalPort());
        assertEquals(externalPort, portForwarding.getExternalPort());
        assertEquals(description, portForwarding.getDescription());
    }

    public void testGet() throws IOException {
        respondWithCodeAndResource(200, JSON_RESPONSE_GET);
        String id = "725ade3c-9760-4880-8080-8fc2dbab9acc";
        PortForwarding portForwarding = osv3().networking().floatingip().portForwarding().get(FLOATINGIP_ID, id);
        assertNotNull(portForwarding);
        assertEquals(portForwarding.getId(), id);
    }

    public void testList() throws IOException {
        respondWithCodeAndResource(200, JSON_RESPONSE_LIST);
        List<? extends PortForwarding> portForwardings = osv3().networking().floatingip().portForwarding().list(FLOATINGIP_ID);
        assertNotNull(portForwardings);
        assertEquals(portForwardings.size(), 2);
    }

    public void testDelete() {
        respondWith(204);
        String id = "725ade3c-9760-4880-8080-8fc2dbab9acc";
        ActionResponse response = osv3().networking().floatingip().portForwarding().delete(FLOATINGIP_ID, id);
        assertTrue(response.isSuccess());
    }
}

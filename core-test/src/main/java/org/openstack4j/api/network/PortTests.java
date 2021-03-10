package org.openstack4j.api.network;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.AllowedAddressPair;
import org.openstack4j.model.network.Port;
import org.testng.annotations.Test;

/**
 * @author Rizwan Qamar
 */
@Test(suiteName = "Port")
public class PortTests extends AbstractTest {

    private static final String JSON_PORTS_EXTERNAL = "/network/ports_external.json";
    private static final String JSON_PORT_EXTERNAL = "/network/port_external.json";
    private static final String NETWORK_ID = "a87cc70a-3e15-4acf-8205-9b711a3531b7";
    private static final Date DATE = new Date(1604096161000L);

    @Test
    public void createPorts() throws Exception {
        respondWith(JSON_PORTS_EXTERNAL);
        List<? extends Port> createdPorts = osv3().networking().port().create(getPorts());
        for (Port port : createdPorts) {
            validatePort(port);
        }
    }

    @Test
    public void createPort() throws Exception {
        respondWith(JSON_PORT_EXTERNAL);
        Port port = osv3().networking().port().create(getPort());
        validatePort(port);
    }

    private void validatePort(Port port) {
        assertEquals(port.getNetworkId(), NETWORK_ID);
        assertEquals(port.getCreatedTime(), DATE);
        assertEquals(port.getUpdatedTime(), DATE);
        
        AllowedAddressPair allowedAddressPair = port.getAllowedAddressPairs().iterator().next();
        assertNotNull(allowedAddressPair.getIpAddress());
        assertNotNull(allowedAddressPair.getMacAddress());        
    }

    private Port getPort() {
        return Builders.port().networkId(NETWORK_ID).build();
    }

    private List<? extends Port> getPorts() {
        List<Port> ports = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ports.add(getPort());
        }
        return ports;
    }

    @Override
    protected Service service() {
        return Service.NETWORK;
    }
}

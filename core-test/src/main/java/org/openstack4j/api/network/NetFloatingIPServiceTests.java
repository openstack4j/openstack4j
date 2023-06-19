/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.openstack4j.api.network;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.model.network.NetFloatingIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test(suiteName = "Network Floating IPs Tests")
public class NetFloatingIPServiceTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    @Test
    public void create() throws Exception {
        respondWithCodeAndResource(201, "/network/network_fips_create.json");

        NetFloatingIP fip = Builders.netFloatingIP().subnetId("9cf3a56a-0f93-4ca7-be18-6979a65f86cb").floatingNetworkId("1188f271-f3ab-491a-a998-7d7fc9e65e01").build();
        NetFloatingIP created = osv3().networking().floatingip().create(fip);
        assertEquals(created.getFloatingIpAddress(), "10.19.15.12");
        assertEquals(created.getProjectId(), "2f0b5d3387f034dfb042c00c007afb4c");
        assertEquals(created.getFloatingNetworkId(), "1188f271-f3ab-491a-a998-7d7fc9e65e01");
    }

    public void testCreateFailure() throws Exception {
        respondWithCodeAndResource(404, "/network/network_fips_create_fail.json");

        NetFloatingIP fip = Builders.netFloatingIP().floatingNetworkId("42").build();
        try {
            osv3().networking().floatingip().create(fip);
            fail("Expected to fail");
        } catch (ClientResponseException expected) {
            assertTrue(expected.toString().contains("is not reachable from subnet"), expected.toString());
        }
    }

    @Test
    public void listIps() throws Exception {
        respondWith("/network/network_fips_list.json");

        List<? extends NetFloatingIP> list = osv3().networking().floatingip().list();
        assertEquals(list.size(), 2);
        NetFloatingIP used = list.get(0);
        assertEquals(used.getStatus(), "ACTIVE");
        assertEquals(used.getRouterId(), "4910645f-7efe-4c37-918e-3ace85162985");
        assertEquals("", used.getDescription());
        assertEquals(used.getTags(), Collections.emptyList());
        assertEquals(used.getProjectId(), "e68d5f9cb37844ecbd793632944f4459");
        assertEquals(used.getTenantId(), "e68d5f9cb37844ecbd793632944f4459");
        assertEquals(used.getCreatedAt(), new Date(1584733565000L));
        assertEquals(used.getUpdatedAt(), new Date(1584733569000L));
        assertEquals(used.getFloatingNetworkId(), "38cc9e61-11fc-4921-843d-80ef249a3710");
        assertEquals(used.getFixedIpAddress(), "10.0.51.16");
        assertEquals(used.getFloatingIpAddress(), "10.0.50.29");
        assertEquals(used.getPortId(), "7095d432-a007-4d6b-9549-683cd1566188");
        assertEquals(used.getId(), "530e2826-ae43-4cde-bd7c-856a33a3061d");
        assertNull(used.getQosPolicyId());
        assertEquals(1, used.getRevisionNumber().intValue());

        NetFloatingIP idle = list.get(1);
        assertEquals(idle.getStatus(), "DOWN");
        assertNull(idle.getRouterId());
        assertEquals("idle", idle.getDescription());
        assertEquals(idle.getTags(), Collections.emptyList());
        assertEquals(idle.getProjectId(), "d7e88181cd044db188592070ec8bf8b4");
        assertEquals(idle.getTenantId(), "d7e88181cd044db188592070ec8bf8b4");
        assertEquals(idle.getCreatedAt(), new Date(1584706487000L));
        assertEquals(idle.getUpdatedAt(), new Date(1584706487000L));
        assertEquals(idle.getFloatingNetworkId(), "38cc9e61-11fc-4921-843d-80ef249a3710");
        assertNull(idle.getFixedIpAddress());
        assertEquals(idle.getFloatingIpAddress(), "10.0.50.8");
        assertNull(idle.getPortId());
        assertEquals(idle.getId(), "65297c50-2c6e-4c5f-96eb-8b4c228cd54c");
        assertNull(idle.getQosPolicyId());
        assertEquals(0, idle.getRevisionNumber().intValue());
    }
}

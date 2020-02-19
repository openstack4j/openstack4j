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

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.api.exceptions.ClientResponseException;
import org.openstack4j.model.network.NetFloatingIP;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(suiteName = "Network Floating IPs Tests")
public class NetFloatingIPServiceTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.NETWORK;
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
}

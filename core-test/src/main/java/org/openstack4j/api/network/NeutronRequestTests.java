package org.openstack4j.api.network;

import java.util.Collections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.ext.NetQosPolicyBandwidthLimitRule;
import org.openstack4j.model.network.ext.NetQosPolicyUpdate;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "backports")
public class NeutronRequestTests extends AbstractTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected Service service() {
        return Service.NETWORK;
    }

    public void portUpdatePreservesSourceAndSkipsResponseFields() throws Exception {
        String response = "{\"port\":{\"id\":\"p1\",\"network_id\":\"n1\",\"name\":\"port\","
                + "\"created_at\":\"2024-01-01T00:00:00Z\",\"binding:profile\":{\"host\":\"old\"},"
                + "\"extra_dhcp_opts\":[{\"opt_name\":\"bootfile-name\",\"opt_value\":\"image\",\"ip_version\":6}]}}";
        respondWith(200, response);
        Port port = osv3().networking().port().get("p1");
        takeRequest();
        for (int i = 0; i < 2; i++) {
            respondWith(200, response);
            osv3().networking().port().update(port);
            RecordedRequest request = takeRequest();
            assertTrue(request.getPath().endsWith("/ports/p1"));
            JsonNode body = MAPPER.readTree(request.getBody().readUtf8()).path("port");
            assertFalse(body.has("id") || body.has("network_id") || body.has("created_at") || body.has("binding:profile"));
            assertDhcpOption(body);
            assertEquals(port.getId(), "p1");
            assertEquals(port.getNetworkId(), "n1");
            assertEquals(port.getProfile().get("host"), "old");
        }

        port.toBuilder().profile(Collections.singletonMap("host", "new")).build();
        respondWith(200, response);
        osv3().networking().port().update(port);
        assertEquals(readBody().path("port").path("binding:profile").path("host").asText(), "new");
    }

    public void createsPortsWithDhcpOptions() throws Exception {
        Port port = Builders.port().networkId("n1").extraDhcpOpt(
                Builders.extraDhcpOpt().optName("bootfile-name").optValue("image").ipVersion(6).build()).build();
        respondWith(201, "{\"port\":{\"id\":\"p1\"}}");
        osv3().networking().port().create(port);
        assertDhcpOption(readBody().path("port"));

        respondWith(201, "{\"ports\":[{\"id\":\"p1\"}]}");
        osv3().networking().port().create(Collections.singletonList(port));
        assertDhcpOption(readBody().path("ports").get(0));
    }

    public void qosUpdatesKeepIdsOutOfTheBody() throws Exception {
        NetQosPolicyUpdate policy = Builders.netQosPolicyUpdate().id("policy-id").shared(false).build();
        respondWith(200, "{\"policy\":{\"id\":\"policy-id\"}}");
        osv3().networking().netQosPolicy().update(policy);
        JsonNode body = readBody().path("policy");
        assertEquals(body.size(), 1);
        assertEquals(body.path("shared").booleanValue(), false);
        assertEquals(policy.getId(), "policy-id");

        String rule = "{\"bandwidth_limit_rule\":{\"id\":\"rule-id\",\"max_kbps\":100}}";
        respondWith(200, rule);
        NetQosPolicyBandwidthLimitRule bandwidth = osv3().networking().netQosPolicyBandwidthLimitRule().get("policy-id", "rule-id");
        takeRequest();
        respondWith(200, rule);
        osv3().networking().netQosPolicyBandwidthLimitRule().update("policy-id", bandwidth);
        JsonNode sentRule = readBody().path("bandwidth_limit_rule");
        assertFalse(sentRule.has("id"));
        assertEquals(sentRule.path("max_kbps").intValue(), 100);
        assertEquals(bandwidth.getId(), "rule-id");
    }

    private JsonNode readBody() throws Exception {
        return MAPPER.readTree(takeRequest().getBody().readUtf8());
    }

    private static void assertDhcpOption(JsonNode body) {
        JsonNode option = body.path("extra_dhcp_opts").get(0);
        assertNotNull(option);
        assertEquals(option.size(), 3);
        assertEquals(option.path("opt_name").asText(), "bootfile-name");
        assertEquals(option.path("opt_value").asText(), "image");
        assertEquals(option.path("ip_version").intValue(), 6);
    }
}

package org.openstack4j.api.identity.v3;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(groups = "backports")
public class KeystoneApplicationCredentialTests extends AbstractTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected Service service() {
        return Service.IDENTITY;
    }

    public void authenticateAndRenewById() throws Exception {
        respondWithToken("initial-token");
        OSClientV3 client = OSFactory.builderV3().endpoint(authURL("/v3"))
                .applicationCredential("credential-id", "test-secret").authenticate();
        JsonNode initial = takeAuthRequest();
        assertEquals(initial.path("identity").path("application_credential").path("id").asText(), "credential-id");
        assertEquals(initial.path("identity").path("application_credential").path("secret").asText(), "test-secret");
        assertFalse(initial.has("scope"));

        respondWith(401, "{\"error\":{\"message\":\"Token expired\"}}");
        respondWithToken("renewed-token");
        respondWith("/identity/v3/users.json");
        assertEquals(client.identity().users().list().size(), 6);
        assertEquals(takeRequest().getHeader("X-Auth-Token"), "initial-token");
        assertEquals(takeAuthRequest(), initial);
        assertEquals(takeRequest().getHeader("X-Auth-Token"), "renewed-token");
    }

    public void authenticateByNameAndOwnerDomain() throws Exception {
        respondWithToken("named-token");
        OSFactory.builderV3().endpoint(authURL("/v3"))
                .applicationCredential("automation", "test-secret", Identifier.byName("owner"), Identifier.byId("default"))
                .authenticate();
        JsonNode credential = takeAuthRequest().path("identity").path("application_credential");
        assertEquals(credential.path("name").asText(), "automation");
        assertEquals(credential.path("user").path("name").asText(), "owner");
        assertEquals(credential.path("user").path("domain").path("id").asText(), "default");
        assertFalse(credential.has("id"));
    }

    private JsonNode takeAuthRequest() throws Exception {
        RecordedRequest request = takeRequest();
        assertEquals(request.getPath(), "/v3/auth/tokens");
        JsonNode auth = MAPPER.readTree(request.getBody().readUtf8()).path("auth");
        assertEquals(auth.path("identity").path("methods").get(0).asText(), "application_credential");
        return auth;
    }

    private void respondWithToken(String token) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Subject-Token", token);
        respondWithHeaderAndResource(headers, 201, JSON_TOKEN);
    }
}

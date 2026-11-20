package org.openstack4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.openstack4j.openstack.OSFactory;

public final class Java8Smoke {

    private Java8Smoke() {
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/v3/auth/tokens", Java8Smoke::authenticate);
        server.start();
        try {
            String token = OSFactory.builderV3().endpoint("http://127.0.0.1:" + server.getAddress().getPort() + "/v3")
                    .applicationCredential("smoke-id", "smoke-secret").authenticate().getToken().getId();
            if (!"smoke-token".equals(token)) {
                throw new AssertionError("Keystone token was not parsed");
            }
        } finally {
            server.stop(0);
        }
    }

    private static void authenticate(HttpExchange exchange) throws IOException {
        try {
            JsonNode identity = new ObjectMapper().readTree(exchange.getRequestBody()).path("auth").path("identity");
            if (!"application_credential".equals(identity.path("methods").path(0).asText())
                    || !"smoke-id".equals(identity.path("application_credential").path("id").asText())) {
                exchange.sendResponseHeaders(400, -1);
                return;
            }
            byte[] body = "{\"token\":{\"catalog\":[]}}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("X-Subject-Token", "smoke-token");
            exchange.sendResponseHeaders(201, body.length);
            exchange.getResponseBody().write(body);
        } finally {
            exchange.close();
        }
    }
}

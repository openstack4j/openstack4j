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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openstack4j.api.OSClient;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;

/**
 * Main class for manual integration testing.
 */
public class Main {

    //private static final String OPENRC = "/home/ogondza/.openrc/files/openrc.sh";
    //private static final String OPENRC = "/home/ogondza/.openrc/files/rhos-c.user.openrc.sh";
    //private static final String OPENRC = "/home/ogondza/.openrc/files/rhos-c.admin.openrc.sh";
    private static final String OPENRC = "/home/ogondza/.openrc/files/phx2-new.sh";
    private static final boolean WIRE_LOG = true;

    // ========

    private static Map<String, String> env = loadOpenrc();
    private static OSClient os = getClient();

    public static void main(String[] args) throws Throwable {
        os.compute().servers().list();
//        for (NetFloatingIP fip : os.networking().floatingip().list()) {
//            System.out.println(fip);
//        }

//        os.networking().floatingip().create(Builders.netFloatingIP().description("Foo").floatingNetworkId("38cc9e61-11fc-4921-843d-80ef249a3710").description(
//                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//        ).build());

//        ServerCreate request = Builders.server().name("test")
//                //.networks(Arrays.asList("25ec4907-36fc-4035-b8d5-b797246330f2"/*, "5058fef2-f89f-4e70-9e01-66af2847ddc4"*/))
//                //.flavor("ab34d89f-a105-4c93-a7d9-185e327fa6d0")
//                //.image("5821ed26-8f40-48f2-b2ae-9289499899bb")
//                .networks(Arrays.asList("49eb58bf-0af5-49be-bd6c-0c7d853da6fe", "49eb58bf-0af5-49be-bd6c-0c7d853da6fe"))
//                .image("ef577284-9c26-47d2-926a-bf404321f9e1")
//                .flavor("dfc712c7-6f1c-4846-acc8-2a45b1153dbc")
//                .build();
//        Server server = os.compute().servers().bootAndWaitActive(request, 600);
//        try {
//            while (server.getStatus() != Server.Status.ACTIVE) {
//                Thread.sleep(5000);
//                try {
//                    server = os.compute().servers().get(server.getId());
//                } catch (ServerResponseException ex) {
//                    if (ex.getStatus() == 503) continue;
//                }
//                System.out.println(server.getStatus());
//            }
//
//            List<? extends Port> ports = os.networking().port().list(PortListOptions.create().deviceId(server.getId()).networkId("49eb58bf-0af5-49be-bd6c-0c7d853da6fe"));
//System.out.println(ports);
//            Port port = ports.get(0);
//            NetFloatingIP fip = Builders.netFloatingIP().portId(port.getId()).floatingNetworkId("38cc9e61-11fc-4921-843d-80ef249a3710").build();
//            System.out.println("creating");
//            fip = os.networking().floatingip().create(fip);
//            System.out.println("created " + fip);
////            os.networking().floatingip().delete(fip.getId());
//        } finally {
//            //os.compute().servers().delete(server.getId());
//        }
    }

    private static Map<String, String> loadOpenrc() {
        Map<String, String> env = new HashMap<>();
        try {
            // Interesting difference between `set` and `env` is that `set` quotes values with special chars in it
            Process bash = new ProcessBuilder("bash", "-c", "source " + OPENRC + " > /dev/null; env | grep ^OS_")
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .start();
            Scanner scanner = new Scanner(bash.getInputStream()).useDelimiter("\\n");
            while (scanner.hasNext()) {
                String pair = scanner.next();
                String[] split = pair.split("=");
                if (split.length != 2) {
                    throw new Error("Unable to parse: " + pair);
                }
                env.put(split[0], split[1]);
            }

            bash.waitFor(1, TimeUnit.SECONDS);
            if (bash.exitValue() != 0) {
                System.err.println("Failed reading openrc.sh from " + OPENRC);
                System.exit(1);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println(env);
        return env;
    }

    private static OSClient getClient() {
        OSFactory.enableHttpLoggingFilter(WIRE_LOG);

        String pn = env.get("OS_PROJECT_NAME");
        String tn = env.get("OS_TENANT_NAME");

        if ((pn == null) == (tn == null)) {
            System.err.println("Either OS_PROJECT_NAME or OS_TENANT_NAME should be provided");
            System.exit(1);
        }

        Config config = Config.newConfig().withSSLVerificationDisabled();
        //config = config.withReadTimeout(50).withConnectionTimeout(50);

        // Keystone v3
        if (pn != null) {
            String pdid = env.get("OS_PROJECT_DOMAIN_ID");
            Identifier id = pdid == null ? Identifier.byName("Default"): Identifier.byId(pdid);

            return OSFactory.builderV3()
                    .withConfig(config)
                    .endpoint(env.get("OS_AUTH_URL"))
                    .credentials(env.get("OS_USERNAME"), env.get("OS_PASSWORD"), Identifier.byName(env.get("OS_USER_DOMAIN_NAME")))
                    .scopeToProject(Identifier.byName(env.get("OS_PROJECT_NAME")), id)
                    .authenticate()
                    .useRegion(null);
        }

        // Keystone v2
        if (tn != null) {
            return OSFactory.builderV2()
                    .withConfig(config)
                    .endpoint(env.get("OS_AUTH_URL"))
                    .credentials(env.get("OS_USERNAME"), env.get("OS_PASSWORD"))
                    .tenantName(env.get("OS_TENANT_NAME"))
                    .authenticate()
                    .useRegion(null);
        }

        System.err.println("Unable to locate auth evn vars. Forgot to `source openrc.sh`?");
        System.exit(1);
        return null; // :-/
    }
}

package org.openstack4j.api

import groovy.util.logging.Slf4j
import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Specification

@Slf4j
class ClientBuilderSpec extends Specification {
    @Rule
    TestName ClientBuilderTest

//    def "test configure with ssl disabled"() {
//        when: "ssl verification disabled"
//        Config config = Config.newConfig().withSSLVerificationDisabled()
//
//        then: "V3 client can be created"
//        println OSFactory.builderV3()
//                .withConfig(config)
//                .endpoint("OS_AUTH_URL")
//                .credentials("OS_USERNAME", "OS_PASSWORD", Identifier.byName("OS_USER_DOMAIN_NAME"))
//                .scopeToProject(Identifier.byName("OS_PROJECT_NAME"))
//                .authenticate()
//
//        then: "V2 client can be created"
//        println OSFactory.builderV2()
//                .withConfig(config)
//                .endpoint("OS_AUTH_URL")
//                .credentials("OS_USERNAME", "OS_PASSWORD")
//                .tenantName("OS_TENANT_NAME")
//                .authenticate()
//
//        assert true == false
//    }
}

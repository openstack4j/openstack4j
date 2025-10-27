package org.openstack4j.api.compute;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.compute.HostAggregate;
import org.openstack4j.openstack.compute.domain.HostAggregateMetadata;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * Host Aggregate Tests
 *
 * @author chenyan
 * @author bboyHan
 */
@Test(suiteName = "HostAggregate")
public class HostAggregateTests extends AbstractTest {

    private static final String JSON_HOST_AGGREGATES = "/compute/aggregates.json";
    private static final String JSON_HOST_AGGREGATE_CREATE = "/compute/aggregate_create.json";

    @Test
    public void serviceListingTest() throws Exception {
        respondWith(JSON_HOST_AGGREGATES);

        List<? extends HostAggregate> hostAggregateList = osv3().compute().hostAggregates().list();
        assertEquals(hostAggregateList.size(), 2);

        HostAggregate aggregate = hostAggregateList.get(0);
        assertEquals(aggregate.getAvailabilityZone(), "uec_zone_1");
        assertEquals(aggregate.getId(), "8");
        assertEquals(aggregate.getName(), "aggregate_zl_test");
    }

    @Test
    public void createTest() throws Exception {
        respondWith(JSON_HOST_AGGREGATE_CREATE);

        String name = "testAggregate01";
        String availabilityZone = "nova";
        HostAggregate hostAggregate = osv3().compute().hostAggregates().create(name, availabilityZone);
        assertNotNull(hostAggregate);
        assertEquals(hostAggregate.getName(), name);
        assertEquals(hostAggregate.getAvailabilityZone(), availabilityZone);
    }

    @Test
    public void setMetadata() throws IOException {
        respondWith(JSON_HOST_AGGREGATE_CREATE);
        Map<String, String> metadata = new HashMap<>();
        metadata.put("key1", "value1");
        metadata.put("key2", null);
        HostAggregate hostAggregate = osv3().compute().hostAggregates().setMetadata("aggregateId", metadata);
        assertNotNull(hostAggregate);

        HostAggregateMetadata ham = new HostAggregateMetadata(metadata);
        String s = ObjectMapperSingleton.getContext(HostAggregateMetadata.class).writer().writeValueAsString(ham);
        assertTrue(s.contains("\"key2\" : null"), "null key2 should be present. found " + s);
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}

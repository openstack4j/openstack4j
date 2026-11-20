package org.openstack4j.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openstack4j.api.Builders;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.manila.Share;
import org.openstack4j.model.manila.ShareInstance;
import org.openstack4j.model.manila.ShareSnapshot;
import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.openstack.heat.domain.HeatResource;
import org.openstack4j.openstack.storage.block.domain.CinderBackendStoragePool;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BackportModelTests {

    @Test
    public void quotaPreservesIdAndSendsOnlyPresentFields() throws Exception {
        ObjectMapper mapper = ObjectMapperSingleton.getContext(CinderBlockQuotaSet.class);
        ObjectMapper json = new ObjectMapper();
        BlockQuotaSet quota = mapper.readValue("{\"quota_set\":{\"id\":\"project\",\"volumes\":10}}",
                CinderBlockQuotaSet.class);
        JsonNode update = json.readTree(mapper.writeValueAsString(quota.toBuilder().volumes(0).build())).path("quota_set");
        assertEquals(quota.getId(), "project");
        assertEquals(quota.getSnapshots(), 0);
        assertEquals(update.size(), 1);
        assertEquals(update.path("volumes").intValue(), 0);

        JsonNode partial = json.readTree(mapper.writeValueAsString(Builders.blockQuotaSet().gigabytes(100).build()))
                .path("quota_set");
        assertEquals(partial.size(), 1);
        assertEquals(partial.path("gigabytes").intValue(), 100);
    }

    @Test
    public void cinderPoolAcceptsKeywordsAndNumericStrings() throws Exception {
        ObjectMapper mapper = ObjectMapperSingleton.getContext(CinderBackendStoragePool.class);
        CinderBackendStoragePool pool = mapper.readValue("{\"capabilities\":{\"total_capacity_gb\":\"infinite\","
                + "\"free_capacity_gb\":\"unknown\",\"provisioned_capacity_gb\":\"12.5\",\"total_volumes\":3}}",
                CinderBackendStoragePool.class);
        assertNull(pool.getCapabilities().getTotalCapacityGb());
        assertNull(pool.getCapabilities().getFreeCapacityGb());
        assertEquals(pool.getCapabilities().getProvisionedcapacitygb(), Long.valueOf(13));
        assertEquals(pool.getCapabilities().getTotalvolumes(), Integer.valueOf(3));
    }

    @Test
    public void newerManilaValuesDoNotBreakResponses() throws Exception {
        ObjectMapper mapper = ObjectMapperSingleton.getContext(Share.class);
        assertEquals(mapper.readValue("\"new-status\"", Share.Status.class), Share.Status.UNRECOGNIZED);
        assertEquals(mapper.readValue("\"new-status\"", ShareInstance.Status.class), ShareInstance.Status.UNRECOGNIZED);
        assertEquals(mapper.readValue("\"new-status\"", ShareSnapshot.Status.class), ShareSnapshot.Status.UNRECOGNIZED);
        assertEquals(mapper.readValue("\"GLUSTERFS\"", Share.Protocol.class), Share.Protocol.GlusterFS);
        assertEquals(mapper.readValue("\"new-protocol\"", Share.Protocol.class), Share.Protocol.UNRECOGNIZED);
        assertEquals(mapper.writeValueAsString(Share.Protocol.GlusterFS), "\"GlusterFS\"");
    }

    @Test
    public void heatResourceReadsWrappedObject() throws Exception {
        ObjectMapper mapper = ObjectMapperSingleton.getContext(HeatResource.class);
        HeatResource resource = mapper.readValue("{\"resource\":{\"resource_name\":\"server\"}}", HeatResource.class);
        assertEquals(resource.getResourceName(), "server");
    }

    @Test
    public void catalogAcceptsOfficialTypesAndKeepsLegacyTypes() {
        assertEquals(ServiceType.forName("block-storage"), ServiceType.BLOCK_STORAGE);
        assertEquals(ServiceType.forName("shared-file-system"), ServiceType.SHARE);
        assertEquals(ServiceType.forName("container-infrastructure-management"), ServiceType.MAGNUM);
        assertEquals(ServiceType.forName("volumev3"), ServiceType.BLOCK_STORAGE);
        assertEquals(ServiceType.BLOCK_STORAGE.getType(), "volume");
    }
}

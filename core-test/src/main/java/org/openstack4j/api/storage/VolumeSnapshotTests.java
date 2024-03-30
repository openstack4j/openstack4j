package org.openstack4j.api.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.core.transport.ObjectMapperSingleton;
import org.openstack4j.model.storage.block.VolumeSnapshot;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeSnapshot;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


@Test(suiteName = "Block Storage Tests")
public class VolumeSnapshotTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    public void listVolumeSnaphotsV1() throws Exception {
        // Check list volumes
        respondWith("/storage/v1/volumesnapshots.json");
        List<? extends VolumeSnapshot> volumes = osv3().blockStorage().snapshots().list();
        assertEquals(volumes.size(), 2);

        // Check that the list request is the one we expect
        RecordedRequest listRequest = server.takeRequest();
        assertNotNull(listRequest.getHeader("X-Auth-Token"));
        assertTrue(listRequest.getPath().matches("/v[12]/\\p{XDigit}*/snapshots"));

        // Check list volumes with filters
        respondWith("/storage/v1/volumesnapshots_filtered.json");
        final String volName = "snap-vol-test-1";
        Map<String, String> filters = new HashMap<String, String>();
        filters.put("display_name", volName);
        List<? extends VolumeSnapshot> filteredVolumes = osv3().blockStorage().snapshots().list(filters);
        assertEquals(filteredVolumes.size(), 1);

        // Check that the list request is the one we expect
        RecordedRequest filteredListRequest = server.takeRequest();
        assertNotNull(filteredListRequest.getHeader("X-Auth-Token"));
        assertTrue(filteredListRequest.getPath().matches("/v[12]/\\p{XDigit}*/snapshots\\?display_name=" + volName));
    }

    @Test
    public void createVolumeSnapshotV2() throws JsonProcessingException {
        ObjectMapper objectMapper = ObjectMapperSingleton.getContext(Object.class);
        VolumeSnapshot disc = CinderVolumeSnapshot.builder().description("disc").build();
        String value = objectMapper.writeValueAsString(disc);
        JsonNode jsonNode = objectMapper.valueToTree(value);
        //see https://github.com/openstack4j/openstack4j/issues/152
        assertFalse(jsonNode.has("display_description"));
    }

}

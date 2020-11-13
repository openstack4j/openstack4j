package org.openstack4j.api.storage;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.openstack.storage.block.domain.CinderBlockQuotaSet;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Test(suiteName = "Block Storage Tests")
public class VolumeTypeQuotaTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.BLOCK_STORAGE;
    }

    @Test
    public void volumeTypeGigabytesQuota() throws Exception {
        respondWith("/storage/v2/updateQuotaSetResponse.json");

        String volumeTypeGigabytesQuotaKey = "gigabytes_ruby";
        String volumeTypeSnapshotsQuotaKey = "snapshots_ruby";
        String volumeTypeVolumesQuotaKey = "volumes_ruby";
        String notAVolumeTypeQuotaKey = "i_dont_want_that_key";

        BlockQuotaSet blockQuotaSet = new CinderBlockQuotaSet()
                .toBuilder()
                .volumeTypesQuotas(
                        ImmutableMap.of(
                                volumeTypeGigabytesQuotaKey, 100,
                                volumeTypeSnapshotsQuotaKey, 5,
                                volumeTypeVolumesQuotaKey, -1
                        )
                )
                .build();

        blockQuotaSet = osv3().blockStorage().quotaSets().updateForTenant("1-2-3", blockQuotaSet);
        String requestBody = server.takeRequest().getBody().readUtf8();

        assertTrue(requestBody.contains("\"gigabytes_ruby\" : 100"));
        assertTrue(requestBody.contains("\"snapshots_ruby\" : 5"));
        assertTrue(requestBody.contains("\"volumes_ruby\" : -1"));

        Map<String, Integer> quotas = blockQuotaSet.getVolumeTypesQuotas();

        assertTrue(quotas.containsKey(volumeTypeGigabytesQuotaKey), "Should contain the ruby volume type gigabytes quota");
        assertTrue(quotas.containsKey(volumeTypeSnapshotsQuotaKey), "Should contain the ruby volume type snapshots quota");
        assertTrue(quotas.containsKey(volumeTypeVolumesQuotaKey), "Should contain the ruby volume type volumes quota");

        assertFalse(quotas.containsKey(notAVolumeTypeQuotaKey), "Should not contain the " + notAVolumeTypeQuotaKey + " key");
    }
}

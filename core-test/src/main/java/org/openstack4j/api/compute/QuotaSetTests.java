package org.openstack4j.api.compute;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.compute.ComputeQuotaDetail;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(suiteName = "QuotaSet")
public class QuotaSetTests extends AbstractTest {

    private static final String QUOTA_SET_DETAIL = "/compute/quota_set_detail.json";

    @Test
    public void getQuotaDetail() throws Exception {
        respondWith(QUOTA_SET_DETAIL);

        ComputeQuotaDetail computeQuotaSetDetail = osv3().compute().quotaSets().getDetail("04578a4e4c8944778f589f0f15fc0369");

        assertEquals(computeQuotaSetDetail.getId(), "04578a4e4c8944778f589f0f15fc0369");

        assertEquals(-1, computeQuotaSetDetail.getCores().getLimit());
        assertEquals(23, computeQuotaSetDetail.getCores().getInUse());
        assertEquals(0, computeQuotaSetDetail.getCores().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getInstances().getLimit());
        assertEquals(9, computeQuotaSetDetail.getInstances().getInUse());
        assertEquals(0, computeQuotaSetDetail.getInstances().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getKeyPairs().getLimit());
        assertEquals(0, computeQuotaSetDetail.getKeyPairs().getInUse());
        assertEquals(0, computeQuotaSetDetail.getKeyPairs().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getMetadataItems().getLimit());
        assertEquals(0, computeQuotaSetDetail.getMetadataItems().getInUse());
        assertEquals(0, computeQuotaSetDetail.getMetadataItems().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getRam().getLimit());
        assertEquals(46080, computeQuotaSetDetail.getRam().getInUse());
        assertEquals(0, computeQuotaSetDetail.getRam().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getServerGroupMembers().getLimit());
        assertEquals(0, computeQuotaSetDetail.getServerGroupMembers().getInUse());
        assertEquals(0, computeQuotaSetDetail.getServerGroupMembers().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getServerGroups().getLimit());
        assertEquals(0, computeQuotaSetDetail.getServerGroups().getInUse());
        assertEquals(0, computeQuotaSetDetail.getServerGroups().getReserved());

    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}

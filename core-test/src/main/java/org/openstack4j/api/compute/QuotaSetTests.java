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

        assertEquals(-1, computeQuotaSetDetail.getFixedIps().getLimit());
        assertEquals(0, computeQuotaSetDetail.getFixedIps().getInUse());
        assertEquals(0, computeQuotaSetDetail.getFixedIps().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getFloatingIps().getLimit());
        assertEquals(0, computeQuotaSetDetail.getFloatingIps().getInUse());
        assertEquals(0, computeQuotaSetDetail.getFloatingIps().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getInstances().getLimit());
        assertEquals(9, computeQuotaSetDetail.getInstances().getInUse());
        assertEquals(0, computeQuotaSetDetail.getInstances().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getInjectedFileContentBytes().getLimit());
        assertEquals(0, computeQuotaSetDetail.getInjectedFileContentBytes().getInUse());
        assertEquals(0, computeQuotaSetDetail.getInjectedFileContentBytes().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getInjectedFilePathBytes().getLimit());
        assertEquals(0, computeQuotaSetDetail.getInjectedFilePathBytes().getInUse());
        assertEquals(0, computeQuotaSetDetail.getInjectedFilePathBytes().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getInjectedFiles().getLimit());
        assertEquals(0, computeQuotaSetDetail.getInjectedFiles().getInUse());
        assertEquals(0, computeQuotaSetDetail.getInjectedFiles().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getKeyPairs().getLimit());
        assertEquals(0, computeQuotaSetDetail.getKeyPairs().getInUse());
        assertEquals(0, computeQuotaSetDetail.getKeyPairs().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getMetadataItems().getLimit());
        assertEquals(0, computeQuotaSetDetail.getMetadataItems().getInUse());
        assertEquals(0, computeQuotaSetDetail.getMetadataItems().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getRam().getLimit());
        assertEquals(46080, computeQuotaSetDetail.getRam().getInUse());
        assertEquals(0, computeQuotaSetDetail.getRam().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getSecurityGroupRules().getLimit());
        assertEquals(0, computeQuotaSetDetail.getSecurityGroupRules().getInUse());
        assertEquals(0, computeQuotaSetDetail.getSecurityGroupRules().getReserved());

        assertEquals(-1, computeQuotaSetDetail.getSecurityGroups().getLimit());
        assertEquals(0, computeQuotaSetDetail.getSecurityGroups().getInUse());
        assertEquals(0, computeQuotaSetDetail.getSecurityGroups().getReserved());

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

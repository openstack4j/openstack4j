package org.openstack4j.api.compute;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActionResponseStatusTests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }

    @DataProvider
    public Object[][] statuses() {
        return new Object[][] {{429}, {502}, {503}};
    }

    @Test(groups = "backports", dataProvider = "statuses")
    public void returnsFailureWithStatusWhenBodyCannotBeRead(int status) throws Exception {
        respondWith(status, "unreadable response");
        ActionResponse result = osv3().compute().servers().delete("server-id");
        takeRequest();
        assertFalse(result.isSuccess());
        assertEquals(result.getCode(), status);
    }

    @Test(groups = "backports")
    public void preservesLegacyConflictFallback() throws Exception {
        respondWith(409, "{}");
        ActionResponse result = osv3().compute().servers().delete("server-id");
        takeRequest();
        assertFalse(result.isSuccess());
        assertEquals(result.getCode(), 409);
    }
}

package org.openstack4j.openstack.telemetry.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.common.functions.EnforceVersionToURL;
import org.openstack4j.openstack.internal.BaseOpenStackService;

public class BaseTelemetryAodhServices extends BaseOpenStackService {

    protected BaseTelemetryAodhServices() {
        super(ServiceType.TELEMETRY_AODH, EnforceVersionToURL.instance("/v2"));
    }

    protected <T> List<T> wrapList(T[] type) {
        if (type != null)
            return Arrays.asList(type);
        return Collections.emptyList();

    }
}

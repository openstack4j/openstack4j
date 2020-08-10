package org.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.openstack4j.api.compute.EventServices;
import org.openstack4j.model.compute.ServerAction;
import org.openstack4j.openstack.compute.domain.ServerEvent;
import org.openstack4j.openstack.compute.domain.ServerEvent.Events;
import org.openstack4j.openstack.heat.domain.HeatEvent;

/**
 * This class implements some methods for manipulation of {@link HeatEvent}
 * objects. The non-exhaustive list of methods is oriented along
 * https://docs.openstack.org/api-ref/compute/index.html#list-actions-for-server
 *
 * @author sujit sah
 *
 */
public class EventServicesImpl extends BaseComputeServices implements EventServices {

    @Override
    public List<? extends ServerAction> list(String serverId) {
        checkNotNull(serverId);
        return get(Events.class, uri("servers/%s/os-instance-actions", serverId)).execute().getList();
    }

    @Override
    public ServerAction show(String serverId, String requestId) {
        checkNotNull(serverId);
        checkNotNull(requestId);
        return get(ServerEvent.class, uri("/servers/%s/os-instance-actions/%s", serverId, requestId)).execute();
    }

}

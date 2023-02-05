package org.openstack4j.openstack.compute.internal.ext;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.ext.InstanceActionsService;
import org.openstack4j.model.compute.InstanceAction;
import org.openstack4j.openstack.compute.domain.NovaInstanceAction;
import org.openstack4j.openstack.compute.domain.NovaInstanceAction.NovaInstanceActions;
import org.openstack4j.openstack.compute.internal.BaseComputeServices;

/**
 * API to list executed instance actions.
 *
 * @author Christian Banse
 */
public class InstanceActionsServiceImpl extends BaseComputeServices implements InstanceActionsService {

    @Override
    public List<? extends InstanceAction> list(String serverId) {
        Objects.requireNonNull(serverId, "serverId");
        return get(NovaInstanceActions.class, uri("/servers/%s/os-instance-actions", serverId)).execute().getList();
    }

    @Override
    public InstanceAction get(String serverId, String requestId) {
        Objects.requireNonNull(serverId, "serverId");
        Objects.requireNonNull(requestId, "requestId");
        return get(NovaInstanceAction.class, uri("/servers/%s/os-instance-actions/%s", serverId, requestId)).execute();
    }

}

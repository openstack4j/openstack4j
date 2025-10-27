package org.openstack4j.openstack.compute.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.ServerGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.ServerGroup;
import org.openstack4j.openstack.compute.domain.NovaServerGroup;
import org.openstack4j.openstack.compute.domain.NovaServerGroup.ServerGroups;

public class ServerGroupServiceImpl extends BaseComputeServices implements ServerGroupService {

    @Override
    public List<? extends ServerGroup> list() {
        return get(ServerGroups.class, uri("/os-server-groups")).execute().getList();
    }

    @Override
    public ServerGroup get(String id) {
        Objects.requireNonNull(id);
        return get(NovaServerGroup.class, uri("/os-server-groups/%s", id)).execute();
    }

    @Override
    public ActionResponse delete(String id) {
        Objects.requireNonNull(id);
        return deleteWithResponse(uri("/os-server-groups/%s", id)).execute();
    }

    @Override
    public ServerGroup create(String name, String policy) {
        NovaServerGroup nsg = NovaServerGroup.create(name, policy);
        return post(NovaServerGroup.class, uri("/os-server-groups")).entity(nsg).execute();
    }

}

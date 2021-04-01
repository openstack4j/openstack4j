package org.openstack4j.openstack.manila.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.manila.ShareServerService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.manila.ShareServer;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.manila.domain.ManilaShareServer;

public class ShareServerServiceImpl extends BaseShareServices implements ShareServerService {
    @Override
    public List<? extends ShareServer> list() {
        return get(ManilaShareServer.ShareServers.class, uri("/share-servers"))
                .execute()
                .getList();
    }

    @Override
    public ShareServer get(String shareServerId) {
        Objects.requireNonNull(shareServerId);
        return get(ManilaShareServer.class, uri("/share-servers/%s", shareServerId)).execute();
    }

    @Override
    public ActionResponse delete(String shareServerId) {
        Objects.requireNonNull(shareServerId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/share-servers/%s", shareServerId)).executeWithResponse());
    }
}

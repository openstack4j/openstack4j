package org.openstack4j.openstack.networking.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.RouterConntrackHelperService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.RouterConntrackHelper;
import org.openstack4j.openstack.networking.domain.NeutronRouterConntrackHelper;
import org.openstack4j.openstack.networking.domain.NeutronRouterConntrackHelper.RouterConntrackHelpers;

/**
 * RouterConntrackHelperService implementation that provides (Neutron) Router Conntrack Helper based Service Operations.
 *
 * @author bboyHan
 */
public class RouterConntrackHelperServiceImpl extends BaseNetworkingServices implements RouterConntrackHelperService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends RouterConntrackHelper> list(String routerId) {
        Objects.requireNonNull(routerId);
        return get(RouterConntrackHelpers.class, uri("/routers/%s/conntrack_helpers", pathSegment(routerId))).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouterConntrackHelper get(String routerId, String conntrackHelperId) {
        Objects.requireNonNull(routerId);
        Objects.requireNonNull(conntrackHelperId);
        return get(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers/%s", pathSegment(routerId), pathSegment(conntrackHelperId))).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String routerId, String conntrackHelperId) {
        Objects.requireNonNull(routerId);
        Objects.requireNonNull(conntrackHelperId);
        return deleteWithResponse(uri("/routers/%s/conntrack_helpers/%s", pathSegment(routerId), pathSegment(conntrackHelperId))).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouterConntrackHelper create(String routerId, RouterConntrackHelper routerConntrackHelper) {
        Objects.requireNonNull(routerId);
        Objects.requireNonNull(routerConntrackHelper);
        return post(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers", pathSegment(routerId)))
                .entity(requestBody(routerConntrackHelper)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RouterConntrackHelper update(String routerId, RouterConntrackHelper routerConntrackHelper) {
        Objects.requireNonNull(routerId);
        Objects.requireNonNull(routerConntrackHelper);
        Objects.requireNonNull(routerConntrackHelper.getId());
        return put(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers/%s", pathSegment(routerId), pathSegment(routerConntrackHelper.getId())))
                .entity(requestBody(routerConntrackHelper))
                .execute();
    }

    private RouterConntrackHelper requestBody(RouterConntrackHelper helper) {
        return NeutronRouterConntrackHelper.builder().helper(helper.getHelper())
                .protocol(helper.getProtocol()).port(helper.getPort()).build();
    }
}

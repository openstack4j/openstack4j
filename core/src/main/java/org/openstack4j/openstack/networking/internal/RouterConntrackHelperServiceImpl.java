package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.networking.RouterConntrackHelperService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.RouterConntrackHelper;
import org.openstack4j.openstack.networking.domain.NeutronRouterConntrackHelper;
import org.openstack4j.openstack.networking.domain.NeutronRouterConntrackHelper.RouterConntrackHelpers;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * RouterService implementation that provides Neutron Router based Service Operations.
 *
 * @author bboyHan
 */
public class RouterConntrackHelperServiceImpl extends BaseNetworkingServices implements RouterConntrackHelperService {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<? extends RouterConntrackHelper> list(String routerId) {
		checkNotNull(routerId);
		return get(RouterConntrackHelpers.class, uri("/routers/%s/conntrack_helpers", routerId)).execute().getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RouterConntrackHelper get(String routerId, String conntrackHelperId) {
		checkNotNull(routerId);
		checkNotNull(conntrackHelperId);
		return get(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers/%s", routerId, conntrackHelperId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionResponse delete(String routerId, String conntrackHelperId) {
		checkNotNull(routerId);
		checkNotNull(conntrackHelperId);
		return deleteWithResponse(uri("/routers/%s/conntrack_helpers/%s", routerId, conntrackHelperId)).execute();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RouterConntrackHelper create(String routerId, RouterConntrackHelper routerConntrackHelper) {
		checkNotNull(routerId);
		checkNotNull(routerConntrackHelper);
		return post(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers", routerId)).entity(routerConntrackHelper).execute();
	}

	/**
	 * {@inheritDoc}
	 */
    @Override
	public RouterConntrackHelper update(String routerId, RouterConntrackHelper routerConntrackHelper) {
		checkNotNull(routerId);
		checkNotNull(routerConntrackHelper);
		checkNotNull(routerConntrackHelper.getId());
		return put(NeutronRouterConntrackHelper.class, uri("/routers/%s/conntrack_helpers/%s", routerId, getAndClearIdentifier(routerConntrackHelper)))
				     .entity(routerConntrackHelper)
				     .execute();
	}

	private String getAndClearIdentifier(RouterConntrackHelper routerConntrackHelper) {
		String conntrackHelperId = routerConntrackHelper.getId();
		routerConntrackHelper.setId(null);
		routerConntrackHelper.setRouterId(null);
		return conntrackHelperId;
	}
}

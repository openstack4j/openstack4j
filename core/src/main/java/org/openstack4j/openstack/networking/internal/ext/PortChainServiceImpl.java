package org.openstack4j.openstack.networking.internal.ext;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.networking.ext.PortChainService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.network.ext.PortChain;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortChain;
import org.openstack4j.openstack.networking.domain.ext.NeutronPortChain.PortChains;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

/**
 * {@inheritDoc}
 */
public class PortChainServiceImpl extends BaseNetworkingServices implements PortChainService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends PortChain> list() {
        return get(PortChains.class, uri("/sfc/port_chains")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortChain create(PortChain portChain) {
        Objects.requireNonNull(portChain);
        return post(NeutronPortChain.class, uri("/sfc/port_chains")).entity(portChain).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String portChainId) {
        Objects.requireNonNull(portChainId);
        return deleteWithResponse(uri("/sfc/port_chains/%s", portChainId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortChain get(String portChainId) {
        Objects.requireNonNull(portChainId);
        return get(NeutronPortChain.class, uri("/sfc/port_chains/%s", portChainId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PortChain update(String portChainId, PortChain portChain) {
        Objects.requireNonNull(portChainId);
        return put(NeutronPortChain.class, uri("/sfc/port_chains/%s", portChainId)).entity(portChain).execute();
    }
}

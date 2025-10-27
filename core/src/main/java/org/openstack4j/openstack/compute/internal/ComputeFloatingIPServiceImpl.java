package org.openstack4j.openstack.compute.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.ComputeFloatingIPService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.common.MapEntity;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP;
import org.openstack4j.openstack.compute.domain.NovaFloatingIP.NovaFloatingIPs;
import org.openstack4j.openstack.compute.domain.NovaFloatingIPPools;
import org.openstack4j.openstack.compute.domain.actions.FloatingIpActions;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;

/**
 * OpenStack Floating-IP API Implementation
 *
 * @author Nathan Anderson
 */
@Deprecated
public class ComputeFloatingIPServiceImpl extends BaseComputeServices implements ComputeFloatingIPService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends FloatingIP> list() {
        return get(NovaFloatingIPs.class, uri("/os-floating-ips")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPoolNames() {
        return get(NovaFloatingIPPools.class, uri("/os-floating-ip-pools")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloatingIP allocateIP(String pool) {
        return post(NovaFloatingIP.class, uri("/os-floating-ips")).entity(MapEntity.create("pool", pool))
                .execute(ExecutionOptions.<NovaFloatingIP>create(PropagateOnStatus.on(404)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deallocateIP(String id) {
        Objects.requireNonNull(id);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/os-floating-ips/%s", id)).executeWithResponse()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(Server server, String fixedIpAddress, String ipAddress) {
        Objects.requireNonNull(server);
        Objects.requireNonNull(ipAddress);

        return invokeAction(server.getId(), FloatingIpActions.Add.create(ipAddress, fixedIpAddress));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(Server server, String ipAddress) {
        return addFloatingIP(server, null, ipAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeFloatingIP(Server server, String ipAddress) {
        Objects.requireNonNull(server);
        Objects.requireNonNull(ipAddress);

        return invokeAction(server.getId(), FloatingIpActions.Remove.create(ipAddress));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(String serverId, String fixedIpAddress, String ipAddress) {
        Objects.requireNonNull(serverId);
        Objects.requireNonNull(ipAddress);
        return invokeAction(serverId, FloatingIpActions.Add.create(ipAddress, fixedIpAddress));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addFloatingIP(String serverId, String ipAddress) {
        return addFloatingIP(serverId, null, ipAddress);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeFloatingIP(String serverId, String ipAddress) {
        Objects.requireNonNull(serverId);
        Objects.requireNonNull(ipAddress);

        return invokeAction(serverId, FloatingIpActions.Remove.create(ipAddress));
    }


}

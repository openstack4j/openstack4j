package org.openstack4j.openstack.identity.v3.internal;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.openstack4j.api.identity.v3.ServiceEndpointService;
import org.openstack4j.api.types.Facing;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.openstack.identity.v3.domain.KeystoneEndpoint;
import org.openstack4j.openstack.identity.v3.domain.KeystoneEndpoint.Endpoints;
import org.openstack4j.openstack.identity.v3.domain.KeystoneService;
import org.openstack4j.openstack.identity.v3.domain.KeystoneService.Services;

import static org.openstack4j.core.transport.ClientConstants.PATH_ENDPOINTS;
import static org.openstack4j.core.transport.ClientConstants.PATH_SERVICES;

/**
 * implementation of v3 service manager service
 */
public class ServiceEndpointServiceImpl extends BaseIdentityServices implements ServiceEndpointService {

    @Override
    public Service create(Service service) {
        Objects.requireNonNull(service);
        return post(KeystoneService.class, uri(PATH_SERVICES)).entity(service).execute();
    }

    @Override
    public Service create(String type, String name, String description, boolean enabled) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Objects.requireNonNull(enabled);
        return create(KeystoneService.builder().type(type).name(name).description(description).enabled(enabled).build());
    }

    @Override
    public Service get(String serviceId) {
        Objects.requireNonNull(serviceId);
        return get(KeystoneService.class, PATH_SERVICES, "/", serviceId).execute();
    }

    @Override
    public Service update(Service service) {
        Objects.requireNonNull(service);
        return patch(KeystoneService.class, PATH_SERVICES, "/", service.getId()).entity(service).execute();
    }

    @Override
    public ActionResponse delete(String serviceId) {
        Objects.requireNonNull(serviceId);
        return deleteWithResponse(PATH_SERVICES, "/", serviceId).execute();
    }

    @Override
    public List<? extends Service> list() {
        return get(Services.class, uri(PATH_SERVICES)).execute().getList();
    }

    @Override
    public List<? extends Endpoint> listEndpoints() {
        return get(Endpoints.class, uri(PATH_ENDPOINTS)).execute().getList();
    }

    @Override
    public Endpoint createEndpoint(Endpoint endpoint) {
        Objects.requireNonNull(endpoint);
        return post(KeystoneEndpoint.class, uri(PATH_ENDPOINTS)).entity(endpoint).execute();
    }

    @Override
    public Endpoint createEndpoint(String name, URL url, Facing iface, String regionId, String serviceId, boolean enabled) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(url);
        Objects.requireNonNull(iface);
        Objects.requireNonNull(regionId);
        Objects.requireNonNull(serviceId);
        Objects.requireNonNull(enabled);
        return createEndpoint(KeystoneEndpoint.builder().name(name).url(url).iface(iface).regionId(regionId).serviceId(serviceId).enabled(enabled).build());
    }

    @Override
    public Endpoint getEndpoint(String endpointId) {
        Objects.requireNonNull(endpointId);
        return get(KeystoneEndpoint.class, PATH_ENDPOINTS, "/", endpointId).execute();
    }

    @Override
    public Endpoint updateEndpoint(Endpoint endpoint) {
        Objects.requireNonNull(endpoint);
        return patch(KeystoneEndpoint.class, PATH_ENDPOINTS, "/", endpoint.getId()).entity(endpoint).execute();
    }

    @Override
    public ActionResponse deleteEndpoint(String endpointId) {
        Objects.requireNonNull(endpointId);
        return deleteWithResponse(PATH_ENDPOINTS, "/", endpointId).execute();
    }

}

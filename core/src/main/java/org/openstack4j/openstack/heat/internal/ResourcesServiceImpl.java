package org.openstack4j.openstack.heat.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.heat.ResourcesService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.heat.Resource;
import org.openstack4j.model.heat.ResourceHealth;
import org.openstack4j.openstack.heat.domain.HeatResource;
import org.openstack4j.openstack.heat.domain.HeatResource.Resources;

/**
 * This class implements some methods for manipulation of {@link HeatResource} objects. The
 * non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html
 *
 * @author Octopus Zhang
 */
public class ResourcesServiceImpl extends BaseHeatServices implements ResourcesService {

    @Override
    public List<? extends Resource> list(String stackName, String stackId) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        return get(Resources.class, uri("/stacks/%s/%s/resources", stackName, stackId)).execute().getList();
    }

    @Override
    public List<? extends Resource> list(String stackNameOrId) {
        return list(stackNameOrId, 1);
    }

    @Override
    public List<? extends Resource> list(String stackNameOrId, int depth) {
        Objects.requireNonNull(stackNameOrId);
        return get(HeatResource.Resources.class, uri("/stacks/%s/resources", stackNameOrId))
                .param("nested_depth", depth).execute().getList();
    }

    @Override
    public Resource show(String stackName, String stackId, String resourceName) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        Objects.requireNonNull(resourceName);
        return get(HeatResource.class, uri("/stacks/%s/%s/resources/%s", stackName, stackId, resourceName)).execute();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getMetadata(String stackName, String stackId, String resourceName) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        Objects.requireNonNull(resourceName);
        return get(HashMap.class, uri("/stacks/%s/%s/resources/%s/metadata", stackName, stackId, resourceName)).execute();
    }

    @Override
    public ActionResponse signal(String stackName, String stackId, String resourceName) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        Objects.requireNonNull(resourceName);
        return postWithResponse(uri("/stacks/%s/%s/resources/%s/signal", stackName, stackId, resourceName)).execute();
    }

    @Override
    public ActionResponse markUnhealthy(String stackName, String stackId, String resourceName, ResourceHealth resourceHealth) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        return patchWithResponse(uri("/stacks/%s/%s/resources/%s", stackName, stackId, resourceName))
                .entity(resourceHealth)
                .execute();
    }
}

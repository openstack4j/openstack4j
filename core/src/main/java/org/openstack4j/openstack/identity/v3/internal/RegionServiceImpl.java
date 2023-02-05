package org.openstack4j.openstack.identity.v3.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.identity.v3.RegionService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Region;
import org.openstack4j.openstack.identity.v3.domain.KeystoneRegion;
import org.openstack4j.openstack.identity.v3.domain.KeystoneRegion.Regions;

import static org.openstack4j.core.transport.ClientConstants.PATH_REGIONS;

/**
 * Implementation of v3 region service
 */
public class RegionServiceImpl extends BaseIdentityServices implements RegionService {

    @Override
    public Region create(Region region) {
        Objects.requireNonNull(region);
        return post(KeystoneRegion.class, uri(PATH_REGIONS)).entity(region).execute();
    }

    @Override
    public Region create(String regionId, String description, String parentRegionId) {
        Objects.requireNonNull(regionId);
        Objects.requireNonNull(description);
        Objects.requireNonNull(parentRegionId);
        return create(KeystoneRegion.builder().id(regionId).description(description).parentRegionId(parentRegionId).build());
    }

    @Override
    public Region get(String regionId) {
        Objects.requireNonNull(regionId);
        return get(KeystoneRegion.class, PATH_REGIONS, "/", regionId).execute();
    }

    @Override
    public Region update(Region region) {
        Objects.requireNonNull(region);
        return patch(KeystoneRegion.class, PATH_REGIONS, "/", region.getId()).entity(region).execute();
    }

    @Override
    public ActionResponse delete(String regionId) {
        Objects.requireNonNull(regionId);
        return deleteWithResponse(PATH_REGIONS, "/", regionId).execute();
    }

    @Override
    public List<? extends Region> list() {
        return get(Regions.class, uri(PATH_REGIONS)).execute().getList();
    }

}

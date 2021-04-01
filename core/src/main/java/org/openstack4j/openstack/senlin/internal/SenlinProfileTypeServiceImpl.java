package org.openstack4j.openstack.senlin.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.senlin.SenlinProfileTypeService;
import org.openstack4j.model.senlin.ProfileType;
import org.openstack4j.openstack.senlin.domain.SenlinProfileType;

/**
 * This class contains getters for all implementation of the available profile-type services
 *
 * @author lion
 */
public class SenlinProfileTypeServiceImpl extends BaseSenlinServices implements SenlinProfileTypeService {

    @Override
    public List<? extends ProfileType> list() {
        return get(SenlinProfileType.ProfileType.class, uri("/profile-types")).execute().getList();
    }

    @Override
    public ProfileType get(String ProfileType) {
        Objects.requireNonNull(ProfileType);
        return get(SenlinProfileType.class, uri("/profile-types/%s", ProfileType)).execute();
    }
}

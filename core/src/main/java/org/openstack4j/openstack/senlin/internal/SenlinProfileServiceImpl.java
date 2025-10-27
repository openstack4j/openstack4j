package org.openstack4j.openstack.senlin.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.senlin.SenlinProfileService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.Profile;
import org.openstack4j.model.senlin.ProfileCreate;
import org.openstack4j.openstack.senlin.domain.SenlinProfile;

/**
 * This class contains getters for all implementation of the available profile services
 *
 * @author lion
 */
public class SenlinProfileServiceImpl extends BaseSenlinServices implements SenlinProfileService {

    @Override
    public List<? extends Profile> list() {
        return get(SenlinProfile.Profile.class, uri("/profiles")).execute().getList();
    }

    @Override
    public Profile create(ProfileCreate newProfile) {
        Objects.requireNonNull(newProfile);
        return post(SenlinProfile.class, uri("/profiles")).entity(newProfile).execute();
    }

    @Override
    public Profile get(String policyID) {
        Objects.requireNonNull(policyID);
        return get(SenlinProfile.class, uri("/profiles/%s", policyID)).execute();
    }

    @Override
    public Profile update(String policyID, ProfileCreate newPolicy) {
        Objects.requireNonNull(policyID);
        Objects.requireNonNull(newPolicy);
        return patch(SenlinProfile.class, uri("/profiles/%s", policyID)).entity(newPolicy).execute();
    }

    @Override
    public ActionResponse delete(String policyID) {
        Objects.requireNonNull(policyID);
        return deleteWithResponse(uri("/profiles/%s", policyID)).execute();
    }
}

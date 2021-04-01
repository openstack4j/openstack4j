package org.openstack4j.openstack.senlin.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.senlin.SenlinPolicyService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.senlin.Policy;
import org.openstack4j.model.senlin.PolicyCreate;
import org.openstack4j.openstack.senlin.domain.SenlinPolicy;

/**
 * This class contains getters for all implementation of the available policy services
 *
 * @author lion
 */
public class SenlinPolicyServiceImpl extends BaseSenlinServices implements SenlinPolicyService {

    @Override
    public List<? extends Policy> list() {
        return get(SenlinPolicy.Policy.class, uri("/policies")).execute().getList();
    }

    @Override
    public Policy create(PolicyCreate newPolicy) {
        Objects.requireNonNull(newPolicy);
        return post(SenlinPolicy.class, uri("/policies")).entity(newPolicy).execute();
    }

    @Override
    public Policy get(String policyID) {
        Objects.requireNonNull(policyID);
        return get(SenlinPolicy.class, uri("/policies/%s", policyID)).execute();
    }

    @Override
    public Policy update(String policyID, PolicyCreate newPolicy) {
        Objects.requireNonNull(policyID);
        Objects.requireNonNull(newPolicy);
        return patch(SenlinPolicy.class, uri("/policies/%s", policyID)).entity(newPolicy).execute();
    }

    @Override
    public ActionResponse delete(String policyID) {
        Objects.requireNonNull(policyID);
        return deleteWithResponse(uri("/policies/%s", policyID)).execute();
    }

}

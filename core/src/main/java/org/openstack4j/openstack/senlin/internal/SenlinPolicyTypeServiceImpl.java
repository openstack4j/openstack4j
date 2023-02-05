package org.openstack4j.openstack.senlin.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.senlin.SenlinPolicyTypeService;
import org.openstack4j.model.senlin.PolicyType;
import org.openstack4j.openstack.senlin.domain.SenlinPolicyType;

/**
 * This class contains getters for all implementation of the available policy-type services
 *
 * @author lion
 */
public class SenlinPolicyTypeServiceImpl extends BaseSenlinServices implements SenlinPolicyTypeService {

    @Override
    public List<? extends PolicyType> list() {
        return get(SenlinPolicyType.PolicyType.class, uri("/policy-types")).execute().getList();
    }

    @Override
    public PolicyType get(String policyType) {
        Objects.requireNonNull(policyType);
        return get(SenlinPolicyType.class, uri("/policy-types/%s", policyType)).execute();
    }
}

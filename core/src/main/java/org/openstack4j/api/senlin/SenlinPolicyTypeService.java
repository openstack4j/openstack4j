package org.openstack4j.api.senlin;

import java.util.List;

import org.openstack4j.model.senlin.PolicyType;

/**
 * This interface defines all methods for the manipulation of PolicyType
 *
 * @author lion
 */
public interface SenlinPolicyTypeService {

    /**
     * Gets a list of currently existing {@link PolicyType}s.
     *
     * @return the list of {@link PolicyType}s
     */
    List<? extends PolicyType> list();

    /**
     * returns details of a {@link PolicyType}.
     *
     * @param policyTypeName Name of {@link PolicyType}
     * @return PolicyType
     */
    PolicyType get(String policyTypeName);
}

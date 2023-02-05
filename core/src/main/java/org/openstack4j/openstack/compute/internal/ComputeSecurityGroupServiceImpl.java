package org.openstack4j.openstack.compute.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.ComputeSecurityGroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.SecGroupExtension;
import org.openstack4j.model.compute.SecGroupExtension.Rule;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroupRule;
import org.openstack4j.openstack.compute.domain.NovaSecGroupExtension.SecurityGroups;

/**
 * Provides operations against the Security Group extension in OpenStack
 * <p>
 * Extension Mapping: (os-security-groups)
 *
 * @author Jeremy Unruh
 */
@Deprecated
public class ComputeSecurityGroupServiceImpl extends BaseComputeServices implements ComputeSecurityGroupService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecGroupExtension> list() {
        return get(SecurityGroups.class, uri("/os-security-groups")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SecGroupExtension> listServerGroups(String serverId) {
        Objects.requireNonNull(serverId);
        return get(SecurityGroups.class, uri("/servers/%s/os-security-groups", serverId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecGroupExtension get(String securityGroupId) {
        Objects.requireNonNull(securityGroupId);
        return get(NovaSecGroupExtension.class, uri("/os-security-groups/%s", securityGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String securityGroupId) {
        Objects.requireNonNull(securityGroupId);
        return deleteWithResponse(uri("/os-security-groups/%s", securityGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecGroupExtension create(String name, String description) {
        Objects.requireNonNull(name);
        return post(NovaSecGroupExtension.class, uri("/os-security-groups"))
                .entity(NovaSecGroupExtension.create(name, description))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SecGroupExtension update(String securityGroupId, String name, String description) {
        Objects.requireNonNull(securityGroupId);
        Objects.requireNonNull(name);
        return put(NovaSecGroupExtension.class, uri("/os-security-groups/%s", securityGroupId))
                .entity(NovaSecGroupExtension.create(name, description))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rule createRule(Rule rule) {
        Objects.requireNonNull(rule);
        return post(SecurityGroupRule.class, uri("/os-security-group-rules")).entity(rule).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteRule(String ruleId) {
        Objects.requireNonNull(ruleId);
        return deleteWithResponse(uri("/os-security-group-rules/%s", ruleId)).execute();
    }
}

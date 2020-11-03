package org.openstack4j.openstack.networking.internal;

import org.openstack4j.api.networking.SecurityGroupTagService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroupTag;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Allows users to set security group tags on their resources.
 *
 * @author bboyHan
 */
public class SecurityGroupTagServiceImpl extends BaseNetworkingServices implements SecurityGroupTagService {

    @Override
    public ActionResponse check(String securityGroupId, String tag) {
        checkNotNull(securityGroupId);
        checkNotNull(tag);
        return getWithResponse(uri("/security-groups/%s/tags/%s", securityGroupId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NeutronSecurityGroupTag list(String securityGroupId) {
        checkNotNull(securityGroupId);
        return get(NeutronSecurityGroupTag.class, uri("/security-groups/%s/tags", securityGroupId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addSingle(String securityGroupId, String tag) {
        checkNotNull(securityGroupId);
        checkNotNull(tag);
        return putWithResponse(uri("/security-groups/%s/tags/%s", securityGroupId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NeutronSecurityGroupTag replace(String securityGroupId, NeutronSecurityGroupTag tags) {
        checkNotNull(securityGroupId);
        checkNotNull(tags);
        return put(NeutronSecurityGroupTag.class, uri("/security-groups/%s/tags", securityGroupId)).entity(tags).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String securityGroupId, String tag) {
        checkNotNull(securityGroupId);
        checkNotNull(tag);
        return deleteWithResponse(uri("/security-groups/%s/tags/%s", securityGroupId, tag)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteAll(String securityGroupId) {
        checkNotNull(securityGroupId);
        return deleteWithResponse(uri("/security-groups/%s/tags", securityGroupId)).execute();
    }

}

package org.openstack4j.openstack.identity.v3.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.identity.v3.GroupService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Group;
import org.openstack4j.model.identity.v3.Role;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.identity.v3.domain.KeystoneGroup;
import org.openstack4j.openstack.identity.v3.domain.KeystoneGroup.Groups;
import org.openstack4j.openstack.identity.v3.domain.KeystoneRole.Roles;
import org.openstack4j.openstack.identity.v3.domain.KeystoneUser.Users;

import static org.openstack4j.core.transport.ClientConstants.PATH_GROUPS;

public class GroupServiceImpl extends BaseIdentityServices implements GroupService {

    @Override
    public Group get(String groupId) {
        Objects.requireNonNull(groupId);
        return get(KeystoneGroup.class, PATH_GROUPS, "/", groupId).execute();
    }

    @Override
    public List<? extends Group> getByName(String groupName) {
        return get(Groups.class, uri(PATH_GROUPS)).param("name", groupName).execute().getList();
    }

    @Override
    public Group getByName(String groupName, String domainId) {
        Objects.requireNonNull(groupName);
        Objects.requireNonNull(domainId);
        return get(Groups.class, uri(PATH_GROUPS)).param("name", groupName).param("domain_id", domainId).execute().first();
    }

    @Override
    public ActionResponse delete(String groupId) {
        Objects.requireNonNull(groupId);
        return deleteWithResponse(PATH_GROUPS, "/", groupId).execute();
    }

    @Override
    public Group update(Group group) {
        Objects.requireNonNull(group);
        return patch(KeystoneGroup.class, PATH_GROUPS, "/", group.getId()).entity(group).execute();
    }

    @Override
    public Group create(Group group) {
        Objects.requireNonNull(group);
        return post(KeystoneGroup.class, uri(PATH_GROUPS)).entity(group).execute();
    }

    @Override
    public Group create(String domainId, String name, String description) {
        Objects.requireNonNull(domainId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        return create(KeystoneGroup.builder().domainId(domainId).name(name).description(description).build());
    }

    @Override
    public List<? extends Group> list() {
        return get(Groups.class, uri(PATH_GROUPS)).execute().getList();
    }

    @Override
    public List<? extends User> listGroupUsers(String groupId) {
        Objects.requireNonNull(groupId);
        return get(Users.class, uri("/groups/%s/users", groupId)).execute().getList();
    }

    @Override
    public ActionResponse checkGroupUser(String groupId, String userId) {
        Objects.requireNonNull(groupId);
        Objects.requireNonNull(userId);
        return head(ActionResponse.class, uri("/groups/%s/users/%s", groupId, userId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addUserToGroup(String groupId, String userId) {
        Objects.requireNonNull(groupId);
        Objects.requireNonNull(userId);
        return put(ActionResponse.class, uri("groups/%s/users/%s", groupId, userId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeUserFromGroup(String groupId, String userId) {
        Objects.requireNonNull(groupId);
        Objects.requireNonNull(userId);
        return deleteWithResponse(uri("groups/%s/users/%s", groupId, userId)).execute();
    }

    @Override
    public List<? extends Role> listProjectGroupRoles(String groupId, String projectId) {
        Objects.requireNonNull(groupId);
        Objects.requireNonNull(projectId);
        return get(Roles.class, uri("projects/%s/groups/%s/roles", projectId, groupId)).execute().getList();
    }

    @Override
    public List<? extends Role> listDomainGroupRoles(String groupId, String domainId) {
        Objects.requireNonNull(groupId);
        Objects.requireNonNull(domainId);
        return get(Roles.class, uri("domains/%s/groups/%s/roles", domainId, groupId)).execute().getList();
    }

}

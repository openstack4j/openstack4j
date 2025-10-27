package org.openstack4j.openstack.trove.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.trove.UserService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.trove.Database;
import org.openstack4j.model.trove.DatabaseUser;
import org.openstack4j.openstack.trove.domain.TroveDatabase.Databases;
import org.openstack4j.openstack.trove.domain.TroveDatabaseUser.DatabaseUsers;

/**
 * User API Implementation
 *
 * @author sumit gandhi
 */
public class DBUserServiceImpl extends BaseTroveServices implements UserService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends DatabaseUser> list(String instanceId) {
        return get(DatabaseUsers.class, uri("/instances/%s/users", instanceId)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Database> listUserDatabases(String instanceId, String userName) {
        return get(Databases.class, uri("/instances/%s/users/%s/databases", instanceId, userName)).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse create(String instanceId, DatabaseUsers databaseUsers) {
        return post(ActionResponse.class, uri("/instances/%s/users", instanceId)).entity(databaseUsers).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String instanceId, String userName) {
        Objects.requireNonNull(instanceId);
        Objects.requireNonNull(userName);
        return deleteWithResponse(uri("/instances/%s/users/%s", instanceId, userName)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse grantUserDBAccess(String instanceId, String userName, Databases databases) {
        Objects.requireNonNull(instanceId);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(databases);
        return put(ActionResponse.class, uri("/instances/%s/users/%s/databases", instanceId, userName)).entity(databases).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse revokeUserDBAccess(String instanceId, String userName, String dbName) {
        Objects.requireNonNull(instanceId);
        Objects.requireNonNull(userName);
        Objects.requireNonNull(dbName);
        return deleteWithResponse(uri("/instances/%s/users/%s/databases/%s", instanceId, userName, dbName)).execute();
    }

}

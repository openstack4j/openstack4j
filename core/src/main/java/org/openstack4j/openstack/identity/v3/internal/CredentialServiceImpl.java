package org.openstack4j.openstack.identity.v3.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.identity.v3.CredentialService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.identity.v3.Credential;
import org.openstack4j.openstack.identity.v3.domain.KeystoneCredential;
import org.openstack4j.openstack.identity.v3.domain.KeystoneCredential.Credentials;

import static org.openstack4j.core.transport.ClientConstants.PATH_CREDENTIALS;

public class CredentialServiceImpl extends BaseIdentityServices implements CredentialService {

    @Override
    public Credential create(Credential credential) {
        Objects.requireNonNull(credential);
        return post(KeystoneCredential.class, uri(PATH_CREDENTIALS)).entity(credential).execute();
    }

    @Override
    public Credential create(String blob, String type, String projectId, String userId) {
        Objects.requireNonNull(blob);
        Objects.requireNonNull(type);
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(userId);
        return create(KeystoneCredential.builder().blob(blob).type(type).projectId(projectId).userId(userId).build());
    }

    @Override
    public Credential get(String credentialId) {
        Objects.requireNonNull(credentialId);
        return get(KeystoneCredential.class, PATH_CREDENTIALS, "/", credentialId).execute();
    }

    @Override
    public Credential update(Credential credential) {
        Objects.requireNonNull(credential);
        return patch(KeystoneCredential.class, PATH_CREDENTIALS, "/", credential.getId()).entity(credential).execute();
    }

    @Override
    public ActionResponse delete(String credentialId) {
        Objects.requireNonNull(credentialId);
        return deleteWithResponse(PATH_CREDENTIALS, "/", credentialId).execute();
    }

    @Override
    public List<? extends Credential> list() {
        return get(Credentials.class, uri(PATH_CREDENTIALS)).execute().getList();
    }

}

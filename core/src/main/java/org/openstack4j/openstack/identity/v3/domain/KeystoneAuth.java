package org.openstack4j.openstack.identity.v3.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.AuthStore;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.v3.Authentication;
import org.openstack4j.openstack.common.Auth.Type;
import org.openstack4j.openstack.common.BasicResourceEntity;
import org.openstack4j.openstack.common.IdResourceEntity;

/**
 * Represents an v3 Auth object.
 */
@JsonRootName("auth")
public class KeystoneAuth implements Authentication, AuthStore {

    private static final long serialVersionUID = 1L;
    @JsonProperty
    private AuthIdentity identity;
    private AuthScope scope;
    @JsonIgnore
    private transient Type type;

    public KeystoneAuth() {
        super();
    }

    public KeystoneAuth(String tokenId) {
        this.identity = AuthIdentity.createTokenType(tokenId);
        this.type = Type.TOKEN;
    }

    public KeystoneAuth(String tokenId, AuthScope scope) {
        this.identity = AuthIdentity.createTokenType(tokenId);
        this.scope = scope;
        this.type = Type.TOKEN;
    }

    public KeystoneAuth(String userId, String password) {
        this(userId, password, null, null);
    }

    public KeystoneAuth(String user, String password, Identifier domain, AuthScope scope) {
        this.identity = AuthIdentity.createCredentialType(user, password, domain);
        this.scope = scope;
        this.type = Type.CREDENTIALS;
    }

    public KeystoneAuth(AuthScope scope, Type type) {
        this.scope = scope;
        this.type = type;
    }

    protected KeystoneAuth(Type type) {
        this.type = type;
    }

    public static KeystoneAuth applicationCredential(String id, String secret) {
        return applicationCredential(new ApplicationCredential(requireValue(id, "Application credential ID"),
                null, requireValue(secret, "Application credential secret"), null));
    }

    public static KeystoneAuth applicationCredential(String name, String secret, Identifier user, Identifier domain) {
        Objects.requireNonNull(user, "Application credential owner is required");
        if (!user.isTypeID()) {
            Objects.requireNonNull(domain, "The owner's domain is required when using a user name");
            requireValue(domain.getId(), "The owner's domain");
        }
        AuthIdentity.AuthPassword.AuthUser owner = new AuthIdentity.AuthPassword.AuthUser(
                requireValue(user.getId(), "Application credential owner"), null, user.isTypeID() ? null : domain);
        return applicationCredential(new ApplicationCredential(null, requireValue(name, "Application credential name"),
                requireValue(secret, "Application credential secret"), owner));
    }

    private static KeystoneAuth applicationCredential(ApplicationCredential credential) {
        KeystoneAuth auth = new KeystoneAuth(Type.APPLICATION_CREDENTIAL);
        auth.identity = new AuthIdentity();
        auth.identity.methods.add("application_credential");
        auth.identity.applicationCredential = credential;
        return auth;
    }

    private static String requireValue(String value, String name) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is required");
        }
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        AuthIdentity.AuthPassword.AuthUser user = passwordUser();
        return user == null ? null : user.getName();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        AuthIdentity.AuthPassword.AuthUser user = passwordUser();
        return user == null ? null : user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getId() {
        AuthIdentity.AuthPassword.AuthUser user = passwordUser();
        return user == null || user.getDomain() == null ? null : user.getDomain().getId();
    }

    @Override
    @JsonIgnore
    public String getName() {
        AuthIdentity.AuthPassword.AuthUser user = passwordUser();
        return user == null || user.getDomain() == null ? null : user.getDomain().getName();
    }

    private AuthIdentity.AuthPassword.AuthUser passwordUser() {
        return identity == null || identity.password == null ? null : identity.password.user;
    }

    @JsonIgnore
    @Override
    public AuthVersion getVersion() {
        return AuthVersion.V3;
    }

    public static final class AuthIdentity implements Identity, Serializable {

        private static final long serialVersionUID = 1L;

        private AuthPassword password;
        private AuthToken token;
        @JsonProperty("application_credential")
        private ApplicationCredential applicationCredential;
        private List<String> methods = new ArrayList<>();

        static AuthIdentity createTokenType(String tokenId) {
            AuthIdentity identity = new AuthIdentity();
            identity.methods.add("token");
            identity.token = new AuthToken(tokenId);
            return identity;
        }

        static AuthIdentity createCredentialType(String username, String password) {
            return createCredentialType(username, password, null);
        }

        static AuthIdentity createCredentialType(String username, String password, Identifier domain) {
            AuthIdentity identity = new AuthIdentity();
            identity.password = new AuthPassword(username, password, domain);
            identity.methods.add("password");
            return identity;
        }

        @Override
        public Password getPassword() {
            return password;
        }

        @Override
        public Token getToken() {
            return token;
        }

        @Override
        public List<String> getMethods() {
            return methods;
        }

        public static final class AuthToken implements Token, Serializable {

            private static final long serialVersionUID = 1L;

            @JsonProperty
            private String id;

            AuthToken() {
            }

            AuthToken(String id) {
                this.id = id;
            }

            @Override
            public String getId() {
                return id;
            }
        }

        public static final class AuthPassword implements Password, Serializable {

            private static final long serialVersionUID = 1L;

            private AuthUser user;

            public AuthPassword() {
            }

            public AuthPassword(String username, String password, Identifier domain) {
                this.user = new AuthUser(username, password, domain);
            }

            @Override
            public User getUser() {
                return user;
            }

            public static final class AuthUser extends BasicResourceEntity implements User {

                private static final long serialVersionUID = 1L;

                private AuthDomain domain;
                private String password;

                public AuthUser() {
                }

                public AuthUser(String username, String password, Identifier domainIdentifier) {
                    this.password = password;
                    if (domainIdentifier != null) {
                        domain = new AuthDomain();
                        if (domainIdentifier.isTypeID())
                            domain.setId(domainIdentifier.getId());
                        else
                            domain.setName(domainIdentifier.getId());
                        setName(username);
                    } else
                        setId(username);
                }

                @Override
                public Domain getDomain() {
                    return domain;
                }

                @Override
                public String getPassword() {
                    return password;
                }

                public static final class AuthDomain extends BasicResourceEntity implements Domain {

                    private static final long serialVersionUID = 1L;

                }
            }
        }
    }

    private static final class ApplicationCredential implements Serializable {

        private static final long serialVersionUID = 1L;

        @JsonProperty
        private final String id;
        @JsonProperty
        private final String name;
        @JsonProperty
        private final String secret;
        @JsonProperty
        private final AuthIdentity.AuthPassword.AuthUser user;

        private ApplicationCredential(String id, String name, String secret, AuthIdentity.AuthPassword.AuthUser user) {
            this.id = id;
            this.name = name;
            this.secret = secret;
            this.user = user;
        }
    }

    public static final class AuthScope implements Scope, Serializable {

        private static final long serialVersionUID = 1L;

        @JsonProperty("project")
        private ScopeProject project;

        @JsonProperty("domain")
        private AuthDomain domain;

        @JsonProperty("OS-TRUST:trust")
        private ScopeTrust trust;

        public AuthScope(ScopeProject project) {
            this.project = project;
        }

        public AuthScope(AuthDomain domain) {
            this.domain = domain;
        }

        public AuthScope(ScopeTrust trust) {
            this.trust = trust;
        }

        public static AuthScope project(Identifier project, Identifier domain) {
            return new AuthScope(ScopeProject.create(project, domain));
        }

        public static AuthScope project(Identifier project) {
            return new AuthScope(ScopeProject.create(project));
        }

        public static AuthScope domain(Identifier domain) {
            Objects.requireNonNull(domain, "Domain Scope: domain identifier or name cannot be null");
            return new AuthScope(new AuthDomain(domain));
        }

        public static AuthScope trust(String id) {
            Objects.requireNonNull(id, "Trust Scope: trust id cannot be null");
            return new AuthScope(new ScopeTrust(id));
        }

        @Override
        public Project getProject() {
            return project;
        }

        @Override
        public Domain getDomain() {
            return domain;
        }

        public static final class ScopeProject extends BasicResourceEntity implements Project {

            private static final long serialVersionUID = 1L;
            private AuthDomain domain;
            @JsonProperty
            private String id;
            @JsonProperty
            private String name;

            public static ScopeProject create(Identifier project) {
                Objects.requireNonNull(project, "Project Scope: project identifier or name cannot be null");

                ScopeProject scope = new ScopeProject();

                if (project.isTypeID()) {
                    scope.id = project.getId();
                }
                return scope;

            }

            public static ScopeProject create(Identifier project, Identifier domain) {
                Objects.requireNonNull(project, "Project Scope: project identifier or name cannot be null");
                Objects.requireNonNull(domain, "Project Scope: domain identifier or name cannot be null");

                ScopeProject scope = new ScopeProject();
                scope.domain = new AuthDomain(domain);
                if (project.isTypeID()) {
                    scope.id = project.getId();
                } else {
                    scope.name = project.getId();
                }
                return scope;
            }

            @Override
            public Domain getDomain() {
                return domain;
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public static final class AuthDomain extends BasicResourceEntity implements Domain {

            private static final long serialVersionUID = 1L;

            @JsonProperty
            private String id;
            @JsonProperty
            private String name;

            public AuthDomain(Identifier domain) {
                if (domain.isTypeID())
                    this.id = domain.getId();
                else
                    this.name = domain.getId();
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public static final class ScopeTrust extends IdResourceEntity {

            private static final long serialVersionUID = 1L;

            public ScopeTrust(String id) {
                this.setId(id);
            }

        }

    }
}

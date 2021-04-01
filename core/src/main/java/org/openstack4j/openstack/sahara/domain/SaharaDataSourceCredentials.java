package org.openstack4j.openstack.sahara.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.sahara.DataSourceCredentials;

/**
 * For mapping JSON response to/from java objects
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
@JsonRootName("credentials")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaharaDataSourceCredentials implements DataSourceCredentials {

    private static final long serialVersionUID = 1L;

    private String password;
    private String user;

    SaharaDataSourceCredentials(String user, String password) {
        this.user = user;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUser() {
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("password", password)
                .add("user", user)
                .toString();
    }

}

package org.openstack4j.openstack.tacker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.util.ToStringHelper;

/**
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
@JsonRootName("attributes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VnfUpdateAttributes {

    private String config;

    public static VnfUpdateAttributes create() {
        return new VnfUpdateAttributes();
    }

    /**
     * User config file or data..
     *
     * @return VnfUpdateAttributes
     */
    public VnfUpdateAttributes config(String config) {
        this.config = config;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("config", config)
                .toString();
    }

    /**
     * @return the config
     */
    public String getConfig() {
        return config;
    }
}

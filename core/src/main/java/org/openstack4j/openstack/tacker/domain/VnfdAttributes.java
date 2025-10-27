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
public class VnfdAttributes {

    private String vnfd;

    public static VnfdAttributes create() {
        return new VnfdAttributes();
    }

    /**
     * VNFD Template to Set..
     *
     * @param vnfd the vnfd template to set
     * @return VnfdAttributes
     */
    public VnfdAttributes vnfd(String vnfd) {
        this.vnfd = vnfd;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("vnfd", vnfd)
                .toString();
    }

    /**
     * @return the vnfd
     */
    public String getVnfd() {
        return vnfd;
    }
}

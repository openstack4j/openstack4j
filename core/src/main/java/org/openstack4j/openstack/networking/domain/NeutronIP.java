package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.network.IP;

/**
 * A Fixed IP Address
 *
 * @author Jeremy Unruh
 */
public class NeutronIP implements IP {

    private static final long serialVersionUID = 1L;

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("subnet_id")
    private String subnetId;

    public NeutronIP() {
    }

    public NeutronIP(String address, String subnetId) {
        this.ipAddress = address;
        this.subnetId = subnetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSubnetId() {
        return subnetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this).add("ipAddress", ipAddress).add("subnetId", subnetId).toString();
    }

}

package org.openstack4j.openstack.manila.domain.actions;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Actions to force down/force up manila services
 */
public class ServiceActions implements ModelEntity {
    private String binary;
    private String host;
    @JsonProperty("forced_down")
    public boolean isForcedDown;

    private ServiceActions(String binary, String host, boolean isForcedDown) {
        this.binary = binary;
        this.host = host;
        this.isForcedDown = isForcedDown;
    }

    public static ServiceActions forceUp(String binary, String host) {
        return new ServiceActions(binary, host, false);
    }

    public static ServiceActions forceDown(String binary, String host) {
        return new ServiceActions(binary, host, true);
    }

    public String getBinary() {
        return binary;
    }

    public String getHost() {
        return host;
    }

    @JsonProperty("forced_down")
    public boolean isForcedDown() {
        return isForcedDown;
    }
}

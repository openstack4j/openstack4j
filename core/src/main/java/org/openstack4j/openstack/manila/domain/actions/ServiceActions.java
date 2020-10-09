package org.openstack4j.openstack.manila.domain.actions;

import org.openstack4j.model.ModelEntity;

/**
 * Actions to force down/force up manila services
 */
public class ServiceActions implements ModelEntity {
    private String binary;
    private String host;
    private boolean forced_down;

    private ServiceActions(String binary, String host, boolean forced_down) {
        this.binary = binary;
        this.host = host;
        this.forced_down = forced_down;
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

    public boolean isForced_down() {
        return forced_down;
    }
}

package org.openstack4j.model.murano.v1.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * @author Nikolay Mahotkin.
 */
public interface ServiceInfo extends ModelEntity {
    /**
     * @return service id.
     */
    String getId();

    /**
     * @return service name.
     */
    String getName();

    /**
     * @return service type(class) name.
     */
    String getType();

    /**
     * @return action list for current service (if available).
     */
    List<? extends ActionInfo> getActions();

    /**
     * @return service status.
     */
    String getStatus();
}

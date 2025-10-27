package org.openstack4j.model.murano.v1.domain;

import java.util.Map;

import org.openstack4j.model.ModelEntity;

/**
 * @author Nikolay Mahotkin.
 */
public interface Application extends ModelEntity {
    /**
     * @return data in map format (Map<String, Object>).
     */
    Map<String, Object> getData();

    /**
     * @return service internal info
     */
    ServiceInfo getService();
}

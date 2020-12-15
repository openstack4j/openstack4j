package org.openstack4j.api.senlin;


import java.util.List;

import org.openstack4j.model.senlin.Version;

/**
 * This interface defines all methods for the manipulation of version
 *
 * @author lion
 */
public interface SenlinVersionService {

    /**
     * Service implementation which provides methods for manipulation of version
     *
     * @return Version
     */
    List<? extends Version> list();

}

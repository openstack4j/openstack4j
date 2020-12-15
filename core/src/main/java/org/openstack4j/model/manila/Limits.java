package org.openstack4j.model.manila;

import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * Limits are the resource limitations that are allowed for each tenant (project).
 *
 * @author Daniel Gonzalez Nothnagel
 */
public interface Limits extends ModelEntity {
    /**
     * @return the rate limits
     */
    List<? extends RateLimit> getRate();

    /**
     * @return the absolute limits
     */
    AbsoluteLimit getAbsolute();
}

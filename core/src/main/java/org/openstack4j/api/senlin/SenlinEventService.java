package org.openstack4j.api.senlin;

import java.util.List;

import org.openstack4j.model.senlin.Event;

/**
 * This interface defines all methods for the manipulation of Event
 *
 * @author lion
 */
public interface SenlinEventService {

    /**
     * Gets a list of currently existing {@link Event}s.
     *
     * @return the list of {@link Event}s
     */
    List<? extends Event> list();

    /**
     * returns details of a {@link Event}.
     *
     * @param eventID Id of {@link Event}
     * @return Event
     */
    Event get(String eventID);
}

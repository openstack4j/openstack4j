package org.openstack4j.model.telemetry;

import java.util.List;

import org.openstack4j.model.ModelEntity;

/**
 * Event represents the state of an object in an OpenStack service
 * at a point in time when something of interest has occurred
 *
 * @author Miroslav Lacina
 */
public interface Event extends ModelEntity {

    /**
     * @return a dotted string defining what event occurred
     */
    String getEventType();

    /**
     * @return a timestamp of when the event occurred on the source system
     */
    String getGenerated();

    /**
     * @return a UUID for this event
     */
    String getMessageId();

    /**
     * @return event’s Traits which contain most of the details of the event
     */
    List<? extends Trait> getTraits();

}

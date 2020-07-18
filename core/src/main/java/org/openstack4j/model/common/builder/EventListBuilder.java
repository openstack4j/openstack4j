package org.openstack4j.model.common.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.EventList;

/**
 * Builder which create events
 *
 * @author sujit sah
 *
 */
public interface EventListBuilder extends Buildable.Builder<EventListBuilder, EventList> {
    /**
     * @see EventList#getEvent()
     */
    EventListBuilder event(String event);

    /**
     * @see EventList#getFinishTime()
     */
    EventListBuilder finishTime(String finish_time);

    /**
     * @see EventList#getResult()
     */
    EventListBuilder result(String result);

    /**
     * @see EventList#getStartTime()
     */
    EventListBuilder startTime(String start_time);

    /**
     * @see EventList#getTraceback()
     */
    EventListBuilder traceback(String traceback);
}

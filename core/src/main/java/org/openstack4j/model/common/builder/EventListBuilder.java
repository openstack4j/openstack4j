package org.openstack4j.model.common.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.ServerActionEvent;

/**
 * Builder which create events
 *
 * @author sujit sah
 *
 */
public interface EventListBuilder extends Buildable.Builder<EventListBuilder, ServerActionEvent> {
    /**
     * @see ServerActionEvent#getEvent()
     */
    EventListBuilder event(String event);

    /**
     * @see ServerActionEvent#getFinishTime()
     */
    EventListBuilder finishTime(String finish_time);

    /**
     * @see ServerActionEvent#getResult()
     */
    EventListBuilder result(String result);

    /**
     * @see ServerActionEvent#getStartTime()
     */
    EventListBuilder startTime(String start_time);

    /**
     * @see ServerActionEvent#getTraceback()
     */
    EventListBuilder traceback(String traceback);
}

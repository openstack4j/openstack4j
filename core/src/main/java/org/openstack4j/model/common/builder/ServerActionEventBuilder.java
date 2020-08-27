package org.openstack4j.model.common.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.compute.ServerActionEvent;

/**
 * Builder which create events
 *
 * @author sujit sah
 *
 */
public interface ServerActionEventBuilder extends Buildable.Builder<ServerActionEventBuilder, ServerActionEvent> {
    /**
     * @see ServerActionEvent#getEvent()
     */
    ServerActionEventBuilder event(String event);

    /**
     * @see ServerActionEvent#getFinishTime()
     */
    ServerActionEventBuilder finishTime(String finish_time);

    /**
     * @see ServerActionEvent#getResult()
     */
    ServerActionEventBuilder result(String result);

    /**
     * @see ServerActionEvent#getStartTime()
     */
    ServerActionEventBuilder startTime(String start_time);

    /**
     * @see ServerActionEvent#getTraceback()
     */
    ServerActionEventBuilder traceback(String traceback);
}

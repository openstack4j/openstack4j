package org.openstack4j.model.compute;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.EventList;

/**
 * This interface describes the getter-methods (and thus components) of a Event.
 * All getters map to the possible return values of
 * <code> GET /v2.1/servers/{server_id}/os-instance-actions​</code>
 * <code> GET /v2.1/servers/{server_id}/os-instance-actions/{request_id}​</code>
 *
 * @see https://docs.openstack.org/api-ref/compute/index.html
 * @author sujit sah
 *
 */
public interface Event extends ModelEntity {
    /**
     * Returns the action of the event
     *
     * @return the action of the event
     */
    public String getAction();

    /**
     * Returns the list of event
     *
     * @return the list of event
     */
    public List<? extends EventList> getEvents();

    /**
     * Returns the id of the server
     *
     * @return the id of the server
     */
    public String getInstaceUuid();

    /**
     * Returns the request id of the event
     *
     * @return the request id of the event
     */
    public String getRequestId();

    /**
     * Returns the start_time of the event
     *
     * @return the start_time of the event
     */
    public Date getStartTime();

    /**
     * Returns the id of the user
     *
     * @return the id of user
     */
    public String getUserId();
}

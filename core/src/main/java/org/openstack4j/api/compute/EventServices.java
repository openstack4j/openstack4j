package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.Event;

/**
 * This interface defines all methods for the manipulation of events
 *
 * @author sujit sah
 *
 */
public interface EventServices extends RestService {
    /**
     * Gets a list of currently existing {@link Event}s for a specified server.
     *
     * @param serverId the id of server
     * @return the list of {@link Event}s
     */
    List<? extends Event> list(String serverId);

    /**
     * Gets a event of currently existing {@link Event}s for a specified request id
     *
     * @param serverId  the id of server
     * @param requestId the request id of server
     * @return the server event detail
     */
    Event show(String serverId, String requestId);

}

package org.openstack4j.api.compute;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.compute.ServerAction;

/**
 * This interface defines all methods for the manipulation of events
 *
 * @author sujit sah
 *
 */
public interface ServerActionsService extends RestService {
    /**
     * Gets a list of currently existing {@link ServerAction}s for a specified server.
     *
     * @param serverId the id of server
     * @return the list of {@link ServerAction}s
     */
    List<? extends ServerAction> list(String serverId);

    /**
     * Gets a event of currently existing {@link ServerAction}s for a specified request id
     *
     * @param serverId  the id of server
     * @param requestId the request id of server
     * @return the server event detail
     */
    ServerAction show(String serverId, String requestId);

}

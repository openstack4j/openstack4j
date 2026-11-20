package org.openstack4j.openstack.compute.functions;

import java.util.function.Function;

import org.openstack4j.core.transport.HttpEntityHandler;
import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.core.transport.functions.ResponseToActionResponse;
import org.openstack4j.model.common.ActionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Function which consumes the input of an HttpResponse and returns the
 * corresponding ActionResponse
 *
 * @author Jeremy Unruh
 */
public class ToActionResponseFunction implements Function<HttpResponse, ActionResponse> {

    public static final ToActionResponseFunction INSTANCE = new ToActionResponseFunction();
    private static final Logger LOG = LoggerFactory.getLogger(ToActionResponseFunction.class);
    private static final String FAILED_MSG = "Cannot '%s' while instance in in state of %s";

    @Override
    public ActionResponse apply(HttpResponse response) {
        return apply(response, null);
    }

    public ActionResponse apply(HttpResponse response, String action) {
        try {
            int status = response.getStatus();
            if (status < 400) {
                return ActionResponse.actionSuccess();
            }
            boolean legacyConflict = status == 409 || status == 500;
            try {
                ActionResponse error = ResponseToActionResponse.INSTANCE.apply(response, legacyConflict);
                if (error != null) {
                    return error;
                }
            } catch (RuntimeException e) {
                LOG.debug("Unreadable error body for status {}", status);
                return ActionResponse.actionFailed(response.getStatusMessage(), status);
            }
            LOG.error("{}, {}", status, response.getStatusMessage());
            return ActionResponse.actionFailed(action == null ? "Instance currently is in build state"
                    : String.format(FAILED_MSG, action, action), 409);
        } finally {
            HttpEntityHandler.closeQuietly(response);
        }
    }

}

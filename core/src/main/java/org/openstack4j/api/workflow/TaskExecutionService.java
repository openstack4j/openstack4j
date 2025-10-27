package org.openstack4j.api.workflow;

import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.workflow.TaskExecution;

/**
 * Service that provides CRUD operations for task executions.
 *
 * @author Renat Akhmerov
 */
public interface TaskExecutionService extends RestService {
    /**
     * List all task executions with details.
     *
     * @return List of task executions.
     */
    List<? extends TaskExecution> list();

    /**
     * Get task execution by its ID.
     *
     * @param id Task execution ID.
     * @return Task execution.
     */
    TaskExecution get(String id);
}

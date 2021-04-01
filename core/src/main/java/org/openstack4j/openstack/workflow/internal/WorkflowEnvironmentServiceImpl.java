package org.openstack4j.openstack.workflow.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.workflow.WorkflowEnvironmentService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.workflow.WorkflowEnvironment;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowEnvironment;
import org.openstack4j.openstack.workflow.domain.MistralWorkflowEnvironment.MistralWorkflowEnvironments;

/**
 * Workflow environment service implementation.
 *
 * @author Renat Akhmerov
 */
public class WorkflowEnvironmentServiceImpl extends BaseMistralService implements WorkflowEnvironmentService {

    @Override
    public List<? extends WorkflowEnvironment> list() {
        return get(MistralWorkflowEnvironments.class, uri("/environments")).execute().getList();
    }

    @Override
    public WorkflowEnvironment create(WorkflowEnvironment env) {
        Objects.requireNonNull(env);

        Invocation<MistralWorkflowEnvironment> invocation = post(
                MistralWorkflowEnvironment.class,
                uri("/environments")
        );

        return invocation.entity(env).execute();
    }

    @Override
    public WorkflowEnvironment get(String identifier) {
        return get(MistralWorkflowEnvironment.class, uri("/environments/%s", identifier)).execute();
    }

    @Override
    public ActionResponse delete(String identifier) {
        return deleteWithResponse(uri("/environments/%s", identifier)).execute();
    }
}

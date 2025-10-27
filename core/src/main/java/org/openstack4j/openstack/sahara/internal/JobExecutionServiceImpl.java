package org.openstack4j.openstack.sahara.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.sahara.JobExecutionService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.JobExecution;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecution.JobExecutions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExecutionUnwrapped;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class JobExecutionServiceImpl extends BaseSaharaServices implements JobExecutionService {

    @Override
    public JobExecution create(JobExecution jobExecution) {
        Objects.requireNonNull(jobExecution);
        SaharaJobExecutionUnwrapped unwrapped = new SaharaJobExecutionUnwrapped(jobExecution);
        return post(SaharaJobExecution.class, uri("/jobs/%s/execute", jobExecution.getJobIdForExecution()))
                .entity(unwrapped)  // setup request
                .execute();
    }

    @Override
    public List<? extends JobExecution> list() {
        return get(JobExecutions.class, uri("/job-executions")).execute().getList();
    }

    @Override
    public JobExecution get(String jobExecutionId) {
        Objects.requireNonNull(jobExecutionId);
        return get(SaharaJobExecution.class, uri("/job-executions/%s", jobExecutionId)).execute();
    }

    @Override
    public JobExecution refreshStatus(String jobExecutionId) {
        Objects.requireNonNull(jobExecutionId);
        return get(SaharaJobExecution.class, uri("/job-executions/%s/refresh-status", jobExecutionId)).execute();
    }

    @Override
    public JobExecution cancel(String jobExecutionId) {
        Objects.requireNonNull(jobExecutionId);
        return get(SaharaJobExecution.class, uri("/job-executions/%s/cancel", jobExecutionId)).execute();
    }

    @Override
    public ActionResponse delete(String jobExecutionId) {
        Objects.requireNonNull(jobExecutionId);
        return deleteWithResponse(uri("/job-executions/%s", jobExecutionId)).execute();
    }
}

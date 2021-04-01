package org.openstack4j.openstack.compute.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.QuotaSetService;
import org.openstack4j.model.compute.Limits;
import org.openstack4j.model.compute.QuotaSet;
import org.openstack4j.model.compute.QuotaSetUpdate;
import org.openstack4j.model.compute.SimpleTenantUsage;
import org.openstack4j.openstack.compute.domain.NovaLimits;
import org.openstack4j.openstack.compute.domain.NovaQuotaSet;
import org.openstack4j.openstack.compute.domain.NovaQuotaSet.NovaQuotaSetClass;
import org.openstack4j.openstack.compute.domain.NovaQuotaSetUpdate.NovaQuotaSetUpdateClass;
import org.openstack4j.openstack.compute.domain.NovaQuotaSetUpdate.NovaQuotaSetUpdateTenant;
import org.openstack4j.openstack.compute.domain.NovaSimpleTenantUsage;
import org.openstack4j.openstack.compute.domain.NovaSimpleTenantUsage.NovaSimpleTenantUsages;

/**
 * OpenStack Quota-Set API Implementation
 *
 * @author Jeremy Unruh
 */
public class QuotaSetServiceImpl extends BaseComputeServices implements QuotaSetService {

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaSet get(String tenantId) {
        return get(tenantId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaSet get(String tenantId, String userId) {
        Objects.requireNonNull(tenantId);
        String uri = (userId != null) ? uri("/os-quota-sets/%s?user_id=%s", tenantId, userId): uri("/os-quota-sets/%s", tenantId);
        return get(NovaQuotaSet.class, uri).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaSet updateForClass(String classId, QuotaSetUpdate qs) {
        Objects.requireNonNull(classId);
        Objects.requireNonNull(qs);

        return put(NovaQuotaSetClass.class, uri("/os-quota-class-sets/%s", classId)).entity(NovaQuotaSetUpdateClass.from(qs)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuotaSet updateForTenant(String tenantId, QuotaSetUpdate qs) {
        Objects.requireNonNull(tenantId);
        Objects.requireNonNull(qs);

        return put(NovaQuotaSet.class, uri("/os-quota-sets/%s", tenantId)).entity(NovaQuotaSetUpdateTenant.from(qs)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Limits limits() {
        return get(NovaLimits.class, uri("/limits")).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SimpleTenantUsage> listTenantUsages() {
        return get(NovaSimpleTenantUsages.class, uri("/os-simple-tenant-usage")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleTenantUsage getTenantUsage(String tenantId) {
        Objects.requireNonNull(tenantId);
        return get(NovaSimpleTenantUsage.class, uri("/os-simple-tenant-usage/%s", tenantId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends SimpleTenantUsage> listTenantUsages(String startTime,
            String endTime) {
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        return get(NovaSimpleTenantUsages.class, uri("/os-simple-tenant-usage"))
                .param("start", startTime)
                .param("end", endTime)
                .execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleTenantUsage getTenantUsage(String tenantId, String startTime,
            String endTime) {
        Objects.requireNonNull(tenantId);
        Objects.requireNonNull(startTime);
        Objects.requireNonNull(endTime);
        return get(NovaSimpleTenantUsage.class, uri("/os-simple-tenant-usage/%s", tenantId))
                .param("start", startTime)
                .param("end", endTime)
                .param("detailed", "1")
                .execute();
    }
}

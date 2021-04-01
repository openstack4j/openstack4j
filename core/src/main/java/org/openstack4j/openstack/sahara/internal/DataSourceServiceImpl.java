package org.openstack4j.openstack.sahara.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.sahara.DataSourceService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.DataSource;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource;
import org.openstack4j.openstack.sahara.domain.SaharaDataSource.DataSources;
import org.openstack4j.openstack.sahara.domain.SaharaDataSourceUnwrapped;

/**
 * Sahara Data Processing Operations
 *
 * @author ekasit.kijsipongse@nectec.or.th
 * @author siwat.pru@outlook.com
 */
public class DataSourceServiceImpl extends BaseSaharaServices implements DataSourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends DataSource> list() {
        return get(DataSources.class, uri("/data-sources")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource get(String datasourceId) {
        Objects.requireNonNull(datasourceId);
        return get(SaharaDataSource.class, uri("/data-sources/%s", datasourceId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataSource create(DataSource datasource) {
        Objects.requireNonNull(datasource);
        SaharaDataSourceUnwrapped unwrapped = new SaharaDataSourceUnwrapped(datasource);
        return post(SaharaDataSource.class, uri("/data-sources"))
                .entity(unwrapped)  // setup request
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String datasourceId) {
        Objects.requireNonNull(datasourceId);
        return deleteWithResponse(uri("/data-sources/%s", datasourceId)).execute();
    }

}

package org.openstack4j.openstack.dns.v2.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.dns.v2.RecordsetService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.openstack.dns.v2.domain.DesignateRecordset;

import static org.openstack4j.core.transport.ClientConstants.PATH_RECORDSETS;
import static org.openstack4j.core.transport.ClientConstants.PATH_ZONES;

public class RecordsetServiceImpl extends BaseDNSServices implements RecordsetService {

    @Override
    public Recordset get(String zoneId, String recordsetId) {
        Objects.requireNonNull(zoneId);
        Objects.requireNonNull(recordsetId);
        return get(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS, "/", recordsetId).execute();
    }

    @Override
    public ActionResponse delete(String zoneId, String recordsetId) {
        Objects.requireNonNull(zoneId);
        Objects.requireNonNull(recordsetId);
        return deleteWithResponse(PATH_ZONES, "/", zoneId, PATH_RECORDSETS, "/", recordsetId).execute();
    }

    @Override
    public Recordset update(String zoneId, Recordset recordset) {
        Objects.requireNonNull(zoneId);
        Objects.requireNonNull(recordset);
        return put(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS, "/", recordset.getId()).entity(recordset).execute();
    }

    @Override
    public Recordset create(String zoneId, Recordset recordset) {
        Objects.requireNonNull(zoneId);
        Objects.requireNonNull(recordset);
        return post(DesignateRecordset.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS).entity(recordset).execute();
    }

    @Override
    public Recordset create(String zoneId, String name, String type, List<String> records) {
        Objects.requireNonNull(zoneId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
        Objects.requireNonNull(records);
        return create(zoneId, DesignateRecordset.builder().name(name).type(type).records(records).build());
    }

    @Override
    public List<? extends Recordset> list(String zoneId) {
        Objects.requireNonNull(zoneId);
        return get(DesignateRecordset.Recordsets.class, PATH_ZONES, "/", zoneId, PATH_RECORDSETS).execute().getList();
    }

    @Override
    public List<? extends Recordset> list() {
        return get(DesignateRecordset.Recordsets.class, uri(PATH_RECORDSETS)).execute().getList();
    }

}

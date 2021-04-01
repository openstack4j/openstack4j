package org.openstack4j.openstack.tacker.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.tacker.VnfService;
import org.openstack4j.core.transport.ExecutionOptions;
import org.openstack4j.core.transport.propagation.PropagateOnStatus;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.tacker.Vnf;
import org.openstack4j.model.tacker.VnfUpdate;
import org.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import org.openstack4j.openstack.tacker.domain.TackerVnf;
import org.openstack4j.openstack.tacker.domain.TackerVnf.TackerVnfs;

/**
 * @author Vishvesh Deshmukh
 * @date Aug 11, 2016
 */
public class VnfServiceImpl extends BaseTackerServices implements VnfService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends TackerVnf> list() {
        return get(TackerVnfs.class, uri("/vnfs")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends TackerVnf> list(Map<String, String> filteringParams) {
        Invocation<TackerVnfs> req = get(TackerVnfs.class, uri("/vnfs"));
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                req = req.param(entry.getKey(), entry.getValue());
            }
        }
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TackerVnf get(String vnfId) {
        Objects.requireNonNull(vnfId);
        return get(TackerVnf.class, uri("/vnfs/%s", vnfId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String vnfId) {
        Objects.requireNonNull(vnfId);
        return ToActionResponseFunction.INSTANCE.apply(delete(Void.class, uri("/vnfs/%s", vnfId)).executeWithResponse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vnf create(Vnf vnf) {
        return post(TackerVnf.class, uri("/vnfs")).entity(vnf).execute(ExecutionOptions.<TackerVnf>create(PropagateOnStatus.on(500)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vnf update(String vnfId, VnfUpdate vnfUpdate) {
        Objects.requireNonNull(vnfId);
        Objects.requireNonNull(vnfUpdate);
        return put(TackerVnf.class, uri("/vnfs/%s", vnfId)).entity(vnfUpdate).execute();
    }
}

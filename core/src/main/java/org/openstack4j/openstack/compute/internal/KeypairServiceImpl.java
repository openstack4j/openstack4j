package org.openstack4j.openstack.compute.internal;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import org.openstack4j.api.compute.KeypairService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Keypair;
import org.openstack4j.openstack.compute.domain.NovaKeypair;
import org.openstack4j.openstack.compute.domain.NovaKeypair.Keypairs;

/**
 * Keypair Service manages SSH Keys within OpenStack Compute (Nova).
 *
 * @author Jeremy Unruh
 */
public class KeypairServiceImpl extends BaseComputeServices implements KeypairService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Keypair> list() {
        return get(Keypairs.class, uri("/os-keypairs")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Keypair get(String name) {
        Objects.requireNonNull(name);
        return get(NovaKeypair.class, uri("/os-keypairs/%s", name)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String name) {
        Objects.requireNonNull(name);
        return deleteWithResponse(uri("/os-keypairs/%s", name)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Keypair create(String name, @Nullable String publicKey) {
        Objects.requireNonNull(name);
        return post(NovaKeypair.class, uri("/os-keypairs")).entity(NovaKeypair.create(name, publicKey)).execute();
    }

}

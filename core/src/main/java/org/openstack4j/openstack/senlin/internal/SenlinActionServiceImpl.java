package org.openstack4j.openstack.senlin.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.senlin.SenlinActionService;
import org.openstack4j.model.senlin.Action;
import org.openstack4j.openstack.senlin.domain.SenlinAction;

/**
 * This class contains getters for all implementation of the available action services
 *
 * @author lion
 */
public class SenlinActionServiceImpl extends BaseSenlinServices implements SenlinActionService {

    @Override
    public List<? extends Action> list() {
        return get(SenlinAction.Action.class, uri("/actions")).execute().getList();
    }

    @Override
    public Action get(String actionID) {
        Objects.requireNonNull(actionID);
        return get(SenlinAction.class, uri("/actions/%s", actionID)).execute();
    }
}

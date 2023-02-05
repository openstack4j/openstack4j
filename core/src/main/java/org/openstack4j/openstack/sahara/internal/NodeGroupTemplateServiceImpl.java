package org.openstack4j.openstack.sahara.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.sahara.NodeGroupTemplateService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.sahara.NodeGroupTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplate;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplate.NodeGroupTemplates;
import org.openstack4j.openstack.sahara.domain.SaharaNodeGroupTemplateUnwrapped;

/**
 * Sahara Data Processing Operations
 *
 * @author Ekasit Kijsipongse
 */
public class NodeGroupTemplateServiceImpl extends BaseSaharaServices implements NodeGroupTemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends NodeGroupTemplate> list() {
        return get(NodeGroupTemplates.class, uri("/node-group-templates")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeGroupTemplate get(String templateId) {
        Objects.requireNonNull(templateId);
        return get(SaharaNodeGroupTemplate.class, uri("/node-group-templates/%s", templateId)).execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public NodeGroupTemplate create(NodeGroupTemplate template) {
        Objects.requireNonNull(template);
        SaharaNodeGroupTemplateUnwrapped unwrapped = new SaharaNodeGroupTemplateUnwrapped(template);
        return post(SaharaNodeGroupTemplate.class, uri("/node-group-templates"))
                .entity(unwrapped)  // setup request
                .execute();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String templateId) {
        Objects.requireNonNull(templateId);
        return deleteWithResponse(uri("/node-group-templates/%s", templateId)).execute();
    }

}

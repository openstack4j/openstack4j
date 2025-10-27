package org.openstack4j.openstack.heat.internal;

import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.Builders;
import org.openstack4j.api.heat.TemplateService;
import org.openstack4j.model.heat.Template;
import org.openstack4j.model.heat.TemplateResponse;
import org.openstack4j.openstack.heat.domain.HeatTemplate;

/**
 * This class implements all methods for manipulation of {@link HeatTemplate}
 * objects. The non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html#stack-templates
 *
 * @author Matthias Reisser
 */
public class TemplateServiceImpl extends BaseHeatServices implements TemplateService {

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplate(String template) {
        Objects.requireNonNull(template);
        return validateTemplate(Builders.template().templateJson(template).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplateByURL(String templateURL) {
        Objects.requireNonNull(templateURL);
        return validateTemplate(Builders.template().templateURL(templateURL).build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TemplateResponse validateTemplate(Template template) {
        Objects.requireNonNull(template);

        try {
            post(String.class, uri("/validate")).entity(template).execute();
        } catch (RuntimeException re) {
            return TemplateResponse.fail(re.getMessage());
        }
        return TemplateResponse.success();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateAsString(String stackName, String stackId) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        return get(String.class, uri("/stacks/%s/%s/template", stackName, stackId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTemplateAsMap(String stackName, String stackId) {
        Objects.requireNonNull(stackName);
        Objects.requireNonNull(stackId);
        return get(Map.class, uri("/stacks/%s/%s/template", stackName, stackId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getTemplateAsMap(String stackNameOrId) {
        Objects.requireNonNull(stackNameOrId);
        return get(Map.class, uri("/stacks/%s/template", stackNameOrId)).execute();
    }


}

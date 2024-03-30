package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.ModelEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author bboyHan
 */
public class NeutronNetQosPolicyRuleTag implements ModelEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String... tags) {
        this.tags.addAll(Arrays.asList(tags));
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("tags", tags).toString();
    }

}

package org.openstack4j.openstack.networking.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.ModelEntity;

/**
 * Neutron Resource Tags Entity
 *
 * @author bboyHan
 */
public class NeutronResourceTag implements ModelEntity {

    private static final long serialVersionUID = 1L;

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
        return new ToStringHelper(this).add("tags", tags).toString();
    }

}

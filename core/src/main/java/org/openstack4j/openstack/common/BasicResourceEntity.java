package org.openstack4j.openstack.common;

import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.common.BasicResource;

/**
 * Basic Id/Name based Entity Model implementation
 *
 * @author Jeremy Unruh
 */
public class BasicResourceEntity extends IdResourceEntity implements BasicResource {

    private static final long serialVersionUID = 1L;

    private String name;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringHelper(getClass())
                .add("id", getId()).add("name", name)
                .toString();
    }
}

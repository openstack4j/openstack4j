package org.openstack4j.openstack.compute.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.openstack4j.model.ModelEntity;

/**
 * set metadata for an aggregate.
 *
 * @author liujunpeng
 */
@JsonRootName("set_metadata")
public class HostAggregateMetadata implements ModelEntity {

    private static final long serialVersionUID = 1L;

    Map<String, String> metadata;

    /**
     * @param metadata for hostAggregateMetadata
     */
    public HostAggregateMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public HostAggregateMetadata() {
    }

    @JsonInclude
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}

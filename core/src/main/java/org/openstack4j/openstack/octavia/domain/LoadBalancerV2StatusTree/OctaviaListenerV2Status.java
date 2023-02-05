package org.openstack4j.openstack.octavia.domain.LoadBalancerV2StatusTree;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.util.ToStringHelper;
import org.openstack4j.model.octavia.status.LbPoolV2Status;
import org.openstack4j.model.octavia.status.ListenerV2Status;

/**
 * An object to hold status of lbaas v2 listener
 *
 * @author wei
 */
@JsonRootName("listeners")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OctaviaListenerV2Status extends Status implements ListenerV2Status {

    @JsonProperty("pools")
    List<LbPoolV2Status> lbPoolStatuses;
    @JsonProperty("name")
    private String name;

    @Override
    public List<LbPoolV2Status> getLbPoolV2Statuses() {
        return lbPoolStatuses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("pools", lbPoolStatuses)
                .add("operatingStatus", operatingStatus)
                .add("provisioningStatus", provisioningStatus)
                .toString();
    }
}

package org.openstack4j.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.network.builder.ExtraDhcpOptBuilder;

/**
 * @author Ales Kemr
 */
public interface ExtraDhcpOptCreate extends Buildable<ExtraDhcpOptBuilder> {

    @JsonProperty("opt_value")
    String getOptValue();

    @JsonProperty("opt_name")
    String getOptName();

    @JsonProperty("ip_version")
    default Integer getIpVersion() {
        return null;
    }
}

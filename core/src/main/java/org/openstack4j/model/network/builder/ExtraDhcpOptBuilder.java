package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.ExtraDhcpOptCreate;

/**
 * @author Ales Kemr
 */
public interface ExtraDhcpOptBuilder extends Builder<ExtraDhcpOptBuilder, ExtraDhcpOptCreate> {

    ExtraDhcpOptBuilder optValue(String optValue);

    ExtraDhcpOptBuilder optName(String optName);

    default ExtraDhcpOptBuilder ipVersion(Integer ipVersion) {
        throw new UnsupportedOperationException("DHCP option IP version is not supported by this builder");
    }

}

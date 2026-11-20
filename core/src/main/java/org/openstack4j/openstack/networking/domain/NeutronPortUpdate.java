package org.openstack4j.openstack.networking.domain;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.network.AllowedAddressPair;
import org.openstack4j.model.network.ExtraDhcpOptCreate;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.Port;

@JsonRootName("port")
public class NeutronPortUpdate implements ModelEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private final String name;
    @JsonProperty("admin_state_up")
    private final boolean adminStateUp;
    @JsonProperty("device_id")
    private final String deviceId;
    @JsonProperty("device_owner")
    private final String deviceOwner;
    @JsonProperty("fixed_ips")
    private final Set<? extends IP> fixedIps;
    @JsonProperty("allowed_address_pairs")
    private final Set<? extends AllowedAddressPair> allowedAddressPairs;
    @JsonProperty("trunkport:type")
    private final String trunkPortType;
    @JsonProperty("trunkport:parent_id")
    private final String trunkPortParentId;
    @JsonProperty("trunkport:vid")
    private final String trunkPortVlanId;
    @JsonProperty("security_groups")
    private final List<String> securityGroups;
    @JsonProperty("extra_dhcp_opts")
    private final List<? extends ExtraDhcpOptCreate> extraDhcpOpts;
    @JsonProperty("port_security_enabled")
    private final Boolean portSecurityEnabled;
    @JsonProperty("binding:host_id")
    private final String hostId;
    @JsonProperty("binding:vnic_type")
    private final String vNicType;
    @JsonProperty("binding:profile")
    private final Map<String, Object> profile;

    public NeutronPortUpdate(Port port) {
        name = port.getName();
        adminStateUp = port.isAdminStateUp();
        deviceId = port.getDeviceId();
        deviceOwner = port.getDeviceOwner();
        fixedIps = port.getFixedIps();
        allowedAddressPairs = port.getAllowedAddressPairs();
        trunkPortType = port.getTrunkPortType();
        trunkPortParentId = port.getTrunkPortParentId();
        trunkPortVlanId = port.getTrunkPortVlanId();
        securityGroups = port.getSecurityGroups();
        extraDhcpOpts = port.getExtraDhcpOpts();
        portSecurityEnabled = port.isPortSecurityEnabled();
        hostId = port.getHostId();
        vNicType = port.getvNicType();
        profile = port instanceof NeutronPort && !((NeutronPort) port).isProfileRequested() ? null : port.getProfile();
    }
}

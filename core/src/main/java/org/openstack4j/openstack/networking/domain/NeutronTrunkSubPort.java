package org.openstack4j.openstack.networking.domain;

import java.util.List;
import java.util.Objects;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.builder.ResourceBuilder;
import org.openstack4j.model.network.TrunkSubPort;
import org.openstack4j.model.network.builder.TrunkSubPortBuilder;
import org.openstack4j.openstack.common.ListEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 * A Subport ONLY used for adding to trunks
 *
 * @author Kashyap Jha
 */
@JsonRootName("sub_port")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeutronTrunkSubPort implements TrunkSubPort, ModelEntity {

    public static class NeutronTrunkSubPorts implements ModelEntity {

        private static final long serialVersionUID = 1L;

        public static NeutronTrunkSubPorts fromSubPorts(List<? extends TrunkSubPort> subPorts) {
            NeutronTrunkSubPorts toCreate = new NeutronTrunkSubPorts();
            for (TrunkSubPort subPort : subPorts) {
                toCreate.subPorts.add(NeutronTrunkSubPort.fromSubPort(subPort));
            }
            return toCreate;
        }

        @JsonProperty("sub_ports")
        private ListEntity<NeutronTrunkSubPort> subPorts;

        public NeutronTrunkSubPorts() {
            subPorts = new ListEntity<>();
        }
    }

    public static class TrunkSubPortConcreteBuilder extends ResourceBuilder<TrunkSubPort, TrunkSubPortConcreteBuilder>
            implements TrunkSubPortBuilder {

        private NeutronTrunkSubPort reference;

        TrunkSubPortConcreteBuilder() {
            this(new NeutronTrunkSubPort());
        }

        TrunkSubPortConcreteBuilder(NeutronTrunkSubPort subPort) {
            this.reference = subPort;
        }

        @Override
        public TrunkSubPort build() {
            return reference;
        }

        @Override
        public TrunkSubPortBuilder from(TrunkSubPort in) {
            reference = (NeutronTrunkSubPort) in;
            return this;
        }

        @Override
        public TrunkSubPortBuilder portId(String portId) {
            reference.portId = portId;
            return this;
        }

        @Override
        protected TrunkSubPort reference() {
            return reference;
        }

        @Override
        public TrunkSubPortBuilder segmentationId(int segmentationId) {
            reference.segmentationId = segmentationId;
            return this;
        }

        @Override
        public TrunkSubPortBuilder segmentationType(String segmentationType) {
            reference.segmentationType = segmentationType;
            return this;
        }
    }

    public static class TrunkSubPorts extends ListResult<NeutronTrunkSubPort> {
        private static final long serialVersionUID = 1L;

        @JsonIgnoreProperties(ignoreUnknown = true)
        @JsonProperty("sub_ports")
        private List<NeutronTrunkSubPort> trunkSubPorts;

        @Override
        protected List<NeutronTrunkSubPort> value() {
            return trunkSubPorts;
        }
    }

    private static final long serialVersionUID = 1L;

    public static TrunkSubPortBuilder builder() {
        return new TrunkSubPortConcreteBuilder();
    }

    public static NeutronTrunkSubPort fromSubPort(TrunkSubPort subPort) {
        NeutronTrunkSubPort toCreate = new NeutronTrunkSubPort();
        toCreate.portId = subPort.getPortId();
        toCreate.segmentationId = subPort.getSegmentationId();
        toCreate.segmentationType = subPort.getSegmentationType();
        return toCreate;
    }

    @JsonProperty("port_id")
    private String portId;

    @JsonProperty("segmentation_id")
    private int segmentationId;

    @JsonProperty("segmentation_type")
    private String segmentationType;

    public NeutronTrunkSubPort() {
    }

    public NeutronTrunkSubPort(String portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof NeutronTrunkSubPort) {
            NeutronTrunkSubPort that = (NeutronTrunkSubPort) obj;
            if (Objects.equals(portId, that.portId) && Objects.equals(segmentationId, that.segmentationId)
                    && Objects.equals(segmentationType, that.segmentationType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Subports don't have the ID attribute. Use {@link getPortId} instead
     */
    @Override
    @Deprecated
    public String getId() {
        return portId;
    }

    /**
     * Subports don't have the name attribute
     */
    @Override
    @Deprecated
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPortId() {
        return portId;
    }

    @Override
    public int getSegmentationId() {
        return segmentationId;
    }

    @Override
    public String getSegmentationType() {
        return segmentationType;
    }

    /**
     * Subports don't have the tenantId attrbute
     */
    @Override
    @Deprecated
    public String getTenantId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return Objects.hash(segmentationId, portId, segmentationType);
    }

    @Override
    @Deprecated
    public void setId(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void setName(String name) {
        throw new UnsupportedOperationException();

    }

    @Override
    @Deprecated
    public void setTenantId(String tenantId) {
        throw new UnsupportedOperationException();

    }

    @Override
    public TrunkSubPortBuilder toBuilder() {
        return new TrunkSubPortConcreteBuilder(this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("segmentationId", segmentationId)
                .add("portId", portId).add("segmentationType", segmentationType).toString();

    }
}
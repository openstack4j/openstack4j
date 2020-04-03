package org.openstack4j.model.network.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.network.TrunkSubPort;

/**
 * A builder which creates a subport
 *
 * @author Kashyap Jha
 */
public interface TrunkSubPortBuilder extends Builder<TrunkSubPortBuilder, TrunkSubPort> {

    /**
     * Set the segmentation ID
     */
    TrunkSubPortBuilder segmentationId(int segmentationId);

    /**
     * Set the port ID
     */
    TrunkSubPortBuilder portId(String portId);

    /**
     * Set the segmentation type
     */
    TrunkSubPortBuilder segmentationType(String segmentationType);

}

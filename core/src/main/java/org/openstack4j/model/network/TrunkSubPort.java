package org.openstack4j.model.network;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.network.builder.TrunkSubPortBuilder;

/**
 * A network subport used ONLY in trunks
 *
 * @author Kashyap Jha
 */
public interface TrunkSubPort extends Resource, Buildable<TrunkSubPortBuilder> {

    /**
     * @return the segmentation ID
     */
    int getSegmentationId();

    /**
     * @return the port ID
     */
    String getPortId();

    /**
     * @return the segmentation type
     */
    String getSegmentationType();
}

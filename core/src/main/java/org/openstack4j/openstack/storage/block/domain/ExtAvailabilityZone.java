package org.openstack4j.openstack.storage.block.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.openstack.common.ListResult;
import org.openstack4j.util.ToStringHelper;

public class ExtAvailabilityZone implements AvailabilityZone {

    private static final long serialVersionUID = 1L;

    ExtZoneState zoneState;
    String zoneName;

    /**
     * {@inheritDoc}
     */
    @Override
    public ZoneState getZoneState() {
        return zoneState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getZoneName() {
        return zoneName;
    }


    @JsonRootName("zoneState")
    static class ExtZoneState implements ZoneState {
        private static final long serialVersionUID = 1L;

        boolean available;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean getAvailable() {
            return available;
        }

        @Override
        public String toString() {
            return new ToStringHelper(this)
                    .add("available", available)
                    .toString();
        }
    }

    public static class AvailabilityZones extends ListResult<ExtAvailabilityZone> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("availabilityZoneInfo")
        private List<ExtAvailabilityZone> result;

        @Override
        protected List<ExtAvailabilityZone> value() {
            return result;
        }
    }
}

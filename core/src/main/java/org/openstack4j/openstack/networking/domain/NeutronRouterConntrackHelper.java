package org.openstack4j.openstack.networking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;
import org.openstack4j.model.network.RouterConntrackHelper;
import org.openstack4j.model.network.builder.RouterConntrackHelperBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Single entity for Routers Conntrack Helper (CT) target rules
 *
 * @author bboyHan
 */
@JsonRootName("conntrack_helper")
public class NeutronRouterConntrackHelper implements RouterConntrackHelper {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;
    @JsonProperty("router_id")
    private String routerId;
    @JsonProperty("helper")
    private String helper;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("port")
    private Integer port;

    public static RouterConntrackHelperBuilder builder() {
        return new RouterConntrackHelperConcreteBuilder();
    }

    @Override
    public RouterConntrackHelperBuilder toBuilder() {
        return new RouterConntrackHelperConcreteBuilder(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRouterId(String routerId) {
        this.routerId = routerId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRouterId() {
        return routerId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHelper() {
        return helper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getProtocol() {
        return protocol;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getPort() {
        return port;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("routerId", routerId).add("helper", helper)
                .add("protocol", protocol).add("port", port)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
		@Override
		public int hashCode() {
			return java.util.Objects.hash(routerId, helper, protocol, port);
		}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }

        if (obj instanceof NeutronRouterConntrackHelper) {
            NeutronRouterConntrackHelper that = (NeutronRouterConntrackHelper) obj;
            return java.util.Objects.equals(routerId, that.routerId) &&
                    java.util.Objects.equals(helper, that.helper) &&
                    java.util.Objects.equals(protocol, that.protocol) &&
                    java.util.Objects.equals(port, that.port);
        }
        return false;
    }

    public static class RouterConntrackHelpers extends ListResult<NeutronRouterConntrackHelper> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("conntrack_helpers")
        private List<NeutronRouterConntrackHelper> conntrackHelpers;

        @Override
        protected List<NeutronRouterConntrackHelper> value() {
            return conntrackHelpers;
        }
    }

    public static class RouterConntrackHelperConcreteBuilder implements RouterConntrackHelperBuilder {

        private NeutronRouterConntrackHelper m;

        RouterConntrackHelperConcreteBuilder() {
            this(new NeutronRouterConntrackHelper());
        }

        RouterConntrackHelperConcreteBuilder(NeutronRouterConntrackHelper m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder from(RouterConntrackHelper in) {
            m = (NeutronRouterConntrackHelper) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelper build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder id(String id) {
            m.id = id;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder routerId(String routerId) {
            m.routerId = routerId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder helper(String helper) {
            m.helper = helper;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder protocol(String protocol) {
            m.protocol = protocol;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public RouterConntrackHelperBuilder port(Integer port) {
            m.port = port;
            return this;
        }


    }
}

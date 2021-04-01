package org.openstack4j.openstack.dns.v2.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.util.ToStringHelper;
import java.util.Objects;
import org.openstack4j.model.dns.v2.Nameserver;
import org.openstack4j.model.dns.v2.builder.NameserverBuilder;
import org.openstack4j.openstack.common.ListResult;

/**
 * model class for designate/v2 nameserver
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DesignateNameserver implements Nameserver {

    private static final long serialVersionUID = 1L;
    private String hostname;
    private Integer priority;

    /**
     * @return the nameserver builder
     */
    public static NameserverBuilder builder() {
        return new NameserverConcreteBuilder();
    }

    @Override
    public NameserverBuilder toBuilder() {
        return new NameserverConcreteBuilder(this);
    }

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("hostname", hostname)
                .add("priority", priority)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(hostname, priority);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DesignateNameserver that = DesignateNameserver.class.cast(obj);
        return Objects.equals(this.hostname, that.hostname)
                && Objects.equals(this.priority, that.priority);
    }

    public static class NameserverConcreteBuilder implements NameserverBuilder {

        DesignateNameserver model;

        NameserverConcreteBuilder() {
            this(new DesignateNameserver());
        }

        NameserverConcreteBuilder(DesignateNameserver model) {
            this.model = model;
        }

        @Override
        public Nameserver build() {
            return model;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public NameserverBuilder from(Nameserver in) {
            if (in != null)
                this.model = (DesignateNameserver) in;
            return this;
        }

        /**
         * @see DesignateNameserver#getHostname()
         */
        @Override
        public NameserverBuilder hostname(String hostname) {
            model.hostname = hostname;
            return this;
        }

        /**
         * @see DesignateNameserver#getPriority()
         */
        @Override
        public NameserverBuilder priority(Integer priority) {
            model.priority = priority;
            return this;
        }

    }

    public static class Nameservers extends ListResult<DesignateNameserver> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("nameservers")
        protected List<DesignateNameserver> list;

        @Override
        public List<DesignateNameserver> value() {
            return list;
        }
    }

}

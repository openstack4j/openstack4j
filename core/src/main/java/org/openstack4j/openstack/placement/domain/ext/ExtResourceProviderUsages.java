package org.openstack4j.openstack.placement.domain.ext;

import org.openstack4j.model.placement.ext.ResourceProviderUsages;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.util.ToStringHelper;

/**
 * The resource provider usages instance
 *
 * @author Jyothi Saroja
 */
@JsonRootName("usages")
public class ExtResourceProviderUsages implements ResourceProviderUsages {
    private static final long serialVersionUID = 1L;

    @JsonProperty("VCPU")
    int vcpu;
    @JsonProperty("PCPU")
    int pcpu;
    @JsonProperty("MEMORY_MB")
    int memoryMb;
    @JsonProperty("DISK_GB")
    int diskGb;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDiskGb() {
        return diskGb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMemoryMb() {
        return memoryMb;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPcpu() {
        return pcpu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVcpu() {
        return vcpu;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ToStringHelper(this).add("vcpu", vcpu).add("pcpu", pcpu)
                .add("memoryMb", memoryMb).add("diskGb", diskGb).toString();
    }
}

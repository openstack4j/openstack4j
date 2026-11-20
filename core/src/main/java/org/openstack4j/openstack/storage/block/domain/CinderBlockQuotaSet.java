package org.openstack4j.openstack.storage.block.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.model.storage.block.builder.BlockQuotaSetBuilder;
import org.openstack4j.util.ToStringHelper;

/**
 * An OpenStack Quota-Set
 *
 * @author Jeremy Unruh
 * @author Manuel Mazzuola
 */
@JsonRootName("quota_set")
public class CinderBlockQuotaSet implements BlockQuotaSet {

    private static final long serialVersionUID = 1L;

    private static final String VOLUME_TYPE_QUOTA_PREFIXES_REGEX = "(gigabytes|snapshots|volumes)_.*";
    private static final int SNAPSHOTS = 1;
    private static final int VOLUMES = 2;
    private static final int GIGABYTES = 4;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private int snapshots;
    private int volumes;
    private int gigabytes;
    // Older serialized quota sets did not record which fields were supplied.
    private Integer specifiedQuotas = 0;

    private Map<String, Integer> volumeTypesQuotas = new HashMap<>();

    public static BlockQuotaSetBuilder builder() {
        return new BlockQuotaSetConcreteBuilder();
    }

    @Override
    public BlockQuotaSetBuilder toBuilder() {
        return new BlockQuotaSetConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public int getSnapshots() {
        return snapshots;
    }

    @Override
    @JsonIgnore
    public int getVolumes() {
        return volumes;
    }

    @Override
    @JsonIgnore
    public int getGigabytes() {
        return gigabytes;
    }

    @JsonProperty("snapshots")
    private Integer requestSnapshots() {
        return quotaValue(SNAPSHOTS, snapshots);
    }

    @JsonProperty("snapshots")
    private void readSnapshots(Integer value) {
        snapshots = readQuota(SNAPSHOTS, value);
    }

    @JsonProperty("volumes")
    private Integer requestVolumes() {
        return quotaValue(VOLUMES, volumes);
    }

    @JsonProperty("volumes")
    private void readVolumes(Integer value) {
        volumes = readQuota(VOLUMES, value);
    }

    @JsonProperty("gigabytes")
    private Integer requestGigabytes() {
        return quotaValue(GIGABYTES, gigabytes);
    }

    @JsonProperty("gigabytes")
    private void readGigabytes(Integer value) {
        gigabytes = readQuota(GIGABYTES, value);
    }

    private Integer quotaValue(int field, int value) {
        return specifiedQuotas == null || (specifiedQuotas & field) != 0 ? value : null;
    }

    private int readQuota(int field, Integer value) {
        if (specifiedQuotas != null) {
            specifiedQuotas = value == null ? specifiedQuotas & ~field : specifiedQuotas | field;
        }
        return value == null ? 0 : value;
    }

    @Override
    @JsonAnyGetter
    public Map<String, Integer> getVolumeTypesQuotas() {
        return volumeTypesQuotas;
    }

    @JsonAnySetter
    private void setVolumeTypesQuotas(String key, Integer value) {
        if (key.matches(VOLUME_TYPE_QUOTA_PREFIXES_REGEX)) this.volumeTypesQuotas.put(key, value);
    }

    @Override
    public String toString() {
        return new ToStringHelper(this).add("snapshots", snapshots).add("volumes", volumes).add("gigabytes", gigabytes).toString();
    }

    public static class BlockQuotaSetConcreteBuilder implements BlockQuotaSetBuilder {

        private CinderBlockQuotaSet model;

        BlockQuotaSetConcreteBuilder() {
            this.model = new CinderBlockQuotaSet();
        }

        BlockQuotaSetConcreteBuilder(CinderBlockQuotaSet model) {
            this.model = model;
        }

        @Override
        public BlockQuotaSet build() {
            return model;
        }

        @Override
        public BlockQuotaSetBuilder from(BlockQuotaSet in) {
            return new BlockQuotaSetConcreteBuilder((CinderBlockQuotaSet) in);
        }

        @Override
        public BlockQuotaSetBuilder volumes(int volumes) {
            model.readVolumes(volumes);
            return this;
        }

        @Override
        public BlockQuotaSetBuilder snapshots(int snapshots) {
            model.readSnapshots(snapshots);
            return this;
        }

        @Override
        public BlockQuotaSetBuilder gigabytes(int gigabytes) {
            model.readGigabytes(gigabytes);
            return this;
        }

        @Override
        public BlockQuotaSetBuilder volumeTypesQuotas(Map<String, Integer> volumeTypesQuotas) {
            model.volumeTypesQuotas = volumeTypesQuotas;
            return this;
        }
    }
}

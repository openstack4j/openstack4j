package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.storage.block.EncryptionType;
import org.openstack4j.model.storage.block.builder.EncryptionTypeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

/**
 * The encryption type defines the characteristics of a encryption.
 *
 * @author Jyothi Saroja
 */
@JsonRootName("encryption")
public class CinderEncryptionType implements EncryptionType {

    private static final long serialVersionUID = 1L;

    @JsonProperty("encryption_id")
    protected String id;
    @JsonProperty("provider")
    protected String provider;
    @JsonProperty("control_location")
    protected String controlLocation;
    @JsonProperty("cipher")
    protected String cipher;
    @JsonProperty("key_size")
    protected int keySize;
    @JsonProperty("volume_type_id")
    protected String volumeTypeId;

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
    public String getProvider() {
        return provider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getVolumeTypeId() {
        return volumeTypeId;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public String getCipher() {
        return cipher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getKeySize() {
        return keySize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getControlLocation() {
        return controlLocation;
    }

    @Override
    public EncryptionTypeBuilder toBuilder() {
        return new ConcreteEncryptionTypeBuilder(this);
    }

    /**
     * @return the Encryption Type Builder
     */
    public static EncryptionTypeBuilder builder() {
        return new ConcreteEncryptionTypeBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("id", id).add("volumeTypeId", volumeTypeId)
                .add("provider", provider).add("cipher", cipher).add("keySize", keySize)
                .add("controlLocation", controlLocation).toString();
    }

    public static class ConcreteEncryptionTypeBuilder implements EncryptionTypeBuilder {

        private CinderEncryptionType m;

        ConcreteEncryptionTypeBuilder() {
            this(new CinderEncryptionType());
        }

        ConcreteEncryptionTypeBuilder(CinderEncryptionType EncryptionType) {
            this.m = EncryptionType;
        }

        @Override
        public EncryptionType build() {
            return m;
        }

        @Override
        public EncryptionTypeBuilder from(EncryptionType in) {
            m = (CinderEncryptionType) in;
            return this;
        }

        @Override
        public EncryptionTypeBuilder provider(String provider) {
            m.provider = provider;
            return this;
        }

        @Override
        public EncryptionTypeBuilder id(String id) {
            m.id = id;
            return this;
        }

        @Override
        public EncryptionTypeBuilder volumeTypeId(String volTypeId) {
            m.volumeTypeId = volTypeId;
            return this;
        }

        @Override
        public EncryptionTypeBuilder cipher(String cipher) {
            m.cipher = cipher;
            return this;
        }

        @Override
        public EncryptionTypeBuilder keySize(int keySize) {
            m.keySize = keySize;
            return this;
        }

        @Override
        public EncryptionTypeBuilder controlLocation(String controlLocation) {
            m.controlLocation = controlLocation;
            return this;
        }
    }
}

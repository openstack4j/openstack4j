package org.openstack4j.model.storage.block.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.storage.block.EncryptionType;

public interface EncryptionTypeBuilder extends Builder<EncryptionTypeBuilder, EncryptionType> {

    /**
     * See {@link EncryptionType#getCipher()} <b>Optional</b>
     *
     * @param cipher Defining cipher for the encryption type
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder cipher(String cipher);

    /**
     * See {@link EncryptionType#getControlLocation()} <b>Optional</b>
     *
     * @param controlLocation Defining control location for the encryption type
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder controlLocation(String controlLocation);

    /**
     * See {@link EncryptionType#getId()} <b>Optional</b>
     *
     * @param id The identifier for the encryption type.
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder id(String id);

    /**
     * See {@link EncryptionType#getKeySize()} <b>Optional</b>
     *
     * @param keySize Defining key size for the encryption type
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder keySize(int keySize);

    /**
     * See {@link EncryptionType#getProvider()}
     *
     * @param provider Defining provider for the encryption type
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder provider(String provider);

    /**
     * See {@link EncryptionType#getVolumeTypeId()}
     *
     * @param volumeTypeId Defining the identifier for the volume type for which encryption type is to be created
     * @return EncryptionTypeBuilder
     */
    EncryptionTypeBuilder volumeTypeId(String volumeTypeId);
}


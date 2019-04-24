package org.openstack4j.model.storage.block;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.storage.block.builder.EncryptionTypeBuilder;

/**
 * The encryption type defines the characteristics of an encryption.
 *
 * @author Jyothi Saroja
 */
public interface EncryptionType extends ModelEntity, Buildable<EncryptionTypeBuilder> {

    /**
     * @return the cipher of the encryption type
     */
    String getCipher();

    /**ss
     * @return the control location of the encryption type
     */
    String getControlLocation();

    /**
     * @return the identifier for the encryption type
     */
    String getId();

    /**
     * @return the key size of the encryption type
     */
    int getKeySize();

    /**
     * @return the provider of the encryption type
     */
    String getProvider();

    /**
     * @return the identifier for the associated volume type
     */
    String getVolumeTypeId();

}

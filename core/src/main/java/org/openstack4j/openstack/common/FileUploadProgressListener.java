package org.openstack4j.openstack.common;

public interface FileUploadProgressListener {
    void updateTransferedByte(long transferedByte);

    default long getFileSize() {
        return -1L;
    }
}

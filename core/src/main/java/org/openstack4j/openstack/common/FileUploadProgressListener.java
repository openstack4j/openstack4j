package org.openstack4j.openstack.common;

public interface FileUploadProgressListener
{
    void updateTransferedByte(long transferedByte);

    long getFileSize();
}

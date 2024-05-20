package org.openstack4j.openstack.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.entity.AbstractHttpEntity;

/**
 * TODO add description.
 *
 * <p>
 * Class responsibility:
 * </p>
 */

public class FileEntity extends AbstractHttpEntity implements Cloneable
{
    protected InputStream is;
    private FileUploadProgressListener listener;
    private long transferredByte;
    private int bufferSize;

    private final long SLEEP_TIME = 3;

    public FileEntity(InputStream is, String contentType, int bufferSize, FileUploadProgressListener listener)
    {
        super();
        if (is == null)
        {
            throw new IllegalArgumentException("File may not be null");
        }
        this.is = is;
        this.transferredByte = 0;
        this.listener = listener;

        if (bufferSize <= 0)
        {
            // 1MB
            bufferSize = 1048576;
        } else
        {
            this.bufferSize = bufferSize;
        }

        setContentType(contentType);
    }

    public boolean isRepeatable()
    {
        return true;
    }

    public long getContentLength()
    {
        if (listener != null)
        {
            return listener.getFileSize();
        }

        try
        {
            if (is instanceof FileInputStream)
            {
                return ((FileInputStream) is).getChannel().size();
            }
        } catch (IOException e)
        {
            return 0;
        }
        return 0;
    }

    public InputStream getContent() throws IOException
    {
        return is;
    }

    public void writeTo(final OutputStream outstream) throws IOException
    {
        if (outstream == null)
        {
            throw new IllegalArgumentException("Output stream may not be null");
        }

        try
        {
            byte[] buffer = new byte[bufferSize];
            int w;
            while ((w = is.read(buffer)) != -1)
            {
                outstream.write(buffer, 0, w);
                this.transferredByte += w;
                listener.updateTransferedByte(transferredByte);
                Thread.sleep(SLEEP_TIME);
            }
            outstream.flush();
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e.getMessage());
            // throw e;
        } finally
        {
            is.close();
        }
    }

    public boolean isStreaming()
    {
        return false;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
}

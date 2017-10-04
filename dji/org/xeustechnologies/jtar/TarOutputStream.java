package org.xeustechnologies.jtar;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TarOutputStream extends FilterOutputStream {
    private long bytesWritten = 0;
    private TarEntry currentEntry;
    private long currentFileSize = 0;

    public TarOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void close() throws IOException {
        closeCurrentEntry();
        write(new byte[1024]);
        super.close();
    }

    public void write(int i) throws IOException {
        super.write(i);
        this.bytesWritten++;
        if (this.currentEntry != null) {
            this.currentFileSize++;
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.currentEntry == null || this.currentEntry.isDirectory() || this.currentEntry.getSize() >= this.currentFileSize + ((long) i2)) {
            super.write(bArr, i, i2);
            return;
        }
        throw new IOException("The current entry[" + this.currentEntry.getName() + "] size[" + this.currentEntry.getSize() + "] is smaller than the bytes[" + (this.currentFileSize + ((long) i2)) + "] being written.");
    }

    public void putNextEntry(TarEntry tarEntry) throws IOException {
        closeCurrentEntry();
        byte[] bArr = new byte[512];
        tarEntry.writeEntryHeader(bArr);
        write(bArr);
        this.currentEntry = tarEntry;
    }

    protected void closeCurrentEntry() throws IOException {
        if (this.currentEntry == null) {
            return;
        }
        if (this.currentEntry.getSize() > this.currentFileSize) {
            throw new IOException("The current entry[" + this.currentEntry.getName() + "] of size[" + this.currentEntry.getSize() + "] has not been fully written.");
        }
        this.currentEntry = null;
        this.currentFileSize = 0;
        pad();
    }

    protected void pad() throws IOException {
        if (this.bytesWritten > 0) {
            int i = (int) (this.bytesWritten % 512);
            if (i > 0) {
                write(new byte[(512 - i)]);
            }
        }
    }
}

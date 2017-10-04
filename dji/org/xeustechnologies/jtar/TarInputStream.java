package org.xeustechnologies.jtar;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TarInputStream extends FilterInputStream {
    private static final int SKIP_BUFFER_SIZE = 2048;
    private long bytesRead = 0;
    private TarEntry currentEntry;
    private long currentFileSize = 0;
    private boolean defaultSkip = false;

    public TarInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public boolean markSupported() {
        return false;
    }

    public synchronized void mark(int i) {
    }

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        if (read != -1) {
            return bArr[0];
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.currentEntry != null) {
            if (this.currentFileSize == this.currentEntry.getSize()) {
                return -1;
            }
            if (this.currentEntry.getSize() - this.currentFileSize < ((long) i2)) {
                i2 = (int) (this.currentEntry.getSize() - this.currentFileSize);
            }
        }
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            if (this.currentEntry != null) {
                this.currentFileSize += (long) read;
            }
            this.bytesRead += (long) read;
        }
        return read;
    }

    public TarEntry getNextEntry() throws IOException {
        int i = 0;
        closeCurrentEntry();
        Object obj = new byte[512];
        Object obj2 = new byte[512];
        int i2 = 0;
        while (i2 < 512) {
            int read = read(obj2, 0, 512 - i2);
            if (read < 0) {
                break;
            }
            System.arraycopy(obj2, 0, obj, i2, read);
            i2 += read;
        }
        for (byte b : obj) {
            if (b != (byte) 0) {
                break;
            }
        }
        i = 1;
        if (i == 0) {
            this.bytesRead += (long) obj.length;
            this.currentEntry = new TarEntry(obj);
        }
        return this.currentEntry;
    }

    protected void closeCurrentEntry() throws IOException {
        if (this.currentEntry != null) {
            if (this.currentEntry.getSize() > this.currentFileSize) {
                long j = 0;
                while (j < this.currentEntry.getSize() - this.currentFileSize) {
                    long skip = skip((this.currentEntry.getSize() - this.currentFileSize) - j);
                    if (skip != 0 || this.currentEntry.getSize() - this.currentFileSize <= 0) {
                        j += skip;
                    } else {
                        throw new IOException("Possible tar file corruption");
                    }
                }
            }
            this.currentEntry = null;
            this.currentFileSize = 0;
            skipPad();
        }
    }

    protected void skipPad() throws IOException {
        long j = 0;
        if (this.bytesRead > 0) {
            int i = (int) (this.bytesRead % 512);
            if (i > 0) {
                while (j < ((long) (512 - i))) {
                    j += skip(((long) (512 - i)) - j);
                }
            }
        }
    }

    public long skip(long j) throws IOException {
        if (this.defaultSkip) {
            return super.skip(j);
        }
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[2048];
        long j2 = j;
        while (j2 > 0) {
            int read = read(bArr, 0, (int) (j2 < 2048 ? j2 : 2048));
            if (read < 0) {
                break;
            }
            j2 -= (long) read;
        }
        return j - j2;
    }

    public boolean isDefaultSkip() {
        return this.defaultSkip;
    }

    public void setDefaultSkip(boolean z) {
        this.defaultSkip = z;
    }
}

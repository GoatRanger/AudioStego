package it.sauronsoftware.ftp4j.connectors;

import java.io.IOException;
import java.io.InputStream;

class Base64InputStream extends InputStream {
    private int[] buffer;
    private int bufferCounter = 0;
    private boolean eof = false;
    private InputStream inputStream;

    public Base64InputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public int read() throws IOException {
        if (this.buffer == null || this.bufferCounter == this.buffer.length) {
            if (this.eof) {
                return -1;
            }
            acquire();
            if (this.buffer.length == 0) {
                this.buffer = null;
                return -1;
            }
            this.bufferCounter = 0;
        }
        int[] iArr = this.buffer;
        int i = this.bufferCounter;
        this.bufferCounter = i + 1;
        return iArr[i];
    }

    private void acquire() throws IOException {
        int i = 1;
        int i2 = 0;
        char[] cArr = new char[4];
        int i3 = 0;
        do {
            int read = this.inputStream.read();
            if (read != -1) {
                char c = (char) read;
                if (Base64.chars.indexOf(c) != -1 || c == Base64.pad) {
                    read = i3 + 1;
                    cArr[i3] = c;
                    i3 = read;
                    continue;
                } else if (!(c == '\r' || c == '\n')) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (i3 != 0) {
                throw new IOException("Bad base64 stream");
            } else {
                this.buffer = new int[0];
                this.eof = true;
                return;
            }
        } while (i3 < 4);
        i3 = 0;
        for (read = 0; read < 4; read++) {
            if (cArr[read] != Base64.pad) {
                if (i3 != 0) {
                    throw new IOException("Bad base64 stream");
                }
            } else if (i3 == 0) {
                i3 = 1;
            }
        }
        if (cArr[3] != Base64.pad) {
            i = 3;
        } else if (this.inputStream.read() != -1) {
            throw new IOException("Bad base64 stream");
        } else {
            this.eof = true;
            if (cArr[2] != Base64.pad) {
                i = 2;
            }
        }
        i3 = 0;
        for (int i4 = 0; i4 < 4; i4++) {
            if (cArr[i4] != Base64.pad) {
                i3 |= Base64.chars.indexOf(cArr[i4]) << ((3 - i4) * 6);
            }
        }
        this.buffer = new int[i];
        while (i2 < i) {
            this.buffer[i2] = (i3 >>> ((2 - i2) * 8)) & 255;
            i2++;
        }
    }

    public void close() throws IOException {
        this.inputStream.close();
    }
}

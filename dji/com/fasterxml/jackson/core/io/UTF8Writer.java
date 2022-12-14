package com.fasterxml.jackson.core.io;

import dji.thirdparty.g.b.a.a;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public final class UTF8Writer extends Writer {
    static final int SURR1_FIRST = 55296;
    static final int SURR1_LAST = 56319;
    static final int SURR2_FIRST = 56320;
    static final int SURR2_LAST = 57343;
    protected final IOContext _context;
    OutputStream _out;
    byte[] _outBuffer;
    final int _outBufferEnd;
    int _outPtr;
    int _surrogate = 0;

    public UTF8Writer(IOContext iOContext, OutputStream outputStream) {
        this._context = iOContext;
        this._out = outputStream;
        this._outBuffer = iOContext.allocWriteEncodingBuffer();
        this._outBufferEnd = this._outBuffer.length - 4;
        this._outPtr = 0;
    }

    public Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream outputStream = this._out;
            this._out = null;
            byte[] bArr = this._outBuffer;
            if (bArr != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(bArr);
            }
            outputStream.close();
            int i = this._surrogate;
            this._surrogate = 0;
            if (i > 0) {
                throwIllegal(i);
            }
        }
    }

    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(char[] cArr) throws IOException {
        write(cArr, 0, cArr.length);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(cArr[i]));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char c = cArr[i6];
                char c2;
                if (c < '??') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char c3 = cArr[i3];
                        if (c3 >= '??') {
                            c2 = c3;
                            i3 = i6;
                            i6 = i9;
                            i9 = c2;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) c3;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c2 = c;
                    i6 = i9;
                    char c4 = c2;
                }
                if (i9 < 2048) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | 192);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | 128);
                } else if (i9 < SURR1_FIRST || i9 > SURR2_LAST) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | a.fw_);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | 128);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | 128);
                } else {
                    if (i9 > SURR1_LAST) {
                        this._outPtr = i3;
                        throwIllegal(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(cArr[i6]);
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        throwIllegal(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | 128);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | 128);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(cArr[i]);
        }
    }

    public void write(int i) throws IOException {
        if (this._surrogate > 0) {
            i = convertSurrogate(i);
        } else if (i >= SURR1_FIRST && i <= SURR2_LAST) {
            if (i > SURR1_LAST) {
                throwIllegal(i);
            }
            this._surrogate = i;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (i < 128) {
            byte[] bArr = this._outBuffer;
            int i2 = this._outPtr;
            this._outPtr = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        int i3 = this._outPtr;
        int i4;
        if (i < 2048) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 6) | 192);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | 128);
        } else if (i <= 65535) {
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 12) | a.fw_);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 6) & 63) | 128);
            i3 = i2 + 1;
            this._outBuffer[i2] = (byte) ((i & 63) | 128);
        } else {
            if (i > 1114111) {
                throwIllegal(i);
            }
            i4 = i3 + 1;
            this._outBuffer[i3] = (byte) ((i >> 18) | 240);
            i2 = i4 + 1;
            this._outBuffer[i4] = (byte) (((i >> 12) & 63) | 128);
            i4 = i2 + 1;
            this._outBuffer[i2] = (byte) (((i >> 6) & 63) | 128);
            i3 = i4 + 1;
            this._outBuffer[i4] = (byte) ((i & 63) | 128);
        }
        this._outPtr = i3;
    }

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    public void write(String str, int i, int i2) throws IOException {
        if (i2 >= 2) {
            int i3;
            if (this._surrogate > 0) {
                i3 = i + 1;
                i2--;
                write(convertSurrogate(str.charAt(i)));
                i = i3;
            }
            i3 = this._outPtr;
            byte[] bArr = this._outBuffer;
            int i4 = this._outBufferEnd;
            int i5 = i2 + i;
            int i6 = i;
            while (i6 < i5) {
                int i7;
                int i8;
                if (i3 >= i4) {
                    this._out.write(bArr, 0, i3);
                    i3 = 0;
                }
                int i9 = i6 + 1;
                char charAt = str.charAt(i6);
                char c;
                if (charAt < '??') {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) charAt;
                    i6 = i5 - i9;
                    i3 = i4 - i7;
                    if (i6 <= i3) {
                        i3 = i6;
                    }
                    i8 = i3 + i9;
                    i6 = i7;
                    i3 = i9;
                    while (i3 < i8) {
                        i9 = i3 + 1;
                        char charAt2 = str.charAt(i3);
                        if (charAt2 >= '??') {
                            c = charAt2;
                            i3 = i6;
                            i6 = i9;
                            i9 = c;
                        } else {
                            i7 = i6 + 1;
                            bArr[i6] = (byte) charAt2;
                            i6 = i7;
                            i3 = i9;
                        }
                    }
                    int i10 = i6;
                    i6 = i3;
                    i3 = i10;
                } else {
                    c = charAt;
                    i6 = i9;
                    char c2 = c;
                }
                if (i9 < 2048) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 6) | 192);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i9 & 63) | 128);
                } else if (i9 < SURR1_FIRST || i9 > SURR2_LAST) {
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i9 >> 12) | a.fw_);
                    i8 = i7 + 1;
                    bArr[i7] = (byte) (((i9 >> 6) & 63) | 128);
                    i3 = i8 + 1;
                    bArr[i8] = (byte) ((i9 & 63) | 128);
                } else {
                    if (i9 > SURR1_LAST) {
                        this._outPtr = i3;
                        throwIllegal(i9);
                    }
                    this._surrogate = i9;
                    if (i6 >= i5) {
                        break;
                    }
                    i9 = i6 + 1;
                    i6 = convertSurrogate(str.charAt(i6));
                    if (i6 > 1114111) {
                        this._outPtr = i3;
                        throwIllegal(i6);
                    }
                    i7 = i3 + 1;
                    bArr[i3] = (byte) ((i6 >> 18) | 240);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) (((i6 >> 12) & 63) | 128);
                    i7 = i3 + 1;
                    bArr[i3] = (byte) (((i6 >> 6) & 63) | 128);
                    i3 = i7 + 1;
                    bArr[i7] = (byte) ((i6 & 63) | 128);
                    i6 = i9;
                }
            }
            this._outPtr = i3;
        } else if (i2 == 1) {
            write(str.charAt(i));
        }
    }

    private int convertSurrogate(int i) throws IOException {
        int i2 = this._surrogate;
        this._surrogate = 0;
        if (i >= SURR2_FIRST && i <= SURR2_LAST) {
            return (((i2 - SURR1_FIRST) << 10) + 65536) + (i - SURR2_FIRST);
        }
        throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i) + "; illegal combination");
    }

    private void throwIllegal(int i) throws IOException {
        if (i > 1114111) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output; max is 0x10FFFF as per RFC 4627");
        } else if (i < SURR1_FIRST) {
            throw new IOException("Illegal character point (0x" + Integer.toHexString(i) + ") to output");
        } else if (i <= SURR1_LAST) {
            throw new IOException("Unmatched first part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        } else {
            throw new IOException("Unmatched second part of surrogate pair (0x" + Integer.toHexString(i) + ")");
        }
    }
}

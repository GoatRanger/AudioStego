package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecycler.ByteBufferType;
import com.fasterxml.jackson.core.util.BufferRecycler.CharBufferType;
import com.fasterxml.jackson.core.util.TextBuffer;

public final class IOContext {
    protected byte[] _base64Buffer = null;
    protected final BufferRecycler _bufferRecycler;
    protected char[] _concatCBuffer = null;
    protected JsonEncoding _encoding;
    protected final boolean _managedResource;
    protected char[] _nameCopyBuffer = null;
    protected byte[] _readIOBuffer = null;
    protected final Object _sourceRef;
    protected char[] _tokenCBuffer = null;
    protected byte[] _writeEncodingBuffer = null;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = obj;
        this._managedResource = z;
    }

    public void setEncoding(JsonEncoding jsonEncoding) {
        this._encoding = jsonEncoding;
    }

    public Object getSourceReference() {
        return this._sourceRef;
    }

    public JsonEncoding getEncoding() {
        return this._encoding;
    }

    public boolean isResourceManaged() {
        return this._managedResource;
    }

    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this._bufferRecycler);
    }

    public byte[] allocReadIOBuffer() {
        if (this._readIOBuffer != null) {
            throw new IllegalStateException("Trying to call allocReadIOBuffer() second time");
        }
        this._readIOBuffer = this._bufferRecycler.allocByteBuffer(ByteBufferType.READ_IO_BUFFER);
        return this._readIOBuffer;
    }

    public byte[] allocWriteEncodingBuffer() {
        if (this._writeEncodingBuffer != null) {
            throw new IllegalStateException("Trying to call allocWriteEncodingBuffer() second time");
        }
        this._writeEncodingBuffer = this._bufferRecycler.allocByteBuffer(ByteBufferType.WRITE_ENCODING_BUFFER);
        return this._writeEncodingBuffer;
    }

    public byte[] allocBase64Buffer() {
        if (this._base64Buffer != null) {
            throw new IllegalStateException("Trying to call allocBase64Buffer() second time");
        }
        this._base64Buffer = this._bufferRecycler.allocByteBuffer(ByteBufferType.BASE64_CODEC_BUFFER);
        return this._base64Buffer;
    }

    public char[] allocTokenBuffer() {
        if (this._tokenCBuffer != null) {
            throw new IllegalStateException("Trying to call allocTokenBuffer() second time");
        }
        this._tokenCBuffer = this._bufferRecycler.allocCharBuffer(CharBufferType.TOKEN_BUFFER);
        return this._tokenCBuffer;
    }

    public char[] allocConcatBuffer() {
        if (this._concatCBuffer != null) {
            throw new IllegalStateException("Trying to call allocConcatBuffer() second time");
        }
        this._concatCBuffer = this._bufferRecycler.allocCharBuffer(CharBufferType.CONCAT_BUFFER);
        return this._concatCBuffer;
    }

    public char[] allocNameCopyBuffer(int i) {
        if (this._nameCopyBuffer != null) {
            throw new IllegalStateException("Trying to call allocNameCopyBuffer() second time");
        }
        this._nameCopyBuffer = this._bufferRecycler.allocCharBuffer(CharBufferType.NAME_COPY_BUFFER, i);
        return this._nameCopyBuffer;
    }

    public void releaseReadIOBuffer(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr != this._readIOBuffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._readIOBuffer = null;
        this._bufferRecycler.releaseByteBuffer(ByteBufferType.READ_IO_BUFFER, bArr);
    }

    public void releaseWriteEncodingBuffer(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr != this._writeEncodingBuffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._writeEncodingBuffer = null;
        this._bufferRecycler.releaseByteBuffer(ByteBufferType.WRITE_ENCODING_BUFFER, bArr);
    }

    public void releaseBase64Buffer(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        if (bArr != this._base64Buffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._base64Buffer = null;
        this._bufferRecycler.releaseByteBuffer(ByteBufferType.BASE64_CODEC_BUFFER, bArr);
    }

    public void releaseTokenBuffer(char[] cArr) {
        if (cArr == null) {
            return;
        }
        if (cArr != this._tokenCBuffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._tokenCBuffer = null;
        this._bufferRecycler.releaseCharBuffer(CharBufferType.TOKEN_BUFFER, cArr);
    }

    public void releaseConcatBuffer(char[] cArr) {
        if (cArr == null) {
            return;
        }
        if (cArr != this._concatCBuffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._concatCBuffer = null;
        this._bufferRecycler.releaseCharBuffer(CharBufferType.CONCAT_BUFFER, cArr);
    }

    public void releaseNameCopyBuffer(char[] cArr) {
        if (cArr == null) {
            return;
        }
        if (cArr != this._nameCopyBuffer) {
            throw new IllegalArgumentException("Trying to release buffer not owned by the context");
        }
        this._nameCopyBuffer = null;
        this._bufferRecycler.releaseCharBuffer(CharBufferType.NAME_COPY_BUFFER, cArr);
    }
}

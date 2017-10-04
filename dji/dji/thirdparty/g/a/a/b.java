package dji.thirdparty.g.a.a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class b extends a {
    private final byte[] l;

    public b(String str, byte[] bArr) {
        super(str);
        this.l = bArr;
    }

    public b(byte[] bArr) {
        super(null);
        this.l = bArr;
    }

    public InputStream a() {
        return new ByteArrayInputStream(this.l);
    }

    public byte[] c(int i, int i2) throws IOException {
        if (i + i2 > this.l.length) {
            throw new IOException("Could not read block (block start: " + i + ", block length: " + i2 + ", data length: " + this.l.length + ").");
        }
        Object obj = new byte[i2];
        System.arraycopy(this.l, i, obj, 0, i2);
        return obj;
    }

    public long c() {
        return (long) this.l.length;
    }

    public byte[] b() throws IOException {
        return this.l;
    }

    public String d() {
        return this.l.length + " byte array";
    }
}

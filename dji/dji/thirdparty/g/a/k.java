package dji.thirdparty.g.a;

import java.io.IOException;
import java.io.OutputStream;

public class k extends OutputStream {
    private final byte[] a;
    private int b = 0;

    public k(int i) {
        this.a = new byte[i];
    }

    public void write(int i) throws IOException {
        if (this.b >= this.a.length) {
            throw new IOException("Write exceeded expected length (" + this.b + ", " + this.a.length + ")");
        }
        this.a[this.b] = (byte) i;
        this.b++;
    }

    public byte[] a() {
        if (this.b >= this.a.length) {
            return this.a;
        }
        Object obj = new byte[this.b];
        System.arraycopy(this.a, 0, obj, 0, this.b);
        return obj;
    }

    public int b() {
        return this.b;
    }
}

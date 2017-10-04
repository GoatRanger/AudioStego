package dji.thirdparty.g.a;

import java.io.IOException;
import java.io.InputStream;

public class g extends InputStream implements a {
    private final InputStream j;
    private int k;
    private int l = 0;
    private long m = 0;

    public g(InputStream inputStream) {
        this.j = inputStream;
    }

    public int read() throws IOException {
        if (this.l <= 0) {
            return this.j.read();
        }
        throw new IOException("BitInputStream: incomplete bit read");
    }

    public final int a(int i) throws IOException {
        int i2 = 0;
        if (i <= 32) {
            int i3;
            if (this.l <= 0) {
                i3 = 0;
                i2 = i;
            } else if (i >= this.l) {
                i3 = ((1 << this.l) - 1) & this.k;
                i -= this.l;
                this.l = 0;
                i2 = i;
            } else {
                this.l -= i;
                i3 = ((1 << i) - 1) & (this.k >> this.l);
            }
            while (i2 >= 8) {
                this.k = this.j.read();
                if (this.k < 0) {
                    throw new IOException("couldn't read bits");
                }
                System.out.println("cache 1: " + this.k + " (" + Integer.toHexString(this.k) + ", " + Integer.toBinaryString(this.k) + ")");
                this.m++;
                i3 = (i3 << 8) | (this.k & 255);
                i2 -= 8;
            }
            if (i2 <= 0) {
                return i3;
            }
            this.k = this.j.read();
            if (this.k < 0) {
                throw new IOException("couldn't read bits");
            }
            System.out.println("cache 2: " + this.k + " (" + Integer.toHexString(this.k) + ", " + Integer.toBinaryString(this.k) + ")");
            this.m++;
            this.l = 8 - i2;
            return (i3 << i2) | (((1 << i2) - 1) & (this.k >> this.l));
        }
        throw new IOException("BitInputStream: unknown error");
    }

    public void a() {
        this.l = 0;
    }

    public long b() {
        return this.m;
    }
}

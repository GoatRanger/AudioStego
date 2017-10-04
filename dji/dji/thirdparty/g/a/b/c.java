package dji.thirdparty.g.a.b;

import dji.thirdparty.g.a.a;
import java.io.IOException;
import java.io.OutputStream;

public class c extends OutputStream implements a {
    private final OutputStream j;
    private final int k;
    private int l = 0;
    private int m = 0;
    private int n = 0;

    public c(OutputStream outputStream, int i) {
        this.k = i;
        this.j = outputStream;
    }

    public void write(int i) throws IOException {
        a(i, 8);
    }

    public void a(int i, int i2) throws IOException {
        int i3 = ((1 << i2) - 1) & i;
        if (this.k == 77) {
            this.m = i3 | (this.m << i2);
        } else if (this.k == 73) {
            this.m = (i3 << this.l) | this.m;
        } else {
            throw new IOException("Unknown byte order: " + this.k);
        }
        this.l += i2;
        while (this.l >= 8) {
            if (this.k == 77) {
                a((this.m >> (this.l - 8)) & 255);
                this.l -= 8;
            } else if (this.k == 73) {
                a(this.m & 255);
                this.m >>= 8;
                this.l -= 8;
            }
            this.m = ((1 << this.l) - 1) & this.m;
        }
    }

    private void a(int i) throws IOException {
        this.j.write(i);
        this.n++;
    }

    public void a() throws IOException {
        if (this.l > 0) {
            int i = ((1 << this.l) - 1) & this.m;
            if (this.k == 77) {
                this.j.write(i << (8 - this.l));
            } else if (this.k == 73) {
                this.j.write(i);
            }
        }
        this.l = 0;
        this.m = 0;
    }

    public int b() {
        return (this.l > 0 ? 1 : 0) + this.n;
    }
}

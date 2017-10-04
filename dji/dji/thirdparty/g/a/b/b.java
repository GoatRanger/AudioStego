package dji.thirdparty.g.a.b;

import dji.thirdparty.g.a.a;
import java.io.IOException;
import java.io.InputStream;

public class b extends InputStream implements a {
    private final InputStream j;
    private final int k;
    private boolean l = false;
    private long m = 0;
    private int n = 0;
    private int o = 0;

    public b(InputStream inputStream, int i) {
        this.k = i;
        this.j = inputStream;
    }

    public int read() throws IOException {
        return a(8);
    }

    public void a() {
        this.l = true;
    }

    public int a(int i) throws IOException {
        int read;
        while (this.n < i) {
            read = this.j.read();
            if (read >= 0) {
                read &= 255;
                if (this.k == 77) {
                    this.o = read | (this.o << 8);
                } else if (this.k == 73) {
                    this.o = (read << this.n) | this.o;
                } else {
                    throw new IOException("Unknown byte order: " + this.k);
                }
                this.m++;
                this.n += 8;
            } else if (this.l) {
                return 257;
            } else {
                return -1;
            }
        }
        read = (1 << i) - 1;
        if (this.k == 77) {
            read &= this.o >> (this.n - i);
        } else if (this.k == 73) {
            read &= this.o;
            this.o >>= i;
        } else {
            throw new IOException("Unknown byte order: " + this.k);
        }
        this.n -= i;
        this.o = ((1 << this.n) - 1) & this.o;
        return read;
    }

    public void b() {
        this.n = 0;
        this.o = 0;
    }

    public long c() {
        return this.m;
    }
}

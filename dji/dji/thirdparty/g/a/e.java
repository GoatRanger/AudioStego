package dji.thirdparty.g.a;

import dji.thirdparty.g.f;
import java.io.IOException;
import java.io.OutputStream;

public class e extends OutputStream implements a {
    protected boolean j = false;
    private int k = 0;
    private final OutputStream l;
    private int m = 77;

    public final void a(boolean z) {
        this.j = z;
    }

    public final boolean a() {
        return this.j;
    }

    public e(OutputStream outputStream, int i) {
        this.m = i;
        this.l = outputStream;
    }

    public e(OutputStream outputStream) {
        this.l = outputStream;
    }

    protected void a(int i, int i2) throws f, IOException {
        if (i != i2) {
            throw new f("Byte Order bytes don't match (" + i + ", " + i2 + ").");
        } else if (i == 77) {
            this.m = i;
        } else if (i == 73) {
            this.m = i;
        } else {
            throw new f("Unknown Byte Order hint: " + i);
        }
    }

    protected void a(int i) {
        this.m = i;
    }

    public int b() {
        return this.m;
    }

    public void write(int i) throws IOException {
        this.l.write(i);
        this.k++;
    }

    public int c() {
        return this.k;
    }

    public final void b(int i) throws f, IOException {
        c(i, 4);
    }

    public final void c(int i) throws f, IOException {
        c(i, 3);
    }

    public final void d(int i) throws f, IOException {
        c(i, 2);
    }

    public final void e(int i) throws f, IOException {
        if (this.m == 77) {
            write((i >> 24) & 255);
            write((i >> 16) & 255);
            write((i >> 8) & 255);
            write(i & 255);
            return;
        }
        write(i & 255);
        write((i >> 8) & 255);
        write((i >> 16) & 255);
        write((i >> 24) & 255);
    }

    public final void f(int i) throws f, IOException {
        if (this.m == 77) {
            write((i >> 8) & 255);
            write(i & 255);
            return;
        }
        write(i & 255);
        write((i >> 8) & 255);
    }

    public final void a(byte[] bArr) throws IOException {
        this.l.write(bArr, 0, bArr.length);
        this.k += bArr.length;
    }

    private byte[] b(int i, int i2) {
        int i3 = 0;
        byte[] bArr = new byte[i2];
        if (this.m == 77) {
            while (i3 < i2) {
                bArr[i3] = (byte) ((i >> (((i2 - i3) - 1) * 8)) & 255);
                i3++;
            }
        } else {
            while (i3 < i2) {
                bArr[i3] = (byte) ((i >> (i3 * 8)) & 255);
                i3++;
            }
        }
        return bArr;
    }

    private final void c(int i, int i2) throws f, IOException {
        write(b(i, i2));
    }
}

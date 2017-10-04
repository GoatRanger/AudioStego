package dji.thirdparty.g.a.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class e {
    private static final int a = 4096;
    private final byte[][] b;
    private int c;
    private final int d;
    private int e;
    private final int f;
    private final a g;
    private final int h;
    private final int i;
    private int j;
    private boolean k;

    public interface a {
        void a(int i);

        void a(int i, int i2);
    }

    public e(int i, int i2) {
        this(i, i2, null);
    }

    public e(int i, int i2, a aVar) {
        this.e = -1;
        this.j = 0;
        this.k = false;
        this.g = aVar;
        this.f = i2;
        this.d = i;
        this.b = new byte[4096][];
        this.h = 1 << i;
        this.i = this.h + 1;
        if (aVar != null) {
            aVar.a(this.h, this.i);
        }
        b();
    }

    private final void b() {
        this.c = this.d;
        int i = 1 << (this.c + 2);
        for (int i2 = 0; i2 < i; i2++) {
            this.b[i2] = new byte[]{(byte) i2};
        }
    }

    private final void c() {
        this.e = (1 << this.d) + 2;
        this.c = this.d;
        e();
    }

    private final int a(b bVar) throws IOException {
        int a = bVar.a(this.c);
        if (this.g != null) {
            this.g.a(a);
        }
        return a;
    }

    private final byte[] a(int i) throws IOException {
        if (i < this.e && i >= 0) {
            return this.b[i];
        }
        throw new IOException("Bad Code: " + i + " codes: " + this.e + " code_size: " + this.c + ", table: " + this.b.length);
    }

    private final boolean b(int i) {
        return i < this.e;
    }

    private final byte a(byte[] bArr) {
        return bArr[0];
    }

    private final void b(byte[] bArr) throws IOException {
        if (this.e < (1 << this.c)) {
            this.b[this.e] = bArr;
            this.e++;
            d();
            return;
        }
        throw new IOException("AddStringToTable: codes: " + this.e + " code_size: " + this.c);
    }

    private final byte[] a(byte[] bArr, byte b) {
        Object obj = new byte[(bArr.length + 1)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        obj[obj.length - 1] = b;
        return obj;
    }

    private final void a(OutputStream outputStream, byte[] bArr) throws IOException {
        outputStream.write(bArr);
        this.j += bArr.length;
    }

    public void a() {
        this.k = true;
    }

    public byte[] a(InputStream inputStream, int i) throws IOException {
        int i2 = -1;
        b bVar = new b(inputStream, this.f);
        if (this.k) {
            bVar.a();
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        c();
        do {
            int a = a(bVar);
            if (a == this.i) {
                break;
            } else if (a == this.h) {
                c();
                if (this.j < i) {
                    i2 = a(bVar);
                    if (i2 == this.i) {
                        break;
                    }
                    a(byteArrayOutputStream, a(i2));
                } else {
                    break;
                }
            } else if (b(a)) {
                a(byteArrayOutputStream, a(a));
                b(a(a(i2), a(a(a))));
                i2 = a;
            } else {
                byte[] a2 = a(a(i2), a(a(i2)));
                a(byteArrayOutputStream, a2);
                b(a2);
                i2 = a;
            }
        } while (this.j < i);
        return byteArrayOutputStream.toByteArray();
    }

    private final void d() {
        int i = 1 << this.c;
        if (this.k) {
            i--;
        }
        if (this.e == i) {
            e();
        }
    }

    private final void e() {
        if (this.c != 12) {
            this.c++;
        }
    }
}

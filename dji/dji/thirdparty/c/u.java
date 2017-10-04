package dji.thirdparty.c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

final class u extends f {
    final transient byte[][] f;
    final transient int[] g;

    u(c cVar, int i) {
        int i2 = 0;
        super(null);
        y.a(cVar.c, 0, (long) i);
        s sVar = cVar.b;
        int i3 = 0;
        int i4 = 0;
        while (i4 < i) {
            if (sVar.e == sVar.d) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += sVar.e - sVar.d;
            i3++;
            sVar = sVar.h;
        }
        this.f = new byte[i3][];
        this.g = new int[(i3 * 2)];
        s sVar2 = cVar.b;
        i4 = 0;
        while (i2 < i) {
            this.f[i4] = sVar2.c;
            int i5 = (sVar2.e - sVar2.d) + i2;
            if (i5 > i) {
                i5 = i;
            }
            this.g[i4] = i5;
            this.g[this.f.length + i4] = sVar2.d;
            sVar2.f = true;
            i4++;
            sVar2 = sVar2.h;
            i2 = i5;
        }
    }

    public String a() {
        return n().a();
    }

    public String b() {
        return n().b();
    }

    public String g() {
        return n().g();
    }

    public f h() {
        return n().h();
    }

    public f i() {
        return n().i();
    }

    public f c() {
        return n().c();
    }

    public f e() {
        return n().e();
    }

    public String f() {
        return n().f();
    }

    public f a(int i) {
        return n().a(i);
    }

    public f a(int i, int i2) {
        return n().a(i, i2);
    }

    public byte b(int i) {
        y.a((long) this.g[this.f.length - 1], (long) i, 1);
        int c = c(i);
        return this.f[c][(i - (c == 0 ? 0 : this.g[c - 1])) + this.g[this.f.length + c]];
    }

    private int c(int i) {
        int binarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return binarySearch >= 0 ? binarySearch : binarySearch ^ -1;
    }

    public int j() {
        return this.g[this.f.length - 1];
    }

    public byte[] k() {
        int i = 0;
        Object obj = new byte[this.g[this.f.length - 1]];
        int length = this.f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            System.arraycopy(this.f[i], i3, obj, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return obj;
    }

    public ByteBuffer m() {
        return ByteBuffer.wrap(k()).asReadOnlyBuffer();
    }

    public void a(OutputStream outputStream) throws IOException {
        int i = 0;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            outputStream.write(this.f[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    void a(c cVar) {
        int i = 0;
        int length = this.f.length;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            s sVar = new s(this.f[i], i3, (i3 + i4) - i2);
            if (cVar.b == null) {
                sVar.i = sVar;
                sVar.h = sVar;
                cVar.b = sVar;
            } else {
                cVar.b.i.a(sVar);
            }
            i++;
            i2 = i4;
        }
        cVar.c = ((long) i2) + cVar.c;
    }

    public boolean a(int i, f fVar, int i2, int i3) {
        if (i > j() - i3) {
            return false;
        }
        int c = c(i);
        while (i3 > 0) {
            int i4 = c == 0 ? 0 : this.g[c - 1];
            int min = Math.min(i3, ((this.g[c] - i4) + i4) - i);
            if (!fVar.a(i2, this.f[c], (i - i4) + this.g[this.f.length + c], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            c++;
        }
        return true;
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i > j() - i3 || i2 > bArr.length - i3) {
            return false;
        }
        int c = c(i);
        while (i3 > 0) {
            int i4 = c == 0 ? 0 : this.g[c - 1];
            int min = Math.min(i3, ((this.g[c] - i4) + i4) - i);
            if (!y.a(this.f[c], (i - i4) + this.g[this.f.length + c], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            c++;
        }
        return true;
    }

    private f n() {
        return new f(k());
    }

    byte[] l() {
        return k();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        boolean z = (obj instanceof f) && ((f) obj).j() == j() && a(0, (f) obj, 0, j());
        return z;
    }

    public int hashCode() {
        int i = this.d;
        if (i == 0) {
            i = 1;
            int length = this.f.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                byte[] bArr = this.f[i2];
                int i4 = this.g[length + i2];
                int i5 = this.g[i2];
                i3 = (i5 - i3) + i4;
                int i6 = i4;
                i4 = i;
                for (i = i6; i < i3; i++) {
                    i4 = (i4 * 31) + bArr[i];
                }
                i2++;
                i3 = i5;
                i = i4;
            }
            this.d = i;
        }
        return i;
    }

    public String toString() {
        return n().toString();
    }

    private Object o() {
        return n();
    }
}

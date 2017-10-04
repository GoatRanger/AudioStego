package dji.thirdparty.g.a.b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class d {
    private int a;
    private final int b;
    private int c;
    private final int d;
    private final boolean e;
    private final int f;
    private final int g;
    private final b h;
    private final Map i;

    private static final class a {
        private final byte[] a;
        private final int b;
        private final int c;
        private final int d;

        public a(byte[] bArr) {
            this(bArr, 0, bArr.length);
        }

        public a(byte[] bArr, int i, int i2) {
            this.a = bArr;
            this.b = i;
            this.c = i2;
            int i3 = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                i3 = ((i3 + (i3 << 8)) ^ (bArr[i4 + i] & 255)) ^ i4;
            }
            this.d = i3;
        }

        public final int hashCode() {
            return this.d;
        }

        public final boolean equals(Object obj) {
            a aVar = (a) obj;
            if (aVar.d != this.d || aVar.c != this.c) {
                return false;
            }
            for (int i = 0; i < this.c; i++) {
                if (aVar.a[aVar.b + i] != this.a[this.b + i]) {
                    return false;
                }
            }
            return true;
        }
    }

    public interface b {
        void a(int i);

        void a(int i, int i2);

        void b(int i);

        void c(int i);
    }

    public d(int i, int i2, boolean z) {
        this(i, i2, z, null);
    }

    public d(int i, int i2, boolean z, b bVar) {
        this.c = -1;
        this.i = new HashMap();
        this.h = bVar;
        this.d = i2;
        this.e = z;
        this.b = i;
        this.f = 1 << i;
        this.g = this.f + 1;
        if (bVar != null) {
            bVar.a(this.f, this.g);
        }
        a();
    }

    private final void a() {
        this.a = this.b;
        int i = (1 << this.a) + 2;
        this.i.clear();
        this.c = 0;
        while (this.c < i) {
            if (!(this.c == this.f || this.c == this.g)) {
                this.i.put(a((byte) this.c), new Integer(this.c));
            }
            this.c++;
        }
    }

    private final void b() {
        a();
        c();
    }

    private final void c() {
        if (this.a != 12) {
            this.a++;
        }
    }

    private final Object a(byte b) {
        return a(new byte[]{b}, 0, 1);
    }

    private final Object a(byte[] bArr, int i, int i2) {
        return new a(bArr, i, i2);
    }

    private final void a(c cVar, int i) throws IOException {
        if (this.h != null) {
            this.h.a(i);
        }
        b(cVar, i);
    }

    private final void a(c cVar) throws IOException {
        if (this.h != null) {
            this.h.a(this.f);
        }
        b(cVar, this.f);
    }

    private final void b(c cVar) throws IOException {
        if (this.h != null) {
            this.h.b(this.g);
        }
        b(cVar, this.g);
    }

    private final void b(c cVar, int i) throws IOException {
        cVar.a(i, this.a);
    }

    private final boolean b(byte[] bArr, int i, int i2) {
        return this.i.containsKey(a(bArr, i, i2));
    }

    private final int c(byte[] bArr, int i, int i2) throws IOException {
        Object obj = this.i.get(a(bArr, i, i2));
        if (obj != null) {
            return ((Integer) obj).intValue();
        }
        throw new IOException("CodeFromString");
    }

    private final boolean a(c cVar, byte[] bArr, int i, int i2) throws IOException {
        return a(cVar, a(bArr, i, i2));
    }

    private final boolean a(c cVar, Object obj) throws IOException {
        boolean z;
        int i = 1 << this.a;
        if (this.e) {
            i--;
        }
        if (this.c != i) {
            z = false;
        } else if (this.a < 12) {
            c();
            z = false;
        } else {
            a(cVar);
            b();
            z = true;
        }
        if (!z) {
            this.i.put(obj, new Integer(this.c));
            this.c++;
        }
        return z;
    }

    public byte[] a(byte[] bArr) throws IOException {
        int i = 0;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        c cVar = new c(byteArrayOutputStream, this.d);
        a();
        b();
        a(cVar);
        int i2 = 0;
        int i3 = 0;
        while (i < bArr.length) {
            if (b(bArr, i3, i2 + 1)) {
                i2++;
            } else {
                a(cVar, c(bArr, i3, i2));
                a(cVar, bArr, i3, i2 + 1);
                i2 = 1;
                i3 = i;
            }
            i++;
        }
        a(cVar, c(bArr, i3, i2));
        b(cVar);
        cVar.a();
        return byteArrayOutputStream.toByteArray();
    }
}

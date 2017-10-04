package dji.thirdparty.g.a.a;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class d extends a {
    private static final int n = 1024;
    private final InputStream l;
    private a m = null;
    private byte[] o = null;
    private Long p = null;

    private class a {
        public final byte[] a;
        final /* synthetic */ d b;
        private a c = null;
        private boolean d = false;

        public a(d dVar, byte[] bArr) {
            this.b = dVar;
            this.a = bArr;
        }

        public a a() throws IOException {
            if (this.c != null) {
                return this.c;
            }
            if (this.d) {
                return null;
            }
            this.d = true;
            this.c = this.b.g();
            return this.c;
        }
    }

    private class b extends InputStream {
        final /* synthetic */ d a;
        private a b;
        private boolean c;
        private int d;

        private b(d dVar) {
            this.a = dVar;
            this.b = null;
            this.c = false;
            this.d = 0;
        }

        public int read() throws IOException {
            if (this.b == null) {
                if (this.c) {
                    return -1;
                }
                this.b = this.a.h();
                this.c = true;
            }
            if (this.b != null && this.d >= this.b.a.length) {
                this.b = this.b.a();
                this.d = 0;
            }
            if (this.b == null || this.d >= this.b.a.length) {
                return -1;
            }
            byte[] bArr = this.b.a;
            int i = this.d;
            this.d = i + 1;
            return bArr[i] & 255;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (bArr == null) {
                throw new NullPointerException();
            } else if (i < 0 || i > bArr.length || i2 < 0 || i + i2 > bArr.length || i + i2 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 == 0) {
                return 0;
            } else {
                if (this.b == null) {
                    if (this.c) {
                        return -1;
                    }
                    this.b = this.a.h();
                    this.c = true;
                }
                if (this.b != null && this.d >= this.b.a.length) {
                    this.b = this.b.a();
                    this.d = 0;
                }
                if (this.b == null) {
                    return -1;
                }
                if (this.d >= this.b.a.length) {
                    return -1;
                }
                int min = Math.min(i2, this.b.a.length - this.d);
                System.arraycopy(this.b.a, this.d, bArr, i, min);
                this.d += min;
                return min;
            }
        }
    }

    public d(InputStream inputStream, String str) {
        super(str);
        this.l = new BufferedInputStream(inputStream);
    }

    private a g() throws IOException {
        if (this.o == null) {
            this.o = new byte[1024];
        }
        int read = this.l.read(this.o);
        if (read < 1) {
            return null;
        }
        if (read < 1024) {
            Object obj = new byte[read];
            System.arraycopy(this.o, 0, obj, 0, read);
            return new a(this, obj);
        }
        byte[] bArr = this.o;
        this.o = null;
        return new a(this, bArr);
    }

    private a h() throws IOException {
        if (this.m == null) {
            this.m = g();
        }
        return this.m;
    }

    public InputStream a() throws IOException {
        return new b();
    }

    public byte[] c(int i, int i2) throws IOException {
        InputStream a = a();
        a.skip((long) i);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        do {
            int read = a.read(bArr, i3, bArr.length - i3);
            if (read < 1) {
                throw new IOException("Could not read block.");
            }
            i3 += read;
        } while (i3 < i2);
        return bArr;
    }

    public long c() throws IOException {
        if (this.p != null) {
            return this.p.longValue();
        }
        InputStream a = a();
        long j = 0;
        while (true) {
            long skip = a.skip(1024);
            if (skip > 0) {
                j += skip;
            } else {
                this.p = new Long(j);
                return j;
            }
        }
    }

    public byte[] b() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (a h = h(); h != null; h = h.a()) {
            byteArrayOutputStream.write(h.a);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String d() {
        return "Inputstream: '" + this.k + "'";
    }
}

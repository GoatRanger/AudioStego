package dji.thirdparty.c;

import com.here.posclient.UpdateOptions;
import dji.thirdparty.g.b.a.a;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.xeustechnologies.jtar.TarHeader;

final class r implements e {
    public final c a = new c();
    public final w b;
    boolean c;

    r(w wVar) {
        if (wVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.b = wVar;
    }

    public c c() {
        return this.a;
    }

    public long a(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else if (this.a.c == 0 && this.b.a(this.a, 8192) == -1) {
            return -1;
        } else {
            return this.a.a(cVar, Math.min(j, this.a.c));
        }
    }

    public boolean g() throws IOException {
        if (!this.c) {
            return this.a.g() && this.b.a(this.a, 8192) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    public void a(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    public boolean b(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.c) {
            throw new IllegalStateException("closed");
        } else {
            while (this.a.c < j) {
                if (this.b.a(this.a, 8192) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    public byte j() throws IOException {
        a(1);
        return this.a.j();
    }

    public f s() throws IOException {
        this.a.a(this.b);
        return this.a.s();
    }

    public f d(long j) throws IOException {
        a(j);
        return this.a.d(j);
    }

    public byte[] x() throws IOException {
        this.a.a(this.b);
        return this.a.x();
    }

    public byte[] g(long j) throws IOException {
        a(j);
        return this.a.g(j);
    }

    public int a(byte[] bArr) throws IOException {
        return a(bArr, 0, bArr.length);
    }

    public void b(byte[] bArr) throws IOException {
        try {
            a((long) bArr.length);
            this.a.b(bArr);
        } catch (EOFException e) {
            EOFException eOFException = e;
            int i = 0;
            while (this.a.c > 0) {
                int a = this.a.a(bArr, i, (int) this.a.c);
                if (a == -1) {
                    throw new AssertionError();
                }
                i += a;
            }
            throw eOFException;
        }
    }

    public int a(byte[] bArr, int i, int i2) throws IOException {
        y.a((long) bArr.length, (long) i, (long) i2);
        if (this.a.c == 0 && this.b.a(this.a, 8192) == -1) {
            return -1;
        }
        return this.a.a(bArr, i, (int) Math.min((long) i2, this.a.c));
    }

    public void b(c cVar, long j) throws IOException {
        try {
            a(j);
            this.a.b(cVar, j);
        } catch (EOFException e) {
            cVar.a(this.a);
            throw e;
        }
    }

    public long a(v vVar) throws IOException {
        if (vVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.b.a(this.a, 8192) != -1) {
            long i = this.a.i();
            if (i > 0) {
                j += i;
                vVar.a_(this.a, i);
            }
        }
        if (this.a.b() <= 0) {
            return j;
        }
        j += this.a.b();
        vVar.a_(this.a, this.a.b());
        return j;
    }

    public String t() throws IOException {
        this.a.a(this.b);
        return this.a.t();
    }

    public String e(long j) throws IOException {
        a(j);
        return this.a.e(j);
    }

    public String a(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.a.a(this.b);
        return this.a.a(charset);
    }

    public String a(long j, Charset charset) throws IOException {
        a(j);
        if (charset != null) {
            return this.a.a(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    public String u() throws IOException {
        long a = a((byte) 10);
        if (a == -1) {
            return this.a.c != 0 ? e(this.a.c) : null;
        } else {
            return this.a.f(a);
        }
    }

    public String v() throws IOException {
        long a = a((byte) 10);
        if (a != -1) {
            return this.a.f(a);
        }
        c cVar = new c();
        this.a.a(cVar, 0, Math.min(32, this.a.b()));
        throw new EOFException("\\n not found: size=" + this.a.b() + " content=" + cVar.s().g() + "...");
    }

    public int w() throws IOException {
        a(1);
        byte c = this.a.c(0);
        if ((c & a.fw_) == 192) {
            a(2);
        } else if ((c & 240) == a.fw_) {
            a(3);
        } else if ((c & 248) == 240) {
            a(4);
        }
        return this.a.w();
    }

    public short k() throws IOException {
        a(2);
        return this.a.k();
    }

    public short n() throws IOException {
        a(2);
        return this.a.n();
    }

    public int l() throws IOException {
        a(4);
        return this.a.l();
    }

    public int o() throws IOException {
        a(4);
        return this.a.o();
    }

    public long m() throws IOException {
        a(8);
        return this.a.m();
    }

    public long p() throws IOException {
        a(8);
        return this.a.p();
    }

    public long q() throws IOException {
        a(1);
        int i = 0;
        while (b((long) (i + 1))) {
            byte c = this.a.c((long) i);
            if ((c < TarHeader.LF_NORMAL || c > (byte) 57) && !(i == 0 && c == (byte) 45)) {
                if (i == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", new Object[]{Byte.valueOf(c)}));
                }
                return this.a.q();
            }
            i++;
        }
        return this.a.q();
    }

    public long r() throws IOException {
        a(1);
        for (int i = 0; b((long) (i + 1)); i++) {
            byte c = this.a.c((long) i);
            if ((c < TarHeader.LF_NORMAL || c > (byte) 57) && ((c < (byte) 97 || c > (byte) 102) && (c < (byte) 65 || c > (byte) 70))) {
                if (i == 0) {
                    throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", new Object[]{Byte.valueOf(c)}));
                }
                return this.a.r();
            }
        }
        return this.a.r();
    }

    public void h(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.a.c == 0 && this.b.a(this.a, 8192) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.a.b());
            this.a.h(min);
            j -= min;
        }
    }

    public long a(byte b) throws IOException {
        return a(b, 0);
    }

    public long a(byte b, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a = this.a.a(b, j);
            if (a != -1) {
                return a;
            }
            a = this.a.c;
            if (this.b.a(this.a, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, a);
        }
    }

    public long b(f fVar) throws IOException {
        return a(fVar, 0);
    }

    public long a(f fVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long a = this.a.a(fVar, j);
            if (a != -1) {
                return a;
            }
            a = this.a.c;
            if (this.b.a(this.a, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, (a - ((long) fVar.j())) + 1);
        }
    }

    public long c(f fVar) throws IOException {
        return b(fVar, 0);
    }

    public long b(f fVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long b = this.a.b(fVar, j);
            if (b != -1) {
                return b;
            }
            b = this.a.c;
            if (this.b.a(this.a, 8192) == -1) {
                return -1;
            }
            j = Math.max(j, b);
        }
    }

    public InputStream h() {
        return new InputStream(this) {
            final /* synthetic */ r a;

            {
                this.a = r1;
            }

            public int read() throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                } else if (this.a.a.c == 0 && this.a.b.a(this.a.a, 8192) == -1) {
                    return -1;
                } else {
                    return this.a.a.j() & 255;
                }
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                y.a((long) bArr.length, (long) i, (long) i2);
                if (this.a.a.c == 0 && this.a.b.a(this.a.a, 8192) == -1) {
                    return -1;
                }
                return this.a.a.a(bArr, i, i2);
            }

            public int available() throws IOException {
                if (!this.a.c) {
                    return (int) Math.min(this.a.a.c, UpdateOptions.SOURCE_ANY);
                }
                throw new IOException("closed");
            }

            public void close() throws IOException {
                this.a.close();
            }

            public String toString() {
                return this.a + ".inputStream()";
            }
        };
    }

    public void close() throws IOException {
        if (!this.c) {
            this.c = true;
            this.b.close();
            this.a.y();
        }
    }

    public x a() {
        return this.b.a();
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }
}

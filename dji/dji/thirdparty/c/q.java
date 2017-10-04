package dji.thirdparty.c;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

final class q implements d {
    public final c a = new c();
    public final v b;
    boolean c;

    q(v vVar) {
        if (vVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = vVar;
    }

    public c c() {
        return this.a;
    }

    public void a_(c cVar, long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a_(cVar, j);
        F();
    }

    public d d(f fVar) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(fVar);
        return F();
    }

    public d b(String str) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str);
        return F();
    }

    public d b(String str, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str, i, i2);
        return F();
    }

    public d n(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(i);
        return F();
    }

    public d b(String str, Charset charset) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str, charset);
        return F();
    }

    public d b(String str, int i, int i2, Charset charset) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.a(str, i, i2, charset);
        return F();
    }

    public d d(byte[] bArr) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(bArr);
        return F();
    }

    public d c(byte[] bArr, int i, int i2) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(bArr, i, i2);
        return F();
    }

    public long a(w wVar) throws IOException {
        if (wVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long a = wVar.a(this.a, 8192);
            if (a == -1) {
                return j;
            }
            j += a;
            F();
        }
    }

    public d a(w wVar, long j) throws IOException {
        while (j > 0) {
            long a = wVar.a(this.a, j);
            if (a == -1) {
                throw new EOFException();
            }
            j -= a;
            F();
        }
        return this;
    }

    public d m(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.b(i);
        return F();
    }

    public d l(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.c(i);
        return F();
    }

    public d k(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.d(i);
        return F();
    }

    public d j(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.e(i);
        return F();
    }

    public d i(int i) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.f(i);
        return F();
    }

    public d p(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.i(j);
        return F();
    }

    public d o(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.j(j);
        return F();
    }

    public d n(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.k(j);
        return F();
    }

    public d m(long j) throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.a.l(j);
        return F();
    }

    public d F() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long i = this.a.i();
        if (i > 0) {
            this.b.a_(this.a, i);
        }
        return this;
    }

    public d f() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long b = this.a.b();
        if (b > 0) {
            this.b.a_(this.a, b);
        }
        return this;
    }

    public OutputStream d() {
        return new OutputStream(this) {
            final /* synthetic */ q a;

            {
                this.a = r1;
            }

            public void write(int i) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                this.a.a.b((byte) i);
                this.a.F();
            }

            public void write(byte[] bArr, int i, int i2) throws IOException {
                if (this.a.c) {
                    throw new IOException("closed");
                }
                this.a.a.b(bArr, i, i2);
                this.a.F();
            }

            public void flush() throws IOException {
                if (!this.a.c) {
                    this.a.flush();
                }
            }

            public void close() throws IOException {
                this.a.close();
            }

            public String toString() {
                return this.a + ".outputStream()";
            }
        };
    }

    public void flush() throws IOException {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        if (this.a.c > 0) {
            this.b.a_(this.a, this.a.c);
        }
        this.b.flush();
    }

    public void close() throws IOException {
        if (!this.c) {
            Throwable th = null;
            try {
                if (this.a.c > 0) {
                    this.b.a_(this.a, this.a.c);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.b.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.c = true;
            if (th != null) {
                y.a(th);
            }
        }
    }

    public x a() {
        return this.b.a();
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }
}

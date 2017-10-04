package dji.thirdparty.c;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class k implements v {
    private final d a;
    private final Deflater b;
    private final g c;
    private boolean d;
    private final CRC32 e = new CRC32();

    public k(v vVar) {
        if (vVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.b = new Deflater(-1, true);
        this.a = p.a(vVar);
        this.c = new g(this.a, this.b);
        b();
    }

    public void a_(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j != 0) {
            b(cVar, j);
            this.c.a_(cVar, j);
        }
    }

    public void flush() throws IOException {
        this.c.flush();
    }

    public x a() {
        return this.a.a();
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.d) {
            Throwable th2 = null;
            try {
                this.c.b();
                c();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.b.end();
                th3 = th2;
            } catch (Throwable th4) {
                th3 = th4;
                if (th2 != null) {
                    th3 = th2;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th22) {
                if (th3 == null) {
                    th3 = th22;
                }
            }
            this.d = true;
            if (th3 != null) {
                y.a(th3);
            }
        }
    }

    private void b() {
        c c = this.a.c();
        c.c(8075);
        c.b(8);
        c.b(0);
        c.e(0);
        c.b(0);
        c.b(0);
    }

    private void c() throws IOException {
        this.a.i((int) this.e.getValue());
        this.a.i(this.b.getTotalIn());
    }

    private void b(c cVar, long j) {
        s sVar = cVar.b;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (sVar.e - sVar.d));
            this.e.update(sVar.c, sVar.d, min);
            j -= (long) min;
            sVar = sVar.h;
        }
    }
}

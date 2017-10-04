package dji.thirdparty.c;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class g implements v {
    private final d a;
    private final Deflater b;
    private boolean c;

    public g(v vVar, Deflater deflater) {
        this(p.a(vVar), deflater);
    }

    g(d dVar, Deflater deflater) {
        if (dVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = dVar;
            this.b = deflater;
        }
    }

    public void a_(c cVar, long j) throws IOException {
        y.a(cVar.c, 0, j);
        while (j > 0) {
            s sVar = cVar.b;
            int min = (int) Math.min(j, (long) (sVar.e - sVar.d));
            this.b.setInput(sVar.c, sVar.d, min);
            a(false);
            cVar.c -= (long) min;
            sVar.d += min;
            if (sVar.d == sVar.e) {
                cVar.b = sVar.a();
                t.a(sVar);
            }
            j -= (long) min;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        c c = this.a.c();
        while (true) {
            int deflate;
            s g = c.g(1);
            if (z) {
                deflate = this.b.deflate(g.c, g.e, 8192 - g.e, 2);
            } else {
                deflate = this.b.deflate(g.c, g.e, 8192 - g.e);
            }
            if (deflate > 0) {
                g.e += deflate;
                c.c += (long) deflate;
                this.a.F();
            } else if (this.b.needsInput()) {
                break;
            }
        }
        if (g.d == g.e) {
            c.b = g.a();
            t.a(g);
        }
    }

    public void flush() throws IOException {
        a(true);
        this.a.flush();
    }

    void b() throws IOException {
        this.b.finish();
        a(false);
    }

    public void close() throws IOException {
        Throwable th;
        if (!this.c) {
            Throwable th2 = null;
            try {
                b();
            } catch (Throwable th3) {
                th2 = th3;
            }
            try {
                this.b.end();
                th = th2;
            } catch (Throwable th4) {
                th = th4;
                if (th2 != null) {
                    th = th2;
                }
            }
            try {
                this.a.close();
            } catch (Throwable th22) {
                if (th == null) {
                    th = th22;
                }
            }
            this.c = true;
            if (th != null) {
                y.a(th);
            }
        }
    }

    public x a() {
        return this.a.a();
    }

    public String toString() {
        return "DeflaterSink(" + this.a + ")";
    }
}

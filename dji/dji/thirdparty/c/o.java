package dji.thirdparty.c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.Inflater;

public final class o implements w {
    private final e a;
    private final Inflater b;
    private int c;
    private boolean d;

    public o(w wVar, Inflater inflater) {
        this(p.a(wVar), inflater);
    }

    o(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        } else {
            this.a = eVar;
            this.b = inflater;
        }
    }

    public long a(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.d) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            boolean b;
            do {
                b = b();
                try {
                    s g = cVar.g(1);
                    int inflate = this.b.inflate(g.c, g.e, 8192 - g.e);
                    if (inflate > 0) {
                        g.e += inflate;
                        cVar.c += (long) inflate;
                        return (long) inflate;
                    } else if (this.b.finished() || this.b.needsDictionary()) {
                        c();
                        if (g.d == g.e) {
                            cVar.b = g.a();
                            t.a(g);
                        }
                        return -1;
                    }
                } catch (Throwable e) {
                    throw new IOException(e);
                }
            } while (!b);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean b() throws IOException {
        if (!this.b.needsInput()) {
            return false;
        }
        c();
        if (this.b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.a.g()) {
            return true;
        } else {
            s sVar = this.a.c().b;
            this.c = sVar.e - sVar.d;
            this.b.setInput(sVar.c, sVar.d, this.c);
            return false;
        }
    }

    private void c() throws IOException {
        if (this.c != 0) {
            int remaining = this.c - this.b.getRemaining();
            this.c -= remaining;
            this.a.h((long) remaining);
        }
    }

    public x a() {
        return this.a.a();
    }

    public void close() throws IOException {
        if (!this.d) {
            this.b.end();
            this.d = true;
            this.a.close();
        }
    }
}

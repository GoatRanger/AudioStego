package dji.thirdparty.b.a.b;

import dji.thirdparty.b.a.j;
import dji.thirdparty.c.c;
import dji.thirdparty.c.v;
import dji.thirdparty.c.x;
import java.io.IOException;
import java.net.ProtocolException;

public final class n implements v {
    private boolean a;
    private final int b;
    private final c c;

    public n(int i) {
        this.c = new c();
        this.b = i;
    }

    public n() {
        this(-1);
    }

    public void close() throws IOException {
        if (!this.a) {
            this.a = true;
            if (this.c.b() < ((long) this.b)) {
                throw new ProtocolException("content-length promised " + this.b + " bytes, but received " + this.c.b());
            }
        }
    }

    public void a_(c cVar, long j) throws IOException {
        if (this.a) {
            throw new IllegalStateException("closed");
        }
        j.a(cVar.b(), 0, j);
        if (this.b == -1 || this.c.b() <= ((long) this.b) - j) {
            this.c.a_(cVar, j);
            return;
        }
        throw new ProtocolException("exceeded content-length limit of " + this.b + " bytes");
    }

    public void flush() throws IOException {
    }

    public x a() {
        return x.b;
    }

    public long b() throws IOException {
        return this.c.b();
    }

    public void a(v vVar) throws IOException {
        c cVar = new c();
        this.c.a(cVar, 0, this.c.b());
        vVar.a_(cVar, cVar.b());
    }
}

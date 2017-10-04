package dji.thirdparty.c;

import java.io.IOException;

public abstract class h implements v {
    private final v a;

    public h(v vVar) {
        if (vVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = vVar;
    }

    public final v b() {
        return this.a;
    }

    public void a_(c cVar, long j) throws IOException {
        this.a.a_(cVar, j);
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public x a() {
        return this.a.a();
    }

    public void close() throws IOException {
        this.a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.a.toString() + ")";
    }
}

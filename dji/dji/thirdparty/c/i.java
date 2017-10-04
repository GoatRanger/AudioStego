package dji.thirdparty.c;

import java.io.IOException;

public abstract class i implements w {
    private final w a;

    public i(w wVar) {
        if (wVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = wVar;
    }

    public final w b() {
        return this.a;
    }

    public long a(c cVar, long j) throws IOException {
        return this.a.a(cVar, j);
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

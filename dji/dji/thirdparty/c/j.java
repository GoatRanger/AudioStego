package dji.thirdparty.c;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class j extends x {
    private x a;

    public j(x xVar) {
        if (xVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = xVar;
    }

    public final x a() {
        return this.a;
    }

    public final j a(x xVar) {
        if (xVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.a = xVar;
        return this;
    }

    public x a(long j, TimeUnit timeUnit) {
        return this.a.a(j, timeUnit);
    }

    public long k_() {
        return this.a.k_();
    }

    public boolean l_() {
        return this.a.l_();
    }

    public long d() {
        return this.a.d();
    }

    public x a(long j) {
        return this.a.a(j);
    }

    public x m_() {
        return this.a.m_();
    }

    public x f() {
        return this.a.f();
    }

    public void g() throws IOException {
        this.a.g();
    }
}

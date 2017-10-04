package dji.thirdparty.f.j;

import dji.thirdparty.f.e.c.a;
import dji.thirdparty.f.e.c.b;
import dji.thirdparty.f.e.d.j;
import dji.thirdparty.f.g;
import dji.thirdparty.f.i.d;
import java.util.concurrent.Executor;

public final class e {
    private static final e d = new e();
    private final g a;
    private final g b;
    private final g c;

    private e() {
        g a = d.getInstance().e().a();
        if (a != null) {
            this.a = a;
        } else {
            this.a = new a();
        }
        a = d.getInstance().e().b();
        if (a != null) {
            this.b = a;
        } else {
            this.b = new a();
        }
        a = d.getInstance().e().c();
        if (a != null) {
            this.c = a;
        } else {
            this.c = d.c();
        }
    }

    public static g a() {
        return c.c();
    }

    public static g b() {
        return j.c();
    }

    public static g c() {
        return d.c;
    }

    public static g d() {
        return d.a;
    }

    public static g e() {
        return d.b;
    }

    public static g f() {
        return new g();
    }

    public static g a(Executor executor) {
        return new b(executor);
    }

    static void g() {
        e eVar = d;
        synchronized (eVar) {
            if (eVar.a instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.a).c();
            }
            if (eVar.b instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.b).c();
            }
            if (eVar.c instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.c).c();
            }
            b.a.c();
            j.d.c();
            j.e.c();
        }
    }

    public static void h() {
        e eVar = d;
        synchronized (eVar) {
            if (eVar.a instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.a).d();
            }
            if (eVar.b instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.b).d();
            }
            if (eVar.c instanceof dji.thirdparty.f.e.c.e) {
                ((dji.thirdparty.f.e.c.e) eVar.c).d();
            }
            b.a.d();
            j.d.d();
            j.e.d();
        }
    }
}

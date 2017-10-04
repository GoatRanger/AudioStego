package dji.thirdparty.f.l;

import dji.thirdparty.f.b.a;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.e.a.r;
import java.util.ArrayList;
import java.util.List;

public final class c<T> extends f<T, T> {
    final g<T> c;
    private final r<T> d = r.a();

    public static <T> c<T> I() {
        final Object gVar = new g();
        gVar.e = new dji.thirdparty.f.d.c<b<T>>() {
            public void a(b<T> bVar) {
                bVar.b(gVar.a(), gVar.f);
            }
        };
        return new c(gVar, gVar);
    }

    protected c(d$f<T> dji_thirdparty_f_d_f_T, g<T> gVar) {
        super(dji_thirdparty_f_d_f_T);
        this.c = gVar;
    }

    public void o_() {
        if (this.c.b) {
            Object b = this.d.b();
            for (b a : this.c.d(b)) {
                a.a(b, this.c.f);
            }
        }
    }

    public void a(Throwable th) {
        if (this.c.b) {
            Object a = this.d.a(th);
            List list = null;
            for (b a2 : this.c.d(a)) {
                try {
                    a2.a(a, this.c.f);
                } catch (Throwable th2) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(th2);
                }
            }
            b.a(list);
        }
    }

    public void a_(T t) {
        for (b a_ : this.c.b()) {
            a_.a_(t);
        }
    }

    public boolean J() {
        return this.c.b().length > 0;
    }

    @a
    public boolean K() {
        return this.d.c(this.c.a());
    }

    @a
    public boolean L() {
        Object a = this.c.a();
        return (a == null || this.d.c(a)) ? false : true;
    }

    @a
    public Throwable M() {
        Object a = this.c.a();
        if (this.d.c(a)) {
            return this.d.h(a);
        }
        return null;
    }
}

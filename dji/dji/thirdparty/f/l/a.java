package dji.thirdparty.f.l;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.e.a.r;
import java.util.ArrayList;
import java.util.List;

public final class a<T> extends f<T, T> {
    final g<T> c;
    volatile Object d;
    private final r<T> e = r.a();

    public static <T> a<T> I() {
        final Object gVar = new g();
        gVar.e = new c<b<T>>() {
            public void a(b<T> bVar) {
                Object a = gVar.a();
                r rVar = gVar.f;
                bVar.c(a, rVar);
                if (a == null || !(rVar.b(a) || rVar.c(a))) {
                    bVar.o_();
                }
            }
        };
        return new a(gVar, gVar);
    }

    protected a(d$f<T> dji_thirdparty_f_d_f_T, g<T> gVar) {
        super(dji_thirdparty_f_d_f_T);
        this.c = gVar;
    }

    public void o_() {
        if (this.c.b) {
            Object obj = this.d;
            if (obj == null) {
                obj = this.e.b();
            }
            for (b bVar : this.c.d(obj)) {
                if (obj == this.e.b()) {
                    bVar.o_();
                } else {
                    bVar.a_(this.e.g(obj));
                    bVar.o_();
                }
            }
        }
    }

    public void a(Throwable th) {
        if (this.c.b) {
            List list = null;
            for (b a : this.c.d(this.e.a(th))) {
                try {
                    a.a(th);
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
        this.d = this.e.a((Object) t);
    }

    public boolean J() {
        return this.c.b().length > 0;
    }

    @dji.thirdparty.f.b.a
    public boolean K() {
        return !this.e.c(this.c.a()) && this.e.e(this.d);
    }

    @dji.thirdparty.f.b.a
    public boolean L() {
        return this.e.c(this.c.a());
    }

    @dji.thirdparty.f.b.a
    public boolean M() {
        Object a = this.c.a();
        return (a == null || this.e.c(a)) ? false : true;
    }

    @dji.thirdparty.f.b.a
    public T N() {
        Object obj = this.d;
        if (this.e.c(this.c.a()) || !this.e.e(obj)) {
            return null;
        }
        return this.e.g(obj);
    }

    @dji.thirdparty.f.b.a
    public Throwable O() {
        Object a = this.c.a();
        if (this.e.c(a)) {
            return this.e.h(a);
        }
        return null;
    }
}

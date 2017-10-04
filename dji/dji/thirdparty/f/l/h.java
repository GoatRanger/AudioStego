package dji.thirdparty.f.l;

import dji.thirdparty.f.d$f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.e;
import dji.thirdparty.f.e.a.r;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.j.g;
import java.util.concurrent.TimeUnit;

public final class h<T> extends f<T, T> {
    private final g<T> c;
    private final a d;

    public static <T> h<T> a(g gVar) {
        final Object gVar2 = new g();
        gVar2.d = new c<b<T>>() {
            public void a(b<T> bVar) {
                bVar.b(gVar2.a(), gVar2.f);
            }
        };
        gVar2.e = gVar2.d;
        return new h(gVar2, gVar2, gVar);
    }

    protected h(d$f<T> dji_thirdparty_f_d_f_T, g<T> gVar, g gVar2) {
        super(dji_thirdparty_f_d_f_T);
        this.c = gVar;
        this.d = gVar2.a();
    }

    public void o_() {
        d(0);
    }

    void I() {
        if (this.c.b) {
            for (b o_ : this.c.d(r.a().b())) {
                o_.o_();
            }
        }
    }

    public void d(long j) {
        this.d.a(new b(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.I();
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    public void a(Throwable th) {
        a(th, 0);
    }

    void c(Throwable th) {
        if (this.c.b) {
            for (b a : this.c.d(r.a().a(th))) {
                a.a(th);
            }
        }
    }

    public void a(final Throwable th, long j) {
        this.d.a(new b(this) {
            final /* synthetic */ h b;

            public void a() {
                this.b.c(th);
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    public void a_(T t) {
        a((Object) t, 0);
    }

    void i(T t) {
        for (e a_ : this.c.b()) {
            a_.a_(t);
        }
    }

    public void a(final T t, long j) {
        this.d.a(new b(this) {
            final /* synthetic */ h b;

            public void a() {
                this.b.i(t);
            }
        }, j, TimeUnit.MILLISECONDS);
    }

    public boolean J() {
        return this.c.b().length > 0;
    }
}

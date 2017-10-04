package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;

public final class cu<T> implements d$g<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    static final class a<T> extends k<T> implements b {
        final k<? super T> a;

        public a(k<? super T> kVar) {
            super(kVar);
            this.a = kVar;
        }

        public void a_(T t) {
            this.a.a_(t);
        }

        public void a(Throwable th) {
            this.a.a(th);
            n_();
        }

        public void o_() {
            this.a.o_();
            n_();
        }

        public void a() {
            o_();
        }
    }

    public cu(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        l a = this.c.a();
        kVar.a(a);
        Object aVar = new a(new d(kVar));
        a.a(aVar, this.a, this.b);
        return aVar;
    }
}

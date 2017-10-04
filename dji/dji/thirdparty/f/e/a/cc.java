package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.e;
import dji.thirdparty.f.g;
import dji.thirdparty.f.g.d;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class cc<T> implements d$g<T, T> {
    final long a;
    final TimeUnit b;
    final g c;

    static final class a<T> extends k<T> implements b {
        private static final Object c = new Object();
        final AtomicReference<Object> a = new AtomicReference(c);
        private final k<? super T> b;

        public a(k<? super T> kVar) {
            this.b = kVar;
        }

        public void c() {
            a((long) IPositioningSession.NotSet);
        }

        public void a_(T t) {
            this.a.set(t);
        }

        public void a(Throwable th) {
            this.b.a(th);
            n_();
        }

        public void o_() {
            this.b.o_();
            n_();
        }

        public void a() {
            Object andSet = this.a.getAndSet(c);
            if (andSet != c) {
                try {
                    this.b.a_(andSet);
                } catch (Throwable th) {
                    dji.thirdparty.f.c.b.a(th, (e) this);
                }
            }
        }
    }

    public cc(long j, TimeUnit timeUnit, g gVar) {
        this.a = j;
        this.b = timeUnit;
        this.c = gVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        k dVar = new d(kVar);
        l a = this.c.a();
        kVar.a(a);
        l aVar = new a(dVar);
        kVar.a(aVar);
        a.a(aVar, this.a, this.a, this.b);
        return aVar;
    }
}

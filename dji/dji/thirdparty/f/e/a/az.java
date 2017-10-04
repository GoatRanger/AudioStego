package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l.c;

public final class az<T, V> implements d$g<T, T> {
    final d<? extends T> a;
    final o<? super T, ? extends d<V>> b;

    public az(d<? extends T> dVar, o<? super T, ? extends d<V>> oVar) {
        this.a = dVar;
        this.b = oVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final e dVar = new dji.thirdparty.f.g.d(kVar);
        final d I = c.I();
        kVar.a(d.c(I).a(dji.thirdparty.f.g.e.a(dVar)));
        return new k<T>(this, kVar) {
            final /* synthetic */ az c;

            public void o_() {
                I.o_();
            }

            public void a(Throwable th) {
                dVar.a(th);
            }

            public void a_(final T t) {
                try {
                    I.a_(((d) this.c.b.a(t)).i(1).d(null).r(new o<V, T>(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public T a(V v) {
                            return t;
                        }
                    }));
                } catch (Throwable th) {
                    b.a(th, (e) this);
                }
            }
        };
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.k;

public final class bp<T, U, R> implements d$g<d<? extends R>, T> {
    final o<? super T, ? extends d<? extends U>> a;
    final p<? super T, ? super U, ? extends R> b;

    public static <T, U> o<T, d<U>> a(final o<? super T, ? extends Iterable<? extends U>> oVar) {
        return new o<T, d<U>>() {
            public /* synthetic */ Object a(Object obj) {
                return b(obj);
            }

            public d<U> b(T t) {
                return d.c((Iterable) oVar.a(t));
            }
        };
    }

    public bp(o<? super T, ? extends d<? extends U>> oVar, p<? super T, ? super U, ? extends R> pVar) {
        this.a = oVar;
        this.b = pVar;
    }

    public k<? super T> a(final k<? super d<? extends R>> kVar) {
        return new k<T>(this, kVar) {
            final /* synthetic */ bp b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(final T t) {
                try {
                    kVar.a_(((d) this.b.a.a(t)).r(new o<U, R>(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public R a(U u) {
                            return this.b.b.b.b(t, u);
                        }
                    }));
                } catch (Throwable th) {
                    b.a(th, kVar, t);
                }
            }
        };
    }
}

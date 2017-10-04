package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.k;

public final class bc<T, U> implements d$g<T, T> {
    final o<? super T, ? extends U> a;

    private static class a {
        static final bc<?, ?> a = new bc(q.c());

        private a() {
        }
    }

    public static <T> bc<T, T> a() {
        return a.a;
    }

    public bc(o<? super T, ? extends U> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            U a;
            boolean b;
            final /* synthetic */ bc d;

            public void a_(T t) {
                Object obj = this.a;
                try {
                    Object a = this.d.a.a(t);
                    this.a = a;
                    if (!this.b) {
                        this.b = true;
                        kVar.a_(t);
                    } else if (obj == a || (a != null && a.equals(obj))) {
                        a(1);
                    } else {
                        kVar.a_(t);
                    }
                } catch (Throwable th) {
                    b.a(th, kVar, t);
                }
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void o_() {
                kVar.o_();
            }
        };
    }
}

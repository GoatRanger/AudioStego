package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.d.q;
import dji.thirdparty.f.k;
import java.util.HashSet;
import java.util.Set;

public final class bb<T, U> implements d$g<T, T> {
    final o<? super T, ? extends U> a;

    private static class a {
        static final bb<?, ?> a = new bb(q.c());

        private a() {
        }
    }

    public static <T> bb<T, T> a() {
        return a.a;
    }

    public bb(o<? super T, ? extends U> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            Set<U> a = new HashSet();
            final /* synthetic */ bb c;

            public void a_(T t) {
                if (this.a.add(this.c.a.a(t))) {
                    kVar.a_(t);
                } else {
                    a(1);
                }
            }

            public void a(Throwable th) {
                this.a = null;
                kVar.a(th);
            }

            public void o_() {
                this.a = null;
                kVar.o_();
            }
        };
    }
}

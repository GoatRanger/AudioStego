package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.g.d;
import dji.thirdparty.f.k;

public final class cf<T> implements d$g<T, T> {

    private static final class a {
        static final cf<Object> a = new cf();

        private a() {
        }
    }

    public static <T> cf<T> a() {
        return a.a;
    }

    cf() {
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new d(new k<T>(this, kVar) {
            final /* synthetic */ cf b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                kVar.a_(t);
            }
        });
    }
}

package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public class bm<T> implements d$g<T, T> {

    private static class a {
        static final bm<?> a = new bm();

        private a() {
        }
    }

    public static <T> bm<T> a() {
        return a.a;
    }

    bm() {
    }

    public k<? super T> a(final k<? super T> kVar) {
        l anonymousClass1 = new k<T>(this) {
            final /* synthetic */ bm b;

            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
            }
        };
        kVar.a(anonymousClass1);
        return anonymousClass1;
    }
}

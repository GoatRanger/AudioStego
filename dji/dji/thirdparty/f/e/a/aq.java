package dji.thirdparty.f.e.a;

import dji.thirdparty.f.d$g;
import dji.thirdparty.f.k;

public final class aq<T> implements d$g<T, T> {

    private static final class a {
        static final aq<Object> a = new aq();

        private a() {
        }
    }

    public static <T> aq<T> a() {
        return a.a;
    }

    aq() {
    }

    public k<? super T> a(k<? super T> kVar) {
        return kVar;
    }
}

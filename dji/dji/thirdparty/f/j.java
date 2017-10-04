package dji.thirdparty.f;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.h.b;
import dji.thirdparty.f.h.c;
import java.util.Map;

public final class j {
    private static final a a = new a();

    private static final class a implements n<Boolean> {
        private a() {
        }

        public /* synthetic */ Object call() {
            return a();
        }

        public Boolean a() {
            return Boolean.valueOf(true);
        }
    }

    private j() {
        throw new IllegalStateException("No instances!");
    }

    public static <K, R> d<R> a(n<? extends K> nVar, Map<? super K, ? extends d<? extends R>> map) {
        return a((n) nVar, (Map) map, d.d());
    }

    public static <K, R> d<R> a(n<? extends K> nVar, Map<? super K, ? extends d<? extends R>> map, g gVar) {
        return a((n) nVar, (Map) map, d.d().d(gVar));
    }

    public static <K, R> d<R> a(n<? extends K> nVar, Map<? super K, ? extends d<? extends R>> map, d<? extends R> dVar) {
        return d.a(new b(nVar, map, dVar));
    }

    public static <T> d<T> a(d<? extends T> dVar, n<Boolean> nVar) {
        return d.a(new c(dVar, a, nVar));
    }

    public static <T> d<T> b(d<? extends T> dVar, n<Boolean> nVar) {
        return d.a(new c(dVar, nVar, nVar));
    }

    public static <R> d<R> a(n<Boolean> nVar, d<? extends R> dVar) {
        return a((n) nVar, (d) dVar, d.d());
    }

    public static <R> d<R> a(n<Boolean> nVar, d<? extends R> dVar, g gVar) {
        return a((n) nVar, (d) dVar, d.d().d(gVar));
    }

    public static <R> d<R> a(n<Boolean> nVar, d<? extends R> dVar, d<? extends R> dVar2) {
        return d.a(new dji.thirdparty.f.h.a(nVar, dVar, dVar2));
    }
}

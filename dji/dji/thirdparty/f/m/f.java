package dji.thirdparty.f.m;

import dji.thirdparty.f.l;
import java.util.concurrent.Future;

public final class f {
    private static final b a = new b();

    private static final class a implements l {
        final Future<?> a;

        public a(Future<?> future) {
            this.a = future;
        }

        public void n_() {
            this.a.cancel(true);
        }

        public boolean b() {
            return this.a.isCancelled();
        }
    }

    static final class b implements l {
        b() {
        }

        public void n_() {
        }

        public boolean b() {
            return true;
        }
    }

    private f() {
        throw new IllegalStateException("No instances!");
    }

    public static l a() {
        return a.c();
    }

    public static l b() {
        return a;
    }

    public static l a(dji.thirdparty.f.d.b bVar) {
        return a.a(bVar);
    }

    public static l a(Future<?> future) {
        return new a(future);
    }

    public static b a(l... lVarArr) {
        return new b(lVarArr);
    }
}

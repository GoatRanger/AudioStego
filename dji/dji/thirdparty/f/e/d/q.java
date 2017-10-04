package dji.thirdparty.f.e.d;

import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.d.r;
import dji.thirdparty.f.d.s;
import dji.thirdparty.f.d.t;
import dji.thirdparty.f.d.u;
import dji.thirdparty.f.d.v;
import dji.thirdparty.f.d.w;
import dji.thirdparty.f.d.x;

public final class q {
    private static final c a = new c();

    private enum a implements o<Object, Boolean> {
        INSTANCE;

        public Boolean b(Object obj) {
            return Boolean.valueOf(false);
        }
    }

    private enum b implements o<Object, Boolean> {
        INSTANCE;

        public Boolean b(Object obj) {
            return Boolean.valueOf(true);
        }
    }

    private static final class c<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements n<R>, o<T0, R>, p<T0, T1, R>, dji.thirdparty.f.d.q<T0, T1, T2, R>, r<T0, T1, T2, T3, R>, s<T0, T1, T2, T3, T4, R>, t<T0, T1, T2, T3, T4, T5, R>, u<T0, T1, T2, T3, T4, T5, T6, R>, v<T0, T1, T2, T3, T4, T5, T6, T7, R>, w<T0, T1, T2, T3, T4, T5, T6, T7, T8, R>, x<R> {
        c() {
        }

        public R call() {
            return null;
        }

        public R a(T0 t0) {
            return null;
        }

        public R b(T0 t0, T1 t1) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
            return null;
        }

        public R a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
            return null;
        }

        public R a(Object... objArr) {
            return null;
        }
    }

    public static <T> o<? super T, Boolean> a() {
        return b.INSTANCE;
    }

    public static <T> o<? super T, Boolean> b() {
        return a.INSTANCE;
    }

    public static <T> o<T, T> c() {
        return new o<T, T>() {
            public T a(T t) {
                return t;
            }
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> c<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> d() {
        return a;
    }
}

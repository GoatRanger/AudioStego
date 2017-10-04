package dji.thirdparty.f.d;

public final class m {
    private static final a a = new a();

    private static final class a<T0, T1, T2, T3, T4, T5, T6, T7, T8> implements b, c<T0>, d<T0, T1>, e<T0, T1, T2>, f<T0, T1, T2, T3>, g<T0, T1, T2, T3, T4>, h<T0, T1, T2, T3, T4, T5>, i<T0, T1, T2, T3, T4, T5, T6>, j<T0, T1, T2, T3, T4, T5, T6, T7>, k<T0, T1, T2, T3, T4, T5, T6, T7, T8>, l {
        a() {
        }

        public void a() {
        }

        public void a(T0 t0) {
        }

        public void a(T0 t0, T1 t1) {
        }

        public void a(T0 t0, T1 t1, T2 t2) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
        }

        public void a(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
        }

        public void a(Object... objArr) {
        }
    }

    private m() {
        throw new IllegalStateException("No instances!");
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> a<T0, T1, T2, T3, T4, T5, T6, T7, T8> a() {
        return a;
    }

    public static n<Void> a(b bVar) {
        return a(bVar, (Void) null);
    }

    public static <T1> o<T1, Void> a(c<T1> cVar) {
        return a((c) cVar, (Void) null);
    }

    public static <T1, T2> p<T1, T2, Void> a(d<T1, T2> dVar) {
        return a((d) dVar, (Void) null);
    }

    public static <T1, T2, T3> q<T1, T2, T3, Void> a(e<T1, T2, T3> eVar) {
        return a((e) eVar, (Void) null);
    }

    public static <T1, T2, T3, T4> r<T1, T2, T3, T4, Void> a(f<T1, T2, T3, T4> fVar) {
        return a((f) fVar, (Void) null);
    }

    public static <T1, T2, T3, T4, T5> s<T1, T2, T3, T4, T5, Void> a(g<T1, T2, T3, T4, T5> gVar) {
        return a((g) gVar, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6> t<T1, T2, T3, T4, T5, T6, Void> a(h<T1, T2, T3, T4, T5, T6> hVar) {
        return a((h) hVar, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> u<T1, T2, T3, T4, T5, T6, T7, Void> a(i<T1, T2, T3, T4, T5, T6, T7> iVar) {
        return a((i) iVar, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> v<T1, T2, T3, T4, T5, T6, T7, T8, Void> a(j<T1, T2, T3, T4, T5, T6, T7, T8> jVar) {
        return a((j) jVar, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> w<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> a(k<T1, T2, T3, T4, T5, T6, T7, T8, T9> kVar) {
        return a((k) kVar, (Void) null);
    }

    public static x<Void> a(l lVar) {
        return a(lVar, (Void) null);
    }

    public static <R> n<R> a(final b bVar, final R r) {
        return new n<R>() {
            public R call() {
                bVar.a();
                return r;
            }
        };
    }

    public static <T1, R> o<T1, R> a(final c<T1> cVar, final R r) {
        return new o<T1, R>() {
            public R a(T1 t1) {
                cVar.a(t1);
                return r;
            }
        };
    }

    public static <T1, T2, R> p<T1, T2, R> a(final d<T1, T2> dVar, final R r) {
        return new p<T1, T2, R>() {
            public R b(T1 t1, T2 t2) {
                dVar.a(t1, t2);
                return r;
            }
        };
    }

    public static <T1, T2, T3, R> q<T1, T2, T3, R> a(final e<T1, T2, T3> eVar, final R r) {
        return new q<T1, T2, T3, R>() {
            public R a(T1 t1, T2 t2, T3 t3) {
                eVar.a(t1, t2, t3);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, R> r<T1, T2, T3, T4, R> a(final f<T1, T2, T3, T4> fVar, final R r) {
        return new r<T1, T2, T3, T4, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4) {
                fVar.a(t1, t2, t3, t4);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, T5, R> s<T1, T2, T3, T4, T5, R> a(final g<T1, T2, T3, T4, T5> gVar, final R r) {
        return new s<T1, T2, T3, T4, T5, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                gVar.a(t1, t2, t3, t4, t5);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, R> t<T1, T2, T3, T4, T5, T6, R> a(final h<T1, T2, T3, T4, T5, T6> hVar, final R r) {
        return new t<T1, T2, T3, T4, T5, T6, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                hVar.a(t1, t2, t3, t4, t5, t6);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> u<T1, T2, T3, T4, T5, T6, T7, R> a(final i<T1, T2, T3, T4, T5, T6, T7> iVar, final R r) {
        return new u<T1, T2, T3, T4, T5, T6, T7, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                iVar.a(t1, t2, t3, t4, t5, t6, t7);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> v<T1, T2, T3, T4, T5, T6, T7, T8, R> a(final j<T1, T2, T3, T4, T5, T6, T7, T8> jVar, final R r) {
        return new v<T1, T2, T3, T4, T5, T6, T7, T8, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                jVar.a(t1, t2, t3, t4, t5, t6, t7, t8);
                return r;
            }
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> w<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> a(final k<T1, T2, T3, T4, T5, T6, T7, T8, T9> kVar, final R r) {
        return new w<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() {
            public R a(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                kVar.a(t1, t2, t3, t4, t5, t6, t7, t8, t9);
                return r;
            }
        };
    }

    public static <R> x<R> a(final l lVar, final R r) {
        return new x<R>() {
            public R a(Object... objArr) {
                lVar.a(objArr);
                return r;
            }
        };
    }
}

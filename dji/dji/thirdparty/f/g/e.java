package dji.thirdparty.f.g;

import dji.thirdparty.f.c.f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.k;

public final class e {
    private e() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> k<T> a() {
        return a(a.a());
    }

    public static <T> k<T> a(final dji.thirdparty.f.e<? super T> eVar) {
        return new k<T>() {
            public void o_() {
                eVar.o_();
            }

            public void a(Throwable th) {
                eVar.a(th);
            }

            public void a_(T t) {
                eVar.a_(t);
            }
        };
    }

    public static <T> k<T> a(final c<? super T> cVar) {
        if (cVar != null) {
            return new k<T>() {
                public final void o_() {
                }

                public final void a(Throwable th) {
                    throw new f(th);
                }

                public final void a_(T t) {
                    cVar.a(t);
                }
            };
        }
        throw new IllegalArgumentException("onNext can not be null");
    }

    public static <T> k<T> a(final c<? super T> cVar, final c<Throwable> cVar2) {
        if (cVar == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (cVar2 != null) {
            return new k<T>() {
                public final void o_() {
                }

                public final void a(Throwable th) {
                    cVar2.a(th);
                }

                public final void a_(T t) {
                    cVar.a(t);
                }
            };
        } else {
            throw new IllegalArgumentException("onError can not be null");
        }
    }

    public static <T> k<T> a(final c<? super T> cVar, final c<Throwable> cVar2, final b bVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (cVar2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        } else if (bVar != null) {
            return new k<T>() {
                public final void o_() {
                    bVar.a();
                }

                public final void a(Throwable th) {
                    cVar2.a(th);
                }

                public final void a_(T t) {
                    cVar.a(t);
                }
            };
        } else {
            throw new IllegalArgumentException("onComplete can not be null");
        }
    }

    public static <T> k<T> a(final k<? super T> kVar) {
        return new k<T>(kVar) {
            public void o_() {
                kVar.o_();
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void a_(T t) {
                kVar.a_(t);
            }
        };
    }
}

package dji.thirdparty.f.g;

import dji.thirdparty.f.c.f;
import dji.thirdparty.f.d.b;
import dji.thirdparty.f.d.c;
import dji.thirdparty.f.e;

public final class a {
    private static final e<Object> a = new e<Object>() {
        public final void o_() {
        }

        public final void a(Throwable th) {
            throw new f(th);
        }

        public final void a_(Object obj) {
        }
    };

    private a() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> e<T> a() {
        return a;
    }

    public static <T> e<T> a(final c<? super T> cVar) {
        if (cVar != null) {
            return new e<T>() {
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

    public static <T> e<T> a(final c<? super T> cVar, final c<Throwable> cVar2) {
        if (cVar == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (cVar2 != null) {
            return new e<T>() {
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

    public static <T> e<T> a(final c<? super T> cVar, final c<Throwable> cVar2, final b bVar) {
        if (cVar == null) {
            throw new IllegalArgumentException("onNext can not be null");
        } else if (cVar2 == null) {
            throw new IllegalArgumentException("onError can not be null");
        } else if (bVar != null) {
            return new e<T>() {
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
}

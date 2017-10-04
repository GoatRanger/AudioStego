package dji.thirdparty.f.e.d;

import dji.thirdparty.f.d.o;
import dji.thirdparty.f.g;
import dji.thirdparty.f.h;
import dji.thirdparty.f.i;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class m<T> extends h<T> {
    final T c;

    class AnonymousClass1 implements dji.thirdparty.f.h.a<T> {
        final /* synthetic */ Object a;

        AnonymousClass1(Object obj) {
            this.a = obj;
        }

        public void a(i<? super T> iVar) {
            iVar.a(this.a);
        }
    }

    static final class a<T> implements dji.thirdparty.f.h.a<T> {
        private final dji.thirdparty.f.e.c.a a;
        private final T b;

        a(dji.thirdparty.f.e.c.a aVar, T t) {
            this.a = aVar;
            this.b = t;
        }

        public void a(i<? super T> iVar) {
            iVar.a(this.a.a(new c(iVar, this.b)));
        }
    }

    static final class b<T> implements dji.thirdparty.f.h.a<T> {
        private final g a;
        private final T b;

        b(g gVar, T t) {
            this.a = gVar;
            this.b = t;
        }

        public void a(i<? super T> iVar) {
            l a = this.a.a();
            iVar.a(a);
            a.a(new c(iVar, this.b));
        }
    }

    static final class c<T> implements dji.thirdparty.f.d.b {
        private final i<? super T> a;
        private final T b;

        c(i<? super T> iVar, T t) {
            this.a = iVar;
            this.b = t;
        }

        public void a() {
            try {
                this.a.a(this.b);
            } catch (Throwable th) {
                this.a.a(th);
            }
        }
    }

    public static final <T> m<T> b(T t) {
        return new m(t);
    }

    protected m(T t) {
        super(new AnonymousClass1(t));
        this.c = t;
    }

    public T e() {
        return this.c;
    }

    public h<T> c(g gVar) {
        if (gVar instanceof dji.thirdparty.f.e.c.a) {
            return h.a(new a((dji.thirdparty.f.e.c.a) gVar, this.c));
        }
        return h.a(new b(gVar, this.c));
    }

    public <R> h<R> g(final o<? super T, ? extends h<? extends R>> oVar) {
        return h.a(new dji.thirdparty.f.h.a<R>(this) {
            final /* synthetic */ m b;

            public void a(final i<? super R> iVar) {
                h hVar = (h) oVar.a(this.b.c);
                if (hVar instanceof m) {
                    iVar.a(((m) hVar).c);
                    return;
                }
                k anonymousClass1 = new k<R>(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void o_() {
                    }

                    public void a(Throwable th) {
                        iVar.a(th);
                    }

                    public void a_(R r) {
                        iVar.a((Object) r);
                    }
                };
                iVar.a((l) anonymousClass1);
                hVar.a(anonymousClass1);
            }
        });
    }
}

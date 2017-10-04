package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e;
import dji.thirdparty.f.g.a;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.f;

public class dc<T, U, V> extends db<T> {

    class AnonymousClass1 implements a<T> {
        final /* synthetic */ n a;

        AnonymousClass1(n nVar) {
            this.a = nVar;
        }

        public l a(final c<T> cVar, final Long l, a aVar) {
            if (this.a == null) {
                return f.b();
            }
            try {
                return ((d) this.a.call()).a(new k<U>(this) {
                    final /* synthetic */ AnonymousClass1 c;

                    public void o_() {
                        cVar.b(l.longValue());
                    }

                    public void a(Throwable th) {
                        cVar.a(th);
                    }

                    public void a_(U u) {
                        cVar.b(l.longValue());
                    }
                });
            } catch (Throwable th) {
                b.a(th, (e) cVar);
                return f.b();
            }
        }
    }

    class AnonymousClass2 implements b<T> {
        final /* synthetic */ o a;

        AnonymousClass2(o oVar) {
            this.a = oVar;
        }

        public l a(final c<T> cVar, final Long l, T t, a aVar) {
            try {
                return ((d) this.a.a(t)).a(new k<V>(this) {
                    final /* synthetic */ AnonymousClass2 c;

                    public void o_() {
                        cVar.b(l.longValue());
                    }

                    public void a(Throwable th) {
                        cVar.a(th);
                    }

                    public void a_(V v) {
                        cVar.b(l.longValue());
                    }
                });
            } catch (Throwable th) {
                b.a(th, (e) cVar);
                return f.b();
            }
        }
    }

    public /* bridge */ /* synthetic */ k a(k kVar) {
        return super.a(kVar);
    }

    public dc(n<? extends d<U>> nVar, o<? super T, ? extends d<V>> oVar, d<? extends T> dVar) {
        super(new AnonymousClass1(nVar), new AnonymousClass2(oVar), dVar, dji.thirdparty.f.j.e.a());
    }
}

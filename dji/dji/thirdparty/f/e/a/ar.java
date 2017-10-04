package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.n;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import java.util.ArrayList;
import java.util.List;

public final class ar<T, TClosing> implements d$g<List<T>, T> {
    final n<? extends d<? extends TClosing>> a;
    final int b;

    final class a extends k<T> {
        final k<? super List<T>> a;
        List<T> b;
        boolean c;
        final /* synthetic */ ar d;

        public a(ar arVar, k<? super List<T>> kVar) {
            this.d = arVar;
            this.a = kVar;
            this.b = new ArrayList(arVar.b);
        }

        public void a_(T t) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.b.add(t);
            }
        }

        public void a(Throwable th) {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                this.c = true;
                this.b = null;
                this.a.a(th);
                n_();
            }
        }

        public void o_() {
            try {
                synchronized (this) {
                    if (this.c) {
                        return;
                    }
                    this.c = true;
                    List list = this.b;
                    this.b = null;
                    this.a.a_(list);
                    this.a.o_();
                    n_();
                }
            } catch (Throwable th) {
                b.a(th, this.a);
            }
        }

        void d() {
            synchronized (this) {
                if (this.c) {
                    return;
                }
                List list = this.b;
                this.b = new ArrayList(this.d.b);
                try {
                    this.a.a_(list);
                } catch (Throwable th) {
                    n_();
                    synchronized (this) {
                        if (!this.c) {
                            this.c = true;
                            b.a(th, this.a);
                        }
                    }
                }
            }
        }
    }

    public ar(n<? extends d<? extends TClosing>> nVar, int i) {
        this.a = nVar;
        this.b = i;
    }

    public ar(final d<? extends TClosing> dVar, int i) {
        this.a = new n<d<? extends TClosing>>(this) {
            final /* synthetic */ ar b;

            public /* synthetic */ Object call() {
                return a();
            }

            public d<? extends TClosing> a() {
                return dVar;
            }
        };
        this.b = i;
    }

    public k<? super T> a(k<? super List<T>> kVar) {
        try {
            d dVar = (d) this.a.call();
            final l aVar = new a(this, new dji.thirdparty.f.g.d(kVar));
            l anonymousClass2 = new k<TClosing>(this) {
                final /* synthetic */ ar b;

                public void a_(TClosing tClosing) {
                    aVar.d();
                }

                public void a(Throwable th) {
                    aVar.a(th);
                }

                public void o_() {
                    aVar.o_();
                }
            };
            kVar.a(anonymousClass2);
            kVar.a(aVar);
            dVar.a(anonymousClass2);
            return aVar;
        } catch (Throwable th) {
            b.a(th, (e) kVar);
            return dji.thirdparty.f.g.e.a();
        }
    }
}

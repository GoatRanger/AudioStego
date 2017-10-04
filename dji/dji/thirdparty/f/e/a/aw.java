package dji.thirdparty.f.e.a;

import com.here.odnp.posclient.pos.IPositioningSession;
import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;
import dji.thirdparty.f.m.e;

public final class aw<T, U> implements d$g<T, T> {
    final o<? super T, ? extends d<U>> a;

    public aw(o<? super T, ? extends d<U>> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final dji.thirdparty.f.g.d dVar = new dji.thirdparty.f.g.d(kVar);
        final l eVar = new e();
        kVar.a(eVar);
        return new k<T>(this, kVar) {
            final a<T> a = new a();
            final k<?> b = this;
            final /* synthetic */ aw e;

            public void c() {
                a((long) IPositioningSession.NotSet);
            }

            public void a_(T t) {
                try {
                    d dVar = (d) this.e.a.a(t);
                    final int a = this.a.a(t);
                    Object anonymousClass1 = new k<U>(this) {
                        final /* synthetic */ AnonymousClass1 b;

                        public void a_(U u) {
                            o_();
                        }

                        public void a(Throwable th) {
                            this.b.b.a(th);
                        }

                        public void o_() {
                            this.b.a.a(a, dVar, this.b.b);
                            n_();
                        }
                    };
                    eVar.a(anonymousClass1);
                    dVar.a(anonymousClass1);
                } catch (Throwable th) {
                    b.a(th, (dji.thirdparty.f.e) this);
                }
            }

            public void a(Throwable th) {
                dVar.a(th);
                n_();
                this.a.a();
            }

            public void o_() {
                this.a.a(dVar, this);
            }
        };
    }
}

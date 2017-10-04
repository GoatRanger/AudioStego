package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class cw<T> implements d$g<T, T> {
    final o<? super T, Boolean> a;

    private final class a extends k<T> {
        final /* synthetic */ cw a;
        private final k<? super T> b;
        private boolean c = false;

        a(cw cwVar, k<? super T> kVar) {
            this.a = cwVar;
            this.b = kVar;
        }

        public void a_(T t) {
            this.b.a_(t);
            try {
                if (((Boolean) this.a.a.a(t)).booleanValue()) {
                    this.c = true;
                    this.b.o_();
                    n_();
                }
            } catch (Throwable th) {
                this.c = true;
                b.a(th, this.b, t);
                n_();
            }
        }

        public void o_() {
            if (!this.c) {
                this.b.o_();
            }
        }

        public void a(Throwable th) {
            if (!this.c) {
                this.b.a(th);
            }
        }

        void b(long j) {
            a(j);
        }
    }

    public cw(o<? super T, Boolean> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(k<? super T> kVar) {
        final l aVar = new a(this, kVar);
        kVar.a(aVar);
        kVar.a(new f(this) {
            final /* synthetic */ cw b;

            public void a(long j) {
                aVar.b(j);
            }
        });
        return aVar;
    }
}

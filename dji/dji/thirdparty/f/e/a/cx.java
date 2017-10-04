package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class cx<T> implements d$g<T, T> {
    final p<? super T, ? super Integer, Boolean> a;

    class AnonymousClass1 implements p<T, Integer, Boolean> {
        final /* synthetic */ o a;

        AnonymousClass1(o oVar) {
            this.a = oVar;
        }

        public /* synthetic */ Object b(Object obj, Object obj2) {
            return a(obj, (Integer) obj2);
        }

        public Boolean a(T t, Integer num) {
            return (Boolean) this.a.a(t);
        }
    }

    public cx(o<? super T, Boolean> oVar) {
        this(new AnonymousClass1(oVar));
    }

    public cx(p<? super T, ? super Integer, Boolean> pVar) {
        this.a = pVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        l anonymousClass2 = new k<T>(this, false, kVar) {
            final /* synthetic */ cx b;
            private int c = 0;
            private boolean d = false;

            public void a_(T t) {
                try {
                    p pVar = this.b.a;
                    int i = this.c;
                    this.c = i + 1;
                    if (((Boolean) pVar.b(t, Integer.valueOf(i))).booleanValue()) {
                        kVar.a_(t);
                        return;
                    }
                    this.d = true;
                    kVar.o_();
                    n_();
                } catch (Throwable th) {
                    this.d = true;
                    b.a(th, kVar, t);
                    n_();
                }
            }

            public void o_() {
                if (!this.d) {
                    kVar.o_();
                }
            }

            public void a(Throwable th) {
                if (!this.d) {
                    kVar.a(th);
                }
            }
        };
        kVar.a(anonymousClass2);
        return anonymousClass2;
    }
}

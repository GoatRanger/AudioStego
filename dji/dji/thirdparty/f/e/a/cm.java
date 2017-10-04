package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.k;

public final class cm<T> implements d$g<T, T> {
    final p<? super T, Integer, Boolean> a;

    public cm(p<? super T, Integer, Boolean> pVar) {
        this.a = pVar;
    }

    public k<? super T> a(final k<? super T> kVar) {
        return new k<T>(this, kVar) {
            boolean a = true;
            int b;
            final /* synthetic */ cm d;

            public void a_(T t) {
                if (this.a) {
                    try {
                        p pVar = this.d.a;
                        int i = this.b;
                        this.b = i + 1;
                        if (((Boolean) pVar.b(t, Integer.valueOf(i))).booleanValue()) {
                            a(1);
                            return;
                        }
                        this.a = false;
                        kVar.a_(t);
                        return;
                    } catch (Throwable th) {
                        b.a(th, kVar, t);
                        return;
                    }
                }
                kVar.a_(t);
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void o_() {
                kVar.o_();
            }
        };
    }

    public static <T> p<T, Integer, Boolean> a(final o<? super T, Boolean> oVar) {
        return new p<T, Integer, Boolean>() {
            public /* synthetic */ Object b(Object obj, Object obj2) {
                return a(obj, (Integer) obj2);
            }

            public Boolean a(T t, Integer num) {
                return (Boolean) oVar.a(t);
            }
        };
    }
}

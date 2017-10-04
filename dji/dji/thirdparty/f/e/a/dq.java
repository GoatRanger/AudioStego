package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.p;
import dji.thirdparty.f.e;
import dji.thirdparty.f.k;
import java.util.Iterator;

public final class dq<T1, T2, R> implements d$g<R, T1> {
    final Iterable<? extends T2> a;
    final p<? super T1, ? super T2, ? extends R> b;

    public dq(Iterable<? extends T2> iterable, p<? super T1, ? super T2, ? extends R> pVar) {
        this.a = iterable;
        this.b = pVar;
    }

    public k<? super T1> a(final k<? super R> kVar) {
        final Iterator it = this.a.iterator();
        try {
            if (it.hasNext()) {
                return new k<T1>(this, kVar) {
                    boolean a;
                    final /* synthetic */ dq d;

                    public void o_() {
                        if (!this.a) {
                            this.a = true;
                            kVar.o_();
                        }
                    }

                    public void a(Throwable th) {
                        if (this.a) {
                            b.b(th);
                            return;
                        }
                        this.a = true;
                        kVar.a(th);
                    }

                    public void a_(T1 t1) {
                        if (!this.a) {
                            try {
                                kVar.a_(this.d.b.b(t1, it.next()));
                                if (!it.hasNext()) {
                                    o_();
                                }
                            } catch (Throwable th) {
                                b.a(th, (e) this);
                            }
                        }
                    }
                };
            }
            kVar.o_();
            return dji.thirdparty.f.g.e.a();
        } catch (Throwable th) {
            b.a(th, (e) kVar);
            return dji.thirdparty.f.g.e.a();
        }
    }
}

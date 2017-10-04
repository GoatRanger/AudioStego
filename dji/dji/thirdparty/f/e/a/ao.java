package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class ao<T> implements d$g<Boolean, T> {
    final o<? super T, Boolean> a;

    public ao(o<? super T, Boolean> oVar) {
        this.a = oVar;
    }

    public k<? super T> a(final k<? super Boolean> kVar) {
        final f eVar = new e(kVar);
        l anonymousClass1 = new k<T>(this) {
            boolean a;
            final /* synthetic */ ao d;

            public void a_(T t) {
                try {
                    if (!((Boolean) this.d.a.a(t)).booleanValue() && !this.a) {
                        this.a = true;
                        eVar.a(Boolean.valueOf(false));
                        n_();
                    }
                } catch (Throwable th) {
                    b.a(th, this, t);
                }
            }

            public void a(Throwable th) {
                kVar.a(th);
            }

            public void o_() {
                if (!this.a) {
                    this.a = true;
                    eVar.a(Boolean.valueOf(true));
                }
            }
        };
        kVar.a(anonymousClass1);
        kVar.a(eVar);
        return anonymousClass1;
    }
}

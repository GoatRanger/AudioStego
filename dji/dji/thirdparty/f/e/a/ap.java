package dji.thirdparty.f.e.a;

import dji.thirdparty.f.c.b;
import dji.thirdparty.f.d$g;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.e.b.e;
import dji.thirdparty.f.f;
import dji.thirdparty.f.k;
import dji.thirdparty.f.l;

public final class ap<T> implements d$g<Boolean, T> {
    final o<? super T, Boolean> a;
    final boolean b;

    public ap(o<? super T, Boolean> oVar, boolean z) {
        this.a = oVar;
        this.b = z;
    }

    public k<? super T> a(final k<? super Boolean> kVar) {
        final f eVar = new e(kVar);
        l anonymousClass1 = new k<T>(this) {
            boolean a;
            boolean b;
            final /* synthetic */ ap e;

            public void a_(T t) {
                this.a = true;
                try {
                    if (((Boolean) this.e.a.a(t)).booleanValue() && !this.b) {
                        this.b = true;
                        eVar.a(Boolean.valueOf(!this.e.b));
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
                if (!this.b) {
                    this.b = true;
                    if (this.a) {
                        eVar.a(Boolean.valueOf(false));
                    } else {
                        eVar.a(Boolean.valueOf(this.e.b));
                    }
                }
            }
        };
        kVar.a(anonymousClass1);
        kVar.a(eVar);
        return anonymousClass1;
    }
}

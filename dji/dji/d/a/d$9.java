package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$9 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$9(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.d(this.a, Float.valueOf(this.a.f), e.V);
        this.a.f = this.a.c / 10.0f;
        if (!this.a.n) {
            d dVar = this.a;
            dVar.f *= -1.0f;
        }
        return d.b(Boolean.valueOf(true));
    }
}

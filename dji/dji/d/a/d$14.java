package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$14 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$14(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.l(this.a, Double.valueOf(this.a.k), e.X);
        if (this.a.n) {
            this.a.k = (double) (((-this.a.c) / 300.0f) * 45.0f);
        } else {
            this.a.k = (double) ((this.a.c / 300.0f) * 45.0f);
        }
        return d.b(Boolean.valueOf(true));
    }
}

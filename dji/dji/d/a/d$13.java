package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$13 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$13(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.k(this.a, Double.valueOf(this.a.j), e.S);
        this.a.j = this.a.h.getLongitude() + (((double) (this.a.c * 5.0f)) * 8.99322E-6d);
        return d.b(Boolean.valueOf(true));
    }
}

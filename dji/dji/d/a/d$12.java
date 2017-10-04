package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$12 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$12(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.j(this.a, Double.valueOf(this.a.i), e.R);
        this.a.i = this.a.h.getLatitude() + (((double) (this.a.c * 5.0f)) * 8.99322E-6d);
        return d.b(Boolean.valueOf(true));
    }
}

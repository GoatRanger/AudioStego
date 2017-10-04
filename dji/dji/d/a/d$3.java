package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$3 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$3(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.n(this.a, Double.valueOf(this.a.m), e.Z);
        if (this.a.n) {
            this.a.m = (double) (((-this.a.c) / 300.0f) * 360.0f);
        } else {
            this.a.m = (double) ((this.a.c / 300.0f) * 360.0f);
        }
        return d.b(Boolean.valueOf(true));
    }
}

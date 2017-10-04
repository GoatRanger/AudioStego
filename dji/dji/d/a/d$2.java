package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$2 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.m(this.a, Double.valueOf(this.a.l), e.Y);
        if (this.a.n) {
            this.a.l = (double) (((-this.a.c) / 300.0f) * 45.0f);
        } else {
            this.a.l = (double) ((this.a.c / 300.0f) * 45.0f);
        }
        return d.b(Boolean.valueOf(true));
    }
}

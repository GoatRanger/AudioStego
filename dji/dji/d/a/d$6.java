package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$6 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$6(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        if (b.a > 30) {
            d.t(this.a, Boolean.valueOf(true), e.D);
        } else {
            d.u(this.a, Boolean.valueOf(false), e.D);
        }
        if (b.a <= 40 || b.a >= 50) {
            d.w(this.a, Boolean.valueOf(true), e.bn);
        } else {
            d.v(this.a, Boolean.valueOf(false), e.bn);
        }
        return d.b(Boolean.valueOf(true));
    }
}

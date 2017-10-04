package dji.d.a;

import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$1 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        if (this.a.q) {
            d.a(this.a, Float.valueOf(this.a.c), e.W);
            d dVar;
            if (this.a.r || this.a.s) {
                dVar = this.a;
                dVar.c -= 1.0f;
                if (this.a.c <= 0.0f) {
                    this.a.u = false;
                    this.a.t = false;
                    this.a.q = false;
                    this.a.r = false;
                    this.a.s = false;
                }
            } else {
                if (this.a.n) {
                    dVar = this.a;
                    dVar.c += 1.0f;
                } else {
                    dVar = this.a;
                    dVar.c -= 1.0f;
                }
                if (this.a.n && this.a.c >= 300.0f) {
                    this.a.n = false;
                } else if (!this.a.n && this.a.c <= 0.0f) {
                    this.a.n = true;
                }
            }
        }
        return d.b(Boolean.valueOf(true));
    }
}

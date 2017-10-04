package dji.d.a;

import dji.common.flightcontroller.DJIGPSSignalStatus;
import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$10 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$10(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        d.e(this.a, this.a.g, e.ae);
        d.f(this.a, Integer.valueOf(this.a.o), e.Q);
        d.g(this.a, Boolean.valueOf(this.a.p), e.aM);
        d dVar = this.a;
        dVar.o++;
        this.a.g = DJIGPSSignalStatus.find(d.a(this.a, this.a.o));
        if (this.a.o >= 30) {
            this.a.o = 0;
        }
        if (this.a.o > 15) {
            this.a.p = true;
        } else {
            this.a.p = false;
        }
        return d.b(Boolean.valueOf(true));
    }
}

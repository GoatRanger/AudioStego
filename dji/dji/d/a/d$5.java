package dji.d.a;

import dji.common.flightcontroller.DJIAircraftRemainingBatteryState;
import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$5 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$5(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        if (b.a > 50) {
            d.o(this.a, Integer.valueOf(55), e.au);
        } else {
            d.p(this.a, Integer.valueOf(20), e.au);
        }
        if (b.a <= 10) {
            d.q(this.a, DJIAircraftRemainingBatteryState.find(2), e.aa);
        } else if (b.a <= 30) {
            d.r(this.a, DJIAircraftRemainingBatteryState.find(1), e.aa);
        } else {
            d.s(this.a, DJIAircraftRemainingBatteryState.find(0), e.aa);
        }
        return d.b(Boolean.valueOf(true));
    }
}

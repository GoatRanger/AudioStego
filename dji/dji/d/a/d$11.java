package dji.d.a;

import dji.log.DJILog;
import dji.sdksharedlib.b.e;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;

class d$11 implements o<Long, d<Boolean>> {
    final /* synthetic */ d a;

    d$11(d dVar) {
        this.a = dVar;
    }

    public d<Boolean> a(Long l) {
        DJILog.d("HAIHAI", "Updating");
        d.h(this.a, Double.valueOf(this.a.h.getLatitude()), e.al);
        d.i(this.a, Double.valueOf(this.a.h.getLongitude()), e.am);
        return d.b(Boolean.valueOf(true));
    }
}

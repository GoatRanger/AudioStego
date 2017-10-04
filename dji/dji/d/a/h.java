package dji.d.a;

import dji.common.remotecontroller.DJIRCGPSData;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.hardware.abstractions.h.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.d.o;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class h extends a {
    DJIRCGPSData a = new DJIRCGPSData();

    public h() {
        p();
    }

    private void p() {
        d.b(1000, TimeUnit.MILLISECONDS, e.c()).n(new o<Long, d<Boolean>>(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public d<Boolean> a(Long l) {
                this.a.b(this.a.a, i.v);
                return d.b(Boolean.valueOf(true));
            }
        }).w().C();
    }
}

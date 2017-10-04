package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalCapabilityKey;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;

public class g extends f {
    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        b();
    }

    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustYaw, Integer.valueOf(-15), Integer.valueOf(15));
    }

    protected void a() {
        super.a();
    }
}

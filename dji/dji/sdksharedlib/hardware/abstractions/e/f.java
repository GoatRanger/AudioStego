package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalCapabilityKey;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;

public class f extends b {
    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        b();
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
    }

    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustPitch, Integer.valueOf(-90), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch, Integer.valueOf(0), Integer.valueOf(30));
    }

    protected void a() {
        super.a();
    }
}

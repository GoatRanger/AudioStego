package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalCapabilityKey;

public class c extends b {
    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustPitch, Integer.valueOf(-90), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch, Integer.valueOf(0), Integer.valueOf(30));
    }
}

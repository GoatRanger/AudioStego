package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalCapabilityKey;

public class i extends b {
    public void b() {
        super.b();
        a(DJIGimbalCapabilityKey.AdjustPitch, Integer.valueOf(-90), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.AdjustYaw, Integer.valueOf(-180), Integer.valueOf(180));
        a(DJIGimbalCapabilityKey.AdjustRoll, Integer.valueOf(-15), Integer.valueOf(15));
        a(DJIGimbalCapabilityKey.ControllerSpeedPitch, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSpeedYaw, Integer.valueOf(0), Integer.valueOf(100));
        a(DJIGimbalCapabilityKey.ControllerSmoothingPitch, Integer.valueOf(0), Integer.valueOf(30));
        a(DJIGimbalCapabilityKey.ControllerSmoothingYaw, Integer.valueOf(0), Integer.valueOf(30));
    }

    protected void a() {
        super.a();
    }
}

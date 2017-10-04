package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.handheld.DJIHandheldPowerMode;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.P3.DataOsdSetPower;
import dji.midware.data.model.P3.DataOsdSetPower.POWER_TYPE;
import dji.midware.data.model.b.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class a extends b {
    @f(a = "HandheldPowerMode")
    public void a(DJIHandheldPowerMode dJIHandheldPowerMode, e eVar) {
        POWER_TYPE power_type = POWER_TYPE.f;
        if (dJIHandheldPowerMode.equals(DJIHandheldPowerMode.Awake)) {
            power_type = POWER_TYPE.c;
        }
        if (dJIHandheldPowerMode.equals(DJIHandheldPowerMode.Sleeping)) {
            power_type = POWER_TYPE.b;
        }
        if (dJIHandheldPowerMode.equals(DJIHandheldPowerMode.PowerOff)) {
            power_type = POWER_TYPE.e;
        }
        DataOsdSetPower.getInstance().a(power_type).start(new 1(this, eVar, dJIHandheldPowerMode));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void a(e eVar) {
        DataOsdActiveStatus.getInstance().setType(b.b).start(new 2(this, eVar));
    }
}

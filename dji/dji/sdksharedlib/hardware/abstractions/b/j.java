package dji.sdksharedlib.hardware.abstractions.b;

import dji.midware.data.config.P3.DeviceType;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.e;

public class j extends a {
    @e(a = "SerialNumber")
    public void l(b.e eVar) {
        a(eVar, false);
    }

    @e(a = "InternalSerialNumber")
    public void m(b.e eVar) {
        a(eVar, true);
    }

    public void a(b.e eVar, boolean z) {
        new dji.midware.data.model.b.b().a(DeviceType.BATTERY).start(new 1(this, z, eVar));
    }
}

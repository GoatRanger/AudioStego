package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.data.model.b.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

public class f extends a {
    @dji.sdksharedlib.hardware.abstractions.f(a = "SelfDischargeDay")
    public void a(Integer num, e eVar) {
        short shortValue = num.shortValue();
        if ((shortValue < (short) 1 || shortValue > (short) 10) && eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.INVALID_PARAM));
        }
        DataCenterSelfDischarge.getInstance().setDays(shortValue).setFlag(false).start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SelfDischargeDay")
    public void i(e eVar) {
        DataCenterSelfDischarge instance = DataCenterSelfDischarge.getInstance();
        instance.setFlag(true).setDays(0).start(new 2(this, instance, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void l(e eVar) {
        a(eVar, false);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "InternalSerialNumber")
    public void m(e eVar) {
        a(eVar, true);
    }

    public void a(e eVar, boolean z) {
        DataBatteryActiveStatus.getInstance().setType(b.b).start(new 3(this, z, eVar));
    }
}

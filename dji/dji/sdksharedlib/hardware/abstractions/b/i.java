package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.data.model.P3.DataCenterSetSelfDischarge;
import dji.midware.data.model.b.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class i extends a {
    @f(a = "SelfDischargeDay")
    public void a(Integer num, e eVar) {
        int intValue = num.intValue();
        if ((intValue < 1 || intValue > 10) && eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.INVALID_PARAM));
        }
        DataCenterSetSelfDischarge dataCenterSetSelfDischarge = new DataCenterSetSelfDischarge();
        dataCenterSetSelfDischarge.a(0);
        dataCenterSetSelfDischarge.b(intValue).start(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SelfDischargeDay")
    public void i(e eVar) {
        DataCenterGetSelfDischarge dataCenterGetSelfDischarge = new DataCenterGetSelfDischarge();
        dataCenterGetSelfDischarge.setEncrypt(0);
        dataCenterGetSelfDischarge.start(new 2(this, dataCenterGetSelfDischarge, eVar));
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

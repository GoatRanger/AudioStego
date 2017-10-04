package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class d extends a {
    public d() {
        this.b = false;
    }

    @e(a = "WarningInformationRecords")
    public void k(b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @e(a = "CurrentWarningInformation")
    public void j(b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @f(a = "SelfDischargeDay")
    public void a(Integer num, b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @e(a = "SelfDischargeDay")
    public void i(b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @e(a = "FirmwareVersion")
    public void h(b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    @e(a = "SerialNumber")
    public void l(b.e eVar) {
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.NOT_SUPPORT_CURRENT_STATE));
        }
    }

    protected boolean s() {
        return false;
    }
}

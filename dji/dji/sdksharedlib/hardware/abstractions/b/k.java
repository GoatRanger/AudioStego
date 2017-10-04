package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataSmartBatteryGetBarCode;
import dji.midware.data.model.P3.DataSmartBatteryGetHistory;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.data.model.P3.DataSmartBatteryGetSetSelfDischargeDays;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.a.c;
import dji.sdksharedlib.hardware.abstractions.b.a.e;
import dji.sdksharedlib.hardware.abstractions.b.f;

public class k extends a {
    public final String h = "DJISDKCacheSmartBatteryAbstraction";
    protected int i = 0;
    protected c j;
    protected e k;

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.j = new c(i);
        this.k = new e(i);
    }

    public void e() {
        super.e();
    }

    protected boolean d() {
        return true;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LifeTimeRemainingPercentage")
    public void a(b.e eVar) {
        this.k.a(new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "NumberOfDischarge")
    public void b(b.e eVar) {
        this.k.a(new 8(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FullChargeEnergy")
    public void c(b.e eVar) {
        this.j.a(new 9(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentCurrent")
    public void d(b.e eVar) {
        this.j.a(new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentEnergy")
    public void e(b.e eVar) {
        this.j.a(new 11(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentVoltage")
    public void f(b.e eVar) {
        this.j.a(new 12(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EnergyRemainingPercentage")
    public void g(b.e eVar) {
        this.j.a(new 13(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "Temperature")
    public void q(b.e eVar) {
        this.j.a(new 14(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "WarningInformationRecords")
    public void k(b.e eVar) {
        if (eVar != null) {
            DataSmartBatteryGetHistory instance = DataSmartBatteryGetHistory.getInstance();
            instance.setIndex(this.i + 1).start(new 15(this, instance, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentWarningInformation")
    public void j(b.e eVar) {
        this.j.a(new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CellVoltages")
    public void n(b.e eVar) {
        if (eVar != null) {
            DataSmartBatteryGetPushCellVoltage instance = DataSmartBatteryGetPushCellVoltage.getInstance();
            instance.setIndex(this.i + 1).setRequestPush(false);
            instance.start(new 3(this, instance, eVar));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SelfDischargeDay")
    public void a(Integer num, b.e eVar) {
        if ((num.intValue() >= 1 && num.intValue() <= 10) || eVar == null) {
            DataSmartBatteryGetSetSelfDischargeDays.getInstance().setIndex(this.i + 1).setType(true).setDays(num.intValue()).start(new 4(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(a.INVALID_PARAM));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SelfDischargeDay")
    public void i(b.e eVar) {
        if (eVar != null) {
            DataSmartBatteryGetSetSelfDischargeDays instance = DataSmartBatteryGetSetSelfDischargeDays.getInstance();
            instance.setIndex(this.i + 1).setType(false).start(new 5(this, eVar, instance));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FirmwareVersion")
    public void h(b.e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.BATTERY);
        dataCommonGetVersion.start(new 6(this, dataCommonGetVersion, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SerialNumber")
    public void l(b.e eVar) {
        a(eVar, false);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "InternalSerialNumber")
    public void m(b.e eVar) {
        a(eVar, true);
    }

    public void a(b.e eVar, boolean z) {
        if (eVar != null) {
            DataSmartBatteryGetBarCode.getInstance().setIndex(this.i + 1).start(new 7(this, z, eVar));
        }
    }
}

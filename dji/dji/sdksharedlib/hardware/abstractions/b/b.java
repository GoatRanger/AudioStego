package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryOverview;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.sdksharedlib.b.a;
import dji.sdksharedlib.hardware.abstractions.b.a.c;
import dji.sdksharedlib.hardware.abstractions.b.a.d;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.e;

public class b extends dji.sdksharedlib.hardware.abstractions.b {
    private static String a = "DJISDKCacheBaseAggregationBatteryAbstraction";
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;
    private static final int i = 5;
    private static final int j = 6;
    private static final int k = 7;
    private int b = Integer.MAX_VALUE;
    private c c;
    private d d;

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.b = i;
        this.c = new c(i);
        this.d = new d();
        b();
    }

    public void e() {
        super.e();
    }

    protected void a() {
        a(a.class, getClass());
    }

    private void b() {
        Object obj = new DJIBatteryOverview[]{new DJIBatteryOverview()};
        a((Object) Integer.valueOf(0), c(a.z));
        a(obj, c(a.s));
        a((Object) Integer.valueOf(0), c(a.d));
        a((Object) Integer.valueOf(0), c(a.e));
        a((Object) Integer.valueOf(0), c(a.b));
        a((Object) Integer.valueOf(0), c(a.c));
        a((Object) Integer.valueOf(0), c(a.g));
        a((Object) Integer.valueOf(0), c(a.t));
        a((Object) Boolean.valueOf(true), c(a.u));
        a((Object) Boolean.valueOf(false), c(a.v));
        a((Object) Boolean.valueOf(false), c(a.w));
        a((Object) Boolean.valueOf(false), c(a.x));
        a((Object) Boolean.valueOf(false), c(a.y));
    }

    @e(a = "CurrentVoltage")
    public void a(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 1(this, eVar));
    }

    @e(a = "CurrentCurrent")
    public void b(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 8(this, eVar));
    }

    @e(a = "FullChargeEnergy")
    public void c(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 9(this, eVar));
    }

    @e(a = "CurrentEnergy")
    public void d(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 10(this, eVar));
    }

    @e(a = "EnergyRemainingPercentage")
    public void e(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 11(this, eVar));
    }

    @e(a = "HighestTemperature")
    public void f(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 12(this, eVar));
    }

    @e(a = "NumberOfConnectedBatteries")
    public void g(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.d.a(new 13(this, eVar));
    }

    @e(a = "Overviews")
    public void h(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.d.a(new 14(this, eVar));
    }

    @e(a = "ComponentDisconnected")
    public void i(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 15(this, eVar));
    }

    @e(a = "VoltageDifferenceDetected")
    public void j(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 2(this, eVar));
    }

    @e(a = "LowCellVoltageDetected")
    public void k(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 3(this, eVar));
    }

    @e(a = "HasDamagedCell")
    public void l(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 4(this, eVar));
    }

    @e(a = "FirmwareDifferenceDetected")
    public void m(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 5(this, eVar));
    }

    private static boolean b(long j, int i) {
        switch (i) {
            case 1:
                if ((281474976710656L & j) != 0) {
                    return true;
                }
                break;
            case 2:
                if ((562949953421312L & j) != 0) {
                    return true;
                }
                break;
            case 3:
                if ((1125899906842624L & j) != 0) {
                    return true;
                }
                break;
            case 4:
                if ((2251799813685248L & j) != 0) {
                    return true;
                }
                break;
            case 5:
                if ((3968 & j) != 0) {
                    return true;
                }
                break;
            case 6:
                if ((126976 & j) != 0) {
                    return true;
                }
                break;
            case 7:
                if ((18014398509481984L & j) != 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    @e(a = "CellVoltage")
    public void n(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (eVar != null) {
            DataSmartBatteryGetPushCellVoltage instance = DataSmartBatteryGetPushCellVoltage.getInstance();
            instance.setIndex(this.b).setRequestPush(false);
            instance.start(new 6(this, instance, eVar));
        }
    }

    @e(a = "Temperature")
    public void o(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.c.a(new 7(this, eVar));
    }
}

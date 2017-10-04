package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryCell;
import dji.common.battery.DJIBatteryStatus;
import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCenterGetBatteryHistory;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.data.model.P3.DataCenterSetSelfDischarge;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataSmartBatteryGetStaticData;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.e;
import java.util.ArrayList;
import java.util.List;

public class a extends b {
    private static final String a = "DJISDKCacheBatteryAbstraction";
    protected static final float f = 273.15f;
    protected boolean b = true;
    protected int c = -1;
    protected DataSmartBatteryGetPushDynamicData d = null;
    protected DataSmartBatteryGetStaticData e = null;
    DJIBatteryCell[] g;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        r();
        dji.thirdparty.a.c.a().a((Object) this);
        b();
    }

    public void e() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.e();
    }

    protected void b() {
        onEventBackgroundThread(DataCenterGetPushBatteryCommon.getInstance());
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushCellVoltage dataSmartBatteryGetPushCellVoltage) {
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        int i = 0;
        if (!(d() || dataCenterGetPushBatteryCommon == null)) {
            int battery;
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getFullCapacity()), dji.sdksharedlib.b.a.b);
            b((Object) DJIBatteryStatus.find(dataCenterGetPushBatteryCommon.getConnStatus()), dji.sdksharedlib.b.a.m);
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getCurrentCapacity()), dji.sdksharedlib.b.a.c);
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getCurrentPV()), dji.sdksharedlib.b.a.d);
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getCurrent()), dji.sdksharedlib.b.a.e);
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getLife()), dji.sdksharedlib.b.a.f);
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 1) {
                battery = DataOsdGetPushCommon.getInstance().getBattery();
            } else if (!DataFlycGetPushSmartBattery.getInstance().isGetted()) {
                battery = 0;
            } else if (DataOsdGetPushCommon.getInstance().getBatteryType() == BatteryType.NonSmart) {
                battery = DataFlycGetPushSmartBattery.getInstance().getVoltagePercent();
            } else {
                battery = DataFlycGetPushSmartBattery.getInstance().getBattery();
            }
            if (battery > 100) {
                battery = 100;
            } else if (battery < 0) {
                battery = 0;
            }
            if (dji.midware.c.a.getInstance().a() == dji.midware.c.a.c.h || dji.midware.c.a.getInstance().a() == dji.midware.c.a.c.i) {
                battery = (int) (((double) (((float) ((dataCenterGetPushBatteryCommon.getRelativeCapacity() - 3) * 100)) / 97.0f)) + 0.5d);
                if (battery > 100) {
                    battery = 100;
                }
                if (battery >= 0) {
                    i = battery;
                }
                battery = i;
            }
            b((Object) Integer.valueOf(battery), dji.sdksharedlib.b.a.g);
            b((Object) Float.valueOf((((float) dataCenterGetPushBatteryCommon.getTemperature()) / 10.0f) - 273.15f), dji.sdksharedlib.b.a.h);
            b((Object) Integer.valueOf(dataCenterGetPushBatteryCommon.getLoopNum()), dji.sdksharedlib.b.a.i);
        }
        b(dataCenterGetPushBatteryCommon.getPartVoltages(), dji.sdksharedlib.b.a.l);
        if (p()) {
            b((Object) Boolean.valueOf(dataCenterGetPushBatteryCommon.isBatteryOnCharge()), dji.sdksharedlib.b.a.E);
        }
    }

    @e(a = "FirmwareVersion")
    public void h(b.e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.BATTERY);
        dataCommonGetVersion.start(new 1(this, dataCommonGetVersion, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SelfDischargeDay")
    public void a(Integer num, b.e eVar) {
        short shortValue = num.shortValue();
        if ((shortValue < (short) 1 || shortValue > (short) 10) && eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(dji.midware.data.config.P3.a.INVALID_PARAM));
        }
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.litchiX) {
            DataCenterSetSelfDischarge dataCenterSetSelfDischarge = new DataCenterSetSelfDischarge();
            dataCenterSetSelfDischarge.a(0);
            dataCenterSetSelfDischarge.b(shortValue).start(new 2(this, eVar));
            return;
        }
        DataCenterSelfDischarge.getInstance().setDays(shortValue).setFlag(false).start(new 3(this, eVar));
    }

    @e(a = "SelfDischargeDay")
    public void i(b.e eVar) {
        ProductType c = i.getInstance().c();
        if (c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.litchiX) {
            DataCenterGetSelfDischarge dataCenterGetSelfDischarge = new DataCenterGetSelfDischarge();
            dataCenterGetSelfDischarge.setEncrypt(0);
            dataCenterGetSelfDischarge.start(new 4(this, dataCenterGetSelfDischarge, eVar));
            return;
        }
        DataCenterSelfDischarge instance = DataCenterSelfDischarge.getInstance();
        instance.setFlag(true).setDays(0).start(new 5(this, instance, eVar));
    }

    @e(a = "CurrentWarningInformation")
    public void j(b.e eVar) {
        boolean z = false;
        DataBatteryGetPushCheckStatus instance = DataBatteryGetPushCheckStatus.getInstance();
        if (instance == null && eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(dji.midware.data.config.P3.a.GET_PARAM_FAILED));
        }
        if (eVar != null) {
            boolean z2;
            boolean z3 = instance.getFirstDischargeStatus() || instance.getSecondDischargeStatus();
            if (instance.getFirstOverheatStatus() || instance.getSecondOverheatStatus()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (instance.getFirstLowheatStatus() || instance.getSecondLowheatStatus()) {
                z = true;
            }
            eVar.a(new DJIBatteryWarningInformation(z3, z2, z, instance.getDischargeShortCircuit(), instance.getCustomDischarge(), instance.getUnderVoltageBatteryCellIndex(), instance.getDamagedBatteryCellIndex()));
        }
    }

    @e(a = "WarningInformationRecords")
    public void k(b.e eVar) {
        DataCenterGetBatteryHistory.getInstance().start(new 6(this, eVar));
    }

    protected String d(String str) {
        return com.dji.frame.c.a.b(str).substring(0, 8);
    }

    @e(a = "SerialNumber")
    public void l(b.e eVar) {
        a(eVar, false);
    }

    @e(a = "InternalSerialNumber")
    public void m(b.e eVar) {
        a(eVar, true);
    }

    public void a(b.e eVar, boolean z) {
        if (i.getInstance().c() == ProductType.Tomato) {
            new dji.midware.data.model.b.b().a(DeviceType.BATTERY).start(new 7(this, z, eVar));
        } else {
            DataBatteryActiveStatus.getInstance().setType(dji.midware.data.model.b.a.b.b).start(new 8(this, z, eVar));
        }
    }

    @e(a = "CellVoltages")
    public void n(b.e eVar) {
        int i = 0;
        int[] partVoltages = DataCenterGetPushBatteryCommon.getInstance().getPartVoltages();
        int length;
        for (int i2 : partVoltages) {
            int i22;
            if (i22 != 0) {
                List arrayList = new ArrayList();
                length = partVoltages.length;
                while (i < length) {
                    i22 = partVoltages[i];
                    if (i22 == 0) {
                        break;
                    }
                    arrayList.add(new DJIBatteryCell(i22));
                    i++;
                }
                if (eVar != null) {
                    eVar.a((DJIBatteryCell[]) arrayList.toArray(new DJIBatteryCell[arrayList.size()]));
                    return;
                }
                return;
            }
        }
        if (eVar != null) {
            eVar.a(DJIBatteryError.getDJIError(dji.midware.data.config.P3.a.GET_PARAM_FAILED));
        }
    }

    @e(a = "NumberOfCells")
    public void o(b.e eVar) {
        new Thread(new 9(this, eVar)).start();
    }

    @e(a = "isSmartBattery")
    public void p(b.e eVar) {
        if (eVar != null) {
            eVar.a(Boolean.valueOf(this.b));
        }
    }

    protected Class<? extends d> c() {
        return dji.sdksharedlib.b.a.class;
    }

    protected boolean d() {
        return false;
    }

    protected boolean p() {
        return false;
    }

    protected void a() {
        a(dji.sdksharedlib.b.a.class, getClass());
    }

    protected boolean q() {
        return this.b;
    }

    protected void r() {
        a((Object) Boolean.valueOf(q()), c(dji.sdksharedlib.b.a.r));
    }

    protected boolean s() {
        return true;
    }
}

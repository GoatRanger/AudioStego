package dji.sdksharedlib.b;

import dji.common.battery.DJIBatteryCell;
import dji.common.battery.DJIBatteryLowCellVoltageOperation;
import dji.common.battery.DJIBatteryOverview;
import dji.common.battery.DJIBatteryStatus;
import dji.common.battery.DJIBatteryWarningInformation;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.b.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.g;
import dji.sdksharedlib.hardware.abstractions.b.h;
import dji.sdksharedlib.hardware.abstractions.b.i;
import dji.sdksharedlib.hardware.abstractions.b.l;

public class a extends d {
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {l.class})
    public static final String A = "Level1CellVoltageThreshold";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {l.class})
    public static final String B = "Level2CellVoltageThreshold";
    @d(a = DJIBatteryLowCellVoltageOperation.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {l.class})
    public static final String C = "Level1CellVoltageOperation";
    @d(a = DJIBatteryLowCellVoltageOperation.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {l.class})
    public static final String D = "Level2CellVoltageOperation";
    @d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String E = "IsBatteryOnCharge";
    @f
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {l.class})
    @c
    public static final String F = "InternalSerialNumber";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String G = "FirmwareVersion";
    public static final String a = "Battery";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String b = "FullChargeEnergy";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String c = "CurrentEnergy";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String d = "CurrentVoltage";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String e = "CurrentCurrent";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class})})
    public static final String f = "LifeTimeRemainingPercentage";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String g = "EnergyRemainingPercentage";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String h = "Temperature";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 4, f = {l.class}), @d(a = Integer.class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, g.class, h.class, b.class})})
    public static final String i = "NumberOfDischarge";
    @d(a = DJIBatteryWarningInformation.class, c = 1, f = {l.class})
    public static final String j = "CurrentWarningInformation";
    @d(a = DJIBatteryWarningInformation[].class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String k = "WarningInformationRecords";
    @dji.sdksharedlib.b.b.a(a = {@d(a = int[].class, c = 4, f = {l.class}), @d(a = int[].class, c = 1, e = {dji.sdksharedlib.hardware.abstractions.b.c.class, b.class})})
    public static final String l = "CellVoltage";
    @f
    @d(a = DJIBatteryStatus.class, c = 4, e = {i.class})
    @c
    public static final String m = "Status";
    @d(a = Integer.class, c = 3, f = {l.class})
    public static final String n = "SelfDischargeDay";
    @d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {l.class})
    public static final String o = "SerialNumber";
    @d(a = DJIBatteryCell[].class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {l.class})
    public static final String p = "CellVoltages";
    @dji.sdksharedlib.b.b.a(a = {@d(a = Integer.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN), @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {l.class})})
    public static final String q = "NumberOfCells";
    @d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN, f = {l.class})
    public static final String r = "isSmartBattery";
    @d(a = DJIBatteryOverview[].class, c = 1, e = {b.class})
    public static final String s = "Overviews";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String t = "HighestTemperature";
    @d(a = Boolean.class, c = 1, e = {b.class})
    public static final String u = "ComponentDisconnected";
    @d(a = Boolean.class, c = 1, e = {b.class})
    public static final String v = "VoltageDifferenceDetected";
    @d(a = Boolean.class, c = 1, e = {b.class})
    public static final String w = "LowCellVoltageDetected";
    @d(a = Boolean.class, c = 1, e = {b.class})
    public static final String x = "HasDamagedCell";
    @d(a = Boolean.class, c = 1, e = {b.class})
    public static final String y = "FirmwareDifferenceDetected";
    @d(a = Integer.class, c = 1, e = {b.class})
    public static final String z = "NumberOfConnectedBatteries";

    public a(String str) {
        super(str);
    }

    protected String a() {
        return a;
    }
}

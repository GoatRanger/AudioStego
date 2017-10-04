package dji.sdksharedlib.b.a;

import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.a.a.e;
import dji.sdksharedlib.hardware.abstractions.a.b;

public class a extends d {
    public static final String a = "AirLink";
    @dji.sdksharedlib.b.b.d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {b.class, dji.d.a.a.class})
    public static final String b = "RemoteControllerSignalStrength";
    @dji.sdksharedlib.b.b.d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String c = "VideoSignalStrength";
    @dji.sdksharedlib.b.b.d(a = LBAirLinkFPVVideoQualityLatency.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.b.class, e.class})
    @c
    public static final String d = "TransmissionMode";
    @dji.sdksharedlib.b.b.d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String e = "IsAuxLinkSupported";
    @dji.sdksharedlib.b.b.d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String f = "IsWiFiLinkSupported";
    @dji.sdksharedlib.b.b.d(a = Boolean.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String g = "IsLBAirLinkSupported";

    public a(String str) {
        super(str);
    }
}

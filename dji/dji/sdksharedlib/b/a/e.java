package dji.sdksharedlib.b.a;

import dji.common.airlink.DJIWiFiSignalQuality;
import dji.common.airlink.WiFiFrequencyBand;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;

public class e extends a {
    public static final String h = "WifiLink";
    @d(c = 8, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String i = "RebootWifi";
    @d(a = DJIWiFiSignalQuality.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    public static final String j = "SignalQuality";
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String k = "WiFiSSID";
    @d(a = String.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String l = "WiFiPassword";
    @d(a = WiFiFrequencyBand.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String m = "WiFiFrequencyBand";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String n = "IsWifiFrequencyEditable";

    public e(String str) {
        super(str);
    }
}

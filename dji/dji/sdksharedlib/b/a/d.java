package dji.sdksharedlib.b.a;

import dji.common.airlink.ChannelSelectionMode;
import dji.common.airlink.FrequencyInterference;
import dji.common.airlink.OcuSyncBandwidth;
import dji.common.airlink.OcuSyncWarningMessage;
import dji.common.airlink.SDRHdOffsetParams;
import dji.sdksharedlib.b.b.c;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.a.a.e;

public class d extends a {
    public static final String h = "OcuSyncLink";
    @dji.sdksharedlib.b.b.d(a = OcuSyncBandwidth.class, c = 7, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String i = "OcuSyncBandwidth";
    @dji.sdksharedlib.b.b.d(a = Float.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String j = "DynamicDataRate";
    @dji.sdksharedlib.b.b.d(a = Integer.class, c = 6, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String k = "FrequencyPointIndex";
    @dji.sdksharedlib.b.b.d(a = Integer[].class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String l = "FrequencyPointIndexValidRange";
    @dji.sdksharedlib.b.b.d(a = FrequencyInterference[].class, c = 1, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String m = "FrequencyPointRSSIs";
    @dji.sdksharedlib.b.b.d(a = OcuSyncWarningMessage[].class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    public static final String n = "OcuSyncWarningMessages";
    @dji.sdksharedlib.b.b.d(a = ChannelSelectionMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String o = "ChannelSelectionMode";
    @f
    @dji.sdksharedlib.b.b.d(a = SDRHdOffsetParams.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    @c
    public static final String p = "SdrHdOffsetParamValues";
    @f
    @dji.sdksharedlib.b.b.d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {e.class})
    @c
    public static final String q = "SdrHdDistOffset";

    public d(String str) {
        super(str);
    }
}

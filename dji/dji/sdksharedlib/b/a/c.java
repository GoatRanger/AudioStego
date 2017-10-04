package dji.sdksharedlib.b.a;

import dji.common.LBAirLinkEncodeMode;
import dji.common.LBAirLinkPIPPosition;
import dji.common.LBAirLinkSecondaryVideoFormat;
import dji.common.VideoDataChannel;
import dji.common.airlink.DJILBAirLinkFrequencyPointRSSI;
import dji.common.airlink.DJISignalInformation;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBAirLinkDataRate;
import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.airlink.LBAirLinkPIPDisplay;
import dji.common.airlink.LBAirLinkSecondaryVideoOutputPort;
import dji.common.airlink.LBAirLinkUnit;
import dji.common.airlink.LBSDRBandwidth;
import dji.common.airlink.SDRHdOffsetParams;
import dji.common.airlink.SDRInterferedTerminal;
import dji.midware.data.model.P3.DataOsdGetPushSDRNfParams.DisLossEvent;
import dji.sdksharedlib.b.b.d;
import dji.sdksharedlib.b.b.f;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import dji.sdksharedlib.hardware.abstractions.a.a.b;

public class c extends a {
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String A = "SDIOutputFormat";
    @d(a = LBAirLinkPIPPosition.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String B = "PIPPosition";
    @d(a = VideoDataChannel.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String C = "VideoDataChannel";
    @f
    @d(a = DJISignalInformation.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @dji.sdksharedlib.b.b.c
    public static final String D = "RemoteControllerAntennaSignalData1";
    @f
    @d(a = DJISignalInformation.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @dji.sdksharedlib.b.b.c
    public static final String E = "RemoteControllerAntennaSignalData2";
    @f
    @d(a = DJISignalInformation.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @dji.sdksharedlib.b.b.c
    public static final String F = "LightBridgeAntennaSignalData1";
    @f
    @d(a = DJISignalInformation.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC)
    @dji.sdksharedlib.b.b.c
    public static final String G = "LightBridgeAntennaSignalData2";
    @d(a = LBSDRBandwidth.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String H = "SDRBandwidth";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String I = "DynamicDataRate";
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String J = "DataRateMinMax";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String K = "CurrentFrequencyPointIndex";
    @d(a = Float[].class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String L = "FrequencyPointIndexValidRange";
    @d(a = LBAirLinkFPVVideoQualityLatency.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {b.class, dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String M = "TransmissionMode";
    @d(a = DJILBAirLinkFrequencyPointRSSI[].class, c = 17, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String N = "ChannelRSSIs";
    @d(a = int[].class, c = 17, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    public static final String O = "SweepFrequency";
    @f
    @d(a = SDRInterferedTerminal.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    @dji.sdksharedlib.b.b.c
    public static final String P = "SdrSignalInterferedEvent";
    @f
    @d(a = SDRHdOffsetParams.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    @dji.sdksharedlib.b.b.c
    public static final String Q = "SdrHdOffsetParamValues";
    @f
    @d(a = Integer.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    @dji.sdksharedlib.b.b.c
    public static final String R = "SdrHdDistOffset";
    @f
    @d(a = DisLossEvent.class, c = 4, d = DJISDKCacheUpdateType.DYNAMIC, e = {dji.sdksharedlib.hardware.abstractions.a.a.d.class})
    @dji.sdksharedlib.b.b.c
    public static final String S = "SdrUsrConfigEvent";
    public static final String h = "LBAirLink";
    @d(a = Float.class, c = 7, d = DJISDKCacheUpdateType.DYNAMIC, e = {b.class})
    public static final String i = "DualEncodeModePercent";
    @d(a = LBAirLinkEncodeMode.class, c = 7, d = DJISDKCacheUpdateType.DYNAMIC, e = {b.class})
    public static final String j = "EncodeMode";
    @d(a = Integer.class, c = 7, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String k = "FPVVideoBandwidthPercent";
    @d(a = LBAirLinkChannelSelectionMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String l = "VideoSource";
    @d(a = LBAirLinkChannelSelectionMode.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String m = "ChannelSelectionMode";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String n = "Channel";
    @d(a = LBAirLinkDataRate.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String o = "DataRate";
    @d(a = LBAirLinkFPVVideoQualityLatency.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String p = "FPVQualityLatency";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String q = "SecondaryVideoOutputEnabled";
    @d(a = LBAirLinkSecondaryVideoOutputPort.class, c = 3, d = DJISDKCacheUpdateType.DYNAMIC, e = {b.class})
    public static final String r = "SecondaryVideoOutputPort";
    @d(a = LBAirLinkPIPDisplay.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String s = "PIPDisplay";
    @d(a = Boolean.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String t = "DisplayOSDEnabled";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String u = "OSDTopMargin";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String v = "OSDLeftMargin";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String w = "OSDBottomMargin";
    @d(a = Integer.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String x = "OSDRightMargin";
    @d(a = LBAirLinkUnit.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String y = "OSDUnits";
    @d(a = LBAirLinkSecondaryVideoFormat.class, c = 3, d = DJISDKCacheUpdateType.USER_DRIVEN, e = {b.class})
    public static final String z = "HDMIOutputFormat";

    public c(String str) {
        super(str);
    }
}

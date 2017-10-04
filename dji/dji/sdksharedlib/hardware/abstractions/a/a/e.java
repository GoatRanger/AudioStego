package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.airlink.ChannelSelectionMode;
import dji.common.airlink.DJISignalInformation;
import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.airlink.OcuSyncBandwidth;
import dji.common.airlink.OcuSyncWarningMessage;
import dji.common.error.DJICameraError;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataOsdGetPushSdrConfigInfo;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataOsdGetPushWirelessState;
import dji.midware.data.model.P3.DataOsdGetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdGetSDRPushCustomCodeRate;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo.SDRConfigInfo;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo.SDRConfigType;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode.SDRImageTransmMode;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.hardware.abstractions.a.a.a.b;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.d;
import dji.thirdparty.f.h.c;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class e extends d implements dji.sdksharedlib.c.d {
    private static final float d = 2400.0f;
    private static final float e = 2.0f;
    private static final long i = 21000;
    protected a a;
    protected Map<OcuSyncWarningMessage, Long> b = new ConcurrentHashMap();
    protected boolean c = false;
    private dji.sdksharedlib.hardware.abstractions.a.a.b.a f;
    private b g;
    private a h;
    private dji.thirdparty.f.d<Boolean> j = dji.thirdparty.f.d.a(new c(dji.thirdparty.f.d.b(Boolean.valueOf(true)).e(1, TimeUnit.SECONDS).r(new 1(this)), new 5(this), new 6(this)));

    public boolean b() {
        return false;
    }

    private boolean a(long j, long j2) {
        return j2 - j > i;
    }

    private synchronized boolean p() {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        z = false;
        Iterator it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            boolean z2;
            if (a(((Long) ((Entry) it.next()).getValue()).longValue(), currentTimeMillis)) {
                it.remove();
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        return z;
    }

    protected boolean a(OcuSyncWarningMessage ocuSyncWarningMessage) {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z2 = false;
        if (this.b.containsKey(ocuSyncWarningMessage)) {
            this.b.put(ocuSyncWarningMessage, Long.valueOf(currentTimeMillis));
        } else {
            this.b.put(ocuSyncWarningMessage, Long.valueOf(currentTimeMillis));
            z2 = true;
        }
        if (!p()) {
            z = z2;
        }
        if (z) {
            d();
        }
        return z;
    }

    public void a(String str, int i, String str2, int i2, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, str2, i2, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        this.a = new a();
        this.a.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(i)).c(dji.sdksharedlib.b.a.d.h).b(Integer.valueOf(i2));
        this.f = new dji.sdksharedlib.hardware.abstractions.a.a.b.a();
        this.g = new b();
        DJISDKCache.getInstance().startListeningForUpdates(this.a.d("ChannelSelectionMode").a(), this, false);
        DJISDKCache.getInstance().startListeningForUpdates(this.a.d(dji.sdksharedlib.b.a.d.i).a(), this, false);
    }

    public void e() {
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        if (dji.thirdparty.a.c.a().c(this.h)) {
            dji.thirdparty.a.c.a().d(this.h);
        }
        super.e();
        DJISDKCache.getInstance().stopListening(this);
        this.f.e();
        this.f = null;
        this.g = null;
        this.b.clear();
    }

    protected void a() {
        a(dji.sdksharedlib.b.a.d.class, getClass());
    }

    private OcuSyncBandwidth q() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.l(dji.sdksharedlib.b.a.d.i));
        if (availableValue == null || availableValue.e() == null) {
            return OcuSyncBandwidth.Unknown;
        }
        return (OcuSyncBandwidth) availableValue.e();
    }

    private ChannelSelectionMode r() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.l("ChannelSelectionMode"));
        if (availableValue == null || availableValue.e() == null) {
            return ChannelSelectionMode.Unknown;
        }
        return (ChannelSelectionMode) availableValue.e();
    }

    private Integer[] a(OcuSyncBandwidth ocuSyncBandwidth, ChannelSelectionMode channelSelectionMode) {
        Integer[] numArr = new Integer[2];
        if (!(ocuSyncBandwidth == null || channelSelectionMode == null)) {
            if (ocuSyncBandwidth.equals(OcuSyncBandwidth.Bandwidth10MHz) && channelSelectionMode.equals(ChannelSelectionMode.Auto)) {
                numArr[0] = Integer.valueOf(1006);
                numArr[1] = Integer.valueOf(1078);
            } else if (ocuSyncBandwidth.equals(OcuSyncBandwidth.Bandwidth10MHz) && channelSelectionMode.equals(ChannelSelectionMode.Manual)) {
                numArr[0] = Integer.valueOf(1007);
                numArr[1] = Integer.valueOf(1072);
            } else if (ocuSyncBandwidth.equals(OcuSyncBandwidth.Bandwidth20MHz) && channelSelectionMode.equals(ChannelSelectionMode.Auto)) {
                numArr[0] = Integer.valueOf(1011);
                numArr[1] = Integer.valueOf(1073);
            } else if (ocuSyncBandwidth.equals(OcuSyncBandwidth.Bandwidth20MHz) && channelSelectionMode.equals(ChannelSelectionMode.Manual)) {
                numArr[0] = Integer.valueOf(1012);
                numArr[1] = Integer.valueOf(1067);
            }
        }
        return numArr;
    }

    private Integer[] s() {
        OcuSyncBandwidth q = q();
        ChannelSelectionMode r = r();
        if (q == OcuSyncBandwidth.Unknown || r == ChannelSelectionMode.Unknown) {
            return null;
        }
        Integer[] a = a(q, r);
        a(a, this.a.d("FrequencyPointIndexValidRange").a());
        return a;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FrequencyPointIndexValidRange")
    public void a(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (eVar != null) {
            dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.l("FrequencyPointIndexValidRange"));
            if (availableValue == null || availableValue.e() == null) {
                eVar.a(DJIError.COMMON_EXECUTION_FAILED);
            } else {
                eVar.a(availableValue.e());
            }
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "OcuSyncBandwidth")
    public void b(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.g.a(dji.sdksharedlib.b.a.d.i, new 7(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "OcuSyncBandwidth")
    public void a(OcuSyncBandwidth ocuSyncBandwidth, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (r() != ChannelSelectionMode.Manual) {
            if (eVar != null) {
                eVar.a(DJICameraError.COMMON_EXECUTION_FAILED);
            }
        } else if (ocuSyncBandwidth != null && ocuSyncBandwidth != OcuSyncBandwidth.Unknown) {
            DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.d, ocuSyncBandwidth.value())).start(new 8(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FrequencyPointRSSIs")
    public void c(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (!((dji.sdksharedlib.hardware.abstractions.b.d) eVar).a()) {
            if (this.h == null) {
                this.h = new a(this, null);
            }
            if (!dji.thirdparty.a.c.a().c(this.h)) {
                dji.thirdparty.a.c.a().a(this.h);
            }
            this.f.d();
        }
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "FrequencyPointIndex")
    public void a(Integer num, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        Integer[] s = s();
        if (s == null) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_INVALID);
        } else if (s[0].intValue() > num.intValue() || s[1].intValue() < num.intValue()) {
            CallbackUtils.onFailure(eVar, DJIError.COMMON_PARAM_ILLEGAL);
        } else if (r() == ChannelSelectionMode.Manual) {
            DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.a, num.intValue())).start(new 9(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_EXECUTION_FAILED);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FrequencyPointIndex")
    public void d(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.g.a(dji.sdksharedlib.b.a.d.k, new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "TransmissionMode")
    public void a(LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (lBAirLinkFPVVideoQualityLatency != null && lBAirLinkFPVVideoQualityLatency != LBAirLinkFPVVideoQualityLatency.Unknown) {
            SDRImageTransmMode sDRImageTransmMode;
            if (lBAirLinkFPVVideoQualityLatency.equals(LBAirLinkFPVVideoQualityLatency.LowLatency)) {
                sDRImageTransmMode = SDRImageTransmMode.a;
            } else if (lBAirLinkFPVVideoQualityLatency.equals(LBAirLinkFPVVideoQualityLatency.HighQuality)) {
                sDRImageTransmMode = SDRImageTransmMode.b;
            } else {
                sDRImageTransmMode = SDRImageTransmMode.c;
            }
            DataOsdSetSDRImageTransmMode.getInstance().a(sDRImageTransmMode).start(new 11(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "TransmissionMode")
    public void e(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        DataOsdGetSDRImageTransmMode.getInstance().start(new 12(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ChannelSelectionMode")
    public void f(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.g.a("ChannelSelectionMode", new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ChannelSelectionMode")
    public void a(ChannelSelectionMode channelSelectionMode, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        if (channelSelectionMode != null && channelSelectionMode != ChannelSelectionMode.Unknown) {
            DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.c, channelSelectionMode.value() == 0 ? 1 : 0)).start(new 3(this, eVar));
        } else if (eVar != null) {
            eVar.a(DJICameraError.COMMON_PARAM_ILLEGAL);
        }
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        s();
    }

    public void onEventBackgroundThread(DataOsdGetSDRPushCustomCodeRate dataOsdGetSDRPushCustomCodeRate) {
        a(Float.valueOf(dataOsdGetSDRPushCustomCodeRate.getCodeRate()), this.a.d("DynamicDataRate").a());
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrConfigInfo dataOsdGetPushSdrConfigInfo) {
        a(OcuSyncBandwidth.find(dataOsdGetPushSdrConfigInfo.getBand()), this.a.d(dji.sdksharedlib.b.a.d.i).a());
        a(Integer.valueOf(dataOsdGetPushSdrConfigInfo.getNF()), this.a.d(dji.sdksharedlib.b.a.d.k).a());
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        if (dataOsdGetPushSignalQuality.isGetRcQuality()) {
            a(Integer.valueOf(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), 0).getPercent()), this.a.d(dji.sdksharedlib.b.a.a.b).a());
            a(Integer.valueOf(dataOsdGetPushSignalQuality.getDownSignalQuality()), this.a.d(dji.sdksharedlib.b.a.a.c).a());
        }
    }

    public synchronized void onEventBackgroundThread(DataOsdGetPushWirelessState dataOsdGetPushWirelessState) {
        OcuSyncWarningMessage ocuSyncWarningMessage;
        switch (4.a[dataOsdGetPushWirelessState.getEventCode().ordinal()]) {
            case 1:
                ocuSyncWarningMessage = OcuSyncWarningMessage.STRONG_TAKE_OFF_INTERFERENCE;
                break;
            case 2:
                ocuSyncWarningMessage = OcuSyncWarningMessage.STRONG_DOWN_LINK_INTERFERENCE;
                break;
            case 3:
                ocuSyncWarningMessage = OcuSyncWarningMessage.STRONG_UP_LINK_INTERFERENCE;
                break;
            case 4:
                ocuSyncWarningMessage = OcuSyncWarningMessage.WEAK_SIGNAL;
                break;
            case 5:
                ocuSyncWarningMessage = OcuSyncWarningMessage.STRONG_INTERFERENCE_WITH_MANUAL_SETTING;
                break;
            case 6:
                ocuSyncWarningMessage = OcuSyncWarningMessage.WEAK_SIGNAL_FROM_GLASS_TO_REMOTE_CONTROLLER;
                break;
            case 7:
                ocuSyncWarningMessage = OcuSyncWarningMessage.AIRCRAFT_LINK_REBOOT;
                break;
            case 8:
                ocuSyncWarningMessage = OcuSyncWarningMessage.WEAK_SIGNAL_FROM_REMOTE_CONTROLLER_TO_GLASS;
                break;
            case 9:
                ocuSyncWarningMessage = OcuSyncWarningMessage.UP_LINK_BROKEN;
                break;
            case 10:
                ocuSyncWarningMessage = OcuSyncWarningMessage.DOWN_LINK_BROKEN;
                break;
            case 11:
                ocuSyncWarningMessage = OcuSyncWarningMessage.LINK_UNUSABLE;
                break;
            case 12:
                ocuSyncWarningMessage = OcuSyncWarningMessage.DEBUG;
                break;
            default:
                ocuSyncWarningMessage = OcuSyncWarningMessage.LINK_UNUSABLE;
                break;
        }
        if (a(ocuSyncWarningMessage)) {
            a(c(), c(dji.sdksharedlib.b.a.d.n));
        }
    }

    protected OcuSyncWarningMessage[] c() {
        OcuSyncWarningMessage[] ocuSyncWarningMessageArr = new OcuSyncWarningMessage[this.b.size()];
        int i = 0;
        for (Entry key : this.b.entrySet()) {
            ocuSyncWarningMessageArr[i] = (OcuSyncWarningMessage) key.getKey();
            i++;
        }
        return ocuSyncWarningMessageArr;
    }

    protected void d() {
        if (!this.c) {
            DJISDKCache.getInstance().addSubscription(this.j.C());
        }
    }
}

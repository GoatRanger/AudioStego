package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.airlink.DJILBAirLinkFrequencyPointRSSI;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.airlink.LBSDRBandwidth;
import dji.common.airlink.SDRHdOffsetParams;
import dji.common.util.SDRLinkHelper;
import dji.midware.data.model.P3.DataOsdGetPushMaxMcs;
import dji.midware.data.model.P3.DataOsdGetPushSDRBarInterference;
import dji.midware.data.model.P3.DataOsdGetPushSDRNfParams;
import dji.midware.data.model.P3.DataOsdGetPushSdrConfigInfo;
import dji.midware.data.model.P3.DataOsdGetPushSdrSweepFrequency;
import dji.midware.data.model.P3.DataOsdGetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo.SDRConfigInfo;
import dji.midware.data.model.P3.DataOsdSetSDRConfigInfo.SDRConfigType;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode;
import dji.midware.data.model.P3.DataOsdSetSDRImageTransmMode.SDRImageTransmMode;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.a.a.a.a;
import dji.sdksharedlib.hardware.abstractions.a.a.b.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;

public class d extends a implements dji.sdksharedlib.c.d {
    private b a;
    private a b;

    public void a(String str, int i, String str2, int i2, c cVar, f fVar) {
        super.a(str, i, str2, i2, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        this.a = new b();
        this.b = new a();
        DJISDKCache.getInstance().startListeningForUpdates(this.c.d("ChannelSelectionMode").a(), this, false);
        DJISDKCache.getInstance().startListeningForUpdates(this.c.d(dji.sdksharedlib.b.a.c.H).a(), this, false);
    }

    public void e() {
        super.e();
        DJISDKCache.getInstance().stopListening(this);
        this.a.e();
        this.a = null;
        this.b = null;
    }

    public void a(LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency, e eVar) {
        SDRImageTransmMode sDRImageTransmMode;
        if (lBAirLinkFPVVideoQualityLatency.equals(LBAirLinkFPVVideoQualityLatency.LowLatency)) {
            sDRImageTransmMode = SDRImageTransmMode.a;
        } else if (lBAirLinkFPVVideoQualityLatency.equals(LBAirLinkFPVVideoQualityLatency.HighQuality)) {
            sDRImageTransmMode = SDRImageTransmMode.b;
        } else {
            sDRImageTransmMode = SDRImageTransmMode.c;
        }
        DataOsdSetSDRImageTransmMode.getInstance().a(sDRImageTransmMode).start(new 1(this, eVar));
    }

    public void d(e eVar) {
        DataOsdGetSDRImageTransmMode.getInstance().start(new 2(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "SDRBandwidth")
    public void u(e eVar) {
        this.b.a(dji.sdksharedlib.b.a.c.H, new 3(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ChannelSelectionMode")
    public void a(e eVar) {
        this.b.a("ChannelSelectionMode", new 4(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "CurrentFrequencyPointIndex")
    public void a(Integer num, e eVar) {
        DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.a, num.intValue())).start(new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "ChannelSelectionMode")
    public void a(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode, e eVar) {
        DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.c, lBAirLinkChannelSelectionMode.value() == 0 ? 1 : 0)).start(new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.f(a = "SDRBandwidth")
    public void a(LBSDRBandwidth lBSDRBandwidth, e eVar) {
        DataOsdSetSDRConfigInfo.getInstance().a(new SDRConfigInfo(SDRConfigType.d, lBSDRBandwidth.value())).start(new 7(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentFrequencyPointIndex")
    public void v(e eVar) {
        this.b.a(dji.sdksharedlib.b.a.c.K, new 8(this, eVar));
    }

    private void d() {
        if (DataOsdGetPushSDRNfParams.getInstance().isGetted()) {
            dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.h(dji.sdksharedlib.b.a.c.H));
            if (availableValue != null && availableValue.e() != null) {
                dji.sdksharedlib.d.a availableValue2 = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.h("ChannelSelectionMode"));
                if (availableValue2 != null && availableValue2.e() != null) {
                    Object obj = new Float[]{Float.valueOf(SDRLinkHelper.ORIGINAL_NF_START_INDEX), Float.valueOf(2481.5f)};
                    LBSDRBandwidth lBSDRBandwidth = (LBSDRBandwidth) availableValue.e();
                    if (!((LBAirLinkChannelSelectionMode) availableValue2.e()).equals(LBAirLinkChannelSelectionMode.Auto)) {
                        if (DataOsdGetPushSDRNfParams.getInstance().getMinNf10M() == 0) {
                            if (lBSDRBandwidth == LBSDRBandwidth.Bandwidth10MHz) {
                                obj[0] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(1007));
                                obj[1] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(1069));
                            } else {
                                obj[0] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(1017));
                                obj[1] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(1056));
                            }
                        } else if (lBSDRBandwidth == LBSDRBandwidth.Bandwidth10MHz) {
                            obj[0] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(DataOsdGetPushSDRNfParams.getInstance().getMinNf10M()));
                            obj[1] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(DataOsdGetPushSDRNfParams.getInstance().getMaxNf10M()));
                        } else {
                            obj[0] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(DataOsdGetPushSDRNfParams.getInstance().getMinNf20M()));
                            obj[1] = Float.valueOf(SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(DataOsdGetPushSDRNfParams.getInstance().getMaxNf20M()));
                        }
                    }
                    availableValue2 = DJISDKCache.getInstance().getAvailableValue(dji.sdksharedlib.a.b.h("FrequencyPointIndexValidRange"));
                    if (availableValue2 == null || availableValue2.e() == null) {
                        a(obj, this.c.d("FrequencyPointIndexValidRange").a());
                        return;
                    }
                    Float[] fArr = (Float[]) availableValue2.e();
                    if (fArr[0].floatValue() != obj[0].floatValue() || fArr[1].floatValue() != obj[1].floatValue()) {
                        a(obj, this.c.d("FrequencyPointIndexValidRange").a());
                    }
                }
            }
        }
    }

    public boolean b() {
        return false;
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ChannelRSSIs")
    public void w(e eVar) {
        if (!((dji.sdksharedlib.hardware.abstractions.b.d) eVar).a()) {
            this.a.d();
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrSweepFrequency dataOsdGetPushSdrSweepFrequency) {
        if (!(this.a == null || this.a.f())) {
            this.a.e();
        }
        Object obj = new DJILBAirLinkFrequencyPointRSSI[dataOsdGetPushSdrSweepFrequency.getRecData().length];
        for (int i = 0; i < dataOsdGetPushSdrSweepFrequency.getRecData().length; i++) {
            obj[i] = new DJILBAirLinkFrequencyPointRSSI(((float) (i * 2)) + 2400.0f, ((float) ((i + 1) * 2)) + 2400.0f, dataOsdGetPushSdrSweepFrequency.getSignalList()[i]);
        }
        a(obj, this.c.d(dji.sdksharedlib.b.a.c.N).a());
    }

    public void onEventBackgroundThread(DataOsdGetPushSdrConfigInfo dataOsdGetPushSdrConfigInfo) {
        a(LBSDRBandwidth.find(dataOsdGetPushSdrConfigInfo.getBand()), this.c.d(dji.sdksharedlib.b.a.c.H).a());
        a(Integer.valueOf(dataOsdGetPushSdrConfigInfo.getNF()), this.c.d(dji.sdksharedlib.b.a.c.K).a());
    }

    public void onEventBackgroundThread(DataOsdGetPushMaxMcs dataOsdGetPushMaxMcs) {
        a(Integer.valueOf(dataOsdGetPushMaxMcs.getMaxMcs()), this.c.d("DynamicDataRate").a());
    }

    public void onEventBackgroundThread(DataOsdGetPushSDRNfParams dataOsdGetPushSDRNfParams) {
        a(new SDRHdOffsetParams(dataOsdGetPushSDRNfParams.getPathLossOffset(), dataOsdGetPushSDRNfParams.getRcLinkOffset(), dataOsdGetPushSDRNfParams.getTxPowerOffset()), this.c.d("SdrHdOffsetParamValues").a());
        a(Integer.valueOf(dataOsdGetPushSDRNfParams.get1KmOffset()), this.c.d("SdrHdDistOffset").a());
        a(dataOsdGetPushSDRNfParams.getDisLossInd(), this.c.d(dji.sdksharedlib.b.a.c.S).a());
        d();
    }

    public void onEventBackgroundThread(DataOsdGetPushSDRBarInterference dataOsdGetPushSDRBarInterference) {
        a(Integer.valueOf(dataOsdGetPushSDRBarInterference.getBeInterfered()), this.c.d(dji.sdksharedlib.b.a.c.P).a());
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        d();
    }
}

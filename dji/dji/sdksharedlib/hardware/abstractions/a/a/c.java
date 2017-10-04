package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.LBAirLinkEncodeMode;
import dji.common.LBAirLinkPIPPosition;
import dji.common.LBAirLinkSecondaryVideoFormat;
import dji.common.VideoDataChannel;
import dji.common.airlink.AirLinkUtils;
import dji.common.airlink.DJISignalInformation;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBAirLinkDataRate;
import dji.common.airlink.LBAirLinkFPVVideoQualityLatency;
import dji.common.airlink.LBAirLinkPIPDisplay;
import dji.common.airlink.LBAirLinkSecondaryVideoOutputPort;
import dji.common.airlink.LBAirLinkUnit;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataOsdGetPushSweepFrequency;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.d;
import dji.sdksharedlib.hardware.abstractions.f;
import java.util.ArrayList;

public abstract class c extends d {
    private static final String a = "DJISDKCacheLightBridgeSeriesAirLinkAbstraction";
    protected a c;

    @f(a = "DualEncodeModePercent")
    public abstract void a(float f, e eVar);

    @f(a = "Channel")
    public abstract void a(int i, e eVar);

    @f(a = "EncodeMode")
    public abstract void a(LBAirLinkEncodeMode lBAirLinkEncodeMode, e eVar);

    @f(a = "PIPPosition")
    public abstract void a(LBAirLinkPIPPosition lBAirLinkPIPPosition, e eVar);

    @f(a = "HDMIOutputFormat")
    public abstract void a(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar);

    @f(a = "VideoDataChannel")
    public abstract void a(VideoDataChannel videoDataChannel, e eVar);

    @f(a = "ChannelSelectionMode")
    public abstract void a(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode, e eVar);

    @f(a = "DataRate")
    public abstract void a(LBAirLinkDataRate lBAirLinkDataRate, e eVar);

    @f(a = "TransmissionMode")
    public abstract void a(LBAirLinkFPVVideoQualityLatency lBAirLinkFPVVideoQualityLatency, e eVar);

    @f(a = "PIPDisplay")
    public abstract void a(LBAirLinkPIPDisplay lBAirLinkPIPDisplay, e eVar);

    @f(a = "SecondaryVideoOutputPort")
    public abstract void a(LBAirLinkSecondaryVideoOutputPort lBAirLinkSecondaryVideoOutputPort, e eVar);

    @f(a = "OSDUnits")
    public abstract void a(LBAirLinkUnit lBAirLinkUnit, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "ChannelSelectionMode")
    public abstract void a(e eVar);

    @f(a = "SecondaryVideoOutputEnabled")
    public abstract void a(boolean z, e eVar);

    @f(a = "FPVVideoBandwidthPercent")
    public abstract void b(int i, e eVar);

    @f(a = "SDIOutputFormat")
    public abstract void b(LBAirLinkSecondaryVideoFormat lBAirLinkSecondaryVideoFormat, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "Channel")
    public abstract void b(e eVar);

    @f(a = "DisplayOSDEnabled")
    public abstract void b(boolean z, e eVar);

    public abstract boolean b();

    @f(a = "OSDTopMargin")
    public abstract void c(int i, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "DataRate")
    public abstract void c(e eVar);

    @f(a = "OSDLeftMargin")
    public abstract void d(int i, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "TransmissionMode")
    public abstract void d(e eVar);

    @f(a = "OSDBottomMargin")
    public abstract void e(int i, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "SecondaryVideoOutputEnabled")
    public abstract void e(e eVar);

    @f(a = "OSDRightMargin")
    public abstract void f(int i, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "SecondaryVideoOutputPort")
    public abstract void f(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "PIPDisplay")
    public abstract void g(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "FPVVideoBandwidthPercent")
    public abstract void h(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "DisplayOSDEnabled")
    public abstract void i(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "OSDTopMargin")
    public abstract void j(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "OSDLeftMargin")
    public abstract void k(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "OSDBottomMargin")
    public abstract void l(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "OSDRightMargin")
    public abstract void m(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "OSDUnits")
    public abstract void n(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "HDMIOutputFormat")
    public abstract void o(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "SDIOutputFormat")
    public abstract void p(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "PIPPosition")
    public abstract void q(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "VideoDataChannel")
    public abstract void r(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "EncodeMode")
    public abstract void s(e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "DualEncodeModePercent")
    public abstract void t(e eVar);

    public void a(String str, int i, String str2, int i2, dji.sdksharedlib.d.c cVar, b.f fVar) {
        super.a(str, i, str2, i2, cVar, fVar);
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        this.c = new a();
        this.c.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(dji.sdksharedlib.b.a.c.h).b(Integer.valueOf(0));
    }

    public void e() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.e();
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
    }

    protected void a() {
        a(dji.sdksharedlib.b.a.c.class, getClass());
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        a aVar = new a();
        aVar.b(dji.sdksharedlib.b.a.a.a).a(Integer.valueOf(0)).c(dji.sdksharedlib.b.a.c.h).b(Integer.valueOf(0));
        a(Integer.valueOf(dataOsdGetPushSignalQuality.getDownSignalQuality()), aVar.d(dji.sdksharedlib.b.a.a.c).a());
        if (!dataOsdGetPushSignalQuality.isGetRcQuality()) {
            if (dataOsdGetPushSignalQuality.getRecData().length > 1) {
                a(new DJISignalInformation(AirLinkUtils.transformRadioSignal(dataOsdGetPushSignalQuality.getDownSignalQuality()), dataOsdGetPushSignalQuality.getAerial1DownSignalQuality()), aVar.d(dji.sdksharedlib.b.a.c.F).a());
            } else {
                a(new DJISignalInformation(AirLinkUtils.transformRadioSignal(dataOsdGetPushSignalQuality.getDownSignalQuality()), 0), aVar.d(dji.sdksharedlib.b.a.c.F).a());
            }
            if (dataOsdGetPushSignalQuality.getRecData().length > 2) {
                a(new DJISignalInformation(AirLinkUtils.transformRadioSignal(dataOsdGetPushSignalQuality.getDownSignalQuality()), dataOsdGetPushSignalQuality.getAerial2DownSignalQuality()), aVar.d(dji.sdksharedlib.b.a.c.G).a());
            }
        } else if (i.getInstance().c().equals(ProductType.Grape2)) {
            a(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), dataOsdGetPushSignalQuality.getAerial1UpSignalQuality()), aVar.d(dji.sdksharedlib.b.a.c.D).a());
            a(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), dataOsdGetPushSignalQuality.getAerial1UpSignalQuality()), aVar.d(dji.sdksharedlib.b.a.c.E).a());
        } else {
            a(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), 0), aVar.d(dji.sdksharedlib.b.a.c.D).a());
            a(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), 0), aVar.d(dji.sdksharedlib.b.a.c.E).a());
        }
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        a(Integer.valueOf(dataRcGetPushParams.getBandWidth() / 10), c(dji.sdksharedlib.b.a.c.k));
    }

    public void onEventBackgroundThread(DataOsdGetPushSweepFrequency dataOsdGetPushSweepFrequency) {
        Object obj = new int[8];
        int[] signalList = dataOsdGetPushSweepFrequency.getSignalList();
        for (int i = 0; i < 8; i++) {
            obj[i] = signalList[i + 13];
        }
        a(obj, c(dji.sdksharedlib.b.a.c.N));
    }

    public boolean c() {
        return false;
    }

    protected void a(CmdId cmdId, int i, e eVar) {
        DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
        dataDm368SetGParams.a(cmdId, i);
        dataDm368SetGParams.start(new 1(this, eVar));
    }
}

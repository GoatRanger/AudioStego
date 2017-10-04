package dji.sdksharedlib.hardware.abstractions.a.b;

import dji.common.airlink.DJIWiFiSignalQuality;
import dji.common.airlink.WiFiFrequencyBand;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.d;
import dji.sdksharedlib.hardware.abstractions.f;

public abstract class a extends d {
    private static final String a = "DJISDKCacheWifiLinkSeriesAbstraction";

    @f(a = "WiFiFrequencyBand")
    public abstract void a(WiFiFrequencyBand wiFiFrequencyBand, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "WiFiFrequencyBand")
    public abstract void a(e eVar);

    @f(a = "WiFiSSID")
    public abstract void a(String str, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "WiFiSSID")
    public abstract void b(e eVar);

    @f(a = "WiFiPassword")
    public abstract void b(String str, e eVar);

    @dji.sdksharedlib.hardware.abstractions.e(a = "IsWifiFrequencyEditable")
    public abstract boolean b();

    @dji.sdksharedlib.hardware.abstractions.e(a = "WiFiPassword")
    public abstract void c(e eVar);

    @dji.sdksharedlib.hardware.abstractions.a(a = "RebootWifi")
    public abstract void d(e eVar);

    public void a(String str, int i, String str2, int i2, c cVar, b.f fVar) {
        super.a(str, i, str2, i2, cVar, fVar);
        dji.thirdparty.a.c.a().a((Object) this);
    }

    public void e() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.e();
    }

    protected void a() {
        a(dji.sdksharedlib.b.a.e.class, getClass());
    }

    public void onEventBackgroundThread(DataWifiGetPushSignal dataWifiGetPushSignal) {
        if (dataWifiGetPushSignal != null) {
            a(Integer.valueOf(dataWifiGetPushSignal.getSignal()), c(dji.sdksharedlib.b.a.a.c));
        }
    }

    public void onEventBackgroundThread(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
        if (dataWifiGetPushElecSignal != null) {
            a(DJIWiFiSignalQuality.find(dataWifiGetPushElecSignal.getSignalStatus().value()), c(dji.sdksharedlib.b.a.e.j));
        }
    }
}

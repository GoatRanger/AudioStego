package dji.sdksharedlib.hardware.abstractions.a;

import dji.common.airlink.DJISignalInformation;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.sdksharedlib.b.a.a;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.d;

public class b extends d {
    private static final String a = "DJISDKCacheAuxLinkAbstraction";

    public void a(String str, int i, String str2, int i2, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, str2, i2, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        dji.thirdparty.a.c.a().a(this);
    }

    public void e() {
        dji.thirdparty.a.c.a().d(this);
        super.e();
    }

    protected void a() {
        a(a.class, getClass());
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        if (dataOsdGetPushSignalQuality.isGetRcQuality()) {
            a(Integer.valueOf(new DJISignalInformation(dataOsdGetPushSignalQuality.getUpSignalQuality(), 0).getPercent()), c(a.b));
        }
    }
}

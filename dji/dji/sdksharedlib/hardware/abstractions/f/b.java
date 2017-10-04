package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.handheld.DJIHandheldPowerMode;
import dji.log.DJILog;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.sdksharedlib.b.g;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.e;

public class b extends dji.sdksharedlib.hardware.abstractions.b {
    private static final String a = "DJISDKCacheHandheldControllerAbstraction";

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        dji.thirdparty.a.c.a().a((Object) this);
    }

    public void e() {
        dji.thirdparty.a.c.a().d((Object) this);
        super.e();
    }

    protected void a() {
        a(g.class, getClass());
    }

    public void onEventBackgroundThread(DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus) {
        if (dataOsdGetPushPowerStatus != null) {
            a((Object) DJIHandheldPowerMode.find(dataOsdGetPushPowerStatus.getPowerStatus()), c(g.d));
            DJILog.d(a, "DJISDKCacheHandheldControllerAbstraction onEventBackgroundThread  Powermode " + DJIHandheldPowerMode.find(dataOsdGetPushPowerStatus.getPowerStatus()));
        }
    }

    @e(a = "FirmwareVersion")
    public void b(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        String str = ".";
        DataCommonGetVersion dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.DM368_G);
        dataCommonGetVersion.start(new 1(this, dataCommonGetVersion, eVar));
    }
}

package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.gimbal.DJIGimbalLoadingBalanceStatus;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;

public class e extends d {
    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        r();
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    protected void r() {
        onEventBackgroundThread(DataGimbalGetPushAbnormalStatus.getInstance());
    }

    protected void a() {
        super.a();
    }

    public void onEventBackgroundThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        b(Boolean.valueOf(!dataGimbalGetPushAbnormalStatus.isPhoneOutGimbal()), f.ao);
        b(DJIGimbalLoadingBalanceStatus.values()[dataGimbalGetPushAbnormalStatus.getGimbalGravity()], f.ap);
        b(Boolean.valueOf(dataGimbalGetPushAbnormalStatus.isMotorProtected()), f.aq);
    }
}

package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.log.DJILog;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$26 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$26(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        DJILog.d("DJISDKCacheRemoteControllerAbstraction", "set workmode 2", true, true);
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        DJILog.d("DJISDKCacheRemoteControllerAbstraction", "set workmode 3", true, true);
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

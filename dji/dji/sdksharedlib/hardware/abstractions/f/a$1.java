package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.error.DJISDKCacheError;
import dji.common.handheld.DJIHandheldPowerMode;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DJIHandheldPowerMode b;
    final /* synthetic */ a c;

    a$1(a aVar, e eVar, DJIHandheldPowerMode dJIHandheldPowerMode) {
        this.c = aVar;
        this.a = eVar;
        this.b = dJIHandheldPowerMode;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(this.b);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJISDKCacheError.DISCONNECTED);
        }
    }
}

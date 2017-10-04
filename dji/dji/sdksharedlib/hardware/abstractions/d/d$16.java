package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$16 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$16(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, Float.valueOf(dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_height_0").value.floatValue()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
    }
}

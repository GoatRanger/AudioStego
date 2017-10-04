package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$4 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$4(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
    }
}

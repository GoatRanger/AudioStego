package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.e.d;
import dji.sdksharedlib.e.a;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$7 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$7(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        a.getInstance().b(this.a);
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        if (this.a != null) {
            CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
        }
    }
}

package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$1(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(obj);
    }

    public void onFailure(a aVar) {
        this.a.a(DJIGimbalError.getDJIError(aVar));
    }
}

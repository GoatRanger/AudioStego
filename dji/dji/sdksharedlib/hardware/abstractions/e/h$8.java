package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class h$8 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ h b;

    h$8(h hVar, e eVar) {
        this.b = hVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(obj);
        h.b(this.b);
    }

    public void onFailure(a aVar) {
        this.a.a(DJIGimbalError.getDJIError(aVar));
    }
}

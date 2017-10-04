package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.error.DJICameraError;
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
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        this.a.a(DJICameraError.getDJIError(aVar));
    }
}

package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class j$5 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ j b;

    j$5(j jVar, e eVar) {
        this.b = jVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(null);
    }

    public void onFailure(a aVar) {
        this.a.a(DJICameraError.getDJIError(aVar));
    }
}

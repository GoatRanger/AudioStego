package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$10 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$10(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(obj);
    }

    public void onFailure(a aVar) {
        this.a.a(DJIError.getDJIError(aVar));
    }
}

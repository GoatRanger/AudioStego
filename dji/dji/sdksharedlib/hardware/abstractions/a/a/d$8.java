package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.abstractions.b;

class d$8 implements e {
    final /* synthetic */ b.e a;
    final /* synthetic */ d b;

    d$8(d dVar, b.e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void a(Object obj) {
        CallbackUtils.onSuccess(this.a, obj);
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}

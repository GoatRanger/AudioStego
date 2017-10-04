package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.abstractions.b;

class b$4 implements e {
    final /* synthetic */ b.e a;
    final /* synthetic */ b b;

    b$4(b bVar, b.e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void a(Object obj) {
        CallbackUtils.onSuccess(this.a, obj);
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}

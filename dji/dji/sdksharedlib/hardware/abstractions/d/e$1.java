package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.hardware.abstractions.b.e;

class e$1 implements e {
    final /* synthetic */ e a;
    final /* synthetic */ e b;

    e$1(e eVar, e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void a(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}

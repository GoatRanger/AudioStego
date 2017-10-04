package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.abstractions.b;

class e$2 implements e {
    final /* synthetic */ b.e a;
    final /* synthetic */ e b;

    e$2(e eVar, b.e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void a(Object obj) {
        CallbackUtils.onSuccess(this.a, obj);
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}

package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class e$3 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ e b;

    e$3(e eVar, e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

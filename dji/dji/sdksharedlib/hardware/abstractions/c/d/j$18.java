package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class j$18 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ j b;

    j$18(j jVar, e eVar) {
        this.b = jVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

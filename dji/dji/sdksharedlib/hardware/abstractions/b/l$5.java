package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class l$5 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ l b;

    l$5(l lVar, e eVar) {
        this.b = lVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

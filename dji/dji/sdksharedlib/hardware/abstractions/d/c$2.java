package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class c$2 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ c b;

    c$2(c cVar, e eVar) {
        this.b = cVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

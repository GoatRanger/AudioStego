package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class l$6 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ l b;

    l$6(l lVar, e eVar) {
        this.b = lVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        l.a(this.b, dji.midware.data.manager.P3.d.read("g_config.voltage.level_1_protect_0").value.intValue());
        CallbackUtils.onSuccess(this.a, Integer.valueOf(l.a(this.b)));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

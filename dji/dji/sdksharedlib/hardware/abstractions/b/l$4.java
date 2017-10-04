package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class l$4 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ l b;

    l$4(l lVar, e eVar) {
        this.b = lVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, Integer.valueOf(dji.midware.data.manager.P3.d.read("g_config.voltage.battery_cell_0").value.intValue()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

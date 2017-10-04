package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIFlightControllerControlMode;
import dji.common.util.CallbackUtils;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class c$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ c b;

    c$1(c cVar, e eVar) {
        this.b = cVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, DJIFlightControllerControlMode.find(dji.midware.data.manager.P3.d.read(a.b).value.intValue()));
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

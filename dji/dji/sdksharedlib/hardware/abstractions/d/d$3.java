package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIFlightFailsafeOperation;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetFsAction;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$3 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$3(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, DJIFlightFailsafeOperation.find(DataFlycGetFsAction.getInstance().getFsAction().value()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}

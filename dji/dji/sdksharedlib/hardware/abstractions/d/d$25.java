package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$25 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$25(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, new Integer(DataFlycGetVoltageWarnning.getInstance().getValue()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
    }
}

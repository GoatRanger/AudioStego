package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$22 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$22(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, Boolean.valueOf(dji.midware.data.manager.P3.d.read("g_config.airport_limit_cfg.cfg_sim_disable_limit_0").value.intValue() == 0));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
    }
}

package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$20 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$20(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        boolean z = true;
        ParamInfo read = dji.midware.data.manager.P3.d.read("g_config.advanced_function.radius_limit_enabled_0");
        e eVar = this.a;
        if (read.value.intValue() != 1) {
            z = false;
        }
        CallbackUtils.onSuccess(eVar, Boolean.valueOf(z));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.getDJIError(aVar));
    }
}

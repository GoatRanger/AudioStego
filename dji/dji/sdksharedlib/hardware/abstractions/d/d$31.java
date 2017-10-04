package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$31 implements d {
    final /* synthetic */ ParamInfo a;
    final /* synthetic */ float b;
    final /* synthetic */ e c;
    final /* synthetic */ d d;

    d$31(d dVar, ParamInfo paramInfo, float f, e eVar) {
        this.d = dVar;
        this.a = paramInfo;
        this.b = f;
        this.c = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a.isCorrect(Float.valueOf(this.b))) {
            new DataFlycSetParams().a(this.a.name, Float.valueOf(this.b)).start(new d(this) {
                final /* synthetic */ d$31 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    CallbackUtils.onSuccess(this.a.c, null);
                }

                public void onFailure(a aVar) {
                    CallbackUtils.onFailure(this.a.c, DJIFlightControllerError.getDJIError(aVar));
                }
            });
        } else {
            CallbackUtils.onFailure(this.c, DJIFlightControllerError.FLIGHT_CONTROLLER_INVALID_PARAMETER);
        }
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.c, DJIFlightControllerError.getDJIError(aVar));
    }
}

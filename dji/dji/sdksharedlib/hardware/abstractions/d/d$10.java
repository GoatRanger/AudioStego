package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.error.DJIFlightControllerError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycStopIoc;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$10 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$10(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycStopIoc.getInstance().getResult();
        if (result == 0) {
            this.b.v(new e(this) {
                final /* synthetic */ d$10 a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    CallbackUtils.onSuccess(this.a.a, null);
                }

                public void a(DJIError dJIError) {
                    CallbackUtils.onFailure(this.a.a, dJIError);
                }
            });
        } else if (this.a != null) {
            CallbackUtils.onFailure(this.a, d.a(result));
        }
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, DJIFlightControllerError.MISSION_RESULT_FAILED);
    }
}

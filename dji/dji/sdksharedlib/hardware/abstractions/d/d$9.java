package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.error.DJIFlightControllerError;
import dji.common.flightcontroller.DJIFlightOrientationMode;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$9 implements e {
    final /* synthetic */ DJIFlightOrientationMode a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$9(d dVar, DJIFlightOrientationMode dJIFlightOrientationMode, e eVar) {
        this.c = dVar;
        this.a = dJIFlightOrientationMode;
        this.b = eVar;
    }

    public void a(Object obj) {
        DataFlycStartIoc.getInstance().setMode(IOCType.find(this.a.value())).start(new d(this) {
            final /* synthetic */ d$9 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int result = DataFlycStartIoc.getInstance().getResult();
                if (result == 0) {
                    if (this.a.b != null) {
                        CallbackUtils.onSuccess(this.a.b, null);
                    }
                } else if (this.a.b != null) {
                    CallbackUtils.onFailure(this.a.b, d.a(result));
                }
            }

            public void onFailure(a aVar) {
                CallbackUtils.onFailure(this.a.b, DJIFlightControllerError.MISSION_RESULT_FAILED);
            }
        });
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.b, dJIError);
    }
}

package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetWheelGain;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$32 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$32(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Short.valueOf((short) DataRcGetWheelGain.getInstance().getGain()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

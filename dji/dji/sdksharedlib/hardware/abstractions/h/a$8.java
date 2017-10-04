package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCGimbalControlSpeed;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetGimbalSpeed;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$8 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataRcGetGimbalSpeed b;
    final /* synthetic */ a c;

    a$8(a aVar, e eVar, DataRcGetGimbalSpeed dataRcGetGimbalSpeed) {
        this.c = aVar;
        this.a = eVar;
        this.b = dataRcGetGimbalSpeed;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(new DJIRCGimbalControlSpeed((short) this.b.getPitch(), (short) this.b.getRoll(), (short) this.b.getYaw()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

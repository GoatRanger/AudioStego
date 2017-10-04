package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCGimbalControlDirection;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$30 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataRcGetGimbalControlMode b;
    final /* synthetic */ a c;

    a$30(a aVar, e eVar, DataRcGetGimbalControlMode dataRcGetGimbalControlMode) {
        this.c = aVar;
        this.a = eVar;
        this.b = dataRcGetGimbalControlMode;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(DJIRCGimbalControlDirection.find(this.b.getMode().a()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

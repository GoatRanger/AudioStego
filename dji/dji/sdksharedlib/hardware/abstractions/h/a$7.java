package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCRequestGimbalControlResult;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$7 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$7(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(DJIRCRequestGimbalControlResult.find(0));
        }
    }

    public void onFailure(a aVar) {
        if (aVar == a.c) {
            if (this.a != null && this.a != null) {
                this.a.a(DJIRCRequestGimbalControlResult.find(1));
            }
        } else if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

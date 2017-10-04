package dji.sdksharedlib.hardware.abstractions.h;

import android.util.Log;
import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCJoinMasterResult;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$20 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$20(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        Log.e("DJISDKCacheRemoteControllerAbstraction", "pwd to int success ");
        if (this.a != null) {
            this.a.a(DJIRCJoinMasterResult.Successful);
        }
    }

    public void onFailure(a aVar) {
        Log.e("DJISDKCacheRemoteControllerAbstraction", "pwd to int error " + aVar.toString());
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

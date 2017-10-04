package dji.sdksharedlib.hardware.abstractions.h;

import android.util.Log;
import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.DJIRCToAircraftPairingState;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcSetFrequency;
import dji.midware.e.d;
import dji.midware.util.c;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$25 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$25(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        Log.i("DJISDKCacheRemoteControllerAbstraction", c.i(DataRcSetFrequency.getInstance().getRecData()));
        if (this.a != null) {
            this.a.a(DJIRCToAircraftPairingState.find(DataRcSetFrequency.getInstance().getRecData()[0]));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}

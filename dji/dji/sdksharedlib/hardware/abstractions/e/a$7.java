package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import dji.common.error.DJIError;
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
        Log.i("DJIGimbalAbstraction", "fineTuneGimbalRollInDegrees onSuccess time:" + System.currentTimeMillis());
        if (this.a != null) {
            this.a.a(obj);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIError.getDJIError(aVar));
        }
    }
}

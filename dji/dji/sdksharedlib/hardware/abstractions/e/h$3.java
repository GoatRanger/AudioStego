package dji.sdksharedlib.hardware.abstractions.e;

import android.util.Log;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class h$3 implements d {
    final /* synthetic */ h a;

    h$3(h hVar) {
        this.a = hVar;
    }

    public void onSuccess(Object obj) {
        Log.e("MotorParam", "onSuccess");
        h.a(this.a).countDownLatch();
    }

    public void onFailure(a aVar) {
        Log.e("MotorParam", "onFailure");
        this.a.a = false;
        h.a(this.a).countDownLatch();
    }
}

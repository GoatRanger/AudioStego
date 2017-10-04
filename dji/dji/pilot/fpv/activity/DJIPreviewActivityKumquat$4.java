package dji.pilot.fpv.activity;

import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import java.util.TimerTask;

class DJIPreviewActivityKumquat$4 extends TimerTask {
    final /* synthetic */ DJIPreviewActivityKumquat a;

    DJIPreviewActivityKumquat$4(DJIPreviewActivityKumquat dJIPreviewActivityKumquat) {
        this.a = dJIPreviewActivityKumquat;
    }

    public void run() {
        float f = 2.0f;
        float b = this.a.A.b();
        if (b < 2.0f) {
            f = 0.1f + b;
        }
        DJISDKCache.getInstance().setValue(b.b(dji.sdksharedlib.b.b.Q), Float.valueOf(f), null);
    }
}

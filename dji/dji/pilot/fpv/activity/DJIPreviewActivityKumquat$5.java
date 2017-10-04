package dji.pilot.fpv.activity;

import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import java.util.TimerTask;

class DJIPreviewActivityKumquat$5 extends TimerTask {
    final /* synthetic */ DJIPreviewActivityKumquat a;

    DJIPreviewActivityKumquat$5(DJIPreviewActivityKumquat dJIPreviewActivityKumquat) {
        this.a = dJIPreviewActivityKumquat;
    }

    public void run() {
        float f = 1.0f;
        float b = this.a.A.b();
        if (b > 1.0f) {
            f = b - 0.1f;
        }
        DJISDKCache.getInstance().setValue(b.b(dji.sdksharedlib.b.b.Q), Float.valueOf((float) (Math.ceil((double) (f * 10.0f)) / 10.0d)), null);
    }
}

package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.handheld.DJIHandheldButtonStatus;
import dji.sdksharedlib.b.g;
import java.util.TimerTask;

class c$1 extends TimerTask {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void run() {
        c.a(this.a, DJIHandheldButtonStatus.Idle, g.e);
    }
}

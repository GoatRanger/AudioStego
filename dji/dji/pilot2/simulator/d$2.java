package dji.pilot2.simulator;

import dji.sdk.api.simulator.DJISimulatorFlycStatus;
import dji.sdk.api.simulator.DJISimulatorGetPushFlycStatusCallBack;

class d$2 implements DJISimulatorGetPushFlycStatusCallBack {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void onResult(final DJISimulatorFlycStatus dJISimulatorFlycStatus) {
        ((DJISimulatorActivity) d.a(this.a)).runOnUiThread(new Runnable(this) {
            final /* synthetic */ d$2 b;

            public void run() {
                e.a(e.a, "SetRotation", "" + (-dJISimulatorFlycStatus.get(1)) + ":" + dJISimulatorFlycStatus.get(2) + ":" + (-dJISimulatorFlycStatus.get(0)));
                e.a(e.a, "SetPosition", "" + (dJISimulatorFlycStatus.get(3) * 3.0f) + ":" + (dJISimulatorFlycStatus.get(4) * 3.0f) + ":" + ((-dJISimulatorFlycStatus.get(5)) * 3.0f));
            }
        });
    }
}

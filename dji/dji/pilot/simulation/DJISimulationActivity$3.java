package dji.pilot.simulation;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJISimulationActivity$3 implements d {
    final /* synthetic */ DJISimulationActivity a;

    DJISimulationActivity$3(DJISimulationActivity dJISimulationActivity) {
        this.a = dJISimulationActivity;
    }

    public void onSuccess(Object obj) {
        DJISimulationActivity.k(this.a).obtainMessage(2, 0, 0, null).sendToTarget();
    }

    public void onFailure(a aVar) {
        DJISimulationActivity$a k = DJISimulationActivity.k(this.a);
        if (aVar == null) {
            aVar = a.D;
        }
        k.obtainMessage(2, 1, 0, aVar).sendToTarget();
    }
}

package dji.pilot.simulation;

import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataRcGetSimFlyStatus.FlySimStatus;

class DJISimulationActivity$5 implements OnClickListener {
    final /* synthetic */ DJISimulationActivity a;

    DJISimulationActivity$5(DJISimulationActivity dJISimulationActivity) {
        this.a = dJISimulationActivity;
    }

    public void onClick(View view) {
        if (DJISimulationActivity.c(this.a) == FlySimStatus.NORMAL) {
            DJISimulationActivity.a(this.a, true);
        } else {
            DJISimulationActivity.a(this.a, false);
        }
    }
}

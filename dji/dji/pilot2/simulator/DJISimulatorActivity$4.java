package dji.pilot2.simulator;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class DJISimulatorActivity$4 implements OnDismissListener {
    final /* synthetic */ DJISimulatorActivity a;

    DJISimulatorActivity$4(DJISimulatorActivity dJISimulatorActivity) {
        this.a = dJISimulatorActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        DJISimulatorActivity.j(this.a).a(DJISimulatorActivity.i(this.a).b(), DJISimulatorActivity.i(this.a).c());
    }
}

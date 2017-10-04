package dji.pilot2.simulator;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import dji.pilot.R;

class DJISimulatorActivity$5 implements OnDismissListener {
    final /* synthetic */ DJISimulatorActivity a;

    DJISimulatorActivity$5(DJISimulatorActivity dJISimulatorActivity) {
        this.a = dJISimulatorActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        DJISimulatorActivity.f(this.a).setImageDrawable(this.a.getResources().getDrawable(R.drawable.v2_smlt_help_normal));
    }
}

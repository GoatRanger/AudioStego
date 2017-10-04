package dji.pilot2.simulator;

import dji.pilot.R;

class DJISimulatorActivity$1 implements d$a {
    final /* synthetic */ DJISimulatorActivity a;

    DJISimulatorActivity$1(DJISimulatorActivity dJISimulatorActivity) {
        this.a = dJISimulatorActivity;
    }

    public void a(float f, int i) {
        DJISimulatorActivity.a(this.a, f);
        DJISimulatorActivity.a(this.a, i);
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJISimulatorActivity$1 a;

            {
                this.a = r1;
            }

            public void run() {
                DJISimulatorActivity.b(this.a.a).setText(String.format(this.a.a.getString(R.string.v2_smlt_windspeed_txt), new Object[]{Float.valueOf(DJISimulatorActivity.a(this.a.a))}));
                DJISimulatorActivity.d(this.a.a).setRotation((float) DJISimulatorActivity.c(this.a.a));
            }
        });
    }
}

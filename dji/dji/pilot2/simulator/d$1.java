package dji.pilot2.simulator;

import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.sdk.api.simulator.DJIExecuteIntResultCallback;
import dji.thirdparty.a.c;

class d$1 implements DJIExecuteIntResultCallback {
    final /* synthetic */ d a;

    d$1(d dVar) {
        this.a = dVar;
    }

    public void onResult(int i) {
        b bVar = new b();
        bVar.a = d.a;
        bVar.b = R.string.v2_smlt_ready_succeed_tip;
        c.a().e(bVar);
        switch (i) {
        }
    }
}

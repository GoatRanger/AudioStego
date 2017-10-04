package dji.pilot.fpv.activity;

import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataSingleCommonCtrl;
import dji.midware.data.model.P3.DataSingleCommonCtrl.CtrlState;
import dji.pilot.visual.a.c;

class DJIPreviewActivityBaseForMC$1 implements Runnable {
    final /* synthetic */ DJIPreviewActivityBaseForMC a;

    DJIPreviewActivityBaseForMC$1(DJIPreviewActivityBaseForMC dJIPreviewActivityBaseForMC) {
        this.a = dJIPreviewActivityBaseForMC;
    }

    public void run() {
        if (!DJIPreviewActivityBaseForMC.a(this.a)) {
            FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
            if (FLYC_STATE.NaviSubMode_Tracking == flycState || FLYC_STATE.TRACK_HEADLOCK == flycState || FLYC_STATE.NaviSubMode_Pointing == flycState || c.getInstance().d() || c.getInstance().c() || DataEyeGetPushException.getInstance().isInPointing()) {
                new DataSingleCommonCtrl().a(CtrlState.b).start(null);
                DJIPreviewActivityBaseForMC.b(this.a).postDelayed(this, 2000);
            }
        }
    }
}

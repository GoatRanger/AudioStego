package dji.pilot.fpv.activity;

import dji.midware.data.manager.P3.ServiceManager;
import dji.pilot.fpv.rightbar.DJISwitchModeView;

class DJIBaseNewPreviewActivity$20 implements Runnable {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$20(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void run() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            DJIBaseNewPreviewActivity.a(this.a, DJISwitchModeView.a);
        }
    }
}

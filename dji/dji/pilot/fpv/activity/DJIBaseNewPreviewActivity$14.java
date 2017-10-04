package dji.pilot.fpv.activity;

import dji.pilot.fpv.rightbar.DJISwitchModeView;

class DJIBaseNewPreviewActivity$14 implements Runnable {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$14(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void run() {
        if (!DJIBaseNewPreviewActivity.w(this.a)) {
            DJIBaseNewPreviewActivity.j(this.a).setBackgroundColor(this.a.getResources().getColor(17170445));
            DJIBaseNewPreviewActivity.a(this.a, true);
        }
        if (this.a.d.isShown()) {
            this.a.d.animate().setDuration(300).alpha(0.0f).withEndAction(new Runnable(this) {
                final /* synthetic */ DJIBaseNewPreviewActivity$14 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.d.hide();
                }
            }).start();
        }
        DJIBaseNewPreviewActivity.g(this.a);
        DJIBaseNewPreviewActivity.x(this.a);
        DJIBaseNewPreviewActivity.a(this.a, DJISwitchModeView.a);
    }
}

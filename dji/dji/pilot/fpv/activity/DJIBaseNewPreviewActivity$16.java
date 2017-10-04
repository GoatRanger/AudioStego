package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import dji.pilot.fpv.leftmenu.b.a;

class DJIBaseNewPreviewActivity$16 implements a {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$16(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a(DialogInterface dialogInterface, int i) {
        DJIBaseNewPreviewActivity.y(this.a).dismiss();
    }

    public void b(DialogInterface dialogInterface, int i) {
        DJIBaseNewPreviewActivity.y(this.a).dismiss();
        DJIBaseNewPreviewActivity.z(this.a);
    }

    public void a(DialogInterface dialogInterface, boolean z, int i) {
    }
}

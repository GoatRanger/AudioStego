package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import dji.pilot.fpv.leftmenu.b.a;

class DJIPreviewActivity$20 implements a {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$20(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void a(DialogInterface dialogInterface, int i) {
        DJIPreviewActivity.S(this.a).dismiss();
    }

    public void b(DialogInterface dialogInterface, int i) {
        DJIPreviewActivity.S(this.a).dismiss();
        DJIPreviewActivity.T(this.a);
    }

    public void a(DialogInterface dialogInterface, boolean z, int i) {
    }
}

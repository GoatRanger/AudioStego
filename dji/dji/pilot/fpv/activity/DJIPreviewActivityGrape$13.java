package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import dji.pilot.fpv.leftmenu.b.a;

class DJIPreviewActivityGrape$13 implements a {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$13(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void a(DialogInterface dialogInterface, int i) {
        DJIPreviewActivityGrape.v(this.a).dismiss();
    }

    public void b(DialogInterface dialogInterface, int i) {
        DJIPreviewActivityGrape.v(this.a).dismiss();
        DJIPreviewActivityGrape.w(this.a);
    }

    public void a(DialogInterface dialogInterface, boolean z, int i) {
    }
}

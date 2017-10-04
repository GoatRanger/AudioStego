package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a.e;
import dji.thirdparty.a.c;

class DJIPreviewActivityGrape$32 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$32(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.a().e(e.b);
    }
}

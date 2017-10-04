package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a.e;
import dji.thirdparty.a.c;

class DJIPreviewActivityLitchi$4 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$4(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.a().e(e.b);
    }
}

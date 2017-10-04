package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a.e;
import dji.thirdparty.a.c;

class DJIPreviewActivity$3 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$3(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.a().e(e.b);
    }
}

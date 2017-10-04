package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a.e;
import dji.thirdparty.a.c;

class DJIBaseNewPreviewActivity$2 implements OnClickListener {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$2(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.a().e(e.b);
    }
}

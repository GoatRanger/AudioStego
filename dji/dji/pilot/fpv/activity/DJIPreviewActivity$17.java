package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.b;
import dji.thirdparty.a.c;

class DJIPreviewActivity$17 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$17(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        b.getInstance().stopStream();
        c.a().e(new a(16));
        this.a.finishThis();
    }
}

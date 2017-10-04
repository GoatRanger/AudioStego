package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.f.a.a;
import dji.pilot.liveshare.b;
import dji.thirdparty.a.c;

class DJIPreviewActivityLitchi$17 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$17(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        b.getInstance().stopStream();
        c.a().e(new a(16));
        this.a.finishThis();
    }
}

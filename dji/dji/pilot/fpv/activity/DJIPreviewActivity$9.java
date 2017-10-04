package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class DJIPreviewActivity$9 implements OnDismissListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$9(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        DJIPreviewActivity.d(this.a, 8192);
    }
}

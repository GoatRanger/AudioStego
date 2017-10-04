package dji.pilot.fpv.activity;

import android.view.View;
import android.view.View.OnClickListener;

class DJIPreviewActivity$38 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$38(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onClick(View view) {
        DJIPreviewActivity.k(this.a, true);
        if (DJIPreviewActivity.at(this.a) != null && DJIPreviewActivity.at(this.a).isShowing()) {
            DJIPreviewActivity.at(this.a).dismiss();
            DJIPreviewActivity.a(this.a, null);
        }
    }
}

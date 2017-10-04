package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.dji.frame.c.b;

class DJIPreviewActivity$11 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$11(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
        b.a(this.a, 3);
    }
}

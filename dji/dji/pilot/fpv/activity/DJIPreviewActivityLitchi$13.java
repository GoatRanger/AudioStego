package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.dji.frame.c.b;

class DJIPreviewActivityLitchi$13 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$13(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
        b.a(this.a, 3);
    }
}

package dji.pilot.fpv.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.dji.frame.c.b;

class DJIPreviewActivityGrape$8 implements OnClickListener {
    final /* synthetic */ DJIPreviewActivityGrape a;

    DJIPreviewActivityGrape$8(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
        b.a(this.a, 3);
    }
}

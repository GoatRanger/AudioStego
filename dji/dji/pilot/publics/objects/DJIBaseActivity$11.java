package dji.pilot.publics.objects;

import android.content.DialogInterface;
import dji.pilot.fpv.leftmenu.b.a;

class DJIBaseActivity$11 implements a {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$11(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public void a(DialogInterface dialogInterface, int i) {
        g.a(this.a, "fpv_go4_recommended", true);
        dialogInterface.dismiss();
    }

    public void b(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }

    public void a(DialogInterface dialogInterface, boolean z, int i) {
    }
}

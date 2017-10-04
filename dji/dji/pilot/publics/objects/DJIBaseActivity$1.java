package dji.pilot.publics.objects;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a;

class DJIBaseActivity$1 implements OnClickListener {
    final /* synthetic */ long a;
    final /* synthetic */ DJIBaseActivity b;

    DJIBaseActivity$1(DJIBaseActivity dJIBaseActivity, long j) {
        this.b = dJIBaseActivity;
        this.a = j;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        g.a(this.b.getBaseContext(), a.d, this.a + 86400000);
        dialogInterface.dismiss();
    }
}

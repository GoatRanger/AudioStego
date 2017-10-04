package dji.pilot.publics.objects;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DJIBaseActivity$7 implements OnClickListener {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$7(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DJIBaseActivity.access$000(this.a).dismiss();
    }
}

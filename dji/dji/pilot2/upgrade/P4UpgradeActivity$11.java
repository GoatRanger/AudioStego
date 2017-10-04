package dji.pilot2.upgrade;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class P4UpgradeActivity$11 implements OnClickListener {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$11(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.finish();
    }
}

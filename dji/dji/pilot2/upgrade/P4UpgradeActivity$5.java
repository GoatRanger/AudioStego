package dji.pilot2.upgrade;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot2.upgrade.P4UpgradeTipBannerView.b;
import dji.thirdparty.a.c;

class P4UpgradeActivity$5 implements OnClickListener {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$5(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        c.a().e(b.Go);
        this.a.finish();
    }
}

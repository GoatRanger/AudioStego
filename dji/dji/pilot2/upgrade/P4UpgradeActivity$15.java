package dji.pilot2.upgrade;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot2.upgrade.P4UpgradeTipBannerView.a;

class P4UpgradeActivity$15 implements OnClickListener {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$15(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.v.sendMessage(this.a.v.obtainMessage(a.LogCollectStart.ordinal()));
    }
}

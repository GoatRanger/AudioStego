package dji.pilot2.upgrade;

import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot2.upgrade.rollback.b;

class P4UpgradeActivity$1 implements OnClickListener {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$1(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void onClick(View view) {
        Object tag = P4UpgradeActivity.a(this.a).getTag();
        if (tag != null && ((P4UpgradeActivity$a) tag).equals(P4UpgradeActivity$a.FINISH)) {
            P4UpgradeActivity.b(this.a);
        } else if (b.a(this.a)) {
            P4UpgradeActivity.c(this.a).e();
        }
    }
}

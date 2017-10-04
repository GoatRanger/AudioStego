package dji.pilot2.upgrade;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

class P4UpgradeActivity$9 implements OnClickListener {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$9(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(this.a, MoreReleaseNoteActivity.class);
        this.a.startActivity(intent);
    }
}

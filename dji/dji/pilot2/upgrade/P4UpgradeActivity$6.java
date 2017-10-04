package dji.pilot2.upgrade;

import android.text.TextUtils;
import com.dji.frame.c.h;
import dji.thirdparty.afinal.f.a;

class P4UpgradeActivity$6 extends a<String> {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$6(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        P4UpgradeActivity.a("mMoreReleaseNote=" + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                P4UpgradeActivity.a = (MoreReleaseNoteModel) h.b(str, MoreReleaseNoteModel.class);
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ P4UpgradeActivity$6 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (TextUtils.isEmpty(P4UpgradeActivity.a.content)) {
                            P4UpgradeActivity.n(this.a.a).setVisibility(4);
                        } else {
                            P4UpgradeActivity.n(this.a.a).setVisibility(0);
                        }
                    }
                });
                P4UpgradeActivity.a("content=" + P4UpgradeActivity.a.content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(Throwable th, int i, String str) {
        P4UpgradeActivity.a("errorNo=" + i);
    }
}

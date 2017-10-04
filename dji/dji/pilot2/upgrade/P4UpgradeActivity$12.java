package dji.pilot2.upgrade;

import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.midware.data.manager.P3.i;
import dji.pilot.R;
import dji.pilot2.upgrade.P4UpgradeTipBannerView.a;

class P4UpgradeActivity$12 implements Callback {
    final /* synthetic */ P4UpgradeActivity a;

    P4UpgradeActivity$12(P4UpgradeActivity p4UpgradeActivity) {
        this.a = p4UpgradeActivity;
    }

    public boolean handleMessage(Message message) {
        P4UpgradeActivity.a("MsgWhat=[" + a.values()[message.what] + "]," + "msg.arg1=" + message.arg1 + ",DJIUpgradeP4Service.curEvent=" + DJIUpgradeP4Service.a + ",CurNetWorkStatusEvent=" + P4UpgradeTipBannerView.b + "mUpgradeP4Manager.isUpgrading()=" + P4UpgradeActivity.c(this.a).c());
        int i = message.what;
        if (a.DownloadStart.ordinal() == i) {
            a();
        } else if (a.DownloadProgress.ordinal() == i) {
            a(message.arg1);
        } else if (a.DownloadFail.ordinal() == i) {
            a(message.obj == null ? "" : message.obj.toString());
        } else if (a.DownloadComplete.ordinal() == i) {
            b();
        } else if (a.UpgradeStart.ordinal() == i) {
            c();
        } else if (a.UpgradeProgress.ordinal() == i) {
            b(message.arg1);
        } else if (a.UpgradeFail.ordinal() == i) {
            b(message.obj == null ? "" : message.obj.toString());
        } else if (a.UpgradeComplete.ordinal() == i) {
            d();
        } else if (a.CollectComplete.ordinal() == i) {
            e();
        } else if (a.WaitTimeout.ordinal() == i) {
            g();
        } else if (a.LogCollectStart.ordinal() == i) {
            P4UpgradeActivity.e(this.a);
        }
        return false;
    }

    private void a() {
        P4UpgradeActivity.f(this.a).setVisibility(8);
        P4UpgradeActivity.g(this.a).setVisibility(0);
        P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_download_progress_an, null);
        P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_download_progress, 0);
    }

    private void a(int i) {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
            P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_download_progress_an, null);
        }
        P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_download_progress, i);
    }

    private void a(String str) {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
        }
        String str2 = str == null ? "" : str.toString();
        if (TextUtils.isEmpty(str2)) {
            str2 = this.a.getResources().getString(R.string.v2_upgrade_activity_download_fail_reason_offline);
        }
        if (!P4UpgradeActivity.i(this.a).equals(str2)) {
            P4UpgradeActivity.a(this.a, str2);
            P4UpgradeActivity.b(this.a, str2);
            P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_download_continue, P4UpgradeActivity$a.DOWNLOAD_CONTINUE);
        }
    }

    private void b() {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
        }
        P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_download_complete_an, null);
        P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_upgrade_start, P4UpgradeActivity$a.UPGRADE);
    }

    private void c() {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
        }
        P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_upgrade_progress, 0);
        P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_upgrade_progress_an, null);
    }

    private void b(int i) {
        if (P4UpgradeActivity.c(this.a).c()) {
            if (P4UpgradeActivity.f(this.a).isShown()) {
                P4UpgradeActivity.f(this.a).setVisibility(8);
                P4UpgradeActivity.g(this.a).setVisibility(0);
                P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_upgrade_progress_an, null);
            }
            P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_upgrade_progress, i);
        }
    }

    private void b(String str) {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
        }
        String str2 = str == null ? "" : str.toString();
        if (TextUtils.isEmpty(str2)) {
            str2 = this.a.getResources().getString(R.string.v2_upgrade_activity_download_fail_reason_offuav);
        }
        if (!P4UpgradeActivity.i(this.a).equals(str2)) {
            P4UpgradeActivity.a(this.a, str2);
            P4UpgradeActivity.b(this.a, str2);
            P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_upgrade_restart, P4UpgradeActivity$a.UPGRADE);
            P4UpgradeActivity.j(this.a);
        }
    }

    private void d() {
        if (P4UpgradeActivity.f(this.a).isShown()) {
            P4UpgradeActivity.f(this.a).setVisibility(8);
            P4UpgradeActivity.g(this.a).setVisibility(0);
        }
        P4UpgradeActivity.h(this.a).a((int) R.drawable.v2_upgrade_activity_upgrade_complete_an, null);
        P4UpgradeActivity.a(this.a, R.string.v2_upgrade_activity_finish_wait, P4UpgradeActivity$a.FINISH);
    }

    private void e() {
        f();
        if (!DJIUpgradeP4Service.g() || DJIUpgradeP4Service.a == DJIUpgradeP4Service.a.d) {
            P4UpgradeActivity.a(this.a, P4UpgradeActivity.k(this.a));
        }
    }

    private void f() {
        switch (P4UpgradeActivity$7.a[i.getInstance().c().ordinal()]) {
            case 1:
                P4UpgradeActivity.l(this.a).setText(R.string.rcupgrade_upgradep4_tip);
                P4UpgradeActivity.m(this.a).setImageResource(R.drawable.v2_p4_disconnect_icon);
                return;
            case 2:
            case 3:
                P4UpgradeActivity.l(this.a).setText(R.string.rcupgrade_upgradep4_tip);
                P4UpgradeActivity.m(this.a).setImageResource(R.drawable.v2_p4_disconnect_icon);
                return;
            case 4:
            case 5:
                P4UpgradeActivity.l(this.a).setText(R.string.rcupgrade_upgrade220ac_tip);
                P4UpgradeActivity.m(this.a).setBackgroundColor(this.a.getResources().getColor(17170445));
                P4UpgradeActivity.m(this.a).setImageResource(R.drawable.v2_kumquatx_icon_disconnected);
                return;
            default:
                P4UpgradeActivity.l(this.a).setText(R.string.rcupgrade_upgradep4_tip);
                P4UpgradeActivity.m(this.a).setImageResource(R.drawable.v2_p4_disconnect_icon);
                return;
        }
    }

    private void g() {
        P4UpgradeActivity.a(this.a, R.string.rcupgrade_upgradep4_timeout);
    }
}

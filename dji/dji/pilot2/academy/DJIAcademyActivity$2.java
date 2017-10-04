package dji.pilot2.academy;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.mine.activity.ContactDjiActivity;
import dji.pilot2.simulator.DJISimulatorActivity;
import dji.pilot2.simulator.d;

class DJIAcademyActivity$2 implements OnClickListener {
    final /* synthetic */ DJIAcademyActivity a;

    DJIAcademyActivity$2(DJIAcademyActivity dJIAcademyActivity) {
        this.a = dJIAcademyActivity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c42:
                this.a.finish();
                return;
            case R.id.c43:
                this.a.startActivity(new Intent(this.a, DJIAccountSignActivity.class));
                return;
            case R.id.c44:
                this.a.startActivity(new Intent(this.a, ContactDjiActivity.class));
                return;
            case R.id.c5_:
                if (!DJIAcademyActivity.b(this.a)) {
                    DJIAcademyActivity.a(this.a, R.string.v2_smlt_academy_cannot_enter_title, R.string.v2_smlt_academy_cannot_enter_content);
                    return;
                } else if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    DJIAcademyActivity.a(this.a, -1, R.string.v2_smlt_not_enter_motorup_tip);
                    return;
                } else {
                    ServiceManager.getInstance().pauseService(true);
                    d.b(true);
                    DJIAcademyActivity.c(this.a).setEnabled(false);
                    this.a.startActivity(new Intent(this.a, DJISimulatorActivity.class));
                    DJIAcademyActivity.c(this.a).setEnabled(true);
                    return;
                }
            default:
                return;
        }
    }
}

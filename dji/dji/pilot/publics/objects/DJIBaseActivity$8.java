package dji.pilot.publics.objects;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.pilot.R;
import dji.pilot.publics.widget.a;

class DJIBaseActivity$8 implements Runnable {
    final /* synthetic */ DataRcAckGimbalCtrPermission a;
    final /* synthetic */ DJIBaseActivity b;

    DJIBaseActivity$8(DJIBaseActivity dJIBaseActivity, DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission) {
        this.b = dJIBaseActivity;
        this.a = dataRcAckGimbalCtrPermission;
    }

    public void run() {
        if (!this.b.isFinishing()) {
            if (DJIBaseActivity.access$100(this.b) == null) {
                DJIBaseActivity.access$102(this.b, a.a(this.b, this.b.getString(R.string.fpv_rc_control_title), this.b.getString(R.string.fpv_rc_control_name, new Object[]{this.a.getName()}), this.b.getString(R.string.base_refuse), new OnClickListener(this) {
                    final /* synthetic */ DJIBaseActivity$8 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a.setIsAgree(false).start();
                        dialogInterface.dismiss();
                    }
                }, this.b.getString(R.string.base_agree), new OnClickListener(this) {
                    final /* synthetic */ DJIBaseActivity$8 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a.setIsAgree(true).start();
                        dialogInterface.dismiss();
                    }
                }));
                DJIBaseActivity.access$100(this.b).a(true);
                DJIBaseActivity.access$100(this.b).b(false);
            }
            DJIBaseActivity.access$100(this.b).show();
            DJILogHelper.getInstance().LOGD(this.b.TAG, "DataRcAckGimbalCtrPermission dialog.show", false, true);
        }
    }
}

package dji.pilot2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataRcAckGimbalCtrPermission;
import dji.pilot.R;
import dji.pilot.publics.widget.a;

class DJIFragmentActivityNoFullScreen$10 implements Runnable {
    final /* synthetic */ DataRcAckGimbalCtrPermission a;
    final /* synthetic */ DJIFragmentActivityNoFullScreen b;

    DJIFragmentActivityNoFullScreen$10(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen, DataRcAckGimbalCtrPermission dataRcAckGimbalCtrPermission) {
        this.b = dJIFragmentActivityNoFullScreen;
        this.a = dataRcAckGimbalCtrPermission;
    }

    public void run() {
        if (DJIFragmentActivityNoFullScreen.c(this.b) == null) {
            DJIFragmentActivityNoFullScreen.a(this.b, a.a(this.b, this.b.getString(R.string.fpv_rc_control_title), this.b.getString(R.string.fpv_rc_control_name, new Object[]{this.a.getName()}), this.b.getString(R.string.base_refuse), new OnClickListener(this) {
                final /* synthetic */ DJIFragmentActivityNoFullScreen$10 a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a.setIsAgree(false).start();
                    dialogInterface.dismiss();
                }
            }, this.b.getString(R.string.base_agree), new OnClickListener(this) {
                final /* synthetic */ DJIFragmentActivityNoFullScreen$10 a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.a.setIsAgree(true).start();
                    dialogInterface.dismiss();
                }
            }));
            DJIFragmentActivityNoFullScreen.c(this.b).a(true);
            DJIFragmentActivityNoFullScreen.c(this.b).b(false);
        }
        DJIFragmentActivityNoFullScreen.c(this.b).show();
        DJILogHelper.getInstance().LOGD(DJIFragmentActivityNoFullScreen.e(), "DataRcAckGimbalCtrPermission dialog.show", false, true);
    }
}

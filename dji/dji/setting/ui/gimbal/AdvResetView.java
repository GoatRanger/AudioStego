package dji.setting.ui.gimbal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalResetUserParams;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;

public class AdvResetView extends ItemViewButtonBig {
    public AdvResetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a("FPV_GimbalSettings_Button_GimbalReset");
        a.a(getContext(), R.string.setting_ui_gimbal_reset_confirm, new OnClickListener(this) {
            final /* synthetic */ AdvResetView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataGimbalResetUserParams.getInstance().start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        a.b();
                        a.c();
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                a.a(this.a.a.a.getContext(), R.string.setting_ui_gimbal_reset_gimbal_setting_success);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("View", "reset=" + aVar, false, true);
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                a.a(this.a.a.a.getContext(), R.string.setting_ui_gimbal_reset_gimbal_setting_fail);
                            }
                        });
                    }
                });
                dialogInterface.dismiss();
            }
        });
    }
}

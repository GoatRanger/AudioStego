package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataGimbalResetUserParams;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import dji.thirdparty.a.c;

public class ResetSettings extends ItemViewButtonBig {
    public ResetSettings(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        a.b(getContext(), R.string.setting_ui_ronin_reset_params_confirm_desc, new OnClickListener(this) {
            final /* synthetic */ ResetSettings a;

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
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                a.a(this.a.a.a.getContext(), R.string.setting_ui_ronin_reset_params_success_tip);
                            }
                        });
                        c.a().e(MotorParams.a.a);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        });
    }
}

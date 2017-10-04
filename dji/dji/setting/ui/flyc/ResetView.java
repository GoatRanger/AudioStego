package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import dji.midware.data.model.P3.DataFlycResetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;

public class ResetView extends ItemViewButtonBig {
    public ResetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a("FPV_MCSettings_Button_ResetAllSettings");
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext(), R.string.setting_ui_flyc_cant_resetall_tip, 0).show();
        } else {
            a.b(getContext(), R.string.setting_ui_flyc_reset_confirm, new OnClickListener(this) {
                final /* synthetic */ ResetView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataFlycResetParams.getInstance().start(new d(this) {
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
                                    this.a.a.a.a();
                                }
                            });
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.a.b();
                                }
                            });
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
        }
    }

    private void a() {
        a.a(getContext(), R.string.setting_ui_flyc_reset_success);
        a.b().a("g_config.flying_limit.max_radius_0", "g_config.flying_limit.max_height_0", "g_config.advanced_function.radius_limit_enabled_0", dji.midware.data.params.P3.a.B);
    }

    private void b() {
        a.a(getContext(), R.string.setting_ui_flyc_reset_fail);
    }
}

package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import java.util.ArrayList;

public class ControlResetView extends ItemViewButtonBig {
    private static final String[] a = new String[]{"g_config.control.atti_torsion_w_rate_0", "g_config.control.brake_sensitivity_0", "g_config.control.rc_tilt_sensitivity_0", "g_config.control.tilt_exp_mid_point_0", "g_config.control.yaw_exp_mid_point_0", "g_config.control.thr_exp_mid_point_0", "g_config.control.atti_vertical_0", "g_config.control.basic_pitch_0", "g_config.control.basic_roll_0", "g_config.control.basic_tail_0"};
    private ArrayList<String> b = new ArrayList();

    public ControlResetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b.add(e.cd);
        this.b.add(e.cg);
        this.b.add(e.ce);
        this.b.add(e.co);
        this.b.add(e.cp);
        this.b.add(e.cn);
        this.b.add(e.cD);
        this.b.add(e.cE);
        this.b.add(e.cG);
        this.b.add(e.cH);
    }

    public void onClick(View view) {
        a.b(getContext(), R.string.setting_ui_flyc_reset_control_confirm, new OnClickListener(this) {
            final /* synthetic */ ControlResetView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dji.sdksharedlib.e.a.a.getInstance().b(this.a.b, new b.e(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj) {
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

                    public void a(DJIError dJIError) {
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

    private void a() {
        a.a(getContext(), R.string.setting_ui_flyc_reset_control_success);
        a.b().a(a);
    }

    private void b() {
        a.a(getContext(), R.string.setting_ui_flyc_reset_control_fail);
    }

    private void c() {
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c();
        }
    }

    public void onEventMainThread(ProductType productType) {
        c();
    }
}

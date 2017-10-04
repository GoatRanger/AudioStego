package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class BeginerView extends DividerLinearLayout implements OnCheckedChangeListener {
    private static final String a = "g_config.novice_cfg.novice_func_enabled_0";
    private Switch b = ((Switch) findViewById(R.id.setting_ui_item_switch));

    public BeginerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a.a((View) this, R.layout.setting_ui_flyc_beginer);
        this.b.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if ((d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() == 1) != z) {
            if (z) {
                e.a("FPV_MCSettings_Switcher_BeginnerMode_ON");
            } else {
                e.a("FPV_MCSettings_Switcher_BeginnerMode_OFF");
            }
            if (z && DataOsdGetPushCommon.getInstance().isMotorUp()) {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_beginner_mode_note);
                a();
            } else if (z) {
                new DataFlycSetParams().a("g_config.novice_cfg.novice_func_enabled_0", Integer.valueOf(1)).start(new dji.midware.e.d(this) {
                    final /* synthetic */ BeginerView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        a.b().a("g_config.novice_cfg.novice_func_enabled_0");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a();
                            }
                        });
                    }
                });
            } else {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_beginner_mode_close_note, new OnClickListener(this) {
                    final /* synthetic */ BeginerView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        new DataFlycSetParams().a("g_config.novice_cfg.novice_func_enabled_0", Integer.valueOf(0)).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                a.b().a("g_config.novice_cfg.novice_func_enabled_0");
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
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
                        });
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ BeginerView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a();
                    }
                }).setCancelable(false);
            }
        }
    }

    protected void a() {
        boolean z = true;
        if (a.a()) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() != 1) {
            z = false;
        }
        this.b.setChecked(z);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            a.b().a("g_config.novice_cfg.novice_func_enabled_0");
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals("g_config.novice_cfg.novice_func_enabled_0")) {
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

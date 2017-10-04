package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.setting.ui.widget.a;

public class BeginnerSwitchBar extends RelativeLayout {
    private static final String a = "BeginnerSwitchBar";
    private static final String b = "g_config.novice_cfg.novice_func_enabled_0";
    private Switch c = null;

    public BeginnerSwitchBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(getContext()).inflate(R.layout.view_beginer_switch_bar, this);
        this.c = (Switch) findViewById(R.id.flightmode_full_beginner_switch);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BeginnerSwitchBar a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                boolean isChecked = ((Switch) view).isChecked();
                if (isChecked && DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    a.a(this.a.getContext(), R.string.gsnew_fpv_gensetting_beginner_mode_note);
                    this.a.b();
                    return;
                }
                new DataFlycSetParams().a("g_config.novice_cfg.novice_func_enabled_0", Integer.valueOf(isChecked ? 1 : 0)).start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        dji.setting.ui.flyc.a.b().a("g_config.novice_cfg.novice_func_enabled_0");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
                        this.a.a.post(new Runnable(this) {
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
            }
        });
        a();
        b();
    }

    private void a() {
        new DataFlycGetParams().setInfos(new String[]{"g_config.novice_cfg.novice_func_enabled_0"}).start(new d(this) {
            final /* synthetic */ BeginnerSwitchBar a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
                this.a.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }
        });
    }

    private void b() {
        boolean z = true;
        if (dji.midware.data.manager.P3.d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() != 1) {
            z = false;
        }
        this.c.setChecked(z);
    }
}

package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class AvoidGroundView extends DividerLinearLayout implements OnCheckedChangeListener {
    private static final String a = "g_config.flying_limit.user_avoid_ground_enable_0";
    private ParamInfo b = null;
    private int c = 0;
    private Switch d = null;

    public AvoidGroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_avoid_ground);
        if (!isInEditMode()) {
            this.b = d.read(a);
            this.d = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.d.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        if (compoundButton == this.d && getVisibility() == 0) {
            if ((this.b.value.intValue() != 0) != z) {
                this.d.setEnabled(false);
                DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                String str = a;
                if (!z) {
                    i = 0;
                }
                dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
                    final /* synthetic */ AvoidGroundView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.d.setEnabled(true);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.b();
                                this.a.a.d.setEnabled(true);
                            }
                        });
                    }
                });
            }
        }
    }

    private void b() {
        this.d.setChecked(this.b.value.intValue() != 0);
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (this.c != flycVersion) {
            this.c = flycVersion;
            if (flycVersion >= 13) {
                setVisibility(8);
            } else {
                setVisibility(8);
            }
        }
    }

    public void onEventMainThread(o oVar) {
        if (o.b == oVar && getVisibility() == 0) {
            a.b().a(a);
        }
    }

    public void onEventMainThread(a.a aVar) {
        if (a.equals(aVar.a)) {
            b();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}

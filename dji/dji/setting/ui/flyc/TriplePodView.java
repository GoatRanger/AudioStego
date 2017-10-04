package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.common.error.DJIError;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class TriplePodView extends ItemViewSwitch implements d {
    private static final String a = "TriplePodView";
    private static final String e = "Tripod";
    private static final String f = "g_config.novice_cfg.novice_func_enabled_0";
    private String g = "";

    public TriplePodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (a.b(a.e("Tripod")) != z) {
            DJISDKCache.getInstance().setValue(b.f("Tripod"), Boolean.valueOf(z), new h(this) {
                final /* synthetic */ TriplePodView a;

                {
                    this.a = r1;
                }

                public void a() {
                }

                public void a(DJIError dJIError) {
                    DJILogHelper.getInstance().LOGD("Triple Mode", "fails code = " + dJIError.getDescription());
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
        }
    }

    protected void a() {
        int flycVersion;
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            flycVersion = DataOsdGetPushCommon.getInstance().getFlycVersion();
        } else {
            flycVersion = 0;
        }
        if (!dji.pilot.publics.e.a.c(i.getInstance().c()) || r0 >= 18) {
            setVisibility(8);
            return;
        }
        if (dji.midware.data.manager.P3.d.read("g_config.novice_cfg.novice_func_enabled_0").value.intValue() == 1) {
            flycVersion = 1;
        } else {
            flycVersion = 0;
        }
        if (flycVersion != 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.eS_.setChecked(a.b(a.e("Tripod")));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.g(this, new String[]{"Tripod"});
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            a();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals("g_config.novice_cfg.novice_func_enabled_0")) {
            a();
        }
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }
}

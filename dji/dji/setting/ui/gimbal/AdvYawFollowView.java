package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class AdvYawFollowView extends ItemViewSwitch {
    private final String a = "yaw_follow_exp";

    public AdvYawFollowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            a.a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        boolean z = true;
        int intValue = e.read("yaw_follow_exp").value.intValue();
        Switch switchR = this.eS_;
        if (intValue != 1) {
            z = false;
        }
        switchR.setChecked(z);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        int intValue = e.read("yaw_follow_exp").value.intValue();
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        if (i != intValue) {
            DataGimbalSetUserParams.getInstance().a("yaw_follow_exp", Integer.valueOf(i)).start(new d(this) {
                final /* synthetic */ AdvYawFollowView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.c();
                    a.a();
                }

                public void onFailure(a aVar) {
                    a.c();
                    a.a();
                }
            });
        }
    }
}

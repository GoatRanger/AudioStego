package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.thirdparty.a.c;

public class VisionAvoidViewGroup extends LinearLayout {
    public VisionAvoidViewGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        setVisibility(dataFlycGetPushAvoidParam.isUserAvoidEnable() ? 0 : 8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(DataFlycGetPushAvoidParam.getInstance());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}

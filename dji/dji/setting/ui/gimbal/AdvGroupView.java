package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.pilot.publics.e.a;
import dji.setting.ui.widget.ItemViewGroup;
import dji.thirdparty.a.c;

public class AdvGroupView extends ItemViewGroup {
    public AdvGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        a();
    }

    public void onEventMainThread(DataGimbalGetPushType dataGimbalGetPushType) {
        a();
    }

    private void a() {
        if (DataGimbalGetPushParams.getInstance().getVersion() == 1 || a.d(null)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (DataGimbalGetPushType.getInstance().isGetted() && DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin) {
            setVisibility(8);
        }
    }
}

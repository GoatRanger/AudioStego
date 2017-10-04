package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.thirdparty.a.c;

public class TabGimbalRonin extends ImageView {
    public TabGimbalRonin(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a(DataGimbalGetPushParams.getInstance());
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.isPushLosed()) {
            setVisibility(8);
        } else if (DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        a(dataGimbalGetPushParams);
    }
}

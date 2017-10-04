package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.pilot.publics.e.a;
import dji.thirdparty.a.c;

public class TabGuidance extends ImageView {
    public TabGuidance(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        if (a.r(i.getInstance().c()) && DataFlycGetPushAvoid.getInstance().isGetted()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        a();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.thirdparty.a.c;

public class TabBatteryM600 extends ImageView {
    public TabBatteryM600(Context context, AttributeSet attributeSet) {
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
        if (a.d(i.getInstance().c())) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.thirdparty.a.c;

public class TabBattery extends ImageView {
    public TabBattery(Context context, AttributeSet attributeSet) {
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
        ProductType c = i.getInstance().c();
        if (i.getInstance().a() == ProductType.Grape2 || a.d(c) || c == ProductType.A3 || c == ProductType.N3 || c == ProductType.A2 || c == ProductType.Orange2) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.thirdparty.a.c;

public class TabBatteryNonSmart extends ImageView {
    public TabBatteryNonSmart(Context context, AttributeSet attributeSet) {
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
        if (c == ProductType.A3 || c == ProductType.N3) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.thirdparty.a.c;

public class TabGeneral extends ImageView {
    public TabGeneral(Context context, AttributeSet attributeSet) {
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
        if (i.getInstance().c() == ProductType.OTHER) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}

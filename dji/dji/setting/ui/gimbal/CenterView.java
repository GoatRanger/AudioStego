package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.publics.e.a;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.thirdparty.a.c;

public class CenterView extends ItemViewButtonBig {
    public CenterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        DataSpecialControl.getInstance().resetGimbal().start(20);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    private void a() {
        ProductType c = i.getInstance().c();
        if (!a.b(c) || c == ProductType.Tomato || c == ProductType.Pomato) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }
}

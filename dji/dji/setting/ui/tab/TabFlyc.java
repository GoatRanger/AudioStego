package dji.setting.ui.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.c.d;
import dji.pilot.publics.e.a;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.thirdparty.a.c;

public class TabFlyc extends ImageView {
    public TabFlyc(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        ProductType c = i.getInstance().c();
        ProductType a = i.getInstance().a();
        int i = d.b == MODE.b ? 1 : 0;
        if (c == ProductType.A2 || !((a != ProductType.Grape2 || a.d(c) || c == ProductType.A3 || c == ProductType.N3) && i == 0)) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        a();
    }
}

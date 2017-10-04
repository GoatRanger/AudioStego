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

public class TabVision extends ImageView {
    public TabVision(Context context, AttributeSet attributeSet) {
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
        int i = d.b == MODE.b ? 1 : 0;
        if (a.k(c) && i == 0) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        a();
    }
}

package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewGroup;
import dji.thirdparty.a.c;

public class LB2GroupView extends ItemViewGroup {
    public LB2GroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
        }
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

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        if (!a.a()) {
            setVisibility(8);
        } else if (this.a == R.string.setting_ui_hd_signal_check) {
            setVisibility(0);
        } else if (DataDm368GetGParams.getInstance().getOutputEnable()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}

package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;

public class AttitudeLimitView extends ItemViewSpinner {
    private String[] a = new String[]{"15", "20", "25", "30"};

    public AttitudeLimitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        if (a.d(i.getInstance().c())) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        this.f.setData(this.a, 0, (b) this);
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onItemClick(int i) {
    }
}

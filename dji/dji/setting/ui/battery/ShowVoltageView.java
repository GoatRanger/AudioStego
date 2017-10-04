package dji.setting.ui.battery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.pilot.battery.a.a;
import dji.setting.ui.widget.ItemViewSwitch;

public class ShowVoltageView extends ItemViewSwitch {
    public ShowVoltageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a() {
        this.eS_.setChecked(a.getInstance().a());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != a.getInstance().a()) {
            a.getInstance().a(z);
        }
    }
}

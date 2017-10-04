package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class TestView extends DividerLinearLayout {
    public TestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_battery_history);
        if (!isInEditMode()) {
        }
    }
}

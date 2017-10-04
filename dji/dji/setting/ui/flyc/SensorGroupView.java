package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.ItemViewGroup;
import dji.thirdparty.a.c;

public class SensorGroupView extends ItemViewGroup {
    public SensorGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        if (!a.a()) {
            if (dji.pilot.publics.e.a.f()) {
                c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_flyc_a3_sensor, this.a, (View) this));
            } else {
                c.a().e(new dji.setting.ui.c(this.b, this.a, (View) this));
            }
        }
    }
}

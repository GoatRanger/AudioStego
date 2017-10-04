package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;

public class VersionPackageLB2View extends ItemViewText {
    public VersionPackageLB2View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        i.getInstance().c();
        if (a.g()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        CharSequence aircraftLB2Version = AppPublicReflect.getAircraftLB2Version();
        if (aircraftLB2Version == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(aircraftLB2Version);
        }
    }
}

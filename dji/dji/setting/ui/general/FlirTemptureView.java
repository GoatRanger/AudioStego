package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.manager.P3.i;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;

public class FlirTemptureView extends ItemViewSpinner {
    private final int[] a = new int[]{R.string.setting_ui_general_temperature_fahrenheit, R.string.setting_ui_general_temperature_celsius, R.string.setting_ui_general_temperature_kelvin};

    public FlirTemptureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f.setData(this.a, 0, (b) this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (a.a(i.getInstance().b())) {
            setVisibility(0);
            this.f.setIndex(DJIGenSettingDataManager.getInstance().w());
            return;
        }
        setVisibility(8);
    }

    public void onItemClick(int i) {
        if (i != DJIGenSettingDataManager.getInstance().w()) {
            DJIGenSettingDataManager.getInstance().c(i);
        }
    }
}

package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.util.AttributeSet;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.ItemViewSpinner;

public class CameraTypeSpinner extends ItemViewSpinner {
    public CameraTypeSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onItemClick(int i) {
    }

    public DJISpinnerButton getSpinner() {
        return this.f;
    }
}

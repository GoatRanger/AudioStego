package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class VisionFrontSensorView extends ItemViewText {
    public VisionFrontSensorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        if (data2100GetPushCheckStatus.isForeSightDemarkAbnormal()) {
            this.d.setText(R.string.setting_ui_vision_abnormal);
            this.d.setTextColor(getContext().getResources().getColor(R.color.setting_ui_battery_red));
            return;
        }
        this.d.setText(R.string.setting_ui_vision_normal);
        this.d.setTextColor(getContext().getResources().getColor(R.color.white));
    }

    public void onEventMainThread(ProductType productType) {
        if (a.k(productType)) {
            setVisibility(0);
            onEventMainThread(Data2100GetPushCheckStatus.getInstance());
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}

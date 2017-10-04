package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;

public class VersionCenterView extends VersionCommonView {
    public VersionCenterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected DeviceType getDeviceType() {
        return DeviceType.CENTER;
    }
}

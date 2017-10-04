package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;

public class VersionFlycView extends VersionCommonView {
    public VersionFlycView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected DeviceType getDeviceType() {
        return DeviceType.FLYC;
    }
}

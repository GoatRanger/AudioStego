package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;

public class VersionDM368GView extends VersionCommonView {
    public VersionDM368GView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected DeviceType getDeviceType() {
        return DeviceType.DM368_G;
    }
}

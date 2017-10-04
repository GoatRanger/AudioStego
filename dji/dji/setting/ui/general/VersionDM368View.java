package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;

public class VersionDM368View extends VersionCommonView {
    public VersionDM368View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected DeviceType getDeviceType() {
        return DeviceType.DM368;
    }

    protected int getDeviceModelId() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.A3 || a.d(c) || c == ProductType.Grape2 || c == ProductType.N3) {
            return 1;
        }
        return super.getDeviceModelId();
    }
}

package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;

public class VersionPackageRcView extends ItemViewText {
    public VersionPackageRcView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        ProductType c = i.getInstance().c();
        if (i.getInstance().a().isFromWifi() || c == ProductType.A3 || c == ProductType.N3) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        CharSequence rcVersion = AppPublicReflect.getRcVersion();
        if (rcVersion == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(rcVersion);
        }
    }
}

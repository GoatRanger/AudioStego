package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class VersionPackageCameraView extends ItemViewText {
    public VersionPackageCameraView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        ProductType c = i.getInstance().c();
        if (c == ProductType.Orange || c == ProductType.BigBanana || c == ProductType.OrangeRAW || c == ProductType.N1 || c == ProductType.Olives || c == ProductType.OrangeCV600 || a.d(c)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (!DataCameraGetPushStateInfo.getInstance().isGetted()) {
            setVisibility(8);
        }
        CharSequence cameraVersion = AppPublicReflect.getCameraVersion();
        if (cameraVersion == null) {
            this.d.setText(R.string.setting_ui_general_default_str);
        } else {
            this.d.setText(cameraVersion);
        }
    }

    public void onEventMainThread(o oVar) {
        a();
    }
}

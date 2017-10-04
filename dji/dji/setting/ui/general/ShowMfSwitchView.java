package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.pilot.publics.objects.g;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class ShowMfSwitchView extends ItemViewSwitch {
    public static final String a = "key_open_mf_switch";

    public enum a {
        TRUE
    }

    public ShowMfSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (g.b(getContext(), a, false) != z) {
            g.a(getContext(), a, z);
            if (!z) {
                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
            }
            c.a().e(a.TRUE);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            updateView();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        updateView();
    }

    public void updateView() {
        if (dji.pilot.publics.e.a.c(null)) {
            setVisibility(0);
            if (g.b(getContext(), a, false)) {
                this.eS_.setChecked(true);
                return;
            } else {
                this.eS_.setChecked(false);
                return;
            }
        }
        setVisibility(8);
    }
}

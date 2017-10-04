package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.ItemViewRadio;

public class ChannelModeView extends ItemViewRadio implements d {
    private c a = b.h("ChannelSelectionMode");

    public ChannelModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(R.string.setting_ui_hd_channel_auto);
        this.h.setText(R.string.setting_ui_hd_channel_custom);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            DJISDKCache.getInstance().startListeningForUpdates(this.a, this, true);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.a);
        if (availableValue != null) {
            if (availableValue.e() == LBAirLinkChannelSelectionMode.Auto) {
                this.g.setChecked(true);
            } else {
                this.h.setChecked(true);
            }
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.g.getId()) {
            setChannelMode(LBAirLinkChannelSelectionMode.Auto);
        } else {
            setChannelMode(LBAirLinkChannelSelectionMode.Manual);
        }
    }

    private void setChannelMode(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode) {
        DJISDKCache.getInstance().setValue(this.a, lBAirLinkChannelSelectionMode, null);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.a)) {
            a();
        }
    }
}

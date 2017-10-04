package dji.setting.ui.battery.nonsmart;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class VoltageView extends ItemViewText {
    public VoltageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        float voltage = 0.001f * ((float) DataFlycGetPushSmartBattery.getInstance().getVoltage());
        this.d.setText(String.format("%.2f V", new Object[]{Float.valueOf(voltage)}));
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        a();
    }
}

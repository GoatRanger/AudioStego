package dji.sdksharedlib.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.product.Model;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;

public class EasyDemoView extends LinearLayout implements d {
    public EasyDemoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this, h.c);
        a.d(this, new String[]{dji.sdksharedlib.b.a.c, dji.sdksharedlib.b.a.b, dji.sdksharedlib.b.a.j});
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.a(this);
    }

    private void a() {
        Model model = (Model) a.a(h.c);
        int[] iArr = (int[]) a.d(dji.sdksharedlib.b.a.l);
        DJIBatteryWarningInformation dJIBatteryWarningInformation = (DJIBatteryWarningInformation) a.d(dji.sdksharedlib.b.a.j);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }
}

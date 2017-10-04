package dji.setting.ui.battery;

import android.content.Context;
import android.util.AttributeSet;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.ItemViewText;
import java.util.Locale;

public class FlightTimeView extends ItemViewText implements d {
    public FlightTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.g(this, new String[]{e.az});
            a();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        int a = a.a(a.e(e.az)) / 10;
        int i = a % 60;
        a /= 60;
        this.d.setText(String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(a), Integer.valueOf(i)}));
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }
}

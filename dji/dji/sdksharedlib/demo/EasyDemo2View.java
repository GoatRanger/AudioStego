package dji.sdksharedlib.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;

public class EasyDemo2View extends LinearLayout implements d {
    c a;
    c b;

    public EasyDemo2View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.a = b.a(h.c);
        this.b = b.b("ISO");
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this, new c[]{this.b, this.a});
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.a(this);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar == this.a || cVar != this.b) {
        }
    }
}

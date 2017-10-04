package dji.sdksharedlib.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;

public class StandardDemoView extends LinearLayout implements d {
    c a = null;
    c b = null;

    public StandardDemoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a aVar = new a();
        aVar.b(h.a);
        aVar.d(h.c);
        this.a = aVar.a();
        aVar = new a();
        aVar.b(b.a);
        aVar.d("ISO");
        this.b = dji.sdksharedlib.a.b.b("ISO");
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DJISDKCache.getInstance().startListeningForUpdates(this.a, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.b, this, true);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DJISDKCache.getInstance().stopListening(this);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.a) || !cVar.equals(this.b)) {
        }
    }
}

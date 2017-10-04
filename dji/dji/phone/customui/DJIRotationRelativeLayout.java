package dji.phone.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import dji.f.a;
import dji.phone.h.b;

public class DJIRotationRelativeLayout extends RelativeLayout {
    private static final String a = "DJIRotationFrameLayout";

    public DJIRotationRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a.b(this);
    }

    public void onEventMainThread(b bVar) {
        float f = 0.0f;
        switch (bVar) {
            case ROTATION_0:
            case ROTATION_180:
                f = (float) bVar.b();
                break;
            case ROTATION_270:
                f = 180.0f;
                break;
        }
        dji.phone.h.a.a((View) this, getRotation(), f, 100);
    }
}

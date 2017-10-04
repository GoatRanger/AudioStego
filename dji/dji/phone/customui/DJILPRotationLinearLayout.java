package dji.phone.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.f.a;
import dji.phone.h.b;
import dji.phone.k.c;

public class DJILPRotationLinearLayout extends LinearLayout {
    private static final String a = "DJILPRotationRelativeLayout";

    public DJILPRotationLinearLayout(Context context) {
        super(context);
    }

    public DJILPRotationLinearLayout(Context context, AttributeSet attributeSet) {
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
        dji.phone.h.a.a(this, getRotation(), c.a(bVar.b()));
    }
}

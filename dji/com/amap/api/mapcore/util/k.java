package com.amap.api.mapcore.util;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;

public class k extends GLSurfaceView implements IGLSurfaceView {
    private IAMapDelegate a;

    public k(Context context) {
        this(context, null);
    }

    public k(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.a = new c(this, context);
    }

    public IAMapDelegate a() {
        return this.a;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.a.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        if (i == 8 || i == 4) {
            this.a.onPause();
        } else if (i == 0) {
            this.a.onResume();
        }
        super.onWindowVisibilityChanged(i);
    }

    protected void onDetachedFromWindow() {
        this.a.onPause();
        super.onDetachedFromWindow();
    }
}

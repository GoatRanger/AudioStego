package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.autonavi.amap.mapcore.interfaces.GLTextureView;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;

public class l extends GLTextureView implements IGLSurfaceView {
    private IAMapDelegate a;

    public l(Context context) {
        this(context, null);
    }

    public l(Context context, AttributeSet attributeSet) {
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

    public void setZOrderOnTop(boolean z) {
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8 || i == 4) {
            this.a.onPause();
        } else if (i == 0) {
            this.a.onResume();
        }
    }

    protected void onDetachedFromWindow() {
        this.a.onPause();
        super.onDetachedFromWindow();
    }
}

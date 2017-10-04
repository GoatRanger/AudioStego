package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

class f extends Handler {
    final /* synthetic */ c a;

    f(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        try {
            if (this.a.ac != null) {
                this.a.ac.onTouch((MotionEvent) message.obj);
            }
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "onTouchHandler");
            th.printStackTrace();
        }
    }
}

package com.here.android.mpa.common;

import android.view.SurfaceHolder;
import com.nokia.maps.annotation.Online;

@Online
public interface OffScreenRenderer {

    @Online
    public interface SurfaceUpdatedListener {
        void onSurfaceUpdated();
    }

    void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener);

    OffScreenRenderer setSize(int i, int i2);

    void start();

    void start(SurfaceHolder surfaceHolder, SurfaceUpdatedListener surfaceUpdatedListener);

    void stop();
}

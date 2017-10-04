package com.here.android.mpa.streetlevel;

import android.content.Context;
import android.view.SurfaceHolder;
import com.here.android.mpa.common.OffScreenRenderer;
import com.here.android.mpa.common.OffScreenRenderer.SurfaceUpdatedListener;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.nokia.maps.ej;

public final class StreetLevelOffScreenCapture implements OffScreenRenderer {
    ej a;

    public StreetLevelOffScreenCapture(Context context) {
        this.a = new ej(context);
    }

    public StreetLevelOffScreenCapture setModel(StreetLevelModel streetLevelModel) {
        this.a.a(streetLevelModel);
        return this;
    }

    public StreetLevelOffScreenCapture setSize(int i, int i2) {
        this.a.a(i, i2);
        return this;
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        this.a.a(onScreenCaptureListener);
    }

    public void start() {
        this.a.a();
    }

    public void start(SurfaceHolder surfaceHolder, SurfaceUpdatedListener surfaceUpdatedListener) {
        this.a.a(surfaceHolder, surfaceUpdatedListener);
    }

    public void stop() {
        this.a.c();
    }

    public void pause() {
        this.a.b();
    }
}

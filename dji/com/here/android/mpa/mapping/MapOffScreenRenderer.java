package com.here.android.mpa.mapping;

import android.content.Context;
import android.view.SurfaceHolder;
import com.here.android.mpa.common.OffScreenRenderer;
import com.here.android.mpa.common.OffScreenRenderer.SurfaceUpdatedListener;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.br;

@Online
public final class MapOffScreenRenderer implements OffScreenRenderer {
    private br a;

    public MapOffScreenRenderer(Context context) {
        this.a = new br(context);
    }

    public MapOffScreenRenderer setMap(Map map) {
        this.a.a(map);
        return this;
    }

    public void addOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        this.a.a(onMapRenderListener);
    }

    public void removeOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        this.a.b(onMapRenderListener);
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

    public MapOffScreenRenderer setSize(int i, int i2) {
        this.a.a(i, i2);
        return this;
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        this.a.a(onScreenCaptureListener);
    }

    public MapOffScreenRenderer setViewRect(ViewRect viewRect) {
        this.a.a(viewRect);
        return this;
    }

    public MapOffScreenRenderer setBlockingRendering(boolean z) {
        this.a.a(z);
        return this;
    }

    public boolean getBlockingRendering() {
        return this.a.e();
    }
}

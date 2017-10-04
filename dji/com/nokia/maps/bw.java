package com.nokia.maps;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.OnMapRenderListener;

public interface bw {
    Bitmap a(MapMarker mapMarker);

    ViewRect a();

    void a(Parcelable parcelable);

    void a(OnScreenCaptureListener onScreenCaptureListener);

    void a(ViewRect viewRect, PointF pointF);

    void a(Map map) throws Exception;

    void a(OnMapRenderListener onMapRenderListener);

    void a(cg cgVar);

    Map b();

    void b(OnMapRenderListener onMapRenderListener);

    void b(cg cgVar);

    MapGesture c();

    void d();

    void e();

    Bundle f();

    void g();

    Bitmap h();
}

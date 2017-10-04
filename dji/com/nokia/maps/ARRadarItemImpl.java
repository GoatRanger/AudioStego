package com.nokia.maps;

import android.graphics.PointF;
import android.graphics.RectF;
import com.here.android.mpa.ar.ARObject;
import com.here.android.mpa.ar.ARRadarItem;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;

@HybridPlus
public class ARRadarItemImpl extends BaseNativeObject {
    private static am<ARRadarItem, ARRadarItemImpl> c = null;
    private static k<ARRadarItem, ARRadarItemImpl> d = null;
    WeakReference<ARObject> a = null;
    private RectF b = new RectF();

    private native void destroy();

    private native PointF getScreenBottomRight();

    private native PointF getScreenTopLeft();

    public native float getBearing();

    public native float getDistance();

    public native float getPanDistance();

    public native float getSpreadDistance();

    public native long getUid();

    public native boolean isOccluded();

    public native boolean isVisible();

    static {
        ce.a(ARRadarItem.class);
    }

    static ARRadarItem a(ARRadarItemImpl aRRadarItemImpl) {
        return aRRadarItemImpl != null ? (ARRadarItem) c.a(aRRadarItemImpl) : null;
    }

    public static void a(k<ARRadarItem, ARRadarItemImpl> kVar, am<ARRadarItem, ARRadarItemImpl> amVar) {
        d = kVar;
        c = amVar;
    }

    @HybridPlusNative
    private ARRadarItemImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroy();
    }

    public void a(ARObject aRObject) {
        this.a = new WeakReference(aRObject);
    }

    public ARObject a() {
        return this.a != null ? (ARObject) this.a.get() : null;
    }

    public RectF b() {
        PointF screenTopLeft = getScreenTopLeft();
        PointF screenBottomRight = getScreenBottomRight();
        this.b.set(screenTopLeft.x, screenTopLeft.y, screenBottomRight.x, screenBottomRight.y);
        return this.b;
    }
}

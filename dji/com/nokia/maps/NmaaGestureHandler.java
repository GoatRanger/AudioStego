package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.MapBuildingObject;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.nokia.maps.MapGestureHandler.MapUserInteractionListener;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class NmaaGestureHandler extends MapGestureHandlerBase {
    private static final String c = NmaaGestureHandler.class.getName();
    private static final Object g = new Object();
    private final a d;
    private final MapImpl e;
    private PointF f = null;
    @OnlineNative
    private int nativeConfigurationHelperPtr;
    @OnlineNative
    private int nativeEventWrapperPtr;
    @OnlineNative
    private int nativeHandlerPtr;
    @OnlineNative
    private int nativeViewConfigurationPtr;

    private interface c {
        void a();
    }

    private class a {
        final /* synthetic */ NmaaGestureHandler a;
        private long b;
        private b c;

        private a(NmaaGestureHandler nmaaGestureHandler) {
            this.a = nmaaGestureHandler;
            this.c = null;
        }

        private final void d() {
            if (SystemClock.uptimeMillis() - this.b >= 16) {
                a();
            }
        }

        public final void a() {
            synchronized (NmaaGestureHandler.g) {
                if (this.c != null) {
                    this.b = SystemClock.uptimeMillis();
                    this.a.pollNative(this.b);
                }
            }
        }

        public void b() {
            synchronized (NmaaGestureHandler.g) {
                if (this.c == null) {
                    this.c = new b(new c(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.d();
                        }
                    });
                    this.c.start();
                }
            }
        }

        public final void c() {
            b bVar = this.c;
            this.c = null;
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    private static class b extends Thread {
        private final c a;
        private volatile boolean b = true;

        b(c cVar) {
            this.a = cVar;
            setName("Gestures");
        }

        public void a() {
            this.b = false;
        }

        public void run() {
            while (this.b) {
                this.a.a();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private native void destroyNative();

    private native boolean handleTouchEventNative(MotionEvent motionEvent);

    private native void initNative(MapImpl mapImpl);

    private native void initViewConfigurationNative();

    private native boolean isDoubleTapEnabledNative();

    private native boolean isFixedMapCenterOnRotateZoomNative();

    private native boolean isKineticFlickEnabledNative();

    private native boolean isLongPressEnabledNative();

    private native boolean isPanningEnabledNative();

    private native boolean isPinchEnabledNative();

    private native boolean isRotateEnabledNative();

    private native boolean isSingleTapEnabledNative();

    private native boolean isTiltEnabledNative();

    private native boolean isTwoFingerPanningEnabledNative();

    private native boolean isTwoFingerTapEnabledNative();

    private native void pollNative(long j);

    private native void setAllGesturesEnabledNative(boolean z);

    private native void setDoubleTapEnabledNative(boolean z);

    private native void setDoubleTapTimeoutNative(int i);

    private native void setFixedMapCenterOnRotateZoomNative(boolean z);

    private native void setKineticFlickEnabledNative(boolean z);

    private native void setLongPressEnabledNative(boolean z);

    private native void setLongPressTimeoutNative(int i);

    private native void setPanningEnabledNative(boolean z);

    private native void setPinchEnabledNative(boolean z);

    private native void setRotateEnabledNative(boolean z);

    private native void setScaledDoubleTapSlopNative(int i);

    private native void setScaledTouchSlopNative(int i);

    private native void setSingleTapEnabledNative(boolean z);

    private native void setTapTimeoutNative(int i);

    private native void setTiltEnabledNative(boolean z);

    private native void setTwoFingerPanningEnabledNative(boolean z);

    private native void setTwoFingerTapEnabledNative(boolean z);

    public native void cancelKineticPanningNative();

    public /* bridge */ /* synthetic */ void addOnGestureListener(OnGestureListener onGestureListener) {
        super.addOnGestureListener(onGestureListener);
    }

    public /* bridge */ /* synthetic */ void b(MapUserInteractionListener mapUserInteractionListener) {
        super.b(mapUserInteractionListener);
    }

    public /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    public /* bridge */ /* synthetic */ void removeOnGestureListener(OnGestureListener onGestureListener) {
        super.removeOnGestureListener(onGestureListener);
    }

    NmaaGestureHandler(MapImpl mapImpl, Context context) {
        BaseNativeObject.t();
        a(context);
        this.e = mapImpl;
        a(mapImpl);
        this.d = new a();
        this.d.b();
    }

    private void a(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        initViewConfigurationNative();
        setTapTimeoutNative(ViewConfiguration.getTapTimeout());
        setLongPressTimeoutNative(ViewConfiguration.getLongPressTimeout());
        setDoubleTapTimeoutNative(ViewConfiguration.getDoubleTapTimeout());
        setScaledTouchSlopNative(viewConfiguration.getScaledTouchSlop());
        setScaledDoubleTapSlopNative(viewConfiguration.getScaledDoubleTapSlop());
    }

    public void a() {
        super.a();
        this.d.a();
    }

    public MapGesture setTiltEnabled(boolean z) {
        setTiltEnabledNative(z);
        return this;
    }

    public boolean isTiltEnabled() {
        return isTiltEnabledNative();
    }

    public MapGesture setRotateEnabled(boolean z) {
        setRotateEnabledNative(z);
        return this;
    }

    public boolean isRotateEnabled() {
        return isRotateEnabledNative();
    }

    public MapGesture setPanningEnabled(boolean z) {
        setPanningEnabledNative(z);
        return this;
    }

    public boolean isPanningEnabled() {
        return isPanningEnabledNative();
    }

    public MapGesture setKineticFlickEnabled(boolean z) {
        setKineticFlickEnabledNative(z);
        return this;
    }

    public boolean isKineticFlickEnabled() {
        return isKineticFlickEnabledNative();
    }

    public MapGesture setPinchEnabled(boolean z) {
        setPinchEnabledNative(z);
        return this;
    }

    public boolean isPinchEnabled() {
        return isPinchEnabledNative();
    }

    public MapGesture setSingleTapEnabled(boolean z) {
        setSingleTapEnabledNative(z);
        return this;
    }

    public boolean isSingleTapEnabled() {
        return isSingleTapEnabledNative();
    }

    public MapGesture setDoubleTapEnabled(boolean z) {
        setDoubleTapEnabledNative(z);
        return this;
    }

    public boolean isDoubleTapEnabled() {
        return isDoubleTapEnabledNative();
    }

    public MapGesture setLongPressEnabled(boolean z) {
        setLongPressEnabledNative(z);
        return this;
    }

    public boolean isLongPressEnabled() {
        return isLongPressEnabledNative();
    }

    public MapGesture setTwoFingerTapEnabled(boolean z) {
        setTwoFingerTapEnabledNative(z);
        return this;
    }

    public boolean isTwoFingerTapEnabled() {
        return isTwoFingerTapEnabledNative();
    }

    public MapGesture setTwoFingerPanningEnabled(boolean z) {
        setTwoFingerPanningEnabledNative(z);
        return this;
    }

    public boolean isTwoFingerPanningEnabled() {
        return isTwoFingerPanningEnabledNative();
    }

    public MapGesture setAllGesturesEnabled(boolean z) {
        setAllGesturesEnabledNative(z);
        return this;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean handleTouchEventNative = handleTouchEventNative(motionEvent);
        if (handleTouchEventNative && this.e != null) {
            this.e.redraw();
        }
        return handleTouchEventNative;
    }

    public MapGesture setFixedMapCenterOnMapRotateZoom(boolean z) {
        setFixedMapCenterOnRotateZoomNative(z);
        return this;
    }

    public boolean isFixedMapCenterOnMapRotateZoom() {
        return isFixedMapCenterOnRotateZoomNative();
    }

    @OnlineNative
    private void handleTap(final int i, final int i2) {
        ce.a(new Runnable(this) {
            final /* synthetic */ NmaaGestureHandler c;

            public void run() {
                PointF pointF = new PointF((float) i, (float) i2);
                List<ViewObject> a = this.c.e.a(this.c.e.e(pointF));
                for (ViewObject viewObject : a) {
                    if (viewObject instanceof MapBuildingObject) {
                        l.a().g();
                        break;
                    }
                }
                this.c.f = pointF;
                if (!this.c.a(pointF)) {
                    ViewObjectImpl.a(a, this.c.e.d(pointF));
                    if (a.size() > 0 && !this.c.a((List) a)) {
                        Log.i(NmaaGestureHandler.c, "notifyMapObjectsSelected unhandled");
                    }
                }
                this.c.f = null;
            }
        });
    }

    protected void finalize() throws Throwable {
        g();
    }

    public void b() {
        this.d.c();
    }

    public void c() {
        b();
    }

    public void d() {
        this.d.b();
    }

    public void cancelKineticPanning() {
        cancelKineticPanningNative();
    }

    private void a(MapImpl mapImpl) {
        synchronized (g) {
            initNative(mapImpl);
        }
    }

    private void g() {
        this.d.c();
        destroyNative();
    }
}

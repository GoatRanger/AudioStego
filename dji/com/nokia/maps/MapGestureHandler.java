package com.nokia.maps;

import android.view.MotionEvent;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.nokia.maps.annotation.OnlineNative;

@OnlineNative
public interface MapGestureHandler extends MapGesture {

    @OnlineNative
    public interface MapUserInteractionListener {
        void a(boolean z);
    }

    @OnlineNative
    public enum Priority {
        LOW,
        HIGH
    }

    void a();

    void a(OnGestureListener onGestureListener, Priority priority);

    void a(MapUserInteractionListener mapUserInteractionListener);

    boolean a(MotionEvent motionEvent);

    void b();

    void b(MapUserInteractionListener mapUserInteractionListener);

    void c();

    void cancelKineticPanning();

    void d();

    void m();

    void removeOnGestureListener(OnGestureListener onGestureListener);
}

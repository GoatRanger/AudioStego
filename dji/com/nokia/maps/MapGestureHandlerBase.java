package com.nokia.maps;

import android.graphics.PointF;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.nokia.maps.MapGestureHandler.MapUserInteractionListener;
import com.nokia.maps.MapGestureHandler.Priority;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.pilot.dji_groundstation.controller.e;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Online
abstract class MapGestureHandlerBase implements MapGestureHandler {
    protected boolean a = false;
    protected volatile boolean b;
    private final EnumMap<Priority, List<OnGestureListener>> c = new EnumMap(Priority.class);
    private final List<MapUserInteractionListener> d = new CopyOnWriteArrayList();
    private final AtomicBoolean e = new AtomicBoolean();

    MapGestureHandlerBase() {
    }

    public void addOnGestureListener(OnGestureListener onGestureListener) {
        a(onGestureListener, Priority.HIGH);
    }

    public void a(OnGestureListener onGestureListener, Priority priority) {
        if (onGestureListener != null) {
            for (Enum enumR : Priority.values()) {
                List list = (List) this.c.get(enumR);
                if (enumR == priority) {
                    List list2;
                    Object obj;
                    if (list == null) {
                        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                        this.c.put(enumR, copyOnWriteArrayList);
                        list2 = copyOnWriteArrayList;
                        obj = 1;
                    } else if (list.contains(onGestureListener)) {
                        list2 = list;
                        obj = null;
                    } else {
                        list2 = list;
                        int i = 1;
                    }
                    if (obj != null) {
                        list2.add(onGestureListener);
                    }
                } else if (list != null) {
                    list.remove(onGestureListener);
                }
            }
        }
    }

    public void removeOnGestureListener(OnGestureListener onGestureListener) {
        Priority[] values = Priority.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            Object obj = values[i];
            List list = (List) this.c.get(obj);
            if (list == null || !list.contains(onGestureListener)) {
                i++;
            } else {
                ((List) this.c.get(obj)).remove(onGestureListener);
                return;
            }
        }
    }

    @OnlineNative
    protected void notifyPanStart() {
        a("pan");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onPanStart : list) {
                            onPanStart.onPanStart();
                        }
                    }
                }
            }
        });
        a(true);
    }

    @OnlineNative
    protected void notifyPanEnd() {
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onPanEnd : list) {
                            onPanEnd.onPanEnd();
                        }
                    }
                }
            }
        });
        a(false);
    }

    @OnlineNative
    protected void notifyMultiFingerManipulationStart() {
        a("multi-finger-active");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onMultiFingerManipulationStart : list) {
                            onMultiFingerManipulationStart.onMultiFingerManipulationStart();
                        }
                    }
                }
            }
        });
        a(true);
    }

    @OnlineNative
    protected void notifyMultifingerManipulationEnd() {
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onMultiFingerManipulationEnd : list) {
                            onMultiFingerManipulationEnd.onMultiFingerManipulationEnd();
                        }
                    }
                }
            }
        });
        a(false);
    }

    protected boolean a(PointF pointF) {
        a("tap");
        Priority[] values = Priority.values();
        for (int length = values.length - 1; length >= 0; length--) {
            List<OnGestureListener> list = (List) this.c.get(values[length]);
            if (list != null) {
                for (OnGestureListener onTapEvent : list) {
                    if (onTapEvent.onTapEvent(pointF)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    protected boolean a(List<ViewObject> list) {
        a("map-objects-selected");
        Priority[] values = Priority.values();
        for (int length = values.length - 1; length >= 0; length--) {
            List<OnGestureListener> list2 = (List) this.c.get(values[length]);
            if (list2 != null) {
                for (OnGestureListener onMapObjectsSelected : list2) {
                    if (onMapObjectsSelected.onMapObjectsSelected(list)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    @OnlineNative
    protected void notifyDoubleTap(final int i, final int i2) {
        a("double-tap");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase c;

            public void run() {
                boolean z = false;
                PointF pointF = new PointF((float) i, (float) i2);
                Priority[] values = Priority.values();
                int length = values.length - 1;
                while (length >= 0 && !z) {
                    boolean z2;
                    List<OnGestureListener> list = (List) this.c.c.get(values[length]);
                    if (list != null) {
                        z2 = z;
                        for (OnGestureListener onDoubleTapEvent : list) {
                            z2 = onDoubleTapEvent.onDoubleTapEvent(pointF);
                            if (z2) {
                                break;
                            }
                        }
                    }
                    z2 = z;
                    length--;
                    z = z2;
                }
            }
        });
    }

    @OnlineNative
    protected void notifyPinchLocked() {
        a("pinch-locked");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onPinchLocked : list) {
                            onPinchLocked.onPinchLocked();
                        }
                    }
                }
            }
        });
    }

    @OnlineNative
    protected boolean notifyPinchZoomEvent(final float f, final int i, final int i2) {
        a("pinch-zoomed");
        this.e.set(false);
        a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase d;

            public void run() {
                PointF pointF = new PointF((float) i, (float) i2);
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0 && !this.d.e.get(); length--) {
                    List<OnGestureListener> list = (List) this.d.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onPinchZoomEvent : list) {
                            this.d.e.compareAndSet(false, onPinchZoomEvent.onPinchZoomEvent(f, pointF));
                            if (this.d.e.get()) {
                                break;
                            }
                        }
                    }
                }
            }
        });
        return this.e.get();
    }

    private void a(Runnable runnable) {
        runnable.run();
    }

    @OnlineNative
    protected void notifyRotateLocked() {
        a("rotate-locked");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onRotateLocked : list) {
                            onRotateLocked.onRotateLocked();
                        }
                    }
                }
            }
        });
    }

    @OnlineNative
    protected void notifyRotateEvent(final float f) {
        a("rotate");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase b;

            public void run() {
                boolean z = false;
                Priority[] values = Priority.values();
                int length = values.length - 1;
                while (length >= 0 && !z) {
                    boolean z2;
                    List<OnGestureListener> list = (List) this.b.c.get(values[length]);
                    if (list != null) {
                        z2 = z;
                        for (OnGestureListener onRotateEvent : list) {
                            z2 = onRotateEvent.onRotateEvent(f);
                            if (z2) {
                                break;
                            }
                        }
                    }
                    z2 = z;
                    length--;
                    z = z2;
                }
            }
        });
    }

    @OnlineNative
    protected void notifyTiltEvent(final float f) {
        a("tilt");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase b;

            public void run() {
                boolean z = false;
                Priority[] values = Priority.values();
                int length = values.length - 1;
                while (length >= 0 && !z) {
                    boolean z2;
                    List<OnGestureListener> list = (List) this.b.c.get(values[length]);
                    if (list != null) {
                        z2 = z;
                        for (OnGestureListener onTiltEvent : list) {
                            z2 = onTiltEvent.onTiltEvent(f);
                            if (z2) {
                                break;
                            }
                        }
                    }
                    z2 = z;
                    length--;
                    z = z2;
                }
            }
        });
    }

    @OnlineNative
    protected void notifyLongPressEvent(final int i, final int i2) {
        a("touch-and-hold");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase c;

            public void run() {
                boolean z = false;
                PointF pointF = new PointF((float) i, (float) i2);
                Priority[] values = Priority.values();
                int length = values.length - 1;
                while (length >= 0 && !z) {
                    boolean z2;
                    List<OnGestureListener> list = (List) this.c.c.get(values[length]);
                    if (list != null) {
                        z2 = z;
                        for (OnGestureListener onLongPressEvent : list) {
                            z2 = onLongPressEvent.onLongPressEvent(pointF);
                            if (z2) {
                                break;
                            }
                        }
                    }
                    z2 = z;
                    length--;
                    z = z2;
                }
            }
        });
        a(true);
    }

    @OnlineNative
    protected void notifyLongPressRelease() {
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                Priority[] values = Priority.values();
                for (int length = values.length - 1; length >= 0; length--) {
                    List<OnGestureListener> list = (List) this.a.c.get(values[length]);
                    if (list != null) {
                        for (OnGestureListener onLongPressRelease : list) {
                            onLongPressRelease.onLongPressRelease();
                        }
                    }
                }
            }
        });
        a(false);
    }

    @OnlineNative
    protected void notifyTwoFingerTapEvent(final int i, final int i2) {
        a("two-finger-tap");
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase c;

            public void run() {
                boolean z = false;
                PointF pointF = new PointF((float) i, (float) i2);
                Priority[] values = Priority.values();
                int length = values.length - 1;
                while (length >= 0 && !z) {
                    boolean z2;
                    List<OnGestureListener> list = (List) this.c.c.get(values[length]);
                    if (list != null) {
                        z2 = z;
                        for (OnGestureListener onTwoFingerTapEvent : list) {
                            z2 = onTwoFingerTapEvent.onTwoFingerTapEvent(pointF);
                            if (z2) {
                                break;
                            }
                        }
                    }
                    z2 = z;
                    length--;
                    z = z2;
                }
            }
        });
    }

    public synchronized void a() {
        boolean z = this.b;
        if (z != this.a) {
            this.a = z;
            for (MapUserInteractionListener a : this.d) {
                a.a(this.a);
            }
        }
    }

    private void a(boolean z) {
        this.b = z;
        ce.a(new Runnable(this) {
            final /* synthetic */ MapGestureHandlerBase a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        });
    }

    public void a(MapUserInteractionListener mapUserInteractionListener) {
        if (!this.d.contains(mapUserInteractionListener)) {
            this.d.add(mapUserInteractionListener);
        }
    }

    public void b(MapUserInteractionListener mapUserInteractionListener) {
        this.d.remove(mapUserInteractionListener);
    }

    public void cancelKineticPanning() {
    }

    public void m() {
    }

    private static void a(String str) {
        if (!ck.b()) {
            ck.a().a(cj.a(e.e, str), 0.0d, 0.0d, true);
        }
    }
}

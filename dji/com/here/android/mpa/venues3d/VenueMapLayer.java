package com.here.android.mpa.venues3d;

import android.content.Context;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.venues3d.VenueService.InitStatus;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.MapGestureHandler;
import com.nokia.maps.MapGestureHandler.Priority;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.dy;
import com.nokia.maps.fc;
import com.nokia.maps.fc$a;
import java.lang.ref.WeakReference;
import java.security.AccessControlException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Internal
public final class VenueMapLayer extends BaseNativeObject implements Map$OnTransformListener {
    private boolean a;
    private final ExecutorService b;
    private ReadWriteLock c;
    private MapGesture d;
    private Map e;
    private OnGestureListener f;
    private VenueService g;
    private RoutingController h;
    private AnimationController i;
    private volatile boolean j;
    private volatile boolean k;
    private final fc<b> l;
    private final fc<c> m;
    private final ApplicationContext$c n;
    private final ApplicationContext$c o;

    @HybridPlus
    static class a implements OnGestureListener {
        VenueMapLayer a;

        public a(VenueMapLayer venueMapLayer) {
            this.a = venueMapLayer;
        }

        public void onPanStart() {
        }

        public void onPanEnd() {
        }

        public void onMultiFingerManipulationStart() {
        }

        public void onMultiFingerManipulationEnd() {
        }

        public boolean onMapObjectsSelected(List<ViewObject> list) {
            return false;
        }

        public boolean onTapEvent(PointF pointF) {
            if (this.a != null) {
                return this.a.a(pointF);
            }
            return false;
        }

        public boolean onDoubleTapEvent(PointF pointF) {
            return false;
        }

        public void onPinchLocked() {
        }

        public boolean onPinchZoomEvent(float f, PointF pointF) {
            return false;
        }

        public void onRotateLocked() {
        }

        public boolean onRotateEvent(float f) {
            return false;
        }

        public boolean onTiltEvent(float f) {
            this.a.onTiltChanged(f);
            return false;
        }

        public boolean onLongPressEvent(PointF pointF) {
            return false;
        }

        public void onLongPressRelease() {
        }

        public boolean onTwoFingerTapEvent(PointF pointF) {
            return false;
        }
    }

    @HybridPlusNative
    private native void createNative(Map map, VenueService venueService);

    @HybridPlusNative
    private native AnimationController getAnimationControllerNative();

    @HybridPlusNative
    private native RoutingController getRoutingControllerNative();

    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    private native List<VenueInfo> onMapStateChanged(GeoCoordinate geoCoordinate, float f);

    @HybridPlusNative
    private native boolean onTapEventNative(float f, float f2);

    @HybridPlusNative
    private native void openAsyncNative(VenueInfo venueInfo, String str);

    @HybridPlusNative
    private native void setVisibleNative(boolean z);

    @HybridPlusNative
    private native void updateLayerNative(float f, boolean z);

    @HybridPlusNative
    private native void updateMapNative(Map map);

    @HybridPlusNative
    native boolean cancelVenueOpeningNative();

    @HybridPlusNative
    native void closeVenueNative();

    @HybridPlusNative
    native boolean getCheckVenuesInViewportNative();

    @HybridPlusNative
    native VenueController getSelectedVenueNative();

    @HybridPlusNative
    native VenueController getVenueControllerNative(String str);

    @HybridPlusNative
    native boolean getVisibleNative();

    @HybridPlusNative
    native boolean isVenueVisibleNative(String str);

    @HybridPlusNative
    native void onTiltChanged(float f);

    @HybridPlusNative
    native void openVenueNative(VenueController venueController);

    @HybridPlusNative
    native void setCheckVenuesInViewportNative(boolean z);

    public VenueMapLayer(Context context, Map map) {
        this();
        ApplicationContext.b().check(7, this.n);
        ApplicationContext.b().check(41, this.o);
        this.g = VenueService.getInstance(context);
        createNative(map, this.g);
        this.g.a(false);
        a(map);
        this.f = new a(this);
    }

    private VenueMapLayer() {
        this.c = new ReentrantReadWriteLock();
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = new fc();
        this.m = new fc();
        this.n = new ApplicationContext$c(this) {
            final /* synthetic */ VenueMapLayer a;

            {
                this.a = r1;
            }

            public void a() {
                throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
            }

            public void b() {
                this.a.j = true;
            }
        };
        this.o = new ApplicationContext$c(this) {
            final /* synthetic */ VenueMapLayer a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.k = false;
            }

            public void b() {
                this.a.k = true;
            }
        };
        this.b = Executors.newCachedThreadPool();
    }

    @HybridPlusNative
    private VenueMapLayer(int i) {
        this();
        this.nativeptr = i;
    }

    public VenueService a() {
        if (this.j) {
            return this.g;
        }
        return null;
    }

    public AnimationController b() {
        if (!this.j) {
            return null;
        }
        if (this.i == null) {
            this.i = getAnimationControllerNative();
        }
        return this.i;
    }

    public void c() {
        if (this.j && e()) {
            this.g.startAsync();
        }
    }

    public void a(Map map, MapGesture mapGesture) {
        if (this.j) {
            dy.a((Object) map, "Map object supplied is null.");
            Map map2 = this.e;
            a(map);
            a(mapGesture);
            if (map2 != this.e) {
                this.i = null;
                this.b.execute(new Runnable(this) {
                    final /* synthetic */ VenueMapLayer a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.updateMapNative(this.a.e);
                    }
                });
            }
        }
    }

    private void a(Map map) {
        dy.a((Object) map, "Map object supplied is null.");
        this.e = map;
        map.removeTransformListener(this);
        map.addTransformListener(this);
    }

    public void a(MapGesture mapGesture) {
        if (this.j && this.d != mapGesture) {
            if (this.d != null) {
                this.d.removeOnGestureListener(this.f);
            }
            this.d = mapGesture;
            if (mapGesture == null) {
                return;
            }
            if (this.d instanceof MapGestureHandler) {
                ((MapGestureHandler) this.d).a(this.f, Priority.HIGH);
            } else {
                this.d.addOnGestureListener(this.f);
            }
        }
    }

    public void a(b bVar) {
        if (this.j && bVar != null) {
            this.l.b(bVar);
            this.l.a(new WeakReference(bVar));
        }
    }

    public void b(b bVar) {
        if (this.j && bVar != null) {
            this.l.b(bVar);
        }
    }

    public void a(c cVar) {
        if (this.j && cVar != null) {
            this.m.b(cVar);
            this.m.a(new WeakReference(cVar));
        }
    }

    @HybridPlusNative
    private void onVenueTappedSync(final VenueController venueController, final float f, final float f2) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer d;

            public void a(b bVar) {
                bVar.a(venueController, f, f2);
            }
        });
    }

    @HybridPlusNative
    private void onVenueSelectedSync(final VenueController venueController) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer b;

            public void a(b bVar) {
                bVar.a(venueController);
            }
        });
    }

    @HybridPlusNative
    private void onVenueUnselectedSync(final VenueController venueController, int i) {
        final DeselectionSource deselectionSource = DeselectionSource.values()[i];
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer c;

            public void a(b bVar) {
                bVar.a(venueController, deselectionSource);
            }
        });
    }

    @HybridPlusNative
    private void onSpaceSelectedSync(final VenueController venueController, final Space space) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer c;

            public void a(b bVar) {
                bVar.a(venueController, space);
            }
        });
    }

    @HybridPlusNative
    private void onSpaceUnselectedSync(final VenueController venueController, final Space space) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer c;

            public void a(b bVar) {
                bVar.b(venueController, space);
            }
        });
    }

    @HybridPlusNative
    private void onFloorChangedSync(final VenueController venueController, final Level level, final Level level2) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer d;

            public void a(b bVar) {
                bVar.a(venueController, level, level2);
            }
        });
    }

    @HybridPlusNative
    private void onVenueVisibilityInViewportChangedSync(final VenueController venueController, final boolean z) {
        this.l.b(new fc$a<b>(this) {
            final /* synthetic */ VenueMapLayer c;

            public void a(b bVar) {
                bVar.a(venueController, z);
            }
        });
    }

    @HybridPlusNative
    private void onLayerNeedUpdate(float f, boolean z) {
        a(f, z);
    }

    @HybridPlusNative
    private void onInitializationCompleted() {
        onMapStateNeedUpdate();
    }

    @HybridPlusNative
    private void onMapStateNeedUpdate() {
        if (this.e != null) {
            onMapTransformEnd(this.e.getMapState());
        }
    }

    @HybridPlusNative
    private void onVenueZoomLevelChangedSync(final VenueController venueController, final boolean z) {
        this.m.b(new fc$a<c>(this) {
            final /* synthetic */ VenueMapLayer c;

            public void a(c cVar) {
                cVar.a(venueController, z);
            }
        });
    }

    public void onMapTransformStart() {
    }

    public void onMapTransformEnd(final MapState mapState) {
        if (this.j && mapState != null && mapState.getCenter() != null && mapState.getCenter().isValid() && !this.a) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ VenueMapLayer b;

                public void run() {
                    if (!this.b.a && this.b.nativeptr != 0) {
                        List a = this.b.onMapStateChanged(mapState.getCenter(), (float) mapState.getZoomLevel());
                        if (a != null && a.size() > 0) {
                            this.b.g.getVenuesAsync(a, mapState.getCenter());
                        }
                    }
                }
            });
        }
    }

    private void a(final float f, final boolean z) {
        if (!this.a) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ VenueMapLayer c;

                public void run() {
                    this.c.updateLayerNative(f, z);
                }
            });
        }
    }

    private boolean a(PointF pointF) {
        if (this.a) {
            return false;
        }
        return onTapEventNative(pointF.x, pointF.y);
    }

    public void a(final boolean z) {
        if (this.j) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ VenueMapLayer b;

                public void run() {
                    this.b.setVisibleNative(z);
                    InitStatus initStatus = this.b.g.getInitStatus();
                    if (z && initStatus != InitStatus.OFFLINE_SUCCESS && initStatus != InitStatus.ONLINE_SUCCESS) {
                        this.b.g.startAsync();
                    }
                }
            });
        }
    }

    public void d() {
        if (this.j && !this.a) {
            this.a = true;
            setVisibleNative(false);
            try {
                this.b.shutdown();
                this.b.awaitTermination(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.e != null) {
                this.e.removeTransformListener(this);
            }
            if (this.d != null) {
                this.d.removeOnGestureListener(this.f);
            }
            if (this.nativeptr != 0) {
                nativeDispose();
            }
        }
    }

    protected void finalize() throws Throwable {
        d();
        super.finalize();
    }

    public VenueInfo a(String str) {
        return a(str, null);
    }

    public VenueInfo a(String str, final String str2) {
        if (!this.j) {
            return null;
        }
        final VenueInfo venueById = this.g.getVenueById(str);
        if (venueById == null) {
            return venueById;
        }
        this.b.execute(new Runnable(this) {
            final /* synthetic */ VenueMapLayer c;

            public void run() {
                this.c.openAsyncNative(venueById, str2);
            }
        });
        return venueById;
    }

    public boolean e() {
        return getVisibleNative();
    }

    public RoutingController f() {
        if (this.k) {
            if (this.h == null) {
                this.h = getRoutingControllerNative();
            }
            return this.h;
        }
        throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
    }
}

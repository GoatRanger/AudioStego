package com.here.android.mpa.common;

import com.nokia.maps.PositioningManagerImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.lang.ref.WeakReference;

public final class PositioningManager {
    private static volatile PositioningManager a = null;
    private static Object b = new Object();
    private PositioningManagerImpl c;

    @Online
    public enum LocationMethod {
        NONE(0),
        GPS(1),
        NETWORK(2),
        GPS_NETWORK(3),
        GPS_NETWORK_INDOOR(4),
        INDOOR(5);
        
        private final int a;

        private LocationMethod(int i) {
            this.a = i;
        }
    }

    @Online
    public enum LocationStatus {
        OUT_OF_SERVICE,
        TEMPORARILY_UNAVAILABLE,
        AVAILABLE
    }

    @Online
    public interface OnPositionChangedListener {
        void onPositionFixChanged(LocationMethod locationMethod, LocationStatus locationStatus);

        void onPositionUpdated(LocationMethod locationMethod, GeoPosition geoPosition, boolean z);
    }

    private PositioningManager() {
    }

    private PositioningManager(PositioningManagerImpl positioningManagerImpl) {
        this.c = positioningManagerImpl;
    }

    static {
        PositioningManagerImpl.a(new k<PositioningManager, PositioningManagerImpl>() {
            public PositioningManagerImpl a(PositioningManager positioningManager) {
                return positioningManager.c;
            }
        });
    }

    @Online
    public static PositioningManager getInstance() {
        if (a == null) {
            synchronized (b) {
                if (a == null) {
                    a = new PositioningManager(PositioningManagerImpl.a());
                }
            }
        }
        return a;
    }

    @Online
    public boolean start(LocationMethod locationMethod) {
        return this.c.a(locationMethod);
    }

    @Online
    public synchronized boolean setDataSource(LocationDataSource locationDataSource) {
        return this.c.a(locationDataSource);
    }

    @Online
    public synchronized LocationDataSource getDataSource() {
        return this.c.b();
    }

    @Online
    public void stop() {
        this.c.c();
    }

    @Online
    public boolean isActive() {
        return this.c.isActive();
    }

    @Online
    public boolean hasValidPosition() {
        return this.c.d();
    }

    @Online
    public boolean hasValidPosition(LocationMethod locationMethod) {
        return this.c.b(locationMethod);
    }

    @Online
    public double getAverageSpeed() {
        return this.c.getAverageSpeed();
    }

    @Online
    public GeoPosition getPosition() {
        return this.c.e();
    }

    @Online
    public GeoPosition getLastKnownPosition() {
        return this.c.f();
    }

    @Online
    public LocationMethod getLocationMethod() {
        return this.c.h();
    }

    @Online
    public LocationStatus getLocationStatus(LocationMethod locationMethod) {
        return this.c.c(locationMethod);
    }

    @Online
    public void addListener(WeakReference<OnPositionChangedListener> weakReference) {
        this.c.a((WeakReference) weakReference);
    }

    @Online
    public void removeListener(OnPositionChangedListener onPositionChangedListener) {
        this.c.a(onPositionChangedListener);
    }

    @HybridPlus
    public RoadElement getRoadElement() {
        return this.c.g();
    }
}

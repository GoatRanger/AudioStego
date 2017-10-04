package com.nokia.maps;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.LocationDataSource;
import com.here.android.mpa.common.LocationDataSourceAutomotive;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.PositioningManager.LocationStatus;
import com.here.android.mpa.common.PositioningManager.OnPositionChangedListener;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.guidance.NavigationManager;
import com.here.android.mpa.guidance.NavigationManager$NavigationMode;
import com.here.android.mpa.guidance.NavigationManager$NavigationState;
import com.here.android.mpa.guidance.NavigationManager$PositionListener;
import com.nokia.maps.annotation.Online;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

@Online
public class PositioningManagerImpl extends BaseNativeObject implements bh {
    private static final String a = PositioningManagerImpl.class.getSimpleName();
    private static k<PositioningManager, PositioningManagerImpl> b;
    private static volatile PositioningManagerImpl c;
    private static final Object d = new Object();
    private static boolean i = false;
    private fc<OnPositionChangedListener> e;
    private LocationDataSource f;
    private LocationDataSource g;
    private Bundle h;
    private LocationMethod j = LocationMethod.NONE;
    private b k = new b();
    private AtomicBoolean l = new AtomicBoolean(false);
    private boolean m;
    private boolean n;
    private ch o;
    private ch p;

    public enum a {
        MOBILE(0),
        AUTOMOTIVE(1);
        
        private int c;

        private a(int i) {
            this.c = i;
        }
    }

    private class b extends NavigationManager$PositionListener {
        final /* synthetic */ PositioningManagerImpl a;

        private b(PositioningManagerImpl positioningManagerImpl) {
            this.a = positioningManagerImpl;
        }

        public void onPositionUpdated(GeoPosition geoPosition) {
            if (geoPosition != null) {
                bj.e(PositioningManagerImpl.a, geoPosition.toString(), new Object[0]);
                this.a.a(c.NAVIGATION_MANAGER, LocationMethod.GPS, geoPosition);
            }
        }
    }

    private enum c {
        DEVICE,
        NAVIGATION_MANAGER
    }

    private native void createNative();

    private native void destroyNative();

    private native void enableProbeCollection(double d, short s);

    private native int getEnabledCount();

    private native int getMapMatcherModeNative();

    private native int getMapMatcherTypeNative();

    private native RoadElementImpl getRoadElementNative();

    private native boolean hasValidPositionNative();

    private native boolean nativeStart(Object obj);

    private native void nativeStop();

    private native void setInvalidLocationNative(long j);

    private native void setMapMatcherModeNative(int i);

    private native boolean setMapMatcherTypeNative(int i);

    private native void updateLocationNative(int i, double d, double d2, double d3, double d4, float f, float f2, float f3, long j);

    private native void updateLocationNative(AutomotiveInput automotiveInput);

    public native void deleteProbeOfflineCache();

    public native void disableProbeCollection();

    public native double getAverageSpeed();

    public native GeoPositionImpl getDevicePosition();

    public native MatchedGeoPositionImpl getMapMatchedPosition();

    public native boolean getProbeCollectionEnabled();

    public native boolean isActive();

    static PositioningManagerImpl a(PositioningManager positioningManager) {
        if (b != null) {
            return (PositioningManagerImpl) b.a(positioningManager);
        }
        return null;
    }

    public static void a(k<PositioningManager, PositioningManagerImpl> kVar) {
        b = kVar;
    }

    static PositioningManagerImpl a(Context context) {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new PositioningManagerImpl(context);
                }
            }
        }
        return c;
    }

    public static PositioningManagerImpl a() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = a(MapsEngine.e());
                }
            }
        }
        return c;
    }

    private PositioningManagerImpl(final Context context) {
        super(true);
        bj.a(a, "PositioningManagerImpl", new Object[0]);
        this.e = new fc();
        ax.a(new ApplicationContext$c(this) {
            final /* synthetic */ PositioningManagerImpl b;

            public void b() {
                this.b.c(context);
            }

            public void a() {
                this.b.b(context);
            }
        });
    }

    public synchronized boolean a(LocationMethod locationMethod) {
        boolean z = false;
        synchronized (this) {
            if (locationMethod != LocationMethod.NONE) {
                NavigationManagerImpl a = NavigationManagerImpl.a();
                if (a != null) {
                    a.b(new WeakReference(this.k));
                }
                if (this.f.start(locationMethod)) {
                    z = nativeStart(a);
                }
                if (z) {
                    this.j = locationMethod;
                    this.l.set(b(this.f));
                    if (this.l.get() && !ck.b()) {
                        if (!this.m) {
                            this.o = new ch();
                        }
                        if (!this.n) {
                            this.p = new ch();
                        }
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(LocationDataSource locationDataSource) {
        boolean z = true;
        synchronized (this) {
            LocationDataSource locationDataSource2 = locationDataSource == null ? this.g : locationDataSource;
            LocationDataSource locationDataSource3 = this.f;
            if (locationDataSource2 != locationDataSource3) {
                if (b(locationDataSource)) {
                    z = false;
                } else {
                    if (locationDataSource instanceof LocationDataSourceAutomotive) {
                        if (!a(a.AUTOMOTIVE)) {
                            z = false;
                        }
                    } else if (!a(a.MOBILE)) {
                        z = false;
                    }
                    if (this.j != LocationMethod.NONE) {
                        bg.a(locationDataSource3).a(null);
                        this.f = locationDataSource2;
                        bg.a(this.f).a((bh) this);
                        if (this.f.start(this.j)) {
                            locationDataSource3.stop();
                        } else {
                            bg.a(this.f).a(null);
                            this.f.stop();
                            this.f = locationDataSource3;
                            bg.a(this.f).a((bh) this);
                            z = false;
                        }
                    } else {
                        bg.a(locationDataSource3).a(null);
                        this.f = locationDataSource2;
                        bg.a(this.f).a((bh) this);
                    }
                    if (z) {
                        this.l.set(b(this.f));
                    }
                }
            }
        }
        return z;
    }

    public synchronized LocationDataSource b() {
        return b(this.f) ? null : this.f;
    }

    private boolean b(LocationDataSource locationDataSource) {
        return (locationDataSource instanceof dv) || (locationDataSource instanceof ax);
    }

    public synchronized void c() {
        nativeStop();
        bj.b(a, "enabled count=%d", new Object[]{Integer.valueOf(getEnabledCount())});
        if (getEnabledCount() == 0) {
            this.f.stop();
            this.j = LocationMethod.NONE;
        }
    }

    public boolean d() {
        return b(h());
    }

    public boolean b(LocationMethod locationMethod) {
        boolean z = true;
        int gpsStatus = this.f.getGpsStatus();
        int networkStatus = this.f.getNetworkStatus();
        int indoorStatus = this.f.getIndoorStatus();
        if (gpsStatus != 2 && networkStatus != 2 && indoorStatus != 2) {
            return false;
        }
        switch (locationMethod) {
            case GPS:
                if (gpsStatus == 2) {
                    return hasValidPositionNative();
                }
                return false;
            case NETWORK:
                if (networkStatus == 2) {
                    return hasValidPositionNative();
                }
                return false;
            case GPS_NETWORK_INDOOR:
                if (Math.max(Math.max(gpsStatus, networkStatus), indoorStatus) != 2) {
                    z = false;
                }
                return z;
            case INDOOR:
                if (indoorStatus != 2) {
                    z = false;
                }
                return z;
            default:
                return hasValidPositionNative();
        }
    }

    public GeoPosition e() {
        NavigationManager instance = NavigationManager.getInstance();
        if (instance == null || instance.getRunningState() == NavigationManager$NavigationState.IDLE) {
            GeoPositionImpl devicePosition = getDevicePosition();
            devicePosition.a(this.h);
            return GeoPositionImpl.a(devicePosition);
        }
        MatchedGeoPositionImpl mapMatchedPosition = getMapMatchedPosition();
        mapMatchedPosition.a(this.h);
        return MatchedGeoPositionImpl.a(mapMatchedPosition);
    }

    public GeoPosition f() {
        if (this.f.getLastKnownLocation() != null) {
            return GeoPositionImpl.a(new GeoPositionImpl(this.f.getLastKnownLocation()));
        }
        return GeoPositionImpl.a(new GeoPositionImpl());
    }

    public RoadElement g() {
        return RoadElementImpl.a(getRoadElementNative());
    }

    public LocationMethod h() {
        return this.j;
    }

    public void a(WeakReference<OnPositionChangedListener> weakReference) {
        if (weakReference != null) {
            this.e.a(weakReference);
        }
    }

    public void a(OnPositionChangedListener onPositionChangedListener) {
        this.e.b(onPositionChangedListener);
    }

    public LocationStatus c(LocationMethod locationMethod) {
        int ordinal = LocationStatus.OUT_OF_SERVICE.ordinal();
        int gpsStatus = this.f.getGpsStatus();
        int networkStatus = this.f.getNetworkStatus();
        int indoorStatus = this.f.getIndoorStatus();
        if (locationMethod != LocationMethod.GPS) {
            if (locationMethod == LocationMethod.NETWORK) {
                gpsStatus = networkStatus;
            } else if (locationMethod != LocationMethod.GPS_NETWORK) {
                gpsStatus = locationMethod == LocationMethod.GPS_NETWORK_INDOOR ? Math.max(Math.max(gpsStatus, networkStatus), indoorStatus) : locationMethod == LocationMethod.INDOOR ? indoorStatus : ordinal;
            } else if (gpsStatus != networkStatus && gpsStatus <= networkStatus) {
                gpsStatus = networkStatus;
            }
        }
        return LocationStatus.values()[gpsStatus];
    }

    protected void finalize() {
        destroyNative();
    }

    public void a(LocationMethod locationMethod, Location location) {
        if (locationMethod != LocationMethod.NONE) {
            if (location == null) {
                bj.e(a, "Location fix lost", new Object[0]);
                setInvalidLocationNative(System.currentTimeMillis());
                return;
            }
            bj.a(a, "method=%s location coord=(%f, %f), speed=%f, timestamp=%d", new Object[]{locationMethod.toString(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getSpeed()), Long.valueOf(location.getTime())});
            b(locationMethod, location);
            if (location.getLongitude() != Double.MAX_VALUE && location.getLatitude() != Double.MAX_VALUE) {
                a(c.DEVICE, locationMethod, GeoPositionImpl.a(new GeoPositionImpl(location)));
            }
        }
    }

    public void a(final LocationMethod locationMethod, final int i) {
        this.e.b(new fc$a<OnPositionChangedListener>(this) {
            final /* synthetic */ PositioningManagerImpl c;

            public void a(OnPositionChangedListener onPositionChangedListener) {
                onPositionChangedListener.onPositionFixChanged(locationMethod, LocationStatus.values()[i]);
            }
        });
    }

    private void b(LocationMethod locationMethod, Location location) {
        if (locationMethod == LocationMethod.NONE) {
            bj.b(a, "Provider %s not recognized.", new Object[]{locationMethod.toString()});
            return;
        }
        NavigationManager instance = NavigationManager.getInstance();
        if (instance != null && instance.getRunningState() == NavigationManager$NavigationState.RUNNING && instance.getNavigationMode() == NavigationManager$NavigationMode.SIMULATION) {
            bj.b(a, "Navigation Simulation is running, ignoring real device location.", new Object[0]);
            return;
        }
        bj.a(a, "Sending location update to native layer - Provider=%s, coord=(%f, %f), speed=%f", new Object[]{location.getProvider(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Float.valueOf(location.getSpeed())});
        if (this.f instanceof LocationDataSourceAutomotive) {
            AutomotiveInput automotiveInput = new AutomotiveInput();
            automotiveInput.setPosition(locationMethod.ordinal(), location.getLatitude(), location.getLongitude(), location.getAltitude(), (double) (location.hasSpeed() ? location.getSpeed() : 1.07374182E9f), location.hasBearing() ? location.getBearing() : 1.07374182E9f, location.hasAccuracy() ? location.getAccuracy() : 1.07374182E9f, location.hasAccuracy() ? location.getAccuracy() : 1.07374182E9f, location.getTime());
            automotiveInput.setHorizontalLargeStandardDeviation(((LocationDataSourceAutomotive) this.f).getHorizontalLargeStandardDeviation());
            automotiveInput.setHorizontalSmallStandardDeviation(((LocationDataSourceAutomotive) this.f).getHorizontalSmallStandardDeviation());
            automotiveInput.setCourseStandardDeviation(((LocationDataSourceAutomotive) this.f).getCourseStandardDeviation());
            automotiveInput.setElevationStandardDeviation(((LocationDataSourceAutomotive) this.f).getElevationStandardDeviationn());
            automotiveInput.setSpeedStandardDeviation(((LocationDataSourceAutomotive) this.f).getSpeedStandardDeviation());
            updateLocationNative(automotiveInput);
        } else {
            updateLocationNative(locationMethod.ordinal(), location.getLatitude(), location.getLongitude(), location.getAltitude(), (double) (location.hasSpeed() ? location.getSpeed() : 1.07374182E9f), location.hasBearing() ? location.getBearing() : 1.07374182E9f, location.hasAccuracy() ? location.getAccuracy() : 1.07374182E9f, location.hasAccuracy() ? location.getAccuracy() : 1.07374182E9f, location.getTime());
        }
        this.h = location.getExtras();
    }

    private void a(c cVar, LocationMethod locationMethod, GeoPosition geoPosition) {
        boolean z;
        if (this.l.get() && !ck.b()) {
            boolean z2;
            ch chVar = null;
            ch chVar2;
            ch chVar3;
            if (locationMethod == LocationMethod.GPS) {
                chVar2 = this.o;
                this.o = new ch();
                z = !this.m;
                this.m = true;
                chVar3 = chVar2;
                z2 = z;
                chVar = chVar3;
            } else if (locationMethod == LocationMethod.NETWORK) {
                chVar2 = this.p;
                this.p = new ch();
                if (this.n) {
                    z = false;
                } else {
                    z = true;
                }
                this.n = true;
                chVar3 = chVar2;
                z2 = z;
                chVar = chVar3;
            } else {
                z2 = false;
            }
            if (chVar != null) {
                chVar.a(cj.a("position", cj.a(locationMethod, geoPosition, z2)), (double) geoPosition.getLongitudeAccuracy(), geoPosition.isValid());
            }
        }
        if (geoPosition.getCoordinate().isValid()) {
            boolean z3;
            bj.a(a, "IN - src=%s method=%s coord=(%f, %f), speed=%f", new Object[]{cVar.toString(), locationMethod.toString(), Double.valueOf(geoPosition.getCoordinate().getLatitude()), Double.valueOf(geoPosition.getCoordinate().getLongitude()), Double.valueOf(geoPosition.getSpeed())});
            NavigationManager instance = NavigationManager.getInstance();
            z = instance != null && instance.getRunningState() == NavigationManager$NavigationState.RUNNING;
            if (cVar == c.NAVIGATION_MANAGER && z) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (cVar == c.DEVICE && z) {
                bj.a(a, "OUT - Navigation in progress. Do nothing.", new Object[0]);
                return;
            }
            bj.a(a, "listener count=%d", new Object[]{Integer.valueOf(this.e.a())});
            final c cVar2 = cVar;
            final LocationMethod locationMethod2 = locationMethod;
            final GeoPosition geoPosition2 = geoPosition;
            this.e.b(new fc$a<OnPositionChangedListener>(this) {
                final /* synthetic */ PositioningManagerImpl e;

                public void a(OnPositionChangedListener onPositionChangedListener) {
                    bj.a(PositioningManagerImpl.a, "listener.onPositionUpdated - src=%s method=%s coord=(%f, %f), speed=%f, isMatched=%b", new Object[]{cVar2.toString(), locationMethod2.toString(), Double.valueOf(geoPosition2.getCoordinate().getLatitude()), Double.valueOf(geoPosition2.getCoordinate().getLongitude()), Double.valueOf(geoPosition2.getSpeed()), Boolean.valueOf(z3)});
                    onPositionChangedListener.onPositionUpdated(locationMethod2, geoPosition2, z3);
                }
            });
            bj.a(a, "OUT", new Object[0]);
            return;
        }
        bj.a(a, "OUT - INVALID COORDINATE.", new Object[0]);
    }

    public boolean a(a aVar) {
        return setMapMatcherTypeNative(aVar.ordinal());
    }

    public int i() {
        return getMapMatcherTypeNative();
    }

    private void b(Context context) {
        bj.a(a, "using: PlatformLocation", new Object[0]);
        this.g = new dv(context);
        this.f = this.g;
        bg.a(this.f).a((bh) this);
        a(false);
    }

    private void c(Context context) {
        bj.a(a, "using: HereLocation", new Object[0]);
        try {
            this.g = new ax(context);
            this.f = this.g;
            bg.a(this.f).a((bh) this);
            a(true);
        } catch (com.nokia.maps.ax.b e) {
            bj.c(a, "HereLocation: error: '%s', falling back to platform location", new Object[]{e.getMessage()});
            b(context);
        }
    }

    void a(boolean z) {
        i = z;
        createNative();
    }
}

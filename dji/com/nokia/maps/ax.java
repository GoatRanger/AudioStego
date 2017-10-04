package com.nokia.maps;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.here.android.mpa.common.LocationDataSource;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.common.RoadElement.Attribute;
import com.here.c.a.a.c;
import com.here.c.a.a.d;
import com.here.c.a.a.e;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.services.HereLocationApiClient.Reason;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import com.here.services.location.LocationListener;
import com.here.services.location.OptionsChangedEvent;
import com.here.services.location.hybrid.HybridLocationApi.GnssOptions;
import com.here.services.location.hybrid.HybridLocationApi.HighAccuracyOptions;
import com.here.services.location.hybrid.HybridLocationApi.NetworkLocationOptions;
import com.here.services.location.hybrid.HybridLocationApi.Options;
import com.here.services.location.util.LocationHelper;
import com.here.services.location.util.OptionsChangeHelper;
import com.here.services.location.util.OptionsChangeHelper.Callbacks;
import com.here.services.util.HereServicesUtil;
import dji.pilot.usercenter.mode.n;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ax extends LocationDataSource implements d, LocationListener, Callbacks {
    private static final String a = ax.class.getSimpleName();
    private static final long b = TimeUnit.MINUTES.toMillis(5);
    private static final long c = TimeUnit.SECONDS.toMillis(6);
    private static com.here.c.a.a n;
    private final Context d;
    private Location e;
    private int f;
    private int g;
    private int h;
    private final HandlerThread i = new HandlerThread("HereLocationTimedCallbacks");
    private final Handler j;
    private com.here.c.a.a.a k;
    private a l;
    private PositioningManager m;
    private final android.location.LocationListener o = new android.location.LocationListener(this) {
        final /* synthetic */ ax a;

        {
            this.a = r1;
        }

        public void onLocationChanged(Location location) {
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
            bj.e(ax.a, "provider: %s", new Object[]{str});
            if ("gps".equals(str)) {
                this.a.a(LocationMethod.GPS, 1);
            } else if ("network".equals(str)) {
                this.a.a(LocationMethod.NETWORK, 1);
                this.a.a(LocationMethod.INDOOR, 1);
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            bj.e(ax.a, "provider: %s status: %d", new Object[]{str, Integer.valueOf(i)});
            if ("gps".equals(str)) {
                this.a.a(LocationMethod.GPS, i);
            }
        }
    };
    private final Runnable p = new Runnable(this) {
        final /* synthetic */ ax a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.j();
        }
    };
    private final Runnable q = new Runnable(this) {
        final /* synthetic */ ax a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.k();
        }
    };
    private final Runnable r = new Runnable(this) {
        final /* synthetic */ ax a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.l();
        }
    };

    private interface a {
        void a();

        void a(LocationMethod locationMethod);

        void b();
    }

    public static class b extends RuntimeException {
    }

    public ax(Context context) {
        this.i.start();
        this.d = context;
        if (Looper.myLooper() == null) {
            bj.a(a, "myLooper is null, preparing", new Object[0]);
            Looper.prepare();
        }
        this.j = new Handler(this.i.getLooper());
        g();
    }

    public synchronized boolean start(final LocationMethod locationMethod) {
        boolean z = false;
        synchronized (this) {
            bj.a(a, "method: %s", new Object[]{locationMethod});
            final com.here.c.a.a a = com.here.c.a.a.a(this.d);
            if (a == null) {
                bj.c(a, "HERE positioning services is null", new Object[0]);
            } else {
                synchronized (this) {
                    if (this.l != null) {
                        bj.a(a, "Already waiting for positioning services connection.", new Object[0]);
                        this.l.a(locationMethod);
                        z = true;
                    } else if (a.b()) {
                        bj.a(a, "HERE positioning services are connecting", new Object[0]);
                        this.l = new a(this) {
                            LocationMethod a = locationMethod;
                            final /* synthetic */ ax d;

                            public void a() {
                                bj.a(ax.a, "DelayedStart.onError", new Object[0]);
                                c();
                            }

                            public void b() {
                                c();
                                boolean a = this.d.a(a, this.a);
                                bj.a(ax.a, "DelayedStart.onConnected: success: %s", new Object[]{Boolean.valueOf(a)});
                            }

                            public void a(LocationMethod locationMethod) {
                                bj.a(ax.a, "DelayedStart.updateLocationMethod: method: %s", new Object[]{locationMethod});
                                this.a = locationMethod;
                            }

                            private void c() {
                                a.b(this.d);
                                synchronized (this.d) {
                                    this.d.l = null;
                                }
                            }
                        };
                        a.a((d) this);
                        z = true;
                    } else {
                        z = a(a, locationMethod);
                    }
                }
            }
        }
        return z;
    }

    public synchronized void stop() {
        if (this.l != null) {
            this.l.a();
        }
        if (this.k != null) {
            i();
            f();
            if (d()) {
                this.k.a(this);
                this.k = null;
            } else {
                bj.c(a, "error: location API not available", new Object[0]);
            }
        }
    }

    public int getGpsStatus() {
        return this.f;
    }

    public int getNetworkStatus() {
        return this.g;
    }

    public int getIndoorStatus() {
        return this.h;
    }

    public Location getLastKnownLocation() {
        if (this.e != null) {
            return this.e;
        }
        synchronized (this) {
            if (d()) {
                Location a = this.k.a();
                return a;
            }
            return null;
        }
    }

    public void onAirplaneModeEnabled() {
    }

    public void onBluetoothLEDisabled() {
    }

    public void onCellDisabled() {
    }

    public void onGnssLocationDisabled() {
        a(LocationMethod.GPS, 0);
    }

    public void onNetworkLocationDisabled() {
        a(LocationMethod.NETWORK, 0);
        a(LocationMethod.INDOOR, 0);
    }

    public void onWifiScansDisabled() {
    }

    public void onLocationChanged(Location location) {
        final LocationMethod a = a(location);
        if (a == LocationMethod.NONE) {
            bj.b(a, "Location provider not recognized.", new Object[0]);
        } else if (b(location)) {
            a(a, 2);
            this.e = location;
            bj.a(a, "Sending location update Method=%s Coord=(%.10f, %.10f) TS=%d Speed=%.2f Provider=%s", new Object[]{a.toString(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Long.valueOf(location.getTime()), Float.valueOf(location.getSpeed()), location.getProvider()});
            this.j.post(new Runnable(this) {
                final /* synthetic */ ax b;

                public void run() {
                    this.b.b(a);
                }
            });
            onLocationUpdated(a, location);
        } else {
            bj.e(a, "Location is filtered out - ignore update", new Object[0]);
        }
    }

    public void onOptionsChanged(OptionsChangedEvent optionsChangedEvent) {
        OptionsChangeHelper.onOptionsChanged(this.d, this, optionsChangedEvent);
    }

    protected void finalize() {
        stop();
        this.i.quit();
    }

    private boolean d() {
        boolean z;
        com.here.c.a.a a = com.here.c.a.a.a(this.d);
        synchronized (this) {
            z = (this.k == null || a == null || !a.a()) ? false : true;
        }
        return z;
    }

    private LocationMethod a(Location location) {
        EnumSet sources = LocationHelper.getSources(location);
        if (sources == null || sources.isEmpty()) {
            return LocationMethod.NONE;
        }
        if (sources.contains(Source.Hardware)) {
            return LocationMethod.GPS;
        }
        if (sources.contains(Source.HighAccuracy)) {
            return LocationMethod.INDOOR;
        }
        if (sources.contains(Source.Offline) || sources.contains(Source.Online) || sources.contains(Source.Cache)) {
            return LocationMethod.NETWORK;
        }
        if (!sources.contains(Source.Fusion)) {
            return LocationMethod.NONE;
        }
        sources = LocationHelper.getTechnologies(location);
        if (sources.size() == 1 && sources.contains(Technology.Gnss)) {
            return LocationMethod.GPS;
        }
        return LocationMethod.NETWORK;
    }

    private Options a(LocationMethod locationMethod) {
        Options options = new Options();
        switch (locationMethod) {
            case INDOOR:
                if (e()) {
                    options.setGnnsOptions(new GnssOptions().setEnabled(false)).setNetworkLocationOptions(new NetworkLocationOptions().setEnabled(false));
                    break;
                }
                return null;
            case GPS_NETWORK_INDOOR:
                break;
            case GPS_NETWORK:
                options.setHighAccuracyOptions(new HighAccuracyOptions().setEnabled(false));
                break;
            case GPS:
                options.setHighAccuracyOptions(new HighAccuracyOptions().setEnabled(false)).setNetworkLocationOptions(new NetworkLocationOptions().setEnabled(false));
                break;
            case NETWORK:
                options.setHighAccuracyOptions(new HighAccuracyOptions().setEnabled(false)).setGnnsOptions(new GnssOptions().setEnabled(false));
                break;
            default:
                return null;
        }
        return options;
    }

    private boolean e() {
        return com.here.c.a.a.e();
    }

    static void a(ApplicationContext$c applicationContext$c) {
        if (applicationContext$c == null) {
            throw new IllegalArgumentException("c is null");
        }
        p();
        c cVar = new c();
        if ((cVar.a(39) | (((0 | cVar.a(35)) | cVar.a(37)) | cVar.a(38))) != 0) {
            applicationContext$c.b();
        } else {
            applicationContext$c.a();
        }
    }

    private void a(LocationMethod locationMethod, int i) {
        int i2;
        if (locationMethod == LocationMethod.GPS && this.f != i) {
            this.f = i;
            i2 = 1;
        } else if (locationMethod == LocationMethod.NETWORK && this.g != i) {
            this.g = i;
            i2 = 1;
        } else if (locationMethod != LocationMethod.INDOOR || this.h == i) {
            i2 = 0;
        } else {
            this.h = i;
            i2 = 1;
        }
        if (i2 != 0) {
            bj.e(a, "%s = %d", new Object[]{locationMethod, Integer.valueOf(i)});
            onStatusUpdated(locationMethod, i);
        }
    }

    private void f() {
        c(LocationMethod.INDOOR);
        c(LocationMethod.NETWORK);
        if (HereServicesUtil.isNetworkLocationEnabled(this.d)) {
            this.g = 1;
            if (e()) {
                this.h = 1;
            } else {
                this.h = 0;
            }
        } else {
            this.g = 0;
            this.h = 0;
        }
        c(LocationMethod.GPS);
        if (HereServicesUtil.hasGps(this.d) && HereServicesUtil.isGpsLocationEnabled(this.d)) {
            this.f = 1;
        } else {
            this.f = 0;
        }
    }

    private void g() {
        LocationManager locationManager = (LocationManager) this.d.getSystemService(n.C);
        if (locationManager != null) {
            List providers = locationManager.getProviders(true);
            this.f = 0;
            if (providers != null && providers.contains("gps")) {
                a(LocationMethod.GPS, 1);
            }
            this.g = 0;
            this.h = 0;
            if (providers != null && providers.contains("network")) {
                a(LocationMethod.NETWORK, 1);
                if (e()) {
                    a(LocationMethod.INDOOR, 1);
                }
            }
        }
    }

    private void h() {
        LocationManager locationManager = (LocationManager) this.d.getSystemService(n.C);
        if (locationManager != null) {
            locationManager.requestLocationUpdates("passive", 0, 0.0f, this.o);
        }
    }

    private void i() {
        LocationManager locationManager = (LocationManager) this.d.getSystemService(n.C);
        if (locationManager != null) {
            locationManager.removeUpdates(this.o);
        }
    }

    private void b(LocationMethod locationMethod) {
        c(locationMethod);
        if (locationMethod == LocationMethod.INDOOR) {
            c(LocationMethod.GPS);
        } else if (locationMethod == LocationMethod.GPS) {
            c(LocationMethod.INDOOR);
        }
        if (locationMethod == LocationMethod.GPS) {
            this.j.postDelayed(this.p, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        } else if (locationMethod == LocationMethod.NETWORK) {
            this.j.postDelayed(this.q, b);
        } else if (locationMethod == LocationMethod.INDOOR) {
            this.j.postDelayed(this.r, c);
        } else {
            bj.b(a, "unsupported method: %s", new Object[]{locationMethod});
        }
    }

    private void c(LocationMethod locationMethod) {
        if (locationMethod == LocationMethod.GPS) {
            this.j.removeCallbacks(this.p);
        } else if (locationMethod == LocationMethod.NETWORK) {
            this.j.removeCallbacks(this.q);
        } else if (locationMethod == LocationMethod.INDOOR) {
            this.j.removeCallbacks(this.r);
        } else {
            bj.b(a, "unsupported method: %s", new Object[]{locationMethod});
        }
    }

    private void j() {
        this.j.postDelayed(this.p, 1500);
        onLocationUpdated(LocationMethod.GPS, null);
        if (getGpsStatus() != 0) {
            a(LocationMethod.GPS, 1);
        }
    }

    private void k() {
        if (getNetworkStatus() != 0) {
            a(LocationMethod.NETWORK, 1);
        }
    }

    private void l() {
        if (getIndoorStatus() != 0) {
            a(LocationMethod.INDOOR, 1);
        }
    }

    private void m() {
        try {
            MapsEngine.c().b(n);
        } catch (Exception e) {
        }
        n = null;
        o();
    }

    private void n() {
    }

    private void o() {
        bj.a(a, "", new Object[0]);
        a(LocationMethod.GPS, 0);
        a(LocationMethod.INDOOR, 0);
        a(LocationMethod.NETWORK, 0);
    }

    private synchronized boolean a(com.here.c.a.a aVar, LocationMethod locationMethod) {
        boolean z = false;
        synchronized (this) {
            if (this.k == null) {
                this.k = aVar.c(new d(this) {
                    final /* synthetic */ ax a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.n();
                    }

                    public void b() {
                        this.a.m();
                    }

                    public void a(Reason reason) {
                        this.a.m();
                    }
                });
                if (this.k == null) {
                    bj.c(a, "could not create HybridLocationApi", new Object[0]);
                }
            }
            try {
                if (this.k.a(this)) {
                    i();
                    Options a = a(locationMethod);
                    if (a == null) {
                        bj.c(a, "(%s): error: location options is null: ", new Object[]{locationMethod});
                    } else {
                        bj.a(a, "(%s)", new Object[]{locationMethod});
                        if (this.k.a(a, this)) {
                            h();
                            z = true;
                        }
                    }
                } else {
                    bj.c(a, "(%s): error: could not stop location updates: ", new Object[]{locationMethod});
                }
            } finally {
                i();
            }
        }
        return z;
    }

    public synchronized void a() {
        if (this.l != null) {
            this.l.b();
        }
    }

    public synchronized void a(Reason reason) {
        if (this.l != null) {
            this.l.a();
        }
    }

    public synchronized void b() {
        if (this.l != null) {
            this.l.a();
        }
    }

    private static void p() {
        if (com.here.c.a.a.d()) {
            try {
                final MapsEngine c = MapsEngine.c();
                n = com.here.c.a.a.a(MapsEngine.e());
                if (n != null) {
                    c.a(n);
                    if (!n.a()) {
                        n.a(new e() {
                            public boolean b() {
                                return c.B();
                            }

                            public String a() {
                                return ApplicationContext.c();
                            }
                        });
                    }
                }
            } catch (Exception e) {
                bj.c(a, "MapsEngine instance not available", new Object[]{e});
                n = null;
            }
        }
    }

    private boolean q() {
        if (this.m == null) {
            this.m = PositioningManager.getInstance();
        }
        RoadElement roadElement = this.m.getRoadElement();
        if (roadElement == null || !roadElement.getAttributes().contains(Attribute.TUNNEL)) {
            return false;
        }
        return true;
    }

    private boolean b(Location location) {
        if (location == null || (q() && location.getAccuracy() > 25.0f)) {
            return false;
        }
        return true;
    }
}

package com.nokia.maps;

import android.content.Context;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.HandlerThread;
import com.here.android.mpa.common.LocationDataSource;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.common.RoadElement.Attribute;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot.usercenter.mode.n;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class dv extends LocationDataSource {
    private static final String a = dv.class.getSimpleName();
    private a b;
    private c c;
    private HandlerThread d = new HandlerThread("LocationUpdates");
    private PositioningManager e = null;
    private LocationManager f;
    private AtomicInteger g;
    private AtomicInteger h;
    private Timer i;
    private Timer j;
    private Location k;
    private Location l;

    private class a implements Listener, LocationListener {
        final /* synthetic */ dv a;

        private a(dv dvVar) {
            this.a = dvVar;
        }

        public void onGpsStatusChanged(int i) {
            this.a.a(i);
        }

        public void onLocationChanged(Location location) {
            this.a.a(new Location(location));
        }

        public void onProviderDisabled(String str) {
            this.a.b(str);
        }

        public void onProviderEnabled(String str) {
            this.a.a(str);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            this.a.a(str, i, new Bundle(bundle));
        }
    }

    private class b extends TimerTask {
        final /* synthetic */ dv a;
        private long b = 0;

        public b(dv dvVar, long j) {
            this.a = dvVar;
            this.b = j;
        }

        public void run() {
            if (this.a.k != null && this.b < this.a.k.getTime()) {
                bj.b(dv.a, "New position update with timestamp(%d) has been sent since the timer was triggered, no need to send fix lost signal now...", new Object[]{Long.valueOf(this.a.k.getTime())});
            } else if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                this.a.a(this.b);
            } else {
                ez.a(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a(this.a.b);
                    }
                });
            }
        }
    }

    private class c implements LocationListener {
        final /* synthetic */ dv a;

        private c(dv dvVar) {
            this.a = dvVar;
        }

        public void onLocationChanged(Location location) {
            this.a.a(new Location(location));
        }

        public void onProviderDisabled(String str) {
            this.a.b(str);
        }

        public void onProviderEnabled(String str) {
            this.a.a(str);
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            this.a.a(str, i, new Bundle(bundle));
        }
    }

    private class d extends TimerTask {
        final /* synthetic */ dv a;
        private Location b;

        private d(dv dvVar) {
            this.a = dvVar;
            this.b = null;
        }

        public void run() {
            synchronized (this.a) {
                if (this.b == this.a.l) {
                    this.a.j.cancel();
                    this.a.j = null;
                    if (this.b != null) {
                        if (MapSettings.k() == MapSettings$b.EWorkerThread) {
                            this.a.g();
                        } else {
                            ez.a(new Runnable(this) {
                                final /* synthetic */ d a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.g();
                                }
                            });
                        }
                    }
                }
                this.b = this.a.l;
            }
        }
    }

    public dv(Context context) {
        bj.e(a, "PlatformLocation", new Object[0]);
        this.b = new a();
        this.c = new c();
        this.f = (LocationManager) context.getSystemService(n.C);
        h();
        this.k = null;
        this.l = null;
        this.d.start();
    }

    private boolean b() {
        if (this.e == null) {
            this.e = PositioningManager.getInstance();
        }
        RoadElement roadElement = this.e.getRoadElement();
        if (roadElement == null || !roadElement.getAttributes().contains(Attribute.TUNNEL)) {
            return false;
        }
        return true;
    }

    private boolean b(Location location) {
        if (!b() || location.getAccuracy() <= 25.0f) {
            return true;
        }
        return false;
    }

    public boolean start(LocationMethod locationMethod) {
        bj.e(a, "start - enabled providers=" + this.f.getProviders(true), new Object[0]);
        switch (locationMethod) {
            case GPS_NETWORK:
            case GPS_NETWORK_INDOOR:
                f();
                e();
                break;
            case GPS:
                e();
                d();
                break;
            case NETWORK:
                f();
                c();
                break;
            default:
                return false;
        }
        return true;
    }

    public void stop() {
        bj.e(a, "stop", new Object[0]);
        i();
        c();
        d();
    }

    private void c() {
        bj.e(a, "stopSat", new Object[0]);
        this.f.removeGpsStatusListener(this.b);
        this.f.removeUpdates(this.b);
    }

    private void d() {
        bj.e(a, "stopNet", new Object[0]);
        this.f.removeUpdates(this.c);
    }

    public int getGpsStatus() {
        return this.g.get();
    }

    public int getNetworkStatus() {
        return this.h.get();
    }

    public int getIndoorStatus() {
        return 0;
    }

    public Location getLastKnownLocation() {
        Location lastKnownLocation = this.f.getLastKnownLocation("gps");
        Location lastKnownLocation2 = this.f.getLastKnownLocation("network");
        if (lastKnownLocation == null) {
            return lastKnownLocation2;
        }
        if (lastKnownLocation2 == null) {
            return lastKnownLocation;
        }
        long time = lastKnownLocation.getTime();
        long time2 = lastKnownLocation2.getTime();
        Object obj = time2 - time > TimeUnit.MINUTES.toMillis(1) ? 1 : null;
        if ((time - time2 > TimeUnit.MINUTES.toMillis(1) ? 1 : null) != null) {
            return lastKnownLocation;
        }
        if (obj != null) {
            return lastKnownLocation2;
        }
        obj = lastKnownLocation.hasAccuracy() ? lastKnownLocation.getAccuracy() - lastKnownLocation2.getAccuracy() > 0.0f ? 1 : null : 1;
        if (obj != null) {
            return lastKnownLocation2;
        }
        return lastKnownLocation;
    }

    public void a(Location location) {
        bj.a(a, "[%d] location coord=(%.10f, %.10f) Provider=%s TS=%d delta=%d", new Object[]{Long.valueOf(r0), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), location.getProvider(), Long.valueOf(location.getTime()), Long.valueOf(System.currentTimeMillis() - location.getTime())});
        if (b(location)) {
            LocationMethod c = c(location.getProvider());
            a(c);
            if (c == LocationMethod.NONE) {
                bj.b(a, "Provider %s not recognized.", new Object[]{c.toString()});
                return;
            }
            if (b(c) != 2) {
                a(c, 2);
            }
            location.setTime(r0);
            a(c, location);
            if (c == j()) {
                bj.a(a, "Sending location update Method=%s Coord=(%.10f, %.10f) TS=%d Speed=%.2f Provider=%s", new Object[]{c.toString(), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Long.valueOf(location.getTime()), Float.valueOf(location.getSpeed()), location.getProvider()});
                onLocationUpdated(c, location);
                return;
            }
            return;
        }
        bj.e(a, "Location is filtered out - ignore update", new Object[0]);
    }

    private void a(String str) {
        bj.d(a, "provider=%s", new Object[]{str});
        LocationMethod c = c(str);
        if (b(c) != 1) {
            a(c, 1);
        }
    }

    private void b(String str) {
        bj.b(a, "provider=%s", new Object[]{str});
        LocationMethod c = c(str);
        if (b(c) != 0) {
            a(c);
            a(c, 0);
        }
    }

    private void a(String str, int i, Bundle bundle) {
        bj.b(a, "provider=%s status=%d", new Object[]{str, Integer.valueOf(i)});
        LocationMethod c = c(str);
        if (b(c) != i) {
            a(c, i);
        }
    }

    private void a(int i) {
        switch (i) {
            case 1:
                bj.e(a, "GPS_EVENT_STARTED", new Object[0]);
                return;
            case 2:
                bj.e(a, "GPS_EVENT_STOPPED", new Object[0]);
                a(0);
                return;
            case 3:
                bj.e(a, "GPS_EVENT_FIRST_FIX", new Object[0]);
                return;
            default:
                return;
        }
    }

    protected void finalize() {
        bj.a(a, "...", new Object[0]);
        this.f.removeGpsStatusListener(this.b);
        stop();
    }

    private void e() {
        bj.a(a, "...", new Object[0]);
        if (this.f.getProvider("gps") != null) {
            this.f.addGpsStatusListener(this.b);
            try {
                this.f.requestLocationUpdates("gps", 0, 0.0f, this.b, this.d.getLooper());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void f() {
        bj.a(a, "...", new Object[0]);
        if (this.f.getProvider("network") != null) {
            try {
                this.f.requestLocationUpdates("network", 0, 0.0f, this.c, this.d.getLooper());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void a(LocationMethod locationMethod, long j) {
        bj.a(a, "method=%s lastLocationUpdateTS=%d", new Object[]{locationMethod.toString(), Long.valueOf(j)});
        if (locationMethod == LocationMethod.GPS) {
            if (this.i != null) {
                a(LocationMethod.GPS);
            }
            this.i = new Timer("GpsUpdateTimer");
            this.i.scheduleAtFixedRate(new b(this, j), OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME, 1500);
        } else if (locationMethod == LocationMethod.NETWORK && this.j == null) {
            this.j = new Timer("NetworkUpdateTimer");
            this.j.schedule(new d(), com.alipay.e.a.a.c.a.a.b, com.alipay.e.a.a.c.a.a.b);
        }
    }

    private synchronized void a(LocationMethod locationMethod) {
        bj.e(a, "Cancel outstanding timer for method=%s ...", new Object[]{locationMethod.toString()});
        if (locationMethod == LocationMethod.GPS && this.i != null) {
            this.i.cancel();
            this.i = null;
        } else if (locationMethod == LocationMethod.NETWORK && this.j != null) {
            this.j.cancel();
            this.j = null;
        }
    }

    private void a(long j) {
        if (this.k == null || j >= this.k.getTime()) {
            onLocationUpdated(LocationMethod.GPS, null);
            if (b(LocationMethod.GPS) != 0) {
                a(LocationMethod.GPS, 1);
                return;
            }
            return;
        }
        bj.b(a, "New position update with timestamp(%d) has been sent since the timer was triggered, no need to send fix lost signal now...", new Object[]{Long.valueOf(this.k.getTime())});
    }

    private void g() {
        bj.b(a, "networkFixLost", new Object[0]);
        if (b(LocationMethod.NETWORK) != 0) {
            a(LocationMethod.NETWORK, 1);
        }
    }

    private LocationMethod c(String str) {
        if (str.equals("gps")) {
            return LocationMethod.GPS;
        }
        if (str.equals("network")) {
            return LocationMethod.NETWORK;
        }
        return LocationMethod.NONE;
    }

    private void a(LocationMethod locationMethod, int i) {
        int i2;
        if (locationMethod == LocationMethod.GPS && this.g.get() != i) {
            this.g.set(i);
            i2 = 1;
        } else if (locationMethod != LocationMethod.NETWORK || this.h.get() == i) {
            i2 = 0;
        } else {
            this.h.set(i);
            i2 = 1;
        }
        if (i2 != 0) {
            onStatusUpdated(locationMethod, i);
            bj.e(a, "method=%s newStatus=%d", new Object[]{locationMethod, Integer.valueOf(i)});
        }
    }

    private int b(LocationMethod locationMethod) {
        if (locationMethod == LocationMethod.GPS) {
            return this.g.get();
        }
        if (locationMethod == LocationMethod.NETWORK) {
            return this.h.get();
        }
        return 0;
    }

    private void h() {
        List providers = this.f.getProviders(true);
        this.g = new AtomicInteger(0);
        if (providers != null && providers.contains("gps")) {
            a(LocationMethod.GPS, 1);
        }
        this.h = new AtomicInteger(0);
        if (providers != null && providers.contains("network")) {
            a(LocationMethod.NETWORK, 1);
        }
    }

    private void i() {
        List providers = this.f.getProviders(true);
        a(LocationMethod.GPS);
        if (providers == null || !providers.contains("gps")) {
            this.g.set(0);
        } else {
            this.g.set(1);
        }
        a(LocationMethod.NETWORK);
        this.h = new AtomicInteger(0);
        if (providers == null || !providers.contains("network")) {
            this.h.set(0);
        } else {
            this.h.set(1);
        }
    }

    private LocationMethod j() {
        if (this.g.get() == 2) {
            return LocationMethod.GPS;
        }
        if (this.h.get() == 2) {
            return LocationMethod.NETWORK;
        }
        return LocationMethod.NONE;
    }

    private void a(LocationMethod locationMethod, Location location) {
        bj.a(a, "[%d] Sending location update to native layer - coord=(%f, %f) TS=%d Provider=%s", new Object[]{Long.valueOf(System.currentTimeMillis()), Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Long.valueOf(location.getTime()), location.getProvider()});
        if (locationMethod == LocationMethod.GPS) {
            this.k = location;
        } else if (locationMethod == LocationMethod.NETWORK) {
            this.l = location;
        }
        a(locationMethod, location.getTime());
    }
}

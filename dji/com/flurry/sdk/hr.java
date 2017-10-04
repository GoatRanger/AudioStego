package com.flurry.sdk;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import dji.pilot.usercenter.mode.n;

public class hr implements com.flurry.sdk.jr.a {
    private static hr a;
    private static final String b = hr.class.getSimpleName();
    private final int c = 3;
    private final long d = 10000;
    private final long e = 90000;
    private final long f = 0;
    private boolean g;
    private Location h;
    private long i = 0;
    private LocationManager j = ((LocationManager) hz.a().c().getSystemService(n.C));
    private a k = new a(this);
    private Location l;
    private boolean m = false;
    private int n = 0;
    private ii<jt> o = new ii<jt>(this) {
        final /* synthetic */ hr a;

        {
            this.a = r1;
        }

        public void a(jt jtVar) {
            if (this.a.i > 0 && this.a.i < System.currentTimeMillis()) {
                in.a(4, hr.b, "No location received in 90 seconds , stopping LocationManager");
                this.a.h();
            }
        }
    };

    class a implements LocationListener {
        final /* synthetic */ hr a;

        public a(hr hrVar) {
            this.a = hrVar;
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
        }

        public void onLocationChanged(Location location) {
            if (location != null) {
                this.a.l = location;
            }
            if (hr.c(this.a) >= 3) {
                in.a(4, hr.b, "Max location reports reached, stopping");
                this.a.h();
            }
        }
    }

    static /* synthetic */ int c(hr hrVar) {
        int i = hrVar.n + 1;
        hrVar.n = i;
        return i;
    }

    public static synchronized hr a() {
        hr hrVar;
        synchronized (hr.class) {
            if (a == null) {
                a = new hr();
            }
            hrVar = a;
        }
        return hrVar;
    }

    public static void b() {
        if (a != null) {
            jq.a().b("ReportLocation", a);
            jq.a().b("ExplicitLocation", a);
        }
        a = null;
    }

    private hr() {
        jr a = jq.a();
        this.g = ((Boolean) a.a("ReportLocation")).booleanValue();
        a.a("ReportLocation", (com.flurry.sdk.jr.a) this);
        in.a(4, b, "initSettings, ReportLocation = " + this.g);
        this.h = (Location) a.a("ExplicitLocation");
        a.a("ExplicitLocation", (com.flurry.sdk.jr.a) this);
        in.a(4, b, "initSettings, ExplicitLocation = " + this.h);
    }

    public synchronized void c() {
        in.a(4, b, "Location update requested");
        if (this.n < 3) {
            g();
        }
    }

    public synchronized void d() {
        in.a(4, b, "Stop update location requested");
        h();
    }

    public Location e() {
        Location location = null;
        if (this.h != null) {
            return this.h;
        }
        if (this.g) {
            Context c = hz.a().c();
            if (!a(c) && !b(c)) {
                return null;
            }
            String i = a(c) ? i() : b(c) ? j() : null;
            if (i != null) {
                location = b(i);
                if (location != null) {
                    this.l = location;
                }
                location = this.l;
            }
        }
        in.a(4, b, "getLocation() = " + location);
        return location;
    }

    private void g() {
        if (!this.m && this.g && this.h == null) {
            Context c = hz.a().c();
            if (c.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || c.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.n = 0;
                String str = null;
                if (a(c)) {
                    str = i();
                } else if (b(c)) {
                    str = j();
                }
                a(str);
                this.l = b(str);
                this.i = System.currentTimeMillis() + 90000;
                k();
                this.m = true;
                in.a(4, b, "LocationProvider started");
            }
        }
    }

    private void h() {
        if (this.m) {
            this.j.removeUpdates(this.k);
            this.n = 0;
            this.i = 0;
            l();
            this.m = false;
            in.a(4, b, "LocationProvider stopped");
        }
    }

    private String i() {
        return "passive";
    }

    private String j() {
        return "network";
    }

    private boolean a(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    private boolean b(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.j.requestLocationUpdates(str, 10000, 0.0f, this.k, Looper.getMainLooper());
        }
    }

    private Location b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.j.getLastKnownLocation(str);
    }

    private void k() {
        in.a(4, b, "Register location timer");
        ju.a().a(this.o);
    }

    private void l() {
        in.a(4, b, "Unregister location timer");
        ju.a().b(this.o);
    }

    public void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -864112343:
                if (str.equals("ReportLocation")) {
                    obj2 = null;
                    break;
                }
                break;
            case -300729815:
                if (str.equals("ExplicitLocation")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.g = ((Boolean) obj).booleanValue();
                in.a(4, b, "onSettingUpdate, ReportLocation = " + this.g);
                return;
            case 1:
                this.h = (Location) obj;
                in.a(4, b, "onSettingUpdate, ExplicitLocation = " + this.h);
                return;
            default:
                in.a(6, b, "LocationProvider internal error! Had to be LocationCriteria, ReportLocation or ExplicitLocation key.");
                return;
        }
    }
}

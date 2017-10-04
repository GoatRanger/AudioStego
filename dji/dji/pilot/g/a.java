package dji.pilot.g;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.mode.n;

public class a {
    private static final String b = "LocationManager";
    a[] a = new a[]{new a(this, "gps"), new a(this, "network")};
    private Context c;
    private LocationManager d;
    private boolean e;

    private class a implements LocationListener {
        Location a;
        boolean b = false;
        String c;
        final /* synthetic */ a d;

        public a(a aVar, String str) {
            this.d = aVar;
            this.c = str;
            this.a = new Location(this.c);
        }

        public void onLocationChanged(Location location) {
            if (location.getLatitude() != 0.0d || location.getLongitude() != 0.0d) {
                if (!this.b) {
                    Log.d(a.b, "Got first location." + location);
                    DJILogHelper.getInstance().LOGD(a.b, "Got first location." + location, false, true);
                }
                this.a.set(location);
                this.b = true;
            }
        }

        public void onProviderEnabled(String str) {
        }

        public void onProviderDisabled(String str) {
            this.b = false;
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            switch (i) {
                case 0:
                case 1:
                    this.b = false;
                    return;
                default:
                    return;
            }
        }

        public Location a() {
            DJILogHelper.getInstance().LOGD(a.b, "Got first location." + this.b, false, true);
            return this.b ? this.a : null;
        }
    }

    public a(Context context) {
        this.c = context;
    }

    public Location a() {
        if (!this.e) {
            return null;
        }
        for (a a : this.a) {
            Location a2 = a.a();
            if (a2 != null) {
                return a2;
            }
        }
        Log.d(b, "No location received yet.");
        return null;
    }

    public void a(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (z) {
                c();
            } else {
                d();
            }
        }
    }

    private void c() {
        if (this.d == null) {
            this.d = (LocationManager) this.c.getSystemService(n.C);
        }
        if (this.d != null) {
            try {
                this.d.requestLocationUpdates("network", 1000, 0.0f, this.a[1], Looper.getMainLooper());
            } catch (Throwable e) {
                Log.i(b, "fail to request location update, ignore", e);
            } catch (IllegalArgumentException e2) {
                Log.d(b, "provider does not exist " + e2.getMessage());
            }
            try {
                this.d.requestLocationUpdates("gps", 1000, 0.0f, this.a[0], Looper.getMainLooper());
            } catch (Throwable e3) {
                Log.i(b, "fail to request location update, ignore", e3);
            } catch (IllegalArgumentException e22) {
                Log.d(b, "provider does not exist " + e22.getMessage());
            }
            Log.d(b, "startReceivingLocationUpdates");
        }
    }

    private void d() {
        if (this.d != null) {
            int i = 0;
            while (i < this.a.length) {
                try {
                    if (VERSION.SDK_INT < 23 || this.c.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 || this.c.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
                        this.d.removeUpdates(this.a[i]);
                        i++;
                    } else {
                        return;
                    }
                } catch (Throwable e) {
                    Log.i(b, "fail to remove location listners, ignore", e);
                }
            }
            Log.d(b, "stopReceivingLocationUpdates");
        }
    }

    public void b() {
        this.c = null;
    }
}

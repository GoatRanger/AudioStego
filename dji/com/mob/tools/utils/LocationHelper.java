package com.mob.tools.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.mob.tools.MobHandlerThread;
import dji.pilot.usercenter.mode.n;

public class LocationHelper implements LocationListener, Callback {
    private int GPSTimeoutSec;
    private boolean gpsRequesting;
    private Handler handler;
    private LocationManager lm;
    private boolean networkRequesting;
    private int networkTimeoutSec;
    private Location res;

    public LocationHelper() {
        MobHandlerThread mobHandlerThread = new MobHandlerThread();
        mobHandlerThread.start();
        this.handler = new Handler(mobHandlerThread.getLooper(), this);
    }

    public Location getLocation(Context context) throws Throwable {
        return getLocation(context, 0);
    }

    public Location getLocation(Context context, int i) throws Throwable {
        return getLocation(context, i, 0);
    }

    public Location getLocation(Context context, int i, int i2) throws Throwable {
        return getLocation(context, i, i2, true);
    }

    public Location getLocation(Context context, int i, int i2, boolean z) throws Throwable {
        this.GPSTimeoutSec = i;
        this.networkTimeoutSec = i2;
        this.lm = (LocationManager) context.getSystemService(n.C);
        if (this.lm == null) {
            return null;
        }
        synchronized (this) {
            this.handler.sendEmptyMessageDelayed(0, 50);
            wait();
        }
        if (this.res == null && z) {
            Object obj;
            Object obj2;
            if (i != 0) {
                obj = 1;
            } else {
                obj = null;
            }
            if (i2 != 0) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            if (obj != null && this.lm.isProviderEnabled("gps")) {
                this.res = this.lm.getLastKnownLocation("gps");
            } else if (obj2 != null && this.lm.isProviderEnabled("network")) {
                this.res = this.lm.getLastKnownLocation("network");
            }
        }
        return this.res;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            onRequest();
        } else if (this.gpsRequesting) {
            onGPSTimeout();
        } else if (this.networkRequesting) {
            this.lm.removeUpdates(this);
            synchronized (this) {
                notifyAll();
            }
            this.handler.getLooper().quit();
        }
        return false;
    }

    private void onRequest() {
        boolean z = false;
        boolean z2 = this.GPSTimeoutSec != 0;
        if (this.networkTimeoutSec != 0) {
            z = true;
        }
        if (z2 && this.lm.isProviderEnabled("gps")) {
            this.gpsRequesting = true;
            this.lm.requestLocationUpdates("gps", 1000, 0.0f, this);
            if (this.GPSTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.GPSTimeoutSec * 1000));
            }
        } else if (z && this.lm.isProviderEnabled("network")) {
            this.networkRequesting = true;
            this.lm.requestLocationUpdates("network", 1000, 0.0f, this);
            if (this.networkTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * 1000));
            }
        } else {
            synchronized (this) {
                notifyAll();
            }
            this.handler.getLooper().quit();
        }
    }

    private void onGPSTimeout() {
        boolean z = false;
        this.lm.removeUpdates(this);
        this.gpsRequesting = false;
        if (this.networkTimeoutSec != 0) {
            z = true;
        }
        if (z && this.lm.isProviderEnabled("network")) {
            this.networkRequesting = true;
            this.lm.requestLocationUpdates("network", 1000, 0.0f, this);
            if (this.networkTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.networkTimeoutSec * 1000));
                return;
            }
            return;
        }
        synchronized (this) {
            notifyAll();
        }
        this.handler.getLooper().quit();
    }

    public void onLocationChanged(Location location) {
        synchronized (this) {
            this.lm.removeUpdates(this);
            this.res = location;
            notifyAll();
        }
        this.handler.getLooper().quit();
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onProviderDisabled(String str) {
    }
}

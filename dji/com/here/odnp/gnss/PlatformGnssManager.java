package com.here.odnp.gnss;

import android.annotation.TargetApi;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings.Secure;
import com.here.odnp.gnss.IGnssManager.IGnnsListener;
import com.here.odnp.util.SafeHandler;
import com.here.posclient.GnssStatus;
import com.here.posclient.Status;
import dji.pilot.usercenter.mode.n;

public class PlatformGnssManager implements IGnssManager {
    private static final double LATITUDE_MAX = 90.0d;
    private static final double LATITUDE_MIN = -90.0d;
    private static final double LONGITUDE_MAX = 180.0d;
    private static final double LONGITUDE_MIN = -180.0d;
    private static final String TAG = "odnp.gnss.PlatformGnssManager";
    private LocationListener mActiveLocationListener;
    private final Context mContext;
    private final NmeaListener mGpsNmeaListener = new NmeaListener() {
        public void onNmeaReceived(long j, String str) {
        }
    };
    private final Listener mGpsStatusListener = new Listener() {
        public void onGpsStatusChanged(int i) {
            String str = "";
            String str2;
            switch (i) {
                case 1:
                    str2 = "STARTED";
                    return;
                case 2:
                    str2 = "STOPPED";
                    return;
                case 3:
                    str2 = "FIRST_FIX";
                    return;
                case 4:
                    StringBuilder stringBuilder = new StringBuilder("SATELLITE_STATUS");
                    GpsStatus gpsStatus = PlatformGnssManager.this.mLocationManager.getGpsStatus(null);
                    if (gpsStatus != null) {
                        int i2 = 0;
                        int i3 = 0;
                        for (GpsSatellite usedInFix : gpsStatus.getSatellites()) {
                            int i4;
                            if (usedInFix.usedInFix()) {
                                i4 = i2 + 1;
                            } else {
                                i4 = i2;
                            }
                            i3++;
                            i2 = i4;
                        }
                        stringBuilder.append(" satellites: total: ").append(i3);
                        stringBuilder.append(" used: ").append(i2);
                    }
                    stringBuilder.toString();
                    return;
                default:
                    return;
            }
        }
    };
    private final SafeHandler mHandler;
    private IGnnsListener mListener;
    private final LocationManager mLocationManager;
    private LocationListener mPassiveLocationListener;

    private static class GnssLocationListener implements LocationListener {
        private GnssLocationListener() {
        }

        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
        }
    }

    private class PassiveLocationListener implements LocationListener {
        private PassiveLocationListener() {
        }

        public void onProviderDisabled(String str) {
            if ("gps".equals(str)) {
                PlatformGnssManager.this.onGnssDisabled();
            }
        }

        public void onProviderEnabled(String str) {
            if ("gps".equals(str)) {
                PlatformGnssManager.this.onGnssEnabled();
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
            if (location != null && "gps".equals(location.getProvider())) {
                PlatformGnssManager.this.onUpdateLocation(location);
            }
        }
    }

    public PlatformGnssManager(Context context, SafeHandler safeHandler) {
        this.mContext = context;
        this.mHandler = safeHandler;
        this.mLocationManager = (LocationManager) this.mContext.getSystemService(n.C);
    }

    public boolean isGnssSupported() {
        return this.mContext.getPackageManager().hasSystemFeature("android.hardware.location.gps");
    }

    public Location getLastKnownGnssLocation() {
        return this.mLocationManager.getLastKnownLocation("gps");
    }

    public synchronized boolean startListening(IGnnsListener iGnnsListener, long j) {
        boolean z;
        if (iGnnsListener == null) {
            throw new IllegalArgumentException("listener is null");
        } else if (isGnssSupported()) {
            stopListening();
            this.mListener = iGnnsListener;
            this.mPassiveLocationListener = new PassiveLocationListener();
            this.mLocationManager.requestLocationUpdates("passive", j, 0.0f, this.mPassiveLocationListener, this.mHandler.getLooper());
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void stopListening() {
        if (this.mListener != null) {
            if (this.mPassiveLocationListener != null) {
                this.mLocationManager.removeUpdates(this.mPassiveLocationListener);
                this.mPassiveLocationListener = null;
            }
            this.mListener = null;
        }
    }

    public Status startGnss() {
        if (!isGnssSupported()) {
            return Status.NotSupportedError;
        }
        if (this.mActiveLocationListener != null) {
            return Status.Ok;
        }
        this.mActiveLocationListener = new GnssLocationListener();
        try {
            startGnssTracing();
            this.mLocationManager.requestLocationUpdates("gps", 1000, 0.0f, this.mActiveLocationListener, Looper.getMainLooper());
            return Status.Ok;
        } catch (Exception e) {
            return Status.GeneralError;
        }
    }

    public void stopGnss() {
        if (this.mActiveLocationListener != null) {
            stopGnssTracing();
            this.mLocationManager.removeUpdates(this.mActiveLocationListener);
            this.mActiveLocationListener = null;
        }
    }

    private synchronized void onUpdateLocation(Location location) {
        if (this.mListener != null) {
            if (isLocationValid(location)) {
                this.mListener.onGnssLocationChanged(location, isSimulated(location));
            }
        }
    }

    private synchronized void onGnssDisabled() {
        if (this.mListener != null) {
            this.mListener.onGnssStatusChanged(GnssStatus.Disabled);
        }
    }

    private synchronized void onGnssEnabled() {
        if (this.mListener != null) {
            this.mListener.onGnssStatusChanged(GnssStatus.Active);
        }
    }

    @TargetApi(18)
    private boolean isSimulated(Location location) {
        if (VERSION.SDK_INT >= 18) {
            return location.isFromMockProvider();
        }
        return isMockingAllowed();
    }

    private boolean isMockingAllowed() {
        return Secure.getInt(this.mContext.getContentResolver(), "mock_location", 1) != 0;
    }

    private static boolean isLocationValid(Location location) {
        double latitude = location.getLatitude();
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            return false;
        }
        latitude = location.getLongitude();
        if (latitude < LONGITUDE_MIN || latitude > LONGITUDE_MAX) {
            return false;
        }
        return true;
    }

    private void startGnssTracing() {
    }

    private void stopGnssTracing() {
    }
}

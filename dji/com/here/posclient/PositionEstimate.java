package com.here.posclient;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import com.here.services.common.Types.Technology;
import com.here.services.location.util.LocationHelper;
import java.util.concurrent.TimeUnit;

public class PositionEstimate {
    private static final String EXTRA_KEY_LOCATION_TYPE = "networkLocationType";
    private static final String EXTRA_KEY_LOCATION_TYPE_VALUE_BLE = "ble";
    private static final String EXTRA_KEY_LOCATION_TYPE_VALUE_CELL = "cell";
    private static final String EXTRA_KEY_LOCATION_TYPE_VALUE_WIFI = "wifi";
    public static final String EXTRA_NO_GPS_LOCATION = "noGPSLocation";
    public static final String KEY_BUILDING_ID = "com.here.services.location:buildingId";
    public static final String KEY_BUILDING_NAME = "com.here.services.location:buildingName";
    public static final String KEY_FLOOR_ID = "com.here.services.location:floorId";
    public static final String KEY_MEASUREMENT_ID = "com.here.services.location:measurementId";
    private static final String KEY_NAMESPACE = "com.here.services.location";
    private static final String KEY_SATELLITES = "satellites";
    public static final String KEY_SOURCE = "com.here.services.location:positionSource";
    public static final String KEY_TECHNOLOGY = "com.here.services.location:positionTechnology";
    public static final String KEY_TIME_SINCE_BOOT = "com.here.services.location:timeSinceBoot";
    private static final String TAG = "posclient.PositionEstimate";
    public double altitude;
    public double altitudeUncertainty;
    public String buildingId;
    public String buildingName;
    public double course;
    public int floorId = 0;
    public double horizontalCepUncertainty;
    public double latitude;
    public double longitude;
    public long measurementId;
    public long source = 0;
    public double speed;
    public long technology = 0;
    public long timeSinceBoot;
    public long timestamp;
    public long valueMask = 0;

    public interface Value {
        public static final int ALTITUDE = 128;
        public static final int ALTITUDE_UNCERTAINTY = 256;
        public static final int BUILDING_ID = 65536;
        public static final int BUILDING_NAME = 262144;
        public static final int COURSE = 2048;
        public static final int FLOOR_ID = 32768;
        public static final int HORIZONTAL_CEP_UNCERTAINTY = 8;
        public static final int LATITUDE = 2;
        public static final int LONGITUDE = 4;
        public static final int MEASUREMENT_ID = 131072;
        public static final int NONE = 0;
        public static final int SOURCE = 16384;
        public static final int SPEED = 512;
        public static final int TECHNOLOGY = 8192;
        public static final int TIMESTAMP = 1;
        public static final int TIME_SINCE_BOOT = 524288;
    }

    public static PositionEstimate fromAndroidLocation(Location location) {
        return new PositionEstimate(location);
    }

    @TargetApi(17)
    public static Location toAndroidLocation(PositionEstimate positionEstimate) {
        if (positionEstimate == null) {
            return null;
        }
        Location location = new Location("network");
        if (!positionEstimate.isValueSet(2)) {
            return null;
        }
        location.setLatitude(positionEstimate.latitude);
        if (!positionEstimate.isValueSet(4)) {
            return null;
        }
        location.setLongitude(positionEstimate.longitude);
        if (!positionEstimate.isValueSet(8)) {
            return null;
        }
        location.setAccuracy((float) positionEstimate.horizontalCepUncertainty);
        if (positionEstimate.isValueSet(1)) {
            location.setTime(positionEstimate.timestamp);
        }
        if (positionEstimate.isValueSet(128)) {
            location.setAltitude(positionEstimate.altitude);
        }
        if (VERSION.SDK_INT >= 17) {
            location.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
        }
        if (positionEstimate.isValueSet(512)) {
            location.setSpeed((float) positionEstimate.speed);
        }
        if (positionEstimate.isValueSet(2048)) {
            location.setBearing((float) (positionEstimate.course < 0.0d ? 360.0d + positionEstimate.course : positionEstimate.course));
        }
        location.setExtras(getExtras(location, positionEstimate));
        return location;
    }

    public static void addNoGpsLocationExtras(Location location) {
        if (VERSION.SDK_INT >= 18 && location != null && !LocationHelper.getTechnologies(location).contains(Technology.Gnss)) {
            if (location.getExtras() == null) {
                location.setExtras(new Bundle());
            }
            location.getExtras().putParcelable(EXTRA_NO_GPS_LOCATION, new Location(location));
        }
    }

    @TargetApi(17)
    private PositionEstimate(Location location) {
        if (location != null) {
            Bundle extras;
            this.timestamp = System.currentTimeMillis();
            this.valueMask = 1;
            if (VERSION.SDK_INT >= 17) {
                this.timeSinceBoot = TimeUnit.NANOSECONDS.toMillis(location.getElapsedRealtimeNanos());
            } else {
                this.timeSinceBoot = SystemClock.elapsedRealtime();
            }
            this.valueMask |= 524288;
            this.longitude = location.getLongitude();
            this.valueMask |= 4;
            this.latitude = location.getLatitude();
            this.valueMask |= 2;
            if (location.hasAccuracy()) {
                this.horizontalCepUncertainty = (double) location.getAccuracy();
                this.valueMask |= 8;
            }
            if (location.hasAltitude()) {
                this.altitude = location.getAltitude();
                this.valueMask |= 128;
            }
            if (location.hasBearing()) {
                double bearing;
                if (location.getBearing() > 180.0f) {
                    bearing = (double) (location.getBearing() - 360.0f);
                } else {
                    bearing = (double) location.getBearing();
                }
                this.course = bearing;
                this.valueMask |= 2048;
            }
            if ("gps".equals(location.getProvider())) {
                this.technology = 1;
                this.valueMask |= 8192;
                extras = location.getExtras();
                if (extras != null && extras.getInt(KEY_SATELLITES) > 2) {
                    this.technology |= 32768;
                }
            } else if ("network".equals(location.getProvider())) {
                this.technology = 6;
                this.valueMask |= 8192;
            }
            if (location.hasSpeed()) {
                this.speed = (double) location.getSpeed();
                this.valueMask |= 512;
            }
            extras = location.getExtras();
            if (extras != null && extras.containsKey("com.here.services.location:measurementId")) {
                this.measurementId = extras.getLong("com.here.services.location:measurementId");
                this.valueMask |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
        }
    }

    private boolean isValueSet(int i) {
        return (this.valueMask & ((long) i)) != 0;
    }

    private static Bundle getExtras(Location location, PositionEstimate positionEstimate) {
        Bundle bundle = new Bundle();
        if ((positionEstimate.source & 1) != 0) {
            bundle.putLong(KEY_SOURCE, 1);
        } else if ((positionEstimate.source & 4) != 0) {
            bundle.putLong(KEY_SOURCE, 4);
        } else if ((positionEstimate.source & 2) != 0) {
            bundle.putLong(KEY_SOURCE, 2);
        } else if ((positionEstimate.source & 16) != 0) {
            bundle.putLong(KEY_SOURCE, 16);
        } else if ((positionEstimate.source & 8) != 0) {
            bundle.putLong(KEY_SOURCE, 8);
        } else if ((positionEstimate.source & 64) != 0) {
            bundle.putLong(KEY_SOURCE, 64);
        } else if ((positionEstimate.source & 128) != 0) {
            bundle.putLong(KEY_SOURCE, 128);
        }
        long j = 0;
        if ((positionEstimate.technology & 4) != 0) {
            j = 0 | 4;
            bundle.putString(EXTRA_KEY_LOCATION_TYPE, EXTRA_KEY_LOCATION_TYPE_VALUE_CELL);
        }
        if ((positionEstimate.technology & 2) != 0) {
            j |= 2;
            bundle.putString(EXTRA_KEY_LOCATION_TYPE, EXTRA_KEY_LOCATION_TYPE_VALUE_WIFI);
        }
        if ((positionEstimate.technology & 16384) != 0) {
            j |= 16384;
            bundle.putString(EXTRA_KEY_LOCATION_TYPE, EXTRA_KEY_LOCATION_TYPE_VALUE_BLE);
        }
        if ((positionEstimate.technology & 256) != 0) {
            j |= 256;
        }
        if ((positionEstimate.technology & 1) != 0) {
            j |= 1;
        }
        bundle.putLong(KEY_TECHNOLOGY, j);
        if (positionEstimate.isValueSet(65536)) {
            bundle.putString(KEY_BUILDING_ID, positionEstimate.buildingId);
        }
        if (positionEstimate.isValueSet(32768)) {
            bundle.putInt(KEY_FLOOR_ID, positionEstimate.floorId);
        }
        if (positionEstimate.isValueSet(524288)) {
            bundle.putLong(KEY_TIME_SINCE_BOOT, positionEstimate.timeSinceBoot);
        }
        if (positionEstimate.isValueSet(131072)) {
            bundle.putLong("com.here.services.location:measurementId", positionEstimate.measurementId);
        }
        if (positionEstimate.isValueSet(262144)) {
            bundle.putString(KEY_BUILDING_NAME, positionEstimate.buildingName);
        }
        return bundle;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PositionEstimate[");
        stringBuilder.append(String.format("%.7f,%.7f", new Object[]{Double.valueOf(this.latitude), Double.valueOf(this.longitude)}));
        stringBuilder.append(String.format(" acc=%.2f", new Object[]{Double.valueOf(this.horizontalCepUncertainty)}));
        if (this.timestamp == 0) {
            stringBuilder.append(" ts=?!?");
        } else {
            stringBuilder.append(" ts=").append(this.timestamp).append("ms");
        }
        if (this.timeSinceBoot == 0) {
            stringBuilder.append(" tsb=?!?");
        } else {
            stringBuilder.append(" tsb=").append(this.timeSinceBoot).append("ms");
        }
        if (this.buildingId != null) {
            stringBuilder.append(String.format(" bldngId=%s", new Object[]{this.buildingId}));
            if (this.floorId > 0) {
                stringBuilder.append(String.format(" flrId=%d", new Object[]{Integer.valueOf(this.floorId)}));
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}

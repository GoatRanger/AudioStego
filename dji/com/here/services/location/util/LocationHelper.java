package com.here.services.location.util;

import android.location.Location;
import android.os.Bundle;
import com.here.posclient.UpdateOptions;
import com.here.services.common.Types.Source;
import com.here.services.common.Types.Technology;
import java.util.EnumSet;

public class LocationHelper {
    private static final String KEY_BUILDING_ID = "com.here.services.location:buildingId";
    private static final String KEY_BUILDING_NAME = "com.here.services.location:buildingName";
    private static final String KEY_FLOOR_ID = "com.here.services.location:floorId";
    private static final String KEY_MEASUREMENT_ID = "com.here.services.location:measurementId";
    private static final String KEY_SOURCE = "com.here.services.location:positionSource";
    private static final String KEY_TECHNOLOGY = "com.here.services.location:positionTechnology";
    private static final String KEY_TIME_SINCE_BOOT = "com.here.services.location:timeSinceBoot";
    private static final long SOURCE_UNSPECIFIED = 0;
    private static final long TECHNOLOGY_UNSPECIFIED = 0;

    public static EnumSet<Source> getSources(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("location is null");
        }
        Bundle extras = location.getExtras();
        long j = 0;
        if (extras != null) {
            j = extras.getLong("com.here.services.location:positionSource");
        }
        return UpdateOptions.getSourceSet(j);
    }

    public static EnumSet<Technology> getTechnologies(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("location is null");
        }
        Bundle extras = location.getExtras();
        long j = 0;
        if (extras != null) {
            j = extras.getLong("com.here.services.location:positionTechnology");
        }
        return UpdateOptions.getTechnologySet(j);
    }

    public static String getBuildingId(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        Bundle extras = location.getExtras();
        return extras == null ? null : extras.getString("com.here.services.location:buildingId");
    }

    public static String getBuildingName(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        Bundle extras = location.getExtras();
        return (extras == null || !extras.containsKey("com.here.services.location:buildingName")) ? null : extras.getString("com.here.services.location:buildingName");
    }

    public static Integer getFloorId(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        Bundle extras = location.getExtras();
        return (extras == null || !extras.containsKey("com.here.services.location:floorId")) ? null : Integer.valueOf(extras.getInt("com.here.services.location:floorId"));
    }

    public static Long getTimeSinceBoot(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        Bundle extras = location.getExtras();
        return (extras == null || !extras.containsKey("com.here.services.location:timeSinceBoot")) ? null : Long.valueOf(extras.getLong("com.here.services.location:timeSinceBoot"));
    }

    public static Long getMeasurementId(Location location) {
        if (location == null) {
            throw new IllegalArgumentException();
        }
        Bundle extras = location.getExtras();
        return (extras == null || !extras.containsKey("com.here.services.location:measurementId")) ? null : Long.valueOf(extras.getLong("com.here.services.location:measurementId"));
    }
}

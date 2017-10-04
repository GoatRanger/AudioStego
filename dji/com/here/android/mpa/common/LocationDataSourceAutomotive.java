package com.here.android.mpa.common;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public abstract class LocationDataSourceAutomotive extends LocationDataSource {
    public float getHorizontalLargeStandardDeviation() {
        return Float.POSITIVE_INFINITY;
    }

    public float getHorizontalSmallStandardDeviation() {
        return Float.POSITIVE_INFINITY;
    }

    public float getCourseStandardDeviation() {
        return Float.POSITIVE_INFINITY;
    }

    public float getSpeedStandardDeviation() {
        return Float.POSITIVE_INFINITY;
    }

    public float getElevationStandardDeviationn() {
        return Float.POSITIVE_INFINITY;
    }
}

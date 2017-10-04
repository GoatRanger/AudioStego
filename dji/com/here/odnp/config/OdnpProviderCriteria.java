package com.here.odnp.config;

import android.location.Criteria;

public final class OdnpProviderCriteria {
    private static final String TAG = "odnp.config.OdnpProviderCriteria";

    public static boolean requiresNetwork() {
        return false;
    }

    public static boolean requiresSatellite() {
        return false;
    }

    public static boolean requiresCell() {
        return false;
    }

    public static boolean hasMonetaryCost() {
        return false;
    }

    public static boolean supportsAltitude() {
        return false;
    }

    public static boolean supportsSpeed() {
        return false;
    }

    public static boolean supportsBearing() {
        return false;
    }

    public static int getPowerRequirement() {
        return 1;
    }

    public static int getAccuracy() {
        return 2;
    }

    public static boolean meetsCriteria(Criteria criteria) {
        if (criteria.isAltitudeRequired() && !supportsAltitude()) {
            return false;
        }
        if (criteria.isSpeedRequired() && !supportsSpeed()) {
            return false;
        }
        if (criteria.isBearingRequired() && !supportsBearing()) {
            return false;
        }
        if (criteria.getHorizontalAccuracy() != 0 && criteria.getAccuracy() < getAccuracy()) {
            return false;
        }
        if (criteria.getPowerRequirement() == 0 || criteria.getPowerRequirement() >= getPowerRequirement()) {
            return true;
        }
        return false;
    }
}

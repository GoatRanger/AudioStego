package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.am;
import com.nokia.maps.cs;
import com.nokia.maps.k;

public final class StreetLevelCoverage {
    private cs a;

    public interface Listener {
        void onCoverageCheckCompleted(GeoCoordinate geoCoordinate, int i, ResultCode resultCode);
    }

    public enum ResultCode {
        UNKNOWN_COVERAGE,
        HAS_COVERAGE,
        HAS_NO_COVERAGE,
        NETWORK_ERROR,
        CANCELLED
    }

    private StreetLevelCoverage(cs csVar) {
        this.a = csVar;
    }

    public StreetLevelCoverage() {
        this.a = new cs();
    }

    public boolean checkInCoverageZone(GeoCoordinate geoCoordinate, int i, boolean z, Listener listener) {
        return this.a.a(geoCoordinate, i, z, listener);
    }

    public void cancel(GeoCoordinate geoCoordinate) {
        this.a.a(geoCoordinate);
    }

    public void setTimeoutLimit(long j) {
        this.a.a(j);
    }

    public long getTimeoutLimit() {
        return this.a.a();
    }

    static {
        cs.a(new k<StreetLevelCoverage, cs>() {
            public cs a(StreetLevelCoverage streetLevelCoverage) {
                return streetLevelCoverage.a;
            }
        }, new am<StreetLevelCoverage, cs>() {
            public StreetLevelCoverage a(cs csVar) {
                if (csVar != null) {
                    return new StreetLevelCoverage(csVar);
                }
                return null;
            }
        });
    }
}

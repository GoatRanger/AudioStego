package com.here.android.mpa.common;

import com.nokia.maps.GeoPositionImpl;
import com.nokia.maps.MatchedGeoPositionImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public final class MatchedGeoPosition extends GeoPosition {
    private MatchedGeoPositionImpl a;

    private MatchedGeoPosition(MatchedGeoPositionImpl matchedGeoPositionImpl) {
        super((GeoPositionImpl) matchedGeoPositionImpl);
        this.a = matchedGeoPositionImpl;
    }

    public boolean isExtrapolated() {
        return this.a.isExtrapolated();
    }

    public boolean isOnStreet() {
        return this.a.isOnStreet();
    }

    public int getMatchQuality() {
        return this.a.getMatchQuality();
    }

    public RoadElement getRoadElement() {
        return this.a.h();
    }

    public GeoPosition getRawPosition() {
        return this.a.i();
    }

    static {
        MatchedGeoPositionImpl.b(new k<MatchedGeoPosition, MatchedGeoPositionImpl>() {
            public MatchedGeoPositionImpl a(MatchedGeoPosition matchedGeoPosition) {
                return matchedGeoPosition.a;
            }
        }, new am<MatchedGeoPosition, MatchedGeoPositionImpl>() {
            public MatchedGeoPosition a(MatchedGeoPositionImpl matchedGeoPositionImpl) {
                return new MatchedGeoPosition(matchedGeoPositionImpl);
            }
        });
    }
}

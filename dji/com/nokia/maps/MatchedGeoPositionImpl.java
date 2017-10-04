package com.nokia.maps;

import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.MatchedGeoPosition;
import com.here.android.mpa.common.RoadElement;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class MatchedGeoPositionImpl extends GeoPositionImpl {
    private static k<MatchedGeoPosition, MatchedGeoPositionImpl> a = null;
    private static am<MatchedGeoPosition, MatchedGeoPositionImpl> b = null;

    public native int getMatchQuality();

    native GeoPositionImpl getRawPositonImpl();

    native RoadElementImpl getRoadElementImpl();

    public native boolean isExtrapolated();

    public native boolean isOnStreet();

    @HybridPlusNative
    private MatchedGeoPositionImpl(int i) {
        super(i);
    }

    static {
        ce.a(MatchedGeoPosition.class);
    }

    public static MatchedGeoPosition a(MatchedGeoPositionImpl matchedGeoPositionImpl) {
        if (matchedGeoPositionImpl != null) {
            return (MatchedGeoPosition) b.a(matchedGeoPositionImpl);
        }
        return null;
    }

    public static void b(k<MatchedGeoPosition, MatchedGeoPositionImpl> kVar, am<MatchedGeoPosition, MatchedGeoPositionImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    public RoadElement h() {
        return RoadElementImpl.a(getRoadElementImpl());
    }

    public GeoPosition i() {
        return GeoPositionImpl.a(getRawPositonImpl());
    }
}

package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.RoadElement;
import com.nokia.maps.DynamicPenaltyImpl;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class DynamicPenalty {
    private final DynamicPenaltyImpl a;

    private DynamicPenalty(DynamicPenaltyImpl dynamicPenaltyImpl) {
        this.a = dynamicPenaltyImpl;
    }

    public DynamicPenalty() {
        this.a = new DynamicPenaltyImpl();
    }

    @Deprecated
    public void addAreaPenalty(GeoPolygon geoPolygon, int i) {
        this.a.a(geoPolygon);
    }

    @Deprecated
    public void removeAreaPenalty(GeoPolygon geoPolygon) {
        this.a.b(geoPolygon);
    }

    public void addBannedArea(GeoPolygon geoPolygon) {
        this.a.a(geoPolygon);
    }

    public void removeBannedArea(GeoPolygon geoPolygon) {
        this.a.b(geoPolygon);
    }

    public void clearAllAreaPenalties() {
        this.a.clearAllAreaPenalties();
    }

    public void addRoadPenalty(RoadElement roadElement, DrivingDirection drivingDirection, int i) {
        this.a.a(roadElement, drivingDirection, i);
    }

    public void removeRoadPenalty(RoadElement roadElement) {
        this.a.a(roadElement);
    }

    public void clearAllRoadPenalties() {
        this.a.clearAllRoadPenalties();
    }

    public void setTrafficPenaltyMode(Route$TrafficPenaltyMode route$TrafficPenaltyMode) {
        this.a.a(route$TrafficPenaltyMode);
    }

    public Route$TrafficPenaltyMode getTrafficPenaltyMode() {
        return this.a.a();
    }

    static {
        DynamicPenaltyImpl.a(new 1(), new 2());
    }
}

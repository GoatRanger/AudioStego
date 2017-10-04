package com.nokia.maps;

import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.DrivingDirection;
import com.here.android.mpa.routing.DynamicPenalty;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class DynamicPenaltyImpl extends BaseNativeObject {
    private static k<DynamicPenalty, DynamicPenaltyImpl> a = null;
    private static am<DynamicPenalty, DynamicPenaltyImpl> b = null;

    private native synchronized void addAreaPenaltyNative(GeoPolygonImpl geoPolygonImpl);

    private native synchronized void addRoadPenaltyNative(IdentifierImpl identifierImpl, int i, int i2);

    private native synchronized void addTrafficPenaltyModeNative(int i);

    private native void createNative();

    private native void deleteNative();

    private native synchronized int getTrafficPenaltyModeNative();

    private native synchronized void removeAreaPenaltyNative(GeoPolygonImpl geoPolygonImpl);

    private native synchronized void removeRoadPenaltyNative(IdentifierImpl identifierImpl);

    public native synchronized void clearAllAreaPenalties();

    public native synchronized void clearAllRoadPenalties();

    static {
        ce.a(DynamicPenalty.class);
    }

    public static void a(k<DynamicPenalty, DynamicPenaltyImpl> kVar, am<DynamicPenalty, DynamicPenaltyImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static DynamicPenaltyImpl a(DynamicPenalty dynamicPenalty) {
        if (dynamicPenalty != null) {
            return (DynamicPenaltyImpl) a.a(dynamicPenalty);
        }
        return null;
    }

    static DynamicPenalty a(DynamicPenaltyImpl dynamicPenaltyImpl) {
        if (dynamicPenaltyImpl != null) {
            return (DynamicPenalty) b.a(dynamicPenaltyImpl);
        }
        return null;
    }

    @HybridPlusNative
    public DynamicPenaltyImpl(int i) {
        this.nativeptr = i;
    }

    public DynamicPenaltyImpl() {
        createNative();
    }

    public void a(GeoPolygon geoPolygon) {
        dy.a((Object) geoPolygon, "area cannot be null");
        addAreaPenaltyNative(GeoPolygonImpl.a(geoPolygon));
    }

    public void b(GeoPolygon geoPolygon) {
        if (geoPolygon != null) {
            removeAreaPenaltyNative(GeoPolygonImpl.a(geoPolygon));
        }
    }

    public void a(RoadElement roadElement, DrivingDirection drivingDirection, int i) {
        dy.a(roadElement.getIdentifier(), "Road Element Identifier cannot be null");
        if (i < 0 || i > 254) {
            throw new IllegalArgumentException("New speed must be between 0 to 254");
        }
        addRoadPenaltyNative(IdentifierImpl.a(roadElement.getIdentifier()), drivingDirection.value(), i);
    }

    public void a(RoadElement roadElement) {
        if (roadElement.getIdentifier() != null) {
            removeRoadPenaltyNative(IdentifierImpl.a(roadElement.getIdentifier()));
        }
    }

    public void a(TrafficPenaltyMode trafficPenaltyMode) {
        addTrafficPenaltyModeNative(trafficPenaltyMode.value());
    }

    public TrafficPenaltyMode a() {
        TrafficPenaltyMode trafficPenaltyMode = TrafficPenaltyMode.DISABLED;
        int trafficPenaltyModeNative = getTrafficPenaltyModeNative();
        if (trafficPenaltyModeNative == TrafficPenaltyMode.AVOID_CONGESTION.value()) {
            return TrafficPenaltyMode.AVOID_CONGESTION;
        }
        if (trafficPenaltyModeNative == TrafficPenaltyMode.OPTIMAL.value()) {
            return TrafficPenaltyMode.OPTIMAL;
        }
        if (trafficPenaltyModeNative == TrafficPenaltyMode.AVOID_LONG_TERM_CLOSURES.value()) {
            return TrafficPenaltyMode.AVOID_LONG_TERM_CLOSURES;
        }
        return trafficPenaltyMode;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        deleteNative();
    }
}

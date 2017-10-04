package com.here.android.mpa.common;

import com.nokia.maps.RoadElementImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@Online
public final class RoadElement {
    private RoadElementImpl a;

    @Online
    public enum Attribute {
        DIR_NO_CARS,
        DIR_FORWARD,
        DIR_BACKWARD,
        DIR_BOTH,
        DIRT_ROAD,
        USAGE_FEE_REQUIRED,
        CARPOOL,
        URBAN,
        TOLLROAD,
        NO_THROUGH_TRAFFIC,
        TUNNEL,
        EXPLICATION,
        SLIPROAD,
        HIGHWAY,
        UNDER_CONSTRUCTION,
        HAS_LANE_DIR,
        HAS_LANE_EXIT,
        FERRY,
        CAR_SHUTTLE_TRAIN,
        DIR_NO_TRUCKS,
        DIR_TRUCK_FORWARD,
        DIR_TRUCK_BACKWARD,
        DIR_TRUCK_BOTH,
        TRUCK_TOLLROAD,
        TRUCK_NO_THROUGH
    }

    @Online
    public enum FormOfWay {
        UNDEFINED,
        MOTORWAY,
        MULTI_CARRIAGEWAY,
        SINGLE_CARRIAGEWAY,
        ROUNDABOUT,
        SPECIAL_TRAFFIC_FIGURE,
        SLIPROAD,
        PEDESTRIAN_ZONE,
        PEDESTRIAN_WALKWAY,
        SERVICE_ACCESS_PARKING,
        SERVICE_ACCESS_OTHER,
        SERVICE_ROAD,
        ETA_PARKING_PLACE,
        ETA_PARKING_BUILDING,
        ETA_UNSTRUCTURED_TRAFFIC_SQUARE,
        ROAD_FOR_AUTHORITIES
    }

    @Online
    public enum PluralType {
        NONE,
        MANEUVER,
        CONNECTOR,
        INDETERMINATE
    }

    public static RoadElement getRoadElement(GeoCoordinate geoCoordinate, String str) {
        return RoadElementImpl.a(geoCoordinate, str);
    }

    public static List<RoadElement> getRoadElements(GeoBoundingBox geoBoundingBox, String str) {
        return RoadElementImpl.a(geoBoundingBox, str);
    }

    public EnumSet<Attribute> getAttributes() {
        return this.a.a();
    }

    public FormOfWay getFormOfWay() {
        return this.a.b();
    }

    public boolean isPlural() {
        return this.a.isPlural();
    }

    public PluralType getPluralType() {
        return this.a.getPluralType();
    }

    public String getRoadName() {
        return this.a.getRoadName();
    }

    public String getRouteName() {
        return this.a.getRouteName();
    }

    public float getSpeedLimit() {
        return this.a.getSpeedLimit();
    }

    public float getDefaultSpeed() {
        return this.a.getDefaultSpeed();
    }

    public float getStaticSpeed() {
        return this.a.getStaticSpeed();
    }

    public int getNumberOfLanes() {
        return this.a.getNumberOfLanes();
    }

    public boolean isPedestrian() {
        return this.a.isPedestrian();
    }

    public Date getStartTime() {
        return this.a.c();
    }

    public double getGeometryLength() {
        return this.a.getGeometryLength();
    }

    public final List<GeoCoordinate> getGeometry() {
        return this.a.d();
    }

    public Identifier getIdentifier() {
        return this.a.e();
    }

    private RoadElement(RoadElementImpl roadElementImpl) {
        this.a = roadElementImpl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (RoadElement.class.isInstance(obj)) {
            return this.a.equals(((RoadElement) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    static {
        RoadElementImpl.a(new k<RoadElement, RoadElementImpl>() {
            public RoadElementImpl a(RoadElement roadElement) {
                return roadElement.a;
            }
        }, new am<RoadElement, RoadElementImpl>() {
            public RoadElement a(RoadElementImpl roadElementImpl) {
                if (roadElementImpl == null || !roadElementImpl.isValid()) {
                    return null;
                }
                return new RoadElement(roadElementImpl);
            }
        });
    }
}

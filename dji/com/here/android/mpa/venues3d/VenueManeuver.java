package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.Maneuver.Turn;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class VenueManeuver extends BaseNativeObject {
    private Space a;

    @HybridPlus
    public enum ActionType {
        NO_ACTION,
        ENTER,
        EXIT
    }

    @HybridPlus
    public enum ConnectorType {
        ELEVATOR,
        ESCALATOR,
        STAIRS,
        SKYWALK,
        SHUTTLE,
        MOVING_SIDEWALK,
        RAMP,
        OTHER_CATEGORY,
        NO_CONNECTOR
    }

    @HybridPlusNative
    private native int getActionNative();

    @HybridPlusNative
    private native int getConnectorTypeNative();

    @HybridPlusNative
    private native Space getSpaceNative();

    @HybridPlusNative
    private native int getTurnNative();

    @HybridPlusNative
    public native int getAngle();

    @HybridPlusNative
    public native int getDistanceFromPreviousManeuver();

    @HybridPlusNative
    public native int getDistanceFromStart();

    @HybridPlusNative
    public native int getDistanceToNextManeuver();

    @HybridPlusNative
    public native int getFloorIndex();

    @HybridPlusNative
    public native GeoCoordinate getGeoCoordinate();

    @HybridPlusNative
    public native int getMapOrientation();

    @HybridPlusNative
    protected VenueManeuver(int i) {
        this.nativeptr = i;
    }

    public ActionType getActionType() {
        return ActionType.values()[getActionNative()];
    }

    public ConnectorType getConnectorType() {
        return ConnectorType.values()[getConnectorTypeNative()];
    }

    public Turn getTurn() {
        return Turn.values()[getTurnNative()];
    }

    public Space getSpace() {
        if (this.a == null) {
            this.a = getSpaceNative();
        }
        return this.a;
    }
}

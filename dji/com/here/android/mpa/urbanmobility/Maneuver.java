package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.a.ab;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.List;

@HybridPlus
public class Maneuver {
    private ab a;

    @HybridPlus
    public enum Action {
        UNDEFINED,
        DEPART,
        DEPART_AIRPORT,
        ARRIVE,
        ARRIVE_AIRPORT,
        ARRIVE_LEFT,
        ARRIVE_RIGHT,
        LEFT_LOOP,
        LEFT_UTURN,
        SHARP_LEFT_TURN,
        LEFT_TURN,
        SLIGHT_LEFT_TURN,
        CONTINUE,
        SLIGHT_RIGHT_TURN,
        RIGHT_TURN,
        SHARP_RIGHT_TURN,
        RIGHT_UTURN,
        RIGHT_LOOP,
        LEFT_EXIT,
        RIGHT_EXIT,
        LEFT_RAMP,
        RIGHT_RAMP,
        LEFT_FORK,
        MIDDLE_FORK,
        RIGHT_FORK,
        LEFT_MERGE,
        RIGHT_MERGE,
        NAME_CHANGE,
        TRAFFIC_CIRCLE,
        FERRY,
        LEFT_ROUNDABOUT_EXIT_1,
        LEFT_ROUNDABOUT_EXIT_2,
        LEFT_ROUNDABOUT_EXIT_3,
        LEFT_ROUNDABOUT_EXIT_4,
        LEFT_ROUNDABOUT_EXIT_5,
        LEFT_ROUNDABOUT_EXIT_6,
        LEFT_ROUNDABOUT_EXIT_7,
        LEFT_ROUNDABOUT_EXIT_8,
        LEFT_ROUNDABOUT_EXIT_9,
        LEFT_ROUNDABOUT_EXIT_10,
        LEFT_ROUNDABOUT_EXIT_11,
        LEFT_ROUNDABOUT_EXIT_12,
        RIGHT_ROUNDABOUT_EXIT_1,
        RIGHT_ROUNDABOUT_EXIT_2,
        RIGHT_ROUNDABOUT_EXIT_3,
        RIGHT_ROUNDABOUT_EXIT_4,
        RIGHT_ROUNDABOUT_EXIT_5,
        RIGHT_ROUNDABOUT_EXIT_6,
        RIGHT_ROUNDABOUT_EXIT_7,
        RIGHT_ROUNDABOUT_EXIT_8,
        RIGHT_ROUNDABOUT_EXIT_9,
        RIGHT_ROUNDABOUT_EXIT_10,
        RIGHT_ROUNDABOUT_EXIT_11,
        RIGHT_ROUNDABOUT_EXIT_12,
        ENTER,
        CHANGE,
        LEAVE
    }

    @HybridPlus
    public enum Direction {
        UNDEFINED,
        FORWARD,
        RIGHT,
        LEFT,
        BEAR_RIGHT,
        LIGHT_RIGHT,
        HARD_RIGHT,
        UTURN_RIGHT,
        UTURN_LEFT,
        HARD_LEFT,
        LIGHT_LEFT,
        BEAR_LEFT
    }

    public Maneuver(ab abVar) {
        if (abVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = abVar;
    }

    public Action getAction() {
        return this.a.a();
    }

    public Direction getDirection() {
        return this.a.b();
    }

    public String getInstruction() {
        return this.a.c();
    }

    public List<GeoCoordinate> getGeometry() {
        return this.a.d();
    }

    public String getNextRoadName() {
        return this.a.e();
    }

    public String getNextRoadNumber() {
        return this.a.f();
    }

    public GeoBoundingBox getBoundingBox() {
        return this.a.g();
    }

    public long getDuration() {
        return this.a.h();
    }

    public int getDistance() {
        return this.a.i();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Maneuver) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ab.a(new am<Maneuver, ab>() {
            public Maneuver a(ab abVar) {
                return new Maneuver(abVar);
            }
        });
    }
}

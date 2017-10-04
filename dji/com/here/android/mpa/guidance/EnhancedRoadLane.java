package com.here.android.mpa.guidance;

import com.nokia.maps.EnhancedRoadLaneImpl;
import com.nokia.maps.annotation.HybridPlus;
import java.util.EnumSet;

@HybridPlus
@Deprecated
public final class EnhancedRoadLane {
    private final EnhancedRoadLaneImpl a;

    @HybridPlus
    @Deprecated
    public enum Arrow {
        STRAIGHT(1),
        SLIGHT_RIGHT(2),
        RIGHT(4),
        HARD_RIGHT(8),
        U_LEFT(16),
        HARD_LEFT(32),
        LEFT(64),
        SLIGHT_LEFT(128),
        MERGE_RIGHT(256),
        MERGE_LEFT(512),
        MERGE_LANES(1024),
        U_RIGHT(2048),
        SECOND_RIGHT(4096),
        SECOND_LEFT(8192);
        
        private final int a;

        private Arrow(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @HybridPlus
    @Deprecated
    public enum CrossingRestriction {
        NO_RESTRICTION(0),
        LEFT(1),
        RIGHT(2),
        BOTH(3);
        
        private final int a;

        private CrossingRestriction(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @HybridPlus
    @Deprecated
    public enum Direction {
        NOT_AVAILABLE(0),
        POSITIVE(1),
        NEGATIVE(2),
        BOTH(3);
        
        private final int a;

        private Direction(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @HybridPlus
    @Deprecated
    public enum Marking {
        NOT_AVAILABLE(0),
        LONG_DASHED(1),
        DOUBLE_SOLID_LINE(2),
        SINGLE_SOLID_LINE(3),
        DOUBLE_INNER_SINGLE_OUTER_DASHED(4),
        DOUBLE_INNER_DASHED_OUTER_SINGLE(5),
        SHORT_DASHED(6),
        SHARED_AREA(7),
        DASHED_BLOCKS(8),
        PHYSICAL_DIVIDER(9),
        DOUBLE_DASHED(10),
        NO_DIVIDER(11),
        CROSSING_ALERT(12);
        
        private final int a;

        private Marking(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @HybridPlus
    @Deprecated
    public enum Type {
        REGULAR(1),
        HOV(2),
        REVERSIBLE(4),
        EXPRESS(8),
        ACCELERATION(16),
        DECELERATION(32),
        AUX_LANE(64),
        SLOW(128),
        PASSING(256),
        DRIVABLE_SHOULDER(512),
        REGULATED_ACCESS(1024),
        TURN(2048),
        CENTRE_TURN(4096),
        TRUCK_PARKING(8192);
        
        private final int a;

        private Type(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @Deprecated
    public EnumSet<Type> getTypes() {
        return this.a.a();
    }

    @Deprecated
    public EnumSet<Arrow> getArrows() {
        return this.a.b();
    }

    @Deprecated
    public Direction getDirection() {
        return this.a.c();
    }

    @Deprecated
    public Marking getDividerMarking() {
        return this.a.d();
    }

    @Deprecated
    public Marking getCenterMarking() {
        return this.a.e();
    }

    @Deprecated
    public int getFromSpeedLimit() {
        return this.a.getFromSpeedLimit();
    }

    @Deprecated
    public int getToSpeedLimit() {
        return this.a.getToSpeedLimit();
    }

    @Deprecated
    public int getHeightRestriction() {
        return this.a.getHeightRestriction();
    }

    @Deprecated
    public int getWidth() {
        return this.a.getWidth();
    }

    @Deprecated
    public CrossingRestriction getCrossingRestriction() {
        return this.a.f();
    }

    private EnhancedRoadLane(EnhancedRoadLaneImpl enhancedRoadLaneImpl) {
        this.a = enhancedRoadLaneImpl;
    }

    static {
        EnhancedRoadLaneImpl.a(new 1());
    }
}

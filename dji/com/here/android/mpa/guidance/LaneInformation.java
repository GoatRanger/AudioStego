package com.here.android.mpa.guidance;

import com.nokia.maps.LaneInformationImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.EnumSet;

@HybridPlus
public final class LaneInformation {
    private final LaneInformationImpl a;

    @HybridPlus
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
    public enum Direction {
        UNDEFINED(0),
        STRAIGHT(1),
        SLIGHTLY_RIGHT(2),
        RIGHT(4),
        SHARP_RIGHT(8),
        U_TURN_LEFT(16),
        SHARP_LEFT(32),
        LEFT(64),
        SLIGHTLY_LEFT(128),
        MERGE_RIGHT(256),
        MERGE_LEFT(512),
        MERGE_LANES(1024),
        U_TURN_RIGHT(2048),
        SECOND_RIGHT(4096),
        SECOND_LEFT(8192);
        
        private int a;

        private Direction(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    public enum MarkingType {
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

        private MarkingType(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    @HybridPlus
    public enum RecommendationState {
        NOT_RECOMMENDED(0),
        RECOMMENDED(1),
        HIGHLY_RECOMMENDED(2),
        NOT_AVAILABLE(3);
        
        private int a;

        private RecommendationState(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    public EnumSet<Direction> getDirections() {
        return this.a.a();
    }

    public boolean isSyntheticDirection() {
        return this.a.isSyntheticDirection();
    }

    public RecommendationState getRecommendationState() {
        return this.a.b();
    }

    public boolean isRegular() {
        return this.a.isRegularNative();
    }

    public boolean isHOV() {
        return this.a.isHOVNative();
    }

    public boolean isReversible() {
        return this.a.isReversibleNative();
    }

    public boolean isExpress() {
        return this.a.isExpressNative();
    }

    public boolean isAcceleration() {
        return this.a.isAccelerationNative();
    }

    public boolean isDeceleration() {
        return this.a.isDecelerationNative();
    }

    public boolean isAuxLane() {
        return this.a.isAuxLaneNative();
    }

    public boolean isSlow() {
        return this.a.isSlowNative();
    }

    public boolean isPassing() {
        return this.a.isPassingNative();
    }

    public boolean isDriveableShoulder() {
        return this.a.isDriveableShoulderNative();
    }

    public boolean isRegulatedAccess() {
        return this.a.isRegulatedAccessNative();
    }

    public boolean isTurn() {
        return this.a.isTurnNative();
    }

    public boolean isCenterTurn() {
        return this.a.isCenterTurnNative();
    }

    public boolean isTruckParking() {
        return this.a.isTruckParkingNative();
    }

    public MarkingType getDividerMarking() {
        return this.a.c();
    }

    public MarkingType getCenterMarking() {
        return this.a.d();
    }

    public int getSpeedLimit() {
        return this.a.getSpeedLimit();
    }

    public int getHeightRestriction() {
        return this.a.getHeightRestriction();
    }

    public int getWidth() {
        return this.a.getWidth();
    }

    public CrossingRestriction getCrossingRestriction() {
        return this.a.e();
    }

    private LaneInformation(LaneInformationImpl laneInformationImpl) {
        this.a = laneInformationImpl;
    }

    static {
        LaneInformationImpl.a(new am<LaneInformation, LaneInformationImpl>() {
            public LaneInformation a(LaneInformationImpl laneInformationImpl) {
                return new LaneInformation(laneInformationImpl);
            }
        });
    }
}

package com.nokia.maps;

import com.here.android.mpa.guidance.LaneInformation;
import com.here.android.mpa.guidance.LaneInformation.CrossingRestriction;
import com.here.android.mpa.guidance.LaneInformation.Direction;
import com.here.android.mpa.guidance.LaneInformation.MarkingType;
import com.here.android.mpa.guidance.LaneInformation.RecommendationState;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class LaneInformationImpl extends BaseNativeObject {
    private static am<LaneInformation, LaneInformationImpl> a = null;

    private native void destroyNative();

    private native int getCenterMarkingNative();

    private native int getCrossingRestrictionNative();

    private native int getDirectionsNative();

    private native int getDividerMarkingNative();

    private native int getRecommendationStateNative();

    public native int getHeightRestriction();

    public native int getSpeedLimit();

    public native int getWidth();

    public native boolean isAccelerationNative();

    public native boolean isAuxLaneNative();

    public native boolean isCenterTurnNative();

    public native boolean isDecelerationNative();

    public native boolean isDriveableShoulderNative();

    public native boolean isExpressNative();

    public native boolean isHOVNative();

    public native boolean isPassingNative();

    public native boolean isRegularNative();

    public native boolean isRegulatedAccessNative();

    public native boolean isReversibleNative();

    public native boolean isSlowNative();

    public native boolean isSyntheticDirection();

    public native boolean isTruckParkingNative();

    public native boolean isTurnNative();

    @HybridPlusNative
    LaneInformationImpl(int i) {
        this.nativeptr = i;
    }

    static {
        ce.a(LaneInformation.class);
    }

    public static void a(am<LaneInformation, LaneInformationImpl> amVar) {
        a = amVar;
    }

    static LaneInformation a(LaneInformationImpl laneInformationImpl) {
        if (laneInformationImpl != null) {
            return (LaneInformation) a.a(laneInformationImpl);
        }
        return null;
    }

    static List<LaneInformation> a(List<LaneInformationImpl> list) {
        List<LaneInformation> arrayList = new ArrayList();
        for (LaneInformationImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public EnumSet<Direction> a() {
        EnumSet<Direction> noneOf = EnumSet.noneOf(Direction.class);
        if (this.nativeptr != 0) {
            return a(getDirectionsNative());
        }
        return noneOf;
    }

    static EnumSet<Direction> a(int i) {
        EnumSet<Direction> noneOf = EnumSet.noneOf(Direction.class);
        if (i == 0) {
            noneOf.add(Direction.UNDEFINED);
        }
        if ((i & 1) != 0) {
            noneOf.add(Direction.STRAIGHT);
        }
        if ((i & 2) != 0) {
            noneOf.add(Direction.SLIGHTLY_RIGHT);
        }
        if ((i & 4) != 0) {
            noneOf.add(Direction.RIGHT);
        }
        if ((i & 8) != 0) {
            noneOf.add(Direction.SHARP_RIGHT);
        }
        if ((i & 16) != 0) {
            noneOf.add(Direction.U_TURN_LEFT);
        }
        if ((i & 32) != 0) {
            noneOf.add(Direction.SHARP_LEFT);
        }
        if ((i & 64) != 0) {
            noneOf.add(Direction.LEFT);
        }
        if ((i & 128) != 0) {
            noneOf.add(Direction.SLIGHTLY_LEFT);
        }
        if ((i & 256) != 0) {
            noneOf.add(Direction.MERGE_RIGHT);
        }
        if ((i & 512) != 0) {
            noneOf.add(Direction.MERGE_LEFT);
        }
        if ((i & 1024) != 0) {
            noneOf.add(Direction.MERGE_LANES);
        }
        if ((i & 2048) != 0) {
            noneOf.add(Direction.U_TURN_RIGHT);
        }
        if ((i & 4096) != 0) {
            noneOf.add(Direction.SECOND_RIGHT);
        }
        if ((i & 8192) != 0) {
            noneOf.add(Direction.SECOND_LEFT);
        }
        return noneOf;
    }

    public RecommendationState b() {
        return RecommendationState.values()[getRecommendationStateNative()];
    }

    public MarkingType c() {
        return MarkingType.values()[getDividerMarkingNative()];
    }

    public MarkingType d() {
        return MarkingType.values()[getCenterMarkingNative()];
    }

    public CrossingRestriction e() {
        return CrossingRestriction.values()[getCrossingRestrictionNative()];
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }
}

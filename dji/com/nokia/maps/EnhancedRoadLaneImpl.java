package com.nokia.maps;

import com.here.android.mpa.guidance.EnhancedRoadLane;
import com.here.android.mpa.guidance.EnhancedRoadLane.Arrow;
import com.here.android.mpa.guidance.EnhancedRoadLane.CrossingRestriction;
import com.here.android.mpa.guidance.EnhancedRoadLane.Direction;
import com.here.android.mpa.guidance.EnhancedRoadLane.Marking;
import com.here.android.mpa.guidance.EnhancedRoadLane.Type;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class EnhancedRoadLaneImpl extends BaseNativeObject {
    private static am<EnhancedRoadLane, EnhancedRoadLaneImpl> a;

    private native void destroyNative();

    private native int getArrowsNative();

    private native int getCenterMarkingNative();

    private native int getCrossingRestrictionNative();

    private native int getDirectionNative();

    private native int getDividerMarkingNative();

    private native int getTypesNative();

    public native int getFromSpeedLimit();

    public native int getHeightRestriction();

    public native int getToSpeedLimit();

    public native int getWidth();

    @HybridPlusNative
    EnhancedRoadLaneImpl(int i) {
        this.nativeptr = i;
    }

    public static void a(am<EnhancedRoadLane, EnhancedRoadLaneImpl> amVar) {
        a = amVar;
    }

    static {
        ce.a(EnhancedRoadLane.class);
    }

    static EnhancedRoadLane a(EnhancedRoadLaneImpl enhancedRoadLaneImpl) {
        if (enhancedRoadLaneImpl != null) {
            return (EnhancedRoadLane) a.a(enhancedRoadLaneImpl);
        }
        return null;
    }

    static List<EnhancedRoadLane> a(List<EnhancedRoadLaneImpl> list) {
        List<EnhancedRoadLane> arrayList = new ArrayList();
        for (EnhancedRoadLaneImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public EnumSet<Type> a() {
        EnumSet<Type> noneOf = EnumSet.noneOf(Type.class);
        if (this.nativeptr != 0) {
            return a(getTypesNative());
        }
        return noneOf;
    }

    static EnumSet<Type> a(int i) {
        EnumSet<Type> noneOf = EnumSet.noneOf(Type.class);
        if ((i & 1) != 0) {
            noneOf.add(Type.REGULAR);
        }
        if ((i & 2) != 0) {
            noneOf.add(Type.HOV);
        }
        if ((i & 4) != 0) {
            noneOf.add(Type.REVERSIBLE);
        }
        if ((i & 8) != 0) {
            noneOf.add(Type.EXPRESS);
        }
        if ((i & 16) != 0) {
            noneOf.add(Type.ACCELERATION);
        }
        if ((i & 32) != 0) {
            noneOf.add(Type.DECELERATION);
        }
        if ((i & 64) != 0) {
            noneOf.add(Type.AUX_LANE);
        }
        if ((i & 128) != 0) {
            noneOf.add(Type.SLOW);
        }
        if ((i & 256) != 0) {
            noneOf.add(Type.PASSING);
        }
        if ((i & 512) != 0) {
            noneOf.add(Type.DRIVABLE_SHOULDER);
        }
        if ((i & 1024) != 0) {
            noneOf.add(Type.REGULATED_ACCESS);
        }
        if ((i & 2048) != 0) {
            noneOf.add(Type.TURN);
        }
        if ((i & 4096) != 0) {
            noneOf.add(Type.CENTRE_TURN);
        }
        if ((i & 8192) != 0) {
            noneOf.add(Type.TRUCK_PARKING);
        }
        return noneOf;
    }

    public EnumSet<Arrow> b() {
        EnumSet<Arrow> noneOf = EnumSet.noneOf(Arrow.class);
        if (this.nativeptr != 0) {
            return b(getArrowsNative());
        }
        return noneOf;
    }

    static EnumSet<Arrow> b(int i) {
        EnumSet<Arrow> noneOf = EnumSet.noneOf(Arrow.class);
        if ((i & 1) != 0) {
            noneOf.add(Arrow.STRAIGHT);
        }
        if ((i & 2) != 0) {
            noneOf.add(Arrow.SLIGHT_RIGHT);
        }
        if ((i & 4) != 0) {
            noneOf.add(Arrow.RIGHT);
        }
        if ((i & 8) != 0) {
            noneOf.add(Arrow.HARD_RIGHT);
        }
        if ((i & 16) != 0) {
            noneOf.add(Arrow.U_LEFT);
        }
        if ((i & 32) != 0) {
            noneOf.add(Arrow.HARD_LEFT);
        }
        if ((i & 64) != 0) {
            noneOf.add(Arrow.LEFT);
        }
        if ((i & 128) != 0) {
            noneOf.add(Arrow.SLIGHT_LEFT);
        }
        if ((i & 256) != 0) {
            noneOf.add(Arrow.MERGE_RIGHT);
        }
        if ((i & 512) != 0) {
            noneOf.add(Arrow.MERGE_LEFT);
        }
        if ((i & 1024) != 0) {
            noneOf.add(Arrow.MERGE_LANES);
        }
        if ((i & 2048) != 0) {
            noneOf.add(Arrow.U_RIGHT);
        }
        if ((i & 4096) != 0) {
            noneOf.add(Arrow.SECOND_RIGHT);
        }
        if ((i & 8192) != 0) {
            noneOf.add(Arrow.SECOND_LEFT);
        }
        return noneOf;
    }

    public Direction c() {
        return Direction.values()[getDirectionNative()];
    }

    public Marking d() {
        return Marking.values()[getDividerMarkingNative()];
    }

    public Marking e() {
        return Marking.values()[getCenterMarkingNative()];
    }

    public CrossingRestriction f() {
        return CrossingRestriction.values()[getCrossingRestrictionNative()];
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }
}

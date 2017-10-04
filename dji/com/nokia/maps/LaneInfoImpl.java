package com.nokia.maps;

import com.here.android.mpa.guidance.LaneInfo;
import com.here.android.mpa.guidance.LaneInfo.Directions;
import com.here.android.mpa.guidance.LaneInfo.Position;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class LaneInfoImpl extends BaseNativeObject {
    private static k<LaneInfo, LaneInfoImpl> a = null;
    private static am<LaneInfo, LaneInfoImpl> b = null;

    private native void destroyLaneInfoNative();

    private native int[] getDirectionsNative();

    public native int getOnRoutePositionNative();

    static {
        ce.a(LaneInfo.class);
    }

    public static void a(k<LaneInfo, LaneInfoImpl> kVar, am<LaneInfo, LaneInfoImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static LaneInfo a(LaneInfoImpl laneInfoImpl) {
        if (laneInfoImpl != null) {
            return (LaneInfo) b.a(laneInfoImpl);
        }
        return null;
    }

    static List<LaneInfo> a(List<LaneInfoImpl> list) {
        List<LaneInfo> arrayList = new ArrayList();
        for (LaneInfoImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    @HybridPlusNative
    private LaneInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyLaneInfoNative();
    }

    public EnumSet<Directions> a() {
        EnumSet<Directions> noneOf = EnumSet.noneOf(Directions.class);
        for (int i : getDirectionsNative()) {
            noneOf.add(Directions.values()[i]);
        }
        return noneOf;
    }

    public Position b() {
        return Position.values()[getOnRoutePositionNative()];
    }
}

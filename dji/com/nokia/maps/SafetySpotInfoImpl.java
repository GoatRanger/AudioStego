package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.SafetySpotInfo;
import com.here.android.mpa.mapping.SafetySpotInfo.Type;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class SafetySpotInfoImpl extends BaseNativeObject {
    private static am<SafetySpotInfo, SafetySpotInfoImpl> a;

    private native void destroySafetySpotNative();

    private final native GeoCoordinateImpl getInternalCoordinate();

    public final native int getHeading1Deg();

    public final native int getHeading2Deg();

    public final native int getSpeedLimit1();

    public final native int getSpeedLimit2();

    public final native int getTypeNative();

    @HybridPlusNative
    private SafetySpotInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroySafetySpotNative();
    }

    public final GeoCoordinate a() {
        return GeoCoordinateImpl.create(getInternalCoordinate());
    }

    public final Type b() {
        return a(getTypeNative());
    }

    private static Type a(int i) {
        switch (i) {
            case 1:
                return Type.SPEED_CAMERA;
            case 2:
                return Type.REDLIGHT_CAMERA;
            case 3:
                return Type.SPEED_REDLIGHT_CAMERA;
            default:
                return Type.UNDEFINED;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ce.a(stringBuilder, "Coordinate", a().toString(), false);
        ce.a(stringBuilder, "Heading 1", Integer.toString(getHeading1Deg()), true);
        ce.a(stringBuilder, "Heading 2", Integer.toString(getHeading2Deg()), true);
        ce.a(stringBuilder, "Speed Limit 1", Integer.toString(getSpeedLimit1()), true);
        ce.a(stringBuilder, "Speed Limit 2", Integer.toString(getSpeedLimit2()), true);
        return stringBuilder.toString();
    }

    public static void a(am<SafetySpotInfo, SafetySpotInfoImpl> amVar) {
        a = amVar;
    }

    static SafetySpotInfo a(SafetySpotInfoImpl safetySpotInfoImpl) {
        if (safetySpotInfoImpl != null) {
            return (SafetySpotInfo) a.a(safetySpotInfoImpl);
        }
        return null;
    }

    static {
        ce.a(SafetySpotInfo.class);
    }
}

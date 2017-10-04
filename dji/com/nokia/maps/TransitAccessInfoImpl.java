package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.mapping.OperatingHours;
import com.here.android.mpa.mapping.TransitAccessInfo;
import com.here.android.mpa.mapping.TransitAccessInfo.Attribute;
import com.here.android.mpa.mapping.TransitAccessInfo.Method;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;

@Online
public class TransitAccessInfoImpl extends BaseNativeObject {
    private static am<TransitAccessInfo, TransitAccessInfoImpl> b;
    private cq a = new cq(TransitAccessInfoImpl.class.getName());

    private native void destroyTransitAccessInfoNative();

    private native int[] getAttributesNative();

    private final native GeoCoordinateImpl getCoordinateNative();

    private native int[] getEntranceMethodsNative();

    private native int[] getExitMethodsNative();

    private final native IdentifierImpl getIdNative();

    private final native OperatingHoursImpl getOpeningHoursImpl();

    private final native IdentifierImpl getStopIdNative();

    private native int[] getTransitTypesNative();

    public final native int getLevel();

    public final native String getName();

    @OnlineNative
    private TransitAccessInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitAccessInfoNative();
    }

    public final GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public final Identifier b() {
        return IdentifierImpl.a(getIdNative());
    }

    public final Identifier c() {
        return IdentifierImpl.a(getStopIdNative());
    }

    public final EnumSet<TransitType> d() {
        EnumSet<TransitType> noneOf = EnumSet.noneOf(TransitType.class);
        for (int valueOf : getTransitTypesNative()) {
            noneOf.add(TransitTypeImpl.valueOf(valueOf));
        }
        return noneOf;
    }

    public final EnumSet<Attribute> e() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int a : getAttributesNative()) {
            noneOf.add(a(a));
        }
        return noneOf;
    }

    public final EnumSet<Method> f() {
        EnumSet<Method> noneOf = EnumSet.noneOf(Method.class);
        for (int b : getEntranceMethodsNative()) {
            noneOf.add(b(b));
        }
        return noneOf;
    }

    public final EnumSet<Method> g() {
        EnumSet<Method> noneOf = EnumSet.noneOf(Method.class);
        for (int b : getExitMethodsNative()) {
            noneOf.add(b(b));
        }
        return noneOf;
    }

    public OperatingHours h() {
        return OperatingHoursImpl.a(getOpeningHoursImpl());
    }

    private static final Attribute a(int i) {
        switch (i) {
            case 0:
                return Attribute.ALLOWS_ENTERING;
            case 1:
                return Attribute.ALLOWS_EXITING;
            case 2:
                return Attribute.ACCESSIBLE_TO_DISABLED;
            case 3:
                return Attribute.LEVEL_DIFFERENT_FROM_GROUND;
            case 4:
                return Attribute.HAS_NAMES;
            default:
                throw new IllegalArgumentException("Attribute value not supported.");
        }
    }

    private static final Method b(int i) {
        switch (i) {
            case 0:
                return Method.STAIRS;
            case 1:
                return Method.ESCALATOR;
            case 2:
                return Method.ELEVATOR;
            case 3:
                return Method.PEDESTRIAN_RAMP;
            default:
                throw new IllegalArgumentException("Method value not supported.");
        }
    }

    public static void a(am<TransitAccessInfo, TransitAccessInfoImpl> amVar) {
        b = amVar;
    }

    static TransitAccessInfo a(TransitAccessInfoImpl transitAccessInfoImpl) {
        if (transitAccessInfoImpl != null) {
            return (TransitAccessInfo) b.a(transitAccessInfoImpl);
        }
        return null;
    }

    static {
        ce.a(TransitAccessInfo.class);
    }
}

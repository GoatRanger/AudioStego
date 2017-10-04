package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.mapping.TransitLineInfo;
import com.here.android.mpa.mapping.TransitLineInfo.Attribute;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.EnumSet;

@Online
public class TransitLineInfoImpl extends BaseNativeObject {
    private static am<TransitLineInfo, TransitLineInfoImpl> b;
    private cq a = new cq(TransitLineInfoImpl.class.getName());

    private native void destroyTransitLineInfoNative();

    private native int getAlpha();

    private native int[] getAttributesNative();

    private native int getBlue();

    private native int getGreen();

    private final native IdentifierImpl getIdNative();

    private native int getRed();

    private final native IdentifierImpl getSystemIdNative();

    public final native String getInformalName();

    public final native String getOfficialName();

    public final native String getShortName();

    public final native TransitType getTransitType();

    @OnlineNative
    private TransitLineInfoImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitLineInfoNative();
    }

    public final Identifier a() {
        return IdentifierImpl.a(getIdNative());
    }

    public final Identifier b() {
        return IdentifierImpl.a(getSystemIdNative());
    }

    public final EnumSet<Attribute> c() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int a : getAttributesNative()) {
            noneOf.add(a(a));
        }
        return noneOf;
    }

    public final int d() {
        return Color.argb(getAlpha(), getRed(), getGreen(), getBlue());
    }

    private static final Attribute a(int i) {
        switch (i) {
            case 0:
                return Attribute.EXPRESS;
            case 1:
                return Attribute.ACCESSIBLE_TO_DISABLED;
            case 2:
                return Attribute.LUGGAGE_RACKS;
            case 3:
                return Attribute.ONBOARD_TOILETS;
            case 4:
                return Attribute.ONBOARD_FOOD;
            case 5:
                return Attribute.SMOKING_ALLOWED;
            case 6:
                return Attribute.SLEEPING_CARS;
            default:
                throw new IllegalArgumentException("Unsupported enum value: " + i);
        }
    }

    public static void a(am<TransitLineInfo, TransitLineInfoImpl> amVar) {
        b = amVar;
    }

    static TransitLineInfo a(TransitLineInfoImpl transitLineInfoImpl) {
        if (transitLineInfoImpl != null) {
            return (TransitLineInfo) b.a(transitLineInfoImpl);
        }
        return null;
    }

    static {
        ce.a(TransitLineInfo.class);
    }
}

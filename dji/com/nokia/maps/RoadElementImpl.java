package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.common.RoadElement.Attribute;
import com.here.android.mpa.common.RoadElement.FormOfWay;
import com.here.android.mpa.common.RoadElement.PluralType;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@Online
public class RoadElementImpl extends BaseNativeObject {
    private static am<RoadElement, RoadElementImpl> b;
    private static k<RoadElement, RoadElementImpl> c;
    private cq a = new cq(RoadElementImpl.class.getName());

    private native void destroyRoadElementNative();

    private native int[] getAttributesNative();

    private native GeoCoordinateImpl[] getGeometryNative();

    private native String getIdentifierNative();

    private static native RoadElementImpl getRoadElementNative(GeoCoordinateImpl geoCoordinateImpl, String str);

    private static native RoadElementImpl[] getRoadElementsNative(GeoBoundingBoxImpl geoBoundingBoxImpl, String str);

    private final native long getStartTimeNative();

    private native int native_getFormOfWay();

    public native float getDefaultSpeed();

    public native double getGeometryLength();

    public native int getNumberOfLanes();

    public native PluralType getPluralType();

    public native String getRoadName();

    public native String getRouteName();

    public native float getSpeedLimit();

    public native float getStaticSpeed();

    public native boolean isPedestrian();

    public native boolean isPlural();

    public native boolean isValid();

    public static void a(k<RoadElement, RoadElementImpl> kVar, am<RoadElement, RoadElementImpl> amVar) {
        b = amVar;
        c = kVar;
    }

    static {
        ce.a(RoadElement.class);
    }

    static RoadElementImpl a(RoadElement roadElement) {
        if (c != null) {
            return (RoadElementImpl) c.a(roadElement);
        }
        return null;
    }

    static RoadElement a(RoadElementImpl roadElementImpl) {
        if (roadElementImpl != null) {
            return (RoadElement) b.a(roadElementImpl);
        }
        return null;
    }

    static List<RoadElement> a(RoadElementImpl[] roadElementImplArr) {
        List<RoadElement> arrayList = new ArrayList();
        if (roadElementImplArr != null) {
            for (RoadElementImpl a : roadElementImplArr) {
                RoadElement a2 = a(a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    @OnlineNative
    RoadElementImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRoadElementNative();
        }
    }

    public EnumSet<Attribute> a() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int i : getAttributesNative()) {
            noneOf.add(Attribute.values()[i]);
        }
        return noneOf;
    }

    public FormOfWay b() {
        return FormOfWay.values()[native_getFormOfWay()];
    }

    public Date c() {
        long startTimeNative = getStartTimeNative();
        if (startTimeNative > 0) {
            return new Date(startTimeNative);
        }
        return null;
    }

    public List<GeoCoordinate> d() {
        return GeoCoordinateImpl.b(Arrays.asList(getGeometryNative()));
    }

    public static List<RoadElement> a(GeoBoundingBox geoBoundingBox, String str) {
        return a(getRoadElementsNative(GeoBoundingBoxImpl.get(geoBoundingBox), str));
    }

    public static RoadElement a(GeoCoordinate geoCoordinate, String str) {
        return a(getRoadElementNative(GeoCoordinateImpl.get(geoCoordinate), str));
    }

    public Identifier e() {
        String identifierNative = getIdentifierNative();
        IdentifierImpl identifierImpl = null;
        if (identifierNative.charAt(0) == '\u0001') {
            identifierImpl = new IdentifierImpl(a.SINGLE, identifierNative.substring(1));
        }
        return IdentifierImpl.a(identifierImpl);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RoadElementImpl)) {
            return false;
        }
        return getIdentifierNative().equals(((RoadElementImpl) obj).getIdentifierNative());
    }

    public int hashCode() {
        return getIdentifierNative().hashCode();
    }
}

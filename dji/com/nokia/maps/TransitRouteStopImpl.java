package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.routing.TransitRouteStop;
import com.here.android.mpa.routing.TransitRouteStop.Attribute;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.EnumSet;

@HybridPlus
public class TransitRouteStopImpl extends BaseNativeObject {
    private static k<TransitRouteStop, TransitRouteStopImpl> b = null;
    private static am<TransitRouteStop, TransitRouteStopImpl> c = null;
    private cq a;

    private native void destroyTransitRouteStopNative();

    private native int[] getAttributesNative();

    private native GeoCoordinateImpl getEgressCoordinateNative();

    private final native IdentifierImpl getIdNative();

    private native GeoCoordinateImpl getPlatformCoordinateNative();

    public native String getName();

    public native int getPlatformLevel();

    static {
        ce.a(TransitRouteStop.class);
    }

    public static void a(k<TransitRouteStop, TransitRouteStopImpl> kVar, am<TransitRouteStop, TransitRouteStopImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static TransitRouteStop a(TransitRouteStopImpl transitRouteStopImpl) {
        if (transitRouteStopImpl != null) {
            return (TransitRouteStop) c.a(transitRouteStopImpl);
        }
        return null;
    }

    public TransitRouteStopImpl() {
        this.a = new cq(TransitRouteStopImpl.class.getName());
        this.nativeptr = 0;
    }

    @HybridPlusNative
    private TransitRouteStopImpl(int i) {
        this.a = new cq(TransitRouteStopImpl.class.getName());
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitRouteStopNative();
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getPlatformCoordinateNative());
    }

    public Identifier b() {
        return IdentifierImpl.a(getIdNative());
    }

    public EnumSet<Attribute> c() {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (int i : getAttributesNative()) {
            noneOf.add(Attribute.values()[i]);
        }
        return noneOf;
    }

    public GeoCoordinate d() {
        return GeoCoordinateImpl.create(getPlatformCoordinateNative());
    }

    public GeoCoordinate e() {
        return GeoCoordinateImpl.create(getPlatformCoordinateNative());
    }
}

package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.TransitManeuver.TransitLineStyle;
import com.here.android.mpa.routing.TransitRouteElement;
import com.here.android.mpa.routing.TransitRouteStop;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@HybridPlus
public class TransitRouteElementImpl extends BaseNativeObject {
    private static k<TransitRouteElement, TransitRouteElementImpl> b = null;
    private static am<TransitRouteElement, TransitRouteElementImpl> c = null;
    private cq a;

    private native void destroyTransitRouteElementNative();

    private native TransitRouteStopImpl getArrivalStation();

    private final native long getArrivalTimeNative();

    private native TransitRouteStopImpl getDepartureStation();

    private final native long getDepartureTimeNative();

    private native GeoCoordinateImpl[] getGeometryNative();

    private final native IdentifierImpl getIdNative();

    private native GeoCoordinateImpl[] getLineGeometryNative();

    private native int getLineStyleNative();

    private native int getPrimaryLineAlpha();

    private native int getPrimaryLineBlue();

    private native int getPrimaryLineGreen();

    private native int getPrimaryLineRed();

    private native int getSecondaryLineAlpha();

    private native int getSecondaryLineBlue();

    private native int getSecondaryLineGreen();

    private native int getSecondaryLineRed();

    private native ImageImpl getSystemAccessLogoNative();

    private native ImageImpl getSystemLogoNative();

    private native int getTransitTypeNative();

    public native String getDestination();

    public native String getLineName();

    public native String getSystemInformalName();

    public native String getSystemOfficialName();

    public native String getSystemShortName();

    public native int getTotalDuration();

    public native String getTransitTypeName();

    public native int getVehicleTravelTime();

    public native boolean hasPrimaryLineColor();

    public native boolean hasSecondaryLineColor();

    static {
        ce.a(TransitRouteElement.class);
    }

    public static void a(k<TransitRouteElement, TransitRouteElementImpl> kVar, am<TransitRouteElement, TransitRouteElementImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static TransitRouteElementImpl a(TransitRouteElement transitRouteElement) {
        return (TransitRouteElementImpl) b.a(transitRouteElement);
    }

    static TransitRouteElement a(TransitRouteElementImpl transitRouteElementImpl) {
        if (transitRouteElementImpl != null) {
            return (TransitRouteElement) c.a(transitRouteElementImpl);
        }
        return null;
    }

    static List<TransitRouteElement> a(List<TransitRouteElementImpl> list) {
        List<TransitRouteElement> arrayList = new ArrayList();
        for (TransitRouteElementImpl a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public TransitRouteElementImpl() {
        this.a = new cq(TransitRouteElementImpl.class.getName());
        this.nativeptr = 0;
    }

    @HybridPlusNative
    private TransitRouteElementImpl(int i) {
        this.a = new cq(TransitRouteElementImpl.class.getName());
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyTransitRouteElementNative();
    }

    public List<GeoCoordinate> a() {
        return GeoCoordinateImpl.b(Arrays.asList(getGeometryNative()));
    }

    public List<GeoCoordinate> b() {
        return GeoCoordinateImpl.b(Arrays.asList(getLineGeometryNative()));
    }

    public int c() {
        if (hasPrimaryLineColor()) {
            return Color.argb(getPrimaryLineAlpha(), getPrimaryLineRed(), getPrimaryLineGreen(), getPrimaryLineBlue());
        }
        return 0;
    }

    public int d() {
        if (hasSecondaryLineColor()) {
            return Color.argb(getSecondaryLineAlpha(), getSecondaryLineRed(), getSecondaryLineGreen(), getSecondaryLineBlue());
        }
        return 0;
    }

    public TransitLineStyle e() {
        return TransitLineStyle.values()[getLineStyleNative()];
    }

    public Image f() {
        return ImageImpl.a(getSystemLogoNative());
    }

    public Image g() {
        return ImageImpl.a(getSystemAccessLogoNative());
    }

    public TransitType h() {
        return TransitTypeImpl.valueOf(getTransitTypeNative());
    }

    public Date i() {
        long departureTimeNative = getDepartureTimeNative();
        if (departureTimeNative > 0) {
            return new Date(departureTimeNative);
        }
        return null;
    }

    public Date j() {
        long arrivalTimeNative = getArrivalTimeNative();
        if (arrivalTimeNative > 0) {
            return new Date(arrivalTimeNative);
        }
        return null;
    }

    public TransitRouteStop k() {
        return TransitRouteStopImpl.a(getDepartureStation());
    }

    public TransitRouteStop l() {
        return TransitRouteStopImpl.a(getArrivalStation());
    }

    public Identifier m() {
        return IdentifierImpl.a(getIdNative());
    }
}

package com.nokia.maps;

import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.TransitManeuver;
import com.here.android.mpa.routing.TransitManeuver.TransitLineStyle;
import com.here.android.mpa.routing.TransitRouteElement;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.Arrays;
import java.util.List;

@HybridPlus
public class TransitManeuverImpl extends ManeuverImpl {
    private static k<TransitManeuver, TransitManeuverImpl> b = null;
    private static am<TransitManeuver, TransitManeuverImpl> c = null;
    private cq a = new cq(TransitManeuverImpl.class.getName());

    private native TransitRouteElementImpl[] getTransitRouteElementsNative();

    public native String getArrivalStopName();

    public native String getDepartureStopName();

    public native String getLineName();

    public native String getSystemOfficialName();

    public native String getSystemShortName();

    public native String getTerminusStopName();

    public native int getTransitTravelTime();

    public native TransitType getTransitType();

    public native String getTransitTypeName();

    static {
        ce.a(TransitManeuver.class);
    }

    public static void b(k<TransitManeuver, TransitManeuverImpl> kVar, am<TransitManeuver, TransitManeuverImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static TransitManeuver a(TransitManeuverImpl transitManeuverImpl) {
        if (transitManeuverImpl != null) {
            return (TransitManeuver) c.a(transitManeuverImpl);
        }
        return null;
    }

    @HybridPlusNative
    TransitManeuverImpl(int i) {
        super(i);
    }

    public boolean n() {
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        if (transitRouteElementImpl == null) {
            return false;
        }
        return transitRouteElementImpl.hasPrimaryLineColor();
    }

    public boolean o() {
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        if (transitRouteElementImpl == null) {
            return false;
        }
        return transitRouteElementImpl.hasSecondaryLineColor();
    }

    public int p() {
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        if (transitRouteElementImpl != null) {
            return transitRouteElementImpl.c();
        }
        return 0;
    }

    public int q() {
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        if (transitRouteElementImpl != null) {
            return transitRouteElementImpl.d();
        }
        return 0;
    }

    public TransitLineStyle r() {
        TransitLineStyle transitLineStyle = TransitLineStyle.UNDEFINED;
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        if (transitRouteElementImpl != null) {
            return transitRouteElementImpl.e();
        }
        return transitLineStyle;
    }

    public String s() {
        TransitRouteElementImpl transitRouteElementImpl = getTransitRouteElementsNative()[0];
        return transitRouteElementImpl == null ? "" : transitRouteElementImpl.getSystemInformalName();
    }

    public List<TransitRouteElement> v() {
        return TransitRouteElementImpl.a(Arrays.asList(getTransitRouteElementsNative()));
    }
}

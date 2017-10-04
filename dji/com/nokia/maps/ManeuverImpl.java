package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Maneuver.Action;
import com.here.android.mpa.routing.Maneuver.Icon;
import com.here.android.mpa.routing.Maneuver.TrafficDirection;
import com.here.android.mpa.routing.Maneuver.Turn;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.Signpost;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Online
public class ManeuverImpl extends BaseNativeObject {
    private static k<Maneuver, ManeuverImpl> a = null;
    private static am<Maneuver, ManeuverImpl> b = null;

    private native void destroyManeuverNative();

    private native int getActionNative();

    private native GeoBoundingBoxImpl getBoundingBoxNative();

    private native GeoCoordinateImpl getCoordinateNative();

    private native int getIconNative();

    private native GeoCoordinateImpl[] getManeuverGeometryNative();

    private native ImageImpl getNextRoadImageNative();

    private native RoadElementImpl[] getRoadElementsNative();

    private native RouteElementImpl[] getRouteElementsNative();

    private native SignpostImpl getSignpostNative();

    private final native long getStartTimeNative();

    private native int getTrafficDirectionNative();

    private native int getTransportModeNative();

    private native int getTurnNative();

    public native int getAngle();

    public native int getDistanceFromPreviousManeuver();

    public native int getDistanceFromStart();

    public native int getDistanceToNextManeuver();

    public native int getMapOrientation();

    public native String getNextRoadName();

    public native String getNextRoadNumber();

    public native String getRoadName();

    public native String getRoadNumber();

    public native boolean isTransit();

    static {
        ce.a(Maneuver.class);
    }

    public static void a(k<Maneuver, ManeuverImpl> kVar, am<Maneuver, ManeuverImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static ManeuverImpl a(Maneuver maneuver) {
        return (ManeuverImpl) a.a(maneuver);
    }

    public static Maneuver a(ManeuverImpl maneuverImpl) {
        Maneuver maneuver;
        if (maneuverImpl != null) {
            try {
                maneuver = (Maneuver) b.a(maneuverImpl);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        maneuver = null;
        return maneuver;
    }

    static List<Maneuver> a(ManeuverImpl[] maneuverImplArr) {
        List arrayList = new ArrayList();
        for (ManeuverImpl maneuverImpl : maneuverImplArr) {
            Object a;
            if (maneuverImpl instanceof TransitManeuverImpl) {
                a = TransitManeuverImpl.a((TransitManeuverImpl) maneuverImpl);
            } else {
                a = a(maneuverImpl);
            }
            arrayList.add(a);
        }
        return arrayList;
    }

    @OnlineNative
    protected ManeuverImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyManeuverNative();
        }
    }

    public GeoCoordinate a() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public GeoBoundingBox b() {
        return GeoBoundingBoxImpl.create(getBoundingBoxNative());
    }

    public Action c() {
        return Action.values()[getActionNative()];
    }

    public Turn d() {
        return Turn.values()[getTurnNative()];
    }

    public TransportMode e() {
        return TransportMode.getMode(getTransportModeNative());
    }

    public TrafficDirection f() {
        return TrafficDirection.values()[getTrafficDirectionNative()];
    }

    public Icon g() {
        return Icon.values()[getIconNative()];
    }

    public Date h() {
        long startTimeNative = getStartTimeNative();
        if (startTimeNative > 0) {
            return new Date(startTimeNative);
        }
        return null;
    }

    public List<RoadElement> i() {
        return RoadElementImpl.a(getRoadElementsNative());
    }

    public List<RouteElement> j() {
        return RouteElementImpl.b(Arrays.asList(getRouteElementsNative()));
    }

    public List<GeoCoordinate> k() {
        return GeoCoordinateImpl.b(Arrays.asList(getManeuverGeometryNative()));
    }

    public Signpost l() {
        return SignpostImpl.a(getSignpostNative());
    }

    public Image m() {
        return ImageImpl.a(getNextRoadImageNative());
    }
}

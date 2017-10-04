package com.nokia.maps;

import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.HazardousGoodType;
import com.here.android.mpa.routing.RouteOptions.PublicTransportLinkFlag;
import com.here.android.mpa.routing.RouteOptions.TimeType;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RouteOptions.TruckType;
import com.here.android.mpa.routing.RouteOptions.TunnelCategory;
import com.here.android.mpa.routing.RouteOptions.Type;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Online
public class RouteOptionsImpl extends BaseNativeObject {
    private static k<RouteOptions, RouteOptionsImpl> b = null;
    private static am<RouteOptions, RouteOptionsImpl> c = null;
    private cq a = new cq(RouteOptionsImpl.class.getName());
    private EnumSet<TransitType> d = EnumSet.noneOf(TransitType.class);
    private Date e;
    private Map<String, String> f = new HashMap();

    private native void clearTruckShippedHazardousGoodsNative();

    private native void createRouteOptionsNative();

    private native void destroyRouteOptionsNative();

    private native int getTimeNative(Date date);

    private native int getTruckHeightNative();

    private native int getTruckLengthNative();

    private native int getTruckLimitedWeightNative();

    private native int[] getTruckShippedHazardousGoodsNative();

    private native int getTruckTunnelCategoryNative();

    private native int getTruckTypeNative();

    private native int getTruckWeightPerAxleNative();

    private native int getTruckWidthNative();

    private native boolean native_getPublicTransportLinkFlag(int i);

    private native boolean native_getPublicTransportTypeAllowed(int i);

    private native int native_getRouteMode();

    private native int native_getRouteType();

    private native void native_setPublicTransportLinkFlag(int i, boolean z);

    private native void native_setPublicTransportTypeAllowed(int i, boolean z);

    private native void native_setRouteMode(int i);

    private native void native_setRouteType(int i);

    private native void setFetchElevationDataNative(boolean z);

    private native void setTime(long j, int i);

    private native void setTruckHeightNative(int i);

    private native void setTruckLengthNative(int i);

    private native void setTruckLimitedWeightNative(int i);

    private native void setTruckShippedHazardousGoodsNative(int[] iArr);

    private native void setTruckTunnelCategoryNative(int i);

    private native void setTruckTypeNative(int i);

    private native void setTruckWeightPerAxleNative(int i);

    private native void setTruckWidthNative(int i);

    public native boolean getAllowCarShuttleTrains();

    public native boolean getAllowCarpool();

    public native boolean getAllowDirtRoads();

    public native boolean getAllowFerries();

    public native boolean getAllowHighways();

    public native boolean getAllowParks();

    public native boolean getAllowTollRoads();

    public native boolean getAllowTunnels();

    public native int getRouteCount();

    public native int getStartDirection();

    public native int getTrailersCountNative();

    public native int getTransitMaximumChanges();

    public native int getTransitMinimumChangeTime();

    public native float getTransitWalkTimeMultiplier();

    public native void setAllowCarShuttleTrains(boolean z);

    public native void setAllowCarpool(boolean z);

    public native void setAllowDirtRoads(boolean z);

    public native void setAllowFerries(boolean z);

    public native void setAllowHighways(boolean z);

    public native void setAllowParks(boolean z);

    public native void setAllowTollRoads(boolean z);

    public native void setAllowTunnels(boolean z);

    public native void setRouteCount(int i);

    public native void setStartDirection(int i);

    public native void setTrailersCountNative(int i);

    public native void setTransitMaximumChanges(int i);

    public native void setTransitMinimumChangeTime(int i);

    public native void setTransitWalkTimeMultiplier(float f);

    static {
        ce.a(RouteOptions.class);
    }

    public static void a(k<RouteOptions, RouteOptionsImpl> kVar, am<RouteOptions, RouteOptionsImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    @OnlineNative
    static RouteOptionsImpl get(RouteOptions routeOptions) {
        return (RouteOptionsImpl) b.a(routeOptions);
    }

    @OnlineNative
    static RouteOptions create(RouteOptionsImpl routeOptionsImpl) {
        if (routeOptionsImpl != null) {
            return (RouteOptions) c.a(routeOptionsImpl);
        }
        return null;
    }

    public RouteOptionsImpl() {
        super(true);
        createRouteOptionsNative();
        a(new Date(System.currentTimeMillis()), TimeType.DEPARTURE);
    }

    @OnlineNative
    private RouteOptionsImpl(int i) {
        super(true);
        this.nativeptr = i;
        for (TransitType transitType : TransitType.values()) {
            if (a(transitType)) {
                this.d.add(transitType);
            }
        }
        Date date = new Date(System.currentTimeMillis());
        a(date, a(date));
    }

    public RouteOptionsImpl(RouteOptions routeOptions) {
        super(true);
        createRouteOptionsNative();
        RouteOptionsImpl routeOptionsImpl = get(routeOptions);
        setAllowCarpool(routeOptionsImpl.getAllowCarpool());
        setAllowCarShuttleTrains(routeOptionsImpl.getAllowCarShuttleTrains());
        setAllowDirtRoads(routeOptionsImpl.getAllowDirtRoads());
        setAllowFerries(routeOptionsImpl.getAllowFerries());
        setAllowHighways(routeOptionsImpl.getAllowHighways());
        setAllowParks(routeOptionsImpl.getAllowParks());
        setAllowTollRoads(routeOptionsImpl.getAllowTollRoads());
        setAllowTunnels(routeOptionsImpl.getAllowTunnels());
        for (String str : routeOptionsImpl.f.keySet()) {
            a(str, (String) routeOptionsImpl.f.get(str));
        }
        for (PublicTransportLinkFlag publicTransportLinkFlag : PublicTransportLinkFlag.values()) {
            a(publicTransportLinkFlag, routeOptionsImpl.a(publicTransportLinkFlag));
        }
        for (TransitType transitType : TransitType.values()) {
            a(transitType, routeOptionsImpl.a(transitType));
        }
        setRouteCount(routeOptionsImpl.getRouteCount());
        a(routeOptionsImpl.a());
        setStartDirection(routeOptionsImpl.getStartDirection());
        setTransitMaximumChanges(routeOptionsImpl.getTransitMaximumChanges());
        setTransitMinimumChangeTime(routeOptionsImpl.getTransitMinimumChangeTime());
        setTransitWalkTimeMultiplier(routeOptionsImpl.getTransitWalkTimeMultiplier());
        a(routeOptionsImpl.b());
        a(routeOptionsImpl);
        Date date = routeOptionsImpl.e != null ? routeOptionsImpl.e : new Date();
        a(date, routeOptionsImpl.a(date));
    }

    private void a(RouteOptionsImpl routeOptionsImpl) {
        c(routeOptionsImpl.g());
        e(routeOptionsImpl.i());
        a(routeOptionsImpl.e());
        a(routeOptionsImpl.d());
        a(routeOptionsImpl.j());
        a(routeOptionsImpl.k());
        b(routeOptionsImpl.f());
        d(routeOptionsImpl.h());
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRouteOptionsNative();
        }
    }

    public void a(TransitType transitType, boolean z) {
        if (z) {
            this.d.add(transitType);
        } else {
            this.d.remove(transitType);
        }
        if (transitType.ordinal() <= TransitType.UNKNOWN.ordinal()) {
            native_setPublicTransportTypeAllowed(transitType.ordinal(), z);
        }
    }

    public boolean a(TransitType transitType) {
        if (transitType.ordinal() <= TransitType.UNKNOWN.ordinal()) {
            return native_getPublicTransportTypeAllowed(transitType.ordinal());
        }
        return this.d.contains(transitType);
    }

    public void a(PublicTransportLinkFlag publicTransportLinkFlag, boolean z) {
        native_setPublicTransportLinkFlag(publicTransportLinkFlag.ordinal(), z);
    }

    public boolean a(PublicTransportLinkFlag publicTransportLinkFlag) {
        return native_getPublicTransportLinkFlag(publicTransportLinkFlag.ordinal());
    }

    public Type a() {
        return Type.values()[native_getRouteType()];
    }

    public void a(Type type) {
        native_setRouteType(type.ordinal());
    }

    public TransportMode b() {
        return TransportMode.getMode(native_getRouteMode());
    }

    public void a(TransportMode transportMode) {
        native_setRouteMode(transportMode.value());
    }

    public void a(Date date, TimeType timeType) {
        this.e = new Date(date.getTime());
        setTime(this.e.getTime(), timeType.value());
    }

    public TimeType a(Date date) {
        switch (getTimeNative(date)) {
            case 1:
                return TimeType.ARRIVAL;
            default:
                return TimeType.DEPARTURE;
        }
    }

    public void a(String str, String str2) {
        dy.a((Object) str, "Name is null");
        dy.a(!str.isEmpty(), "Name is empty");
        dy.a((Object) str2, "Value is null");
        this.f.put(str, str2);
    }

    public Map<String, String> c() {
        return this.f;
    }

    public void a(EnumSet<HazardousGoodType> enumSet) {
        dy.a((Object) enumSet, "Hazardous goods set is null");
        clearTruckShippedHazardousGoodsNative();
        int[] iArr = new int[enumSet.size()];
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            iArr[i] = ((HazardousGoodType) it.next()).toInt();
            i = i2;
        }
        setTruckShippedHazardousGoodsNative(iArr);
    }

    public EnumSet<HazardousGoodType> d() {
        EnumSet<HazardousGoodType> noneOf = EnumSet.noneOf(HazardousGoodType.class);
        for (int type : getTruckShippedHazardousGoodsNative()) {
            noneOf.add(HazardousGoodType.getType(type));
        }
        return noneOf;
    }

    public void a(float f) {
        boolean z = f > 0.0f || Float.isNaN(f);
        dy.a(z, "Limited weight must be greater than zero");
        setTruckLimitedWeightNative(f(f));
    }

    public float e() {
        return a(getTruckLimitedWeightNative());
    }

    public void b(float f) {
        boolean z = f > 0.0f || Float.isNaN(f);
        dy.a(z, "Weight per axle must be greater than zero");
        setTruckWeightPerAxleNative(f(f));
    }

    public float f() {
        return a(getTruckWeightPerAxleNative());
    }

    public void c(float f) {
        boolean z = f > 0.0f || Float.isNaN(f);
        dy.a(z, "Height must be greater than zero");
        setTruckHeightNative(g(f));
    }

    public float g() {
        return b(getTruckHeightNative());
    }

    public void d(float f) {
        boolean z = f > 0.0f || Float.isNaN(f);
        dy.a(z, "Width must be greater than zero");
        setTruckWidthNative(g(f));
    }

    public float h() {
        return b(getTruckWidthNative());
    }

    public void e(float f) {
        boolean z = f > 0.0f || Float.isNaN(f);
        dy.a(z, "Length must be greater than zero");
        setTruckLengthNative(g(f));
    }

    public float i() {
        return b(getTruckLengthNative());
    }

    public void a(TunnelCategory tunnelCategory) {
        dy.a((Object) tunnelCategory, "TunnelCategory value is null");
        setTruckTunnelCategoryNative(tunnelCategory.toInt());
    }

    public TunnelCategory j() {
        return TunnelCategory.getCategory(getTruckTunnelCategoryNative());
    }

    public void a(TruckType truckType) {
        dy.a((Object) truckType, "TruckType is null");
        setTruckTypeNative(truckType.toInt());
    }

    public TruckType k() {
        return TruckType.getType(getTruckTypeNative());
    }

    public void a(boolean z) {
        setFetchElevationDataNative(z);
    }

    private static int f(float f) {
        if (Float.isNaN(f)) {
            return 0;
        }
        return (int) (1000.0f * f);
    }

    private static float a(int i) {
        if (i == 0) {
            return Float.NaN;
        }
        return 0.001f * ((float) i);
    }

    private static int g(float f) {
        if (Float.isNaN(f)) {
            return 0;
        }
        return (int) (DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * f);
    }

    private static float b(int i) {
        if (i == 0) {
            return Float.NaN;
        }
        return 0.01f * ((float) i);
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + 31) * 31)) * 31;
        if (getAllowCarpool()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowCarShuttleTrains()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowDirtRoads()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowFerries()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowHighways()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowParks()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowTollRoads()) {
            i = 1231;
        } else {
            i = 1237;
        }
        hashCode = (i + hashCode) * 31;
        if (getAllowTunnels()) {
            i = 1231;
        } else {
            i = 1237;
        }
        i += hashCode;
        PublicTransportLinkFlag[] values = PublicTransportLinkFlag.values();
        int length = values.length;
        hashCode = 0;
        while (hashCode < length) {
            int i3 = i * 31;
            if (a(values[hashCode])) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode++;
            i = i3 + i;
        }
        TransitType[] values2 = TransitType.values();
        length = values2.length;
        hashCode = 0;
        while (hashCode < length) {
            i3 = i * 31;
            if (a(values2[hashCode])) {
                i = 1231;
            } else {
                i = 1237;
            }
            hashCode++;
            i = i3 + i;
        }
        i = ((int) (((float) (((((((((a() == null ? 0 : a().hashCode()) + (((i * 31) + getRouteCount()) * 31)) * 31) + getStartDirection()) * 31) + getTransitMaximumChanges()) * 31) + getTransitMinimumChangeTime()) * 31)) + getTransitWalkTimeMultiplier())) * 31;
        if (b() != null) {
            i2 = b().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof RouteOptions) {
            obj = get((RouteOptions) obj);
        } else {
            RouteOptionsImpl routeOptionsImpl = (RouteOptionsImpl) obj;
        }
        if (this.e == null) {
            if (obj.e != null) {
                return false;
            }
        } else if (!this.e.equals(obj.e)) {
            return false;
        }
        if (this.f == null) {
            if (obj.f != null) {
                return false;
            }
        } else if (!this.f.equals(obj.f)) {
            return false;
        }
        if (getAllowCarpool() != obj.getAllowCarpool()) {
            return false;
        }
        if (getAllowCarShuttleTrains() != obj.getAllowCarShuttleTrains()) {
            return false;
        }
        if (getAllowDirtRoads() != obj.getAllowDirtRoads()) {
            return false;
        }
        if (getAllowFerries() != obj.getAllowFerries()) {
            return false;
        }
        if (getAllowHighways() != obj.getAllowHighways()) {
            return false;
        }
        if (getAllowParks() != obj.getAllowParks()) {
            return false;
        }
        if (getAllowTollRoads() != obj.getAllowTollRoads()) {
            return false;
        }
        if (getAllowTunnels() != obj.getAllowTunnels()) {
            return false;
        }
        for (PublicTransportLinkFlag publicTransportLinkFlag : PublicTransportLinkFlag.values()) {
            if (a(publicTransportLinkFlag) != obj.a(publicTransportLinkFlag)) {
                return false;
            }
        }
        for (TransitType transitType : TransitType.values()) {
            if (a(transitType) != obj.a(transitType)) {
                return false;
            }
        }
        if (getRouteCount() != obj.getRouteCount()) {
            return false;
        }
        if (a() != obj.a()) {
            return false;
        }
        if (getStartDirection() != obj.getStartDirection()) {
            return false;
        }
        if (getTransitMaximumChanges() != obj.getTransitMaximumChanges()) {
            return false;
        }
        if (getTransitMinimumChangeTime() != obj.getTransitMinimumChangeTime()) {
            return false;
        }
        if (getTransitWalkTimeMultiplier() != obj.getTransitWalkTimeMultiplier()) {
            return false;
        }
        if (b() != obj.b()) {
            return false;
        }
        return true;
    }
}

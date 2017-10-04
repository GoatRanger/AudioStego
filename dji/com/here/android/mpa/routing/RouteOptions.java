package com.here.android.mpa.routing;

import android.util.SparseArray;
import com.here.android.mpa.common.TransitType;
import com.nokia.maps.RouteOptionsImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dy;
import com.nokia.maps.k;
import java.util.Date;
import java.util.EnumSet;

public class RouteOptions {
    @Online
    public static final int START_DIRECTION_ANY = 65535;
    protected RouteOptionsImpl a;

    @HybridPlus
    public enum HazardousGoodType {
        EXPLOSIVE(0),
        GAS(1),
        FLAMMABLE(2),
        COMBUSTIBLE(3),
        ORGANIC(4),
        POISON(5),
        RADIOACTIVE(6),
        CORROSIVE(7),
        POISONOUS_INHALATION(8),
        HARMFUL_TO_WATER(9),
        OTHER(10);
        
        private static SparseArray<HazardousGoodType> b;
        private final int a;

        static {
            b = new SparseArray();
            HazardousGoodType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                HazardousGoodType hazardousGoodType = values[i];
                b.put(hazardousGoodType.a, hazardousGoodType);
                i++;
            }
        }

        private HazardousGoodType(int i) {
            this.a = i;
        }

        public int toInt() {
            return this.a;
        }

        public static HazardousGoodType getType(int i) {
            return (HazardousGoodType) b.get(i);
        }
    }

    @HybridPlus
    public enum PublicTransportLinkFlag {
        ONLY_SLEEPER(0),
        ONLY_BARRIER_FREE(1);
        
        private int a;

        private PublicTransportLinkFlag(int i) {
            this.a = i;
        }
    }

    @Online
    public enum TimeType {
        DEPARTURE(0),
        ARRIVAL(1);
        
        private int a;

        private TimeType(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    public enum TruckType {
        TRUCK(0),
        TRACTOR_TRUCK(1);
        
        private static SparseArray<TruckType> b;
        private final int a;

        static {
            b = new SparseArray();
            TruckType[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                TruckType truckType = values[i];
                b.put(truckType.a, truckType);
                i++;
            }
        }

        private TruckType(int i) {
            this.a = i;
        }

        public int toInt() {
            return this.a;
        }

        public static TruckType getType(int i) {
            return (TruckType) b.get(i);
        }
    }

    @HybridPlus
    public enum TunnelCategory {
        B(1),
        C(2),
        D(3),
        E(4),
        UNDEFINED(0);
        
        private static SparseArray<TunnelCategory> b;
        private final int a;

        static {
            b = new SparseArray();
            TunnelCategory[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                TunnelCategory tunnelCategory = values[i];
                b.put(tunnelCategory.a, tunnelCategory);
                i++;
            }
        }

        private TunnelCategory(int i) {
            this.a = i;
        }

        public int toInt() {
            return this.a;
        }

        public static TunnelCategory getCategory(int i) {
            return (TunnelCategory) b.get(i);
        }
    }

    @Online
    public RouteOptions() {
        this.a = new RouteOptionsImpl();
    }

    @Online
    public RouteOptions(RouteOptions routeOptions) {
        this.a = new RouteOptionsImpl(routeOptions);
    }

    protected RouteOptions(RouteOptionsImpl routeOptionsImpl) {
        this.a = routeOptionsImpl;
    }

    @HybridPlus
    public RouteOptions setPublicTransportTypeAllowed(TransitType transitType, boolean z) {
        this.a.a(transitType, z);
        return this;
    }

    @HybridPlus
    public boolean isPublicTransportTypeAllowed(TransitType transitType) {
        return this.a.a(transitType);
    }

    @HybridPlus
    public RouteOptions setPublicTransportLinkFlag(PublicTransportLinkFlag publicTransportLinkFlag, boolean z) {
        this.a.a(publicTransportLinkFlag, z);
        return this;
    }

    @HybridPlus
    public boolean getPublicTransportLinkFlag(PublicTransportLinkFlag publicTransportLinkFlag) {
        return this.a.a(publicTransportLinkFlag);
    }

    @Online
    public Type getRouteType() {
        return this.a.a();
    }

    @Online
    public RouteOptions setRouteType(Type type) {
        this.a.a(type);
        return this;
    }

    @Online
    public TransportMode getTransportMode() {
        return this.a.b();
    }

    @Online
    public RouteOptions setTransportMode(TransportMode transportMode) {
        this.a.a(transportMode);
        return this;
    }

    @Online
    public boolean areHighwaysAllowed() {
        return this.a.getAllowHighways();
    }

    @Online
    public RouteOptions setHighwaysAllowed(boolean z) {
        this.a.setAllowHighways(z);
        return this;
    }

    @Online
    public boolean areTollRoadsAllowed() {
        return this.a.getAllowTollRoads();
    }

    @Online
    public RouteOptions setTollRoadsAllowed(boolean z) {
        this.a.setAllowTollRoads(z);
        return this;
    }

    @Online
    public boolean areFerriesAllowed() {
        return this.a.getAllowFerries();
    }

    @Online
    public RouteOptions setFerriesAllowed(boolean z) {
        this.a.setAllowFerries(z);
        return this;
    }

    @Online
    public boolean areTunnelsAllowed() {
        return this.a.getAllowTunnels();
    }

    @Online
    public RouteOptions setTunnelsAllowed(boolean z) {
        this.a.setAllowTunnels(z);
        return this;
    }

    @Online
    public boolean areDirtRoadsAllowed() {
        return this.a.getAllowDirtRoads();
    }

    @Online
    public RouteOptions setDirtRoadsAllowed(boolean z) {
        this.a.setAllowDirtRoads(z);
        return this;
    }

    @Online
    public boolean areCarShuttleTrainsAllowed() {
        return this.a.getAllowCarShuttleTrains();
    }

    @Online
    public RouteOptions setCarShuttleTrainsAllowed(boolean z) {
        this.a.setAllowCarShuttleTrains(z);
        return this;
    }

    @Online
    public boolean areParksAllowed() {
        return this.a.getAllowParks();
    }

    @Online
    public RouteOptions setParksAllowed(boolean z) {
        this.a.setAllowParks(z);
        return this;
    }

    @Online
    public boolean isCarpoolAllowed() {
        return this.a.getAllowCarpool();
    }

    @Online
    public RouteOptions setCarpoolAllowed(boolean z) {
        this.a.setAllowCarpool(z);
        return this;
    }

    @HybridPlus
    public RouteOptions setTransitMinimumChangeTime(int i) {
        this.a.setTransitMinimumChangeTime(i);
        return this;
    }

    @HybridPlus
    public int getTransitMinimumChangeTime() {
        return this.a.getTransitMinimumChangeTime();
    }

    @HybridPlus
    public RouteOptions setTransitMaximumChanges(int i) {
        this.a.setTransitMaximumChanges(i);
        return this;
    }

    @HybridPlus
    public int getTransitMaximumChanges() {
        return this.a.getTransitMaximumChanges();
    }

    @HybridPlus
    public RouteOptions setTransitWalkTimeMultiplier(float f) {
        this.a.setTransitWalkTimeMultiplier(f);
        return this;
    }

    @HybridPlus
    public float getTransitWalkTimeMultiplier() {
        return this.a.getTransitWalkTimeMultiplier();
    }

    @Online
    public RouteOptions setTime(Date date, TimeType timeType) {
        this.a.a(date, timeType);
        return this;
    }

    @Online
    public TimeType getTime(Date date) {
        return this.a.a(date);
    }

    @Online
    public int getRouteCount() {
        return this.a.getRouteCount();
    }

    @Online
    public RouteOptions setRouteCount(int i) {
        this.a.setRouteCount(i);
        return this;
    }

    @Online
    public RouteOptions setStartDirection(int i) {
        this.a.setStartDirection(i);
        return this;
    }

    @Online
    public int getStartDirection() {
        return this.a.getStartDirection();
    }

    @HybridPlus
    public RouteOptions setTruckTrailersCount(int i) {
        dy.a(i > -1, "Number of trailers cannot be negative");
        this.a.setTrailersCountNative(i);
        return this;
    }

    @HybridPlus
    public int getTruckTrailersCount() {
        return this.a.getTrailersCountNative();
    }

    @HybridPlus
    public RouteOptions setTruckShippedHazardousGoods(EnumSet<HazardousGoodType> enumSet) {
        this.a.a((EnumSet) enumSet);
        return this;
    }

    @HybridPlus
    public EnumSet<HazardousGoodType> getTruckShippedHazardousGoods() {
        return this.a.d();
    }

    @HybridPlus
    public RouteOptions setTruckLimitedWeight(float f) {
        this.a.a(f);
        return this;
    }

    @HybridPlus
    public float getTruckLimitedWeight() {
        return this.a.e();
    }

    @HybridPlus
    public RouteOptions setTruckWeightPerAxle(float f) {
        this.a.b(f);
        return this;
    }

    @HybridPlus
    public float getTruckWeightPerAxle() {
        return this.a.f();
    }

    @HybridPlus
    public RouteOptions setTruckHeight(float f) {
        this.a.c(f);
        return this;
    }

    @HybridPlus
    public float getTruckHeight() {
        return this.a.g();
    }

    @HybridPlus
    public RouteOptions setTruckWidth(float f) {
        this.a.d(f);
        return this;
    }

    @HybridPlus
    public float getTruckWidth() {
        return this.a.h();
    }

    @HybridPlus
    public RouteOptions setTruckLength(float f) {
        this.a.e(f);
        return this;
    }

    @HybridPlus
    public float getTruckLength() {
        return this.a.i();
    }

    @HybridPlus
    public RouteOptions setTruckTunnelCategory(TunnelCategory tunnelCategory) {
        this.a.a(tunnelCategory);
        return this;
    }

    @HybridPlus
    public TunnelCategory getTruckTunnelCategory() {
        return this.a.j();
    }

    @HybridPlus
    public RouteOptions setTruckType(TruckType truckType) {
        this.a.a(truckType);
        return this;
    }

    @Internal
    public void setFetchElevationData(boolean z) {
        this.a.a(z);
    }

    @HybridPlus
    public TruckType getTruckType() {
        return this.a.k();
    }

    static {
        RouteOptionsImpl.a(new k<RouteOptions, RouteOptionsImpl>() {
            public RouteOptionsImpl a(RouteOptions routeOptions) {
                return routeOptions.a;
            }
        }, new am<RouteOptions, RouteOptionsImpl>() {
            public RouteOptions a(RouteOptionsImpl routeOptionsImpl) {
                return routeOptionsImpl != null ? new RouteOptions(routeOptionsImpl) : null;
            }
        });
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
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
        RouteOptions routeOptions = (RouteOptions) obj;
        if (this.a == null) {
            if (routeOptions.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(routeOptions.a)) {
            return true;
        } else {
            return false;
        }
    }
}

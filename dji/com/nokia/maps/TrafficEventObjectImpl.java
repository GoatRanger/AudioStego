package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.mapping.TrafficEventObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class TrafficEventObjectImpl extends MapProxyObjectImpl {
    private static am<TrafficEventObject, TrafficEventObjectImpl> c;
    private TrafficEvent a = TrafficEventImpl.a(getTrafficEventNative());
    private cq b = new cq(TrafficEventObjectImpl.class.getName());

    private final native GeoCoordinateImpl getCoordinateNative();

    private final native TrafficEventImpl getTrafficEventNative();

    @HybridPlusNative
    private TrafficEventObjectImpl(int i) {
        super(i);
    }

    public final GeoCoordinate b() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public final TrafficEvent c() {
        return this.a;
    }

    public static void a(am<TrafficEventObject, TrafficEventObjectImpl> amVar) {
        c = amVar;
    }

    static {
        ce.a(TrafficEventObject.class);
    }
}

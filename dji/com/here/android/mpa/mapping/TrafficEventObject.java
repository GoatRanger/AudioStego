package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.TrafficEventObjectImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public final class TrafficEventObject extends MapProxyObject {
    private TrafficEventObjectImpl b;

    @HybridPlusNative
    private TrafficEventObject(TrafficEventObjectImpl trafficEventObjectImpl) {
        super(trafficEventObjectImpl);
        this.b = trafficEventObjectImpl;
    }

    public GeoCoordinate getCoordinate() {
        return this.b.b();
    }

    public TrafficEvent getTrafficEvent() {
        return this.b.c();
    }

    static {
        TrafficEventObjectImpl.a(new am<TrafficEventObject, TrafficEventObjectImpl>() {
            public TrafficEventObject a(TrafficEventObjectImpl trafficEventObjectImpl) {
                if (trafficEventObjectImpl != null) {
                    return new TrafficEventObject(trafficEventObjectImpl);
                }
                return null;
            }
        });
    }
}

package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.nokia.maps.TransitRouteStopImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.EnumSet;

@HybridPlus
public final class TransitRouteStop {
    private TransitRouteStopImpl a;

    @HybridPlus
    public enum Attribute {
        ELEVATOR(0),
        ESCALATOR(1),
        STAIRS(2);
        
        private int a;

        private Attribute(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    private TransitRouteStop(TransitRouteStopImpl transitRouteStopImpl) {
        this.a = transitRouteStopImpl;
    }

    public String getName() {
        return this.a.getName();
    }

    public GeoCoordinate getCoordinate() {
        return this.a.a();
    }

    public Identifier getId() {
        return this.a.b();
    }

    public EnumSet<Attribute> getAttributes() {
        return this.a.c();
    }

    public int getPlatformLevel() {
        return this.a.getPlatformLevel();
    }

    public GeoCoordinate getPlatformCoordinate() {
        return this.a.d();
    }

    public GeoCoordinate getEgressCoordinate() {
        return this.a.e();
    }

    static {
        TransitRouteStopImpl.a(new k<TransitRouteStop, TransitRouteStopImpl>() {
            public TransitRouteStopImpl a(TransitRouteStop transitRouteStop) {
                return transitRouteStop.a;
            }
        }, new am<TransitRouteStop, TransitRouteStopImpl>() {
            public TransitRouteStop a(TransitRouteStopImpl transitRouteStopImpl) {
                return transitRouteStopImpl != null ? new TransitRouteStop(transitRouteStopImpl) : null;
            }
        });
    }
}

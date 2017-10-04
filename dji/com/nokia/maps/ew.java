package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.routing.TransitRouteStop.Attribute;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.restrouting.Stop;
import java.util.EnumSet;

@HybridPlus
public class ew extends TransitRouteStopImpl {
    private String a;
    private GeoCoordinate b;
    private Identifier c;

    ew(Stop stop) {
        if (stop != null) {
            this.a = stop.b();
            this.c = IdentifierImpl.a(new IdentifierImpl(a.STRING, ""));
            com.nokia.maps.restrouting.GeoCoordinate a = stop.a();
            if (a != null) {
                this.b = new GeoCoordinate(a.a().doubleValue(), a.b().doubleValue());
            }
        }
    }

    public String getName() {
        return this.a;
    }

    public GeoCoordinate a() {
        return this.b;
    }

    public Identifier b() {
        return this.c;
    }

    public EnumSet<Attribute> c() {
        return EnumSet.noneOf(Attribute.class);
    }

    public int getPlatformLevel() {
        return 0;
    }

    public GeoCoordinate d() {
        return this.b;
    }

    public GeoCoordinate e() {
        return this.b;
    }

    protected void finalize() {
    }
}

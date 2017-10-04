package com.here.android.mpa.routing;

import com.nokia.maps.RouteTtaImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.util.EnumSet;

@Online
public final class RouteTta {
    private final RouteTtaImpl a;

    @Online
    public enum Detail {
        BLOCKED_ROAD(1),
        CARPOOL(2),
        RESTRICTED_TURN(4);
        
        private final int a;

        private Detail(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    public int getDuration() {
        return this.a.getDuration();
    }

    public boolean isBlocked() {
        return this.a.isBlocked();
    }

    public EnumSet<Detail> getDetails() {
        return this.a.a();
    }

    private RouteTta(RouteTtaImpl routeTtaImpl) {
        this.a = routeTtaImpl;
    }

    static {
        RouteTtaImpl.a(new am<RouteTta, RouteTtaImpl>() {
            public RouteTta a(RouteTtaImpl routeTtaImpl) {
                return new RouteTta(routeTtaImpl);
            }
        });
    }
}

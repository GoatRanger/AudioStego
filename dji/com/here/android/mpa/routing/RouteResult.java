package com.here.android.mpa.routing;

import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.eh;
import com.nokia.maps.k;
import java.util.EnumSet;

@Online
public class RouteResult {
    private eh a;

    @Online
    public enum ViolatedOption {
        AVOID_HIGHWAYS(0),
        AVOID_TOLL_ROADS(1),
        AVOID_FERRIES(2),
        AVOID_TUNNELS(3),
        AVOID_DIRT_ROADS(4),
        AVOID_CAR_SHUTTLE_TRAINS(5),
        AVOID_PARKS(6),
        BLOCKED_ROADS(7),
        START_DIRECTION(8),
        CARPOOL(9),
        TIME_RESTRICTED_TURN(10);
        
        private int a;

        private ViolatedOption(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    RouteResult(eh ehVar) {
        this.a = ehVar;
    }

    public Route getRoute() {
        return this.a.a();
    }

    public EnumSet<ViolatedOption> getViolatedOptions() {
        return this.a.b();
    }

    static {
        eh.a(new k<RouteResult, eh>() {
            public eh a(RouteResult routeResult) {
                return routeResult.a;
            }
        }, new am<RouteResult, eh>() {
            public RouteResult a(eh ehVar) {
                return ehVar != null ? new RouteResult(ehVar) : null;
            }
        });
    }
}

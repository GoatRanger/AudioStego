package com.nokia.maps.a;

import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.UMRoute;
import com.here.android.mpa.routing.UMRouteResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import com.nokia.maps.eh;

public class aq extends eh {
    private static am<UMRouteResult, aq> b = null;
    private final am a;

    public aq(RouteResult routeResult) {
        super(routeResult);
        this.a = null;
    }

    aq(am amVar) {
        this.a = amVar;
        a(this.a);
    }

    public UMRoute c() {
        return this.a != null ? am.a(this.a) : null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof aq)) {
            return false;
        }
        aq aqVar = (aq) obj;
        if (this.a != null) {
            return this.a.equals(aqVar.a);
        }
        if (aqVar.a != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.a != null ? this.a.hashCode() : 0) + (super.hashCode() * 31);
    }

    static {
        ce.a(UMRouteResult.class);
    }

    public static void a(am<UMRouteResult, aq> amVar) {
        b = amVar;
    }

    public static UMRouteResult a(aq aqVar) {
        try {
            if (b != null) {
                return (UMRouteResult) b.a(aqVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

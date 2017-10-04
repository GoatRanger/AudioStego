package com.here.android.mpa.routing;

import com.nokia.maps.a.aq;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class UMRouteResult extends RouteResult {
    private final aq a;

    private UMRouteResult(aq aqVar) {
        super(aqVar);
        this.a = aqVar;
    }

    public UMRoute getUMRoute() {
        return this.a.c();
    }

    static {
        aq.a(new am<UMRouteResult, aq>() {
            public UMRouteResult a(aq aqVar) {
                if (aqVar == null) {
                    return null;
                }
                try {
                    return new UMRouteResult(aqVar);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

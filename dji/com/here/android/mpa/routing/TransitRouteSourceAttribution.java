package com.here.android.mpa.routing;

import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ev;
import java.util.List;

@HybridPlus
public class TransitRouteSourceAttribution {
    private ev a;

    public String getAttribution() {
        return this.a.a();
    }

    public List<TransitRouteSupplier> getSuppliers() {
        return this.a.b();
    }

    private TransitRouteSourceAttribution(ev evVar) {
        this.a = evVar;
    }

    static {
        ev.a(new am<TransitRouteSourceAttribution, ev>() {
            public TransitRouteSourceAttribution a(ev evVar) {
                return evVar != null ? new TransitRouteSourceAttribution(evVar) : null;
            }
        });
    }
}

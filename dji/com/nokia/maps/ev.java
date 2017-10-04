package com.nokia.maps;

import com.here.android.mpa.routing.TransitRouteSourceAttribution;
import com.here.android.mpa.routing.TransitRouteSupplier;
import com.nokia.maps.restrouting.SourceAttribution;
import com.nokia.maps.restrouting.Supplier;
import java.util.ArrayList;
import java.util.List;

public class ev {
    private static am<TransitRouteSourceAttribution, ev> c = null;
    private String a;
    private List<TransitRouteSupplier> b = new ArrayList();

    static {
        ce.a(TransitRouteSourceAttribution.class);
    }

    public static void a(am<TransitRouteSourceAttribution, ev> amVar) {
        c = amVar;
    }

    ev(SourceAttribution sourceAttribution) {
        this.a = sourceAttribution.a();
        List b = sourceAttribution.b();
        if (b != null && b.size() > 0) {
            for (Supplier exVar : sourceAttribution.b()) {
                this.b.add(ex.a(new ex(exVar)));
            }
        }
    }

    static TransitRouteSourceAttribution a(ev evVar) {
        if (evVar != null) {
            return (TransitRouteSourceAttribution) c.a(evVar);
        }
        return null;
    }

    public String a() {
        return this.a;
    }

    public List<TransitRouteSupplier> b() {
        return this.b;
    }
}

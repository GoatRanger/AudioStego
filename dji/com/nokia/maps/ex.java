package com.nokia.maps;

import com.here.android.mpa.routing.TransitRouteSupplier;
import com.here.android.mpa.routing.TransitRouteSupplierNote;
import com.nokia.maps.restrouting.Supplier;
import com.nokia.maps.restrouting.SupplierNote;
import java.util.ArrayList;
import java.util.List;

public class ex {
    private static am<TransitRouteSupplier, ex> d = null;
    private String a;
    private String b;
    private List<TransitRouteSupplierNote> c = new ArrayList();

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public List<TransitRouteSupplierNote> c() {
        return this.c;
    }

    static {
        ce.a(TransitRouteSupplier.class);
    }

    public static void a(am<TransitRouteSupplier, ex> amVar) {
        d = amVar;
    }

    ex(Supplier supplier) {
        this.a = supplier.a();
        this.b = supplier.b();
        List<SupplierNote> c = supplier.c();
        if (c != null && c.size() > 0) {
            for (SupplierNote eyVar : c) {
                this.c.add(ey.a(new ey(eyVar)));
            }
        }
    }

    static TransitRouteSupplier a(ex exVar) {
        if (exVar != null) {
            return (TransitRouteSupplier) d.a(exVar);
        }
        return null;
    }
}

package com.here.android.mpa.routing;

import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ey;

@HybridPlus
public class TransitRouteSupplierNote {
    private ey a;

    public String getType() {
        return this.a.a();
    }

    public String getText() {
        return this.a.b();
    }

    public String getUrl() {
        return this.a.c();
    }

    public String getUrlText() {
        return this.a.d();
    }

    private TransitRouteSupplierNote(ey eyVar) {
        this.a = eyVar;
    }

    static {
        ey.a(new am<TransitRouteSupplierNote, ey>() {
            public TransitRouteSupplierNote a(ey eyVar) {
                return eyVar != null ? new TransitRouteSupplierNote(eyVar) : null;
            }
        });
    }
}

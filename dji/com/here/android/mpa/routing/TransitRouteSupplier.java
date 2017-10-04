package com.here.android.mpa.routing;

import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ex;
import java.util.List;

@HybridPlus
public class TransitRouteSupplier {
    private ex a;

    public String getTitle() {
        return this.a.a();
    }

    public String getUrl() {
        return this.a.b();
    }

    public List<TransitRouteSupplierNote> getNotes() {
        return this.a.c();
    }

    private TransitRouteSupplier(ex exVar) {
        this.a = exVar;
    }

    static {
        ex.a(new am<TransitRouteSupplier, ex>() {
            public TransitRouteSupplier a(ex exVar) {
                return exVar != null ? new TransitRouteSupplier(exVar) : null;
            }
        });
    }
}

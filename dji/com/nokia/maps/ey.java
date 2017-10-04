package com.nokia.maps;

import com.here.android.mpa.routing.TransitRouteSupplierNote;
import com.nokia.maps.restrouting.SupplierNote;

public class ey {
    private static am<TransitRouteSupplierNote, ey> e = null;
    private String a;
    private String b;
    private String c;
    private String d;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    static {
        ce.a(TransitRouteSupplierNote.class);
    }

    public static void a(am<TransitRouteSupplierNote, ey> amVar) {
        e = amVar;
    }

    ey(SupplierNote supplierNote) {
        this.a = supplierNote.a();
        this.b = supplierNote.b();
        this.c = supplierNote.c();
        this.d = supplierNote.d();
    }

    static TransitRouteSupplierNote a(ey eyVar) {
        if (eyVar != null) {
            return (TransitRouteSupplierNote) e.a(eyVar);
        }
        return null;
    }
}

package com.nokia.maps.a;

import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.UMRouteOptions;
import com.nokia.maps.RouteOptionsImpl;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import com.nokia.maps.k;

public class an extends RouteOptionsImpl {
    private static k<UMRouteOptions, an> c = null;
    private static am<UMRouteOptions, an> d = null;
    private int a = -1;
    private boolean b = false;

    public an(RouteOptions routeOptions) {
        super(routeOptions);
    }

    public an(UMRouteOptions uMRouteOptions) {
        super((RouteOptions) uMRouteOptions);
        an a = a(uMRouteOptions);
        this.a = a.a;
        this.b = a.b;
    }

    public void a(int i) {
        this.a = i;
    }

    public int l() {
        return this.a;
    }

    public boolean m() {
        return this.b;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        an anVar = (an) obj;
        boolean z = super.equals(obj) && this.b == anVar.b && this.a == anVar.a;
        return z;
    }

    public int hashCode() {
        return (((this.b ? 1231 : 1237) + (super.hashCode() * 31)) * 31) + this.a;
    }

    static {
        ce.a(UMRouteOptions.class);
    }

    public static void b(k<UMRouteOptions, an> kVar, am<UMRouteOptions, an> amVar) {
        c = kVar;
        d = amVar;
    }

    public static an a(UMRouteOptions uMRouteOptions) {
        return (an) c.a(uMRouteOptions);
    }

    public static UMRouteOptions a(an anVar) {
        if (anVar != null) {
            return (UMRouteOptions) d.a(anVar);
        }
        return null;
    }
}

package com.nokia.maps;

import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteResult.ViolatedOption;
import java.util.EnumSet;

public class eh extends BaseNativeObject {
    private static k<RouteResult, eh> d = null;
    private static am<RouteResult, eh> e = null;
    private cq a = new cq(eh.class.getName());
    private RouteImpl b = null;
    private EnumSet<ViolatedOption> c = null;

    static {
        ce.a(RouteResult.class);
    }

    public static void a(k<RouteResult, eh> kVar, am<RouteResult, eh> amVar) {
        d = kVar;
        e = amVar;
    }

    static eh a(RouteResult routeResult) {
        return (eh) d.a(routeResult);
    }

    public static RouteResult a(eh ehVar) {
        try {
            if (e != null) {
                return (RouteResult) e.a(ehVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public eh(RouteResult routeResult) {
        eh a = a(routeResult);
        this.b = a.b;
        this.c = a.c;
    }

    public Route a() {
        return RouteImpl.create(this.b);
    }

    public void a(RouteImpl routeImpl) {
        this.b = routeImpl;
    }

    public EnumSet<ViolatedOption> b() {
        return this.c;
    }

    public void a(EnumSet<ViolatedOption> enumSet) {
        this.c = enumSet;
    }
}

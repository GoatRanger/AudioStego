package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.ah;
import com.here.a.a.a.a.ai;
import com.here.a.a.a.a.c;
import com.here.a.a.a.a.t;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.UMCalculateResult;
import com.here.android.mpa.routing.UMCalculateResult.ViolatedOption;
import com.here.android.mpa.routing.UMRouteResult;
import com.here.android.mpa.urbanmobility.Alert;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.Link;
import com.here.android.mpa.urbanmobility.Operator;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import com.nokia.maps.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class h {
    private static k<UMCalculateResult, h> l;
    private static am<UMCalculateResult, h> m;
    private ErrorCode a;
    private String b;
    private List<UMRouteResult> c;
    private final String d;
    private final String e;
    private final Collection<Link> f;
    private final List<Alert> g;
    private EnumSet<ViolatedOption> h;
    private final List<Operator> i;
    private RouteManager j;
    private RoutePlan k;

    h() {
        this.a = ErrorCode.NONE;
        this.b = null;
        this.h = EnumSet.noneOf(ViolatedOption.class);
        this.c = new ArrayList();
        this.d = null;
        this.e = null;
        this.f = Collections.emptyList();
        this.g = Collections.emptyList();
        this.i = Collections.emptyList();
    }

    h(RoutePlan routePlan, ai aiVar) {
        this.a = ErrorCode.NONE;
        this.b = null;
        this.h = EnumSet.noneOf(ViolatedOption.class);
        List<ah> a = aiVar.a();
        if (a.isEmpty()) {
            this.c = Collections.emptyList();
        } else {
            this.c = new ArrayList(a.size());
            for (ah amVar : a) {
                this.c.add(aq.a(new aq(new am(routePlan, amVar))));
            }
        }
        this.d = aiVar.b;
        this.e = aiVar.a;
        Collection<t> d = aiVar.d();
        if (d.isEmpty()) {
            this.f = Collections.emptyList();
        } else {
            this.f = new ArrayList(d.size());
            for (t zVar : d) {
                this.f.add(z.a(new z(zVar)));
            }
        }
        Collection<c> c = aiVar.c();
        if (c.isEmpty()) {
            this.g = Collections.emptyList();
        } else {
            this.g = new ArrayList(c.size());
            for (c fVar : c) {
                this.g.add(f.a(new f(fVar)));
            }
        }
        if (!((Boolean) aiVar.d.c(Boolean.valueOf(true))).booleanValue()) {
            this.h.add(ViolatedOption.MAXIMUM_WALKING_DISTANCE);
        }
        if (!((Boolean) aiVar.e.c(Boolean.valueOf(true))).booleanValue()) {
            this.h.add(ViolatedOption.MAXIMUM_CHANGES_COUNT);
        }
        if (!((Boolean) aiVar.f.c(Boolean.valueOf(true))).booleanValue()) {
            this.h.add(ViolatedOption.RESTRICT_TRANSIT_TYPES);
        }
        if (!((Boolean) aiVar.g.c(Boolean.valueOf(true))).booleanValue()) {
            this.h.add(ViolatedOption.WALKING_SPEED);
        }
        Collection<ac> b = aiVar.b();
        if (b.isEmpty()) {
            this.i = Collections.emptyList();
            return;
        }
        this.i = new ArrayList(b.size());
        for (ac aiVar2 : b) {
            this.i.add(ai.a(new ai(aiVar2)));
        }
    }

    public ErrorCode a() {
        return this.a;
    }

    public void a(ErrorCode errorCode) {
        this.a = errorCode;
    }

    public String b() {
        return this.b != null ? this.b : "";
    }

    public void a(String str) {
        this.b = str;
    }

    public List<UMRouteResult> c() {
        return Collections.unmodifiableList(this.c);
    }

    public void a(UMRouteResult uMRouteResult) {
        this.c.add(uMRouteResult);
    }

    public boolean d() {
        return (this.e == null || this.e.isEmpty() || this.d == null || this.d.isEmpty()) ? false : true;
    }

    String e() {
        return this.d;
    }

    String f() {
        return this.e;
    }

    public Collection<Link> g() {
        return Collections.unmodifiableCollection(this.f);
    }

    public Collection<Alert> h() {
        return Collections.unmodifiableCollection(this.g);
    }

    public EnumSet<ViolatedOption> i() {
        return this.h;
    }

    public Collection<Operator> j() {
        return Collections.unmodifiableCollection(this.i);
    }

    void a(RoutePlan routePlan) {
        this.k = routePlan;
    }

    RoutePlan k() {
        return this.k;
    }

    void a(RouteManager routeManager) {
        this.j = routeManager;
    }

    RouteManager l() {
        return this.j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (this.a == hVar.a) {
            if (this.b != null) {
                if (this.b.equals(hVar.b)) {
                    return true;
                }
            } else if (hVar.b == null && this.c.equals(hVar.c)) {
                if (this.d != null) {
                    if (this.d.equals(hVar.d)) {
                        return true;
                    }
                } else if (hVar.d == null) {
                    if (this.e != null) {
                        if (this.e.equals(hVar.e)) {
                            return true;
                        }
                    } else if (hVar.e == null && this.f.equals(hVar.f) && this.g.equals(hVar.g) && this.h.equals(hVar.h) && this.i.equals(hVar.i)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((((this.b != null ? this.b.hashCode() : 0) + (this.a.hashCode() * 31)) * 31) + this.c.hashCode()) * 31;
        if (this.d != null) {
            hashCode = this.d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.e != null) {
            i = this.e.hashCode();
        }
        return ((((((((hashCode + i) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }

    static h a(UMCalculateResult uMCalculateResult) {
        return (h) l.a(uMCalculateResult);
    }

    public static void a(k<UMCalculateResult, h> kVar, am<UMCalculateResult, h> amVar) {
        l = kVar;
        m = amVar;
    }

    static UMCalculateResult a(h hVar) {
        if (hVar != null) {
            return (UMCalculateResult) m.a(hVar);
        }
        return null;
    }

    static {
        ce.a(UMCalculateResult.class);
    }
}

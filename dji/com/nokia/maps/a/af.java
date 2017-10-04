package com.nokia.maps.a;

import com.here.a.a.a.a.s;
import com.here.a.a.a.a.z;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.MultiBoardResult;
import com.here.android.mpa.urbanmobility.StationWithDepartureBoard;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class af {
    private static am<MultiBoardResult, af> c;
    private List<StationWithDepartureBoard> a;
    private Collection<Line> b;

    public Collection<StationWithDepartureBoard> a() {
        return Collections.unmodifiableList(this.a);
    }

    public Collection<Line> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    protected af(z zVar) {
        List<com.here.a.a.a.a.am> a = zVar.a();
        if (a.isEmpty()) {
            this.a = Collections.emptyList();
        } else {
            this.a = new ArrayList(a.size());
            for (com.here.a.a.a.a.am awVar : a) {
                this.a.add(aw.a(new aw(awVar)));
            }
        }
        Collection<s> b = zVar.b();
        if (b.isEmpty()) {
            this.b = Collections.emptyList();
            return;
        }
        this.b = new ArrayList(b.size());
        for (s xVar : b) {
            this.b.add(x.a(new x(xVar)));
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        af afVar = (af) obj;
        if (this.a.equals(afVar.a) && this.b.equals(afVar.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public static void a(am<MultiBoardResult, af> amVar) {
        c = amVar;
    }

    static MultiBoardResult a(af afVar) {
        if (afVar != null) {
            return (MultiBoardResult) c.a(afVar);
        }
        return null;
    }

    static {
        ce.a(MultiBoardResult.class);
    }
}

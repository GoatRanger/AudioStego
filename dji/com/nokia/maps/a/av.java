package com.nokia.maps.a;

import com.here.a.a.a.a.ak;
import com.here.a.a.a.a.al;
import com.here.a.a.a.a.s;
import com.here.android.mpa.urbanmobility.Line;
import com.here.android.mpa.urbanmobility.Station;
import com.here.android.mpa.urbanmobility.StationSearchResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class av {
    private static am<StationSearchResult, av> c;
    private List<Station> a;
    private Collection<Line> b;

    public av(al alVar) {
        Iterator it;
        if (alVar.a()) {
            this.a = Collections.emptyList();
        } else {
            this.a = new ArrayList();
            it = alVar.iterator();
            while (it.hasNext()) {
                this.a.add(at.a(new at((ak) it.next())));
            }
        }
        if (alVar.b().isEmpty()) {
            this.b = Collections.emptySet();
            return;
        }
        this.b = new HashSet();
        for (s xVar : alVar.b()) {
            this.b.add(x.a(new x(xVar)));
        }
    }

    public List<Station> a() {
        return Collections.unmodifiableList(this.a);
    }

    public Collection<Line> b() {
        return Collections.unmodifiableCollection(this.b);
    }

    public static void a(am<StationSearchResult, av> amVar) {
        c = amVar;
    }

    static StationSearchResult a(av avVar) {
        if (avVar != null) {
            return (StationSearchResult) c.a(avVar);
        }
        return null;
    }

    static {
        ce.a(StationSearchResult.class);
    }
}

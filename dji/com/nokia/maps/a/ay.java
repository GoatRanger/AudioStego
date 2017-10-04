package com.nokia.maps.a;

import com.here.a.a.a.a.an;
import com.here.a.a.a.a.ao;
import com.here.android.mpa.urbanmobility.Ticket;
import com.here.android.mpa.urbanmobility.TicketCollection;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ay {
    private static am<TicketCollection, ay> b;
    private List<Ticket> a;

    protected ay(ao aoVar) {
        Collection<an> a = aoVar.a();
        if (a.isEmpty()) {
            this.a = Collections.emptyList();
            return;
        }
        this.a = new ArrayList(a.size());
        for (an azVar : a) {
            this.a.add(az.a(new az(azVar)));
        }
    }

    public Collection<Ticket> a() {
        return Collections.unmodifiableCollection(this.a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ay) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public static void a(am<TicketCollection, ay> amVar) {
        b = amVar;
    }

    static TicketCollection a(ay ayVar) {
        if (ayVar != null) {
            return (TicketCollection) b.a(ayVar);
        }
        return null;
    }

    static {
        ce.a(TicketCollection.class);
    }
}

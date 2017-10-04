package com.here.a.a.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class ao implements Iterable<an> {
    private Collection<an> a;

    public ao(Collection<an> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("TicketCollection should contain at least one Ticket.");
        }
        this.a = collection;
    }

    public Collection<an> a() {
        return Collections.unmodifiableCollection(this.a);
    }

    public static ao fromJSON(o oVar) {
        p d = oVar.d("Ticket");
        Collection arrayList = new ArrayList(d.a());
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(an.fromJSON((o) it.next()));
        }
        return new ao(arrayList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ao) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public Iterator<an> iterator() {
        return a().iterator();
    }
}

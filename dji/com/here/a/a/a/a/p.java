package com.here.a.a.a.a;

import com.here.a.a.a.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class p implements Iterable<o> {
    private List<o> a;

    protected p(Iterable<?> iterable) {
        if (iterable == null) {
            throw new NullPointerException("Items can't be null.");
        }
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (next instanceof f) {
                arrayList.add(new o((f) next));
            }
        }
        if (arrayList.isEmpty()) {
            this.a = Collections.emptyList();
        } else {
            this.a = new ArrayList(arrayList);
        }
    }

    public Iterator<o> iterator() {
        return this.a.iterator();
    }

    public int a() {
        return this.a.size();
    }

    public o a(int i) {
        return (o) this.a.get(i);
    }
}

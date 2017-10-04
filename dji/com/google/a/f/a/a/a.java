package com.google.a.f.a.a;

import com.google.a.c.b;
import com.google.a.c.g;
import com.google.a.e;
import com.google.a.i.b.c;
import com.google.a.i.b.f;
import com.google.a.m;
import com.google.a.q;
import com.google.a.u;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class a extends c {
    private static final g[] a = new g[0];

    public a(b bVar) {
        super(bVar);
    }

    public g[] a(Map<e, ?> map) throws m {
        f[] a = new b(a(), map == null ? null : (u) map.get(e.NEED_RESULT_POINT_CALLBACK)).a(map);
        if (a.length == 0) {
            throw m.a();
        }
        List arrayList = new ArrayList();
        for (f a2 : a) {
            try {
                arrayList.add(a(a2));
            } catch (q e) {
            }
        }
        if (arrayList.isEmpty()) {
            return a;
        }
        return (g[]) arrayList.toArray(new g[arrayList.size()]);
    }
}

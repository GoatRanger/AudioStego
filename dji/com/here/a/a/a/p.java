package com.here.a.a.a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class p extends i {
    private Set<String> b = Collections.emptySet();
    private Boolean c;

    public p(String str, String str2, String str3, Set<String> set) {
        super(str, str2, str3);
        a(set);
    }

    protected String a() {
        return "search/by_stopids";
    }

    protected Map<String, String> b() {
        Map hashMap = new HashMap(super.b());
        hashMap.put("stopIds", s.a(this.b));
        if (this.c != null) {
            hashMap.put("details", this.c.booleanValue() ? "1" : "0");
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public p a(Set<String> set) {
        if (set == null || set.isEmpty()) {
            throw new IllegalArgumentException("Station IDs can't be null or empty.");
        }
        this.b = set;
        return this;
    }

    public Collection<String> c() {
        List arrayList = new ArrayList(this.b);
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }
}

package com.a.a;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class i {
    private final Map<String, Object> a = new ConcurrentHashMap();

    public i() {
        this.a.put("All", Boolean.valueOf(true));
    }

    public i a(String str, boolean z) {
        if ("Segment.io".equals(str)) {
            throw new IllegalArgumentException("Segment integration cannot be enabled or disabled.");
        }
        this.a.put(str, Boolean.valueOf(z));
        return this;
    }

    public Map<String, Object> a() {
        return new LinkedHashMap(this.a);
    }
}

package com.alipay.android.a.a.a;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class b implements Serializable {
    private static final long a = -6098125857367743614L;
    private Map<String, String> b = new HashMap();

    public String a(String str) {
        return (String) this.b.get(str);
    }

    public Map<String, String> a() {
        return this.b;
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public void a(Map<String, String> map) {
        this.b = map;
    }
}

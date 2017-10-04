package com.facebook.internal;

import com.facebook.n.a;
import dji.pilot.usercenter.mode.n;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class l {
    public static final int a = 2;
    public static final int b = 4;
    public static final int c = 9;
    public static final int d = 17;
    public static final int e = 102;
    public static final int f = 190;
    public static final int g = 341;
    public static final String h = "recovery_message";
    public static final String i = "name";
    public static final String j = "other";
    public static final String k = "transient";
    public static final String l = "login_recoverable";
    private static l s;
    private final Map<Integer, Set<Integer>> m;
    private final Map<Integer, Set<Integer>> n;
    private final Map<Integer, Set<Integer>> o;
    private final String p;
    private final String q;
    private final String r;

    l(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.m = map;
        this.n = map2;
        this.o = map3;
        this.p = str;
        this.q = str2;
        this.r = str3;
    }

    public Map<Integer, Set<Integer>> a() {
        return this.m;
    }

    public Map<Integer, Set<Integer>> b() {
        return this.n;
    }

    public Map<Integer, Set<Integer>> c() {
        return this.o;
    }

    public String a(a aVar) {
        switch (aVar) {
            case OTHER:
                return this.p;
            case LOGIN_RECOVERABLE:
                return this.r;
            case TRANSIENT:
                return this.q;
            default:
                return null;
        }
    }

    public a a(int i, int i2, boolean z) {
        if (z) {
            return a.TRANSIENT;
        }
        Set set;
        if (this.m != null && this.m.containsKey(Integer.valueOf(i))) {
            set = (Set) this.m.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return a.OTHER;
            }
        }
        if (this.o != null && this.o.containsKey(Integer.valueOf(i))) {
            set = (Set) this.o.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return a.LOGIN_RECOVERABLE;
            }
        }
        if (this.n != null && this.n.containsKey(Integer.valueOf(i))) {
            set = (Set) this.n.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return a.TRANSIENT;
            }
        }
        return a.OTHER;
    }

    public static synchronized l d() {
        l lVar;
        synchronized (l.class) {
            if (s == null) {
                s = e();
            }
            lVar = s;
        }
        return lVar;
    }

    private static l e() {
        return new l(null, new HashMap<Integer, Set<Integer>>() {
            {
                put(Integer.valueOf(2), null);
                put(Integer.valueOf(4), null);
                put(Integer.valueOf(9), null);
                put(Integer.valueOf(17), null);
                put(Integer.valueOf(l.g), null);
            }
        }, new HashMap<Integer, Set<Integer>>() {
            {
                put(Integer.valueOf(102), null);
                put(Integer.valueOf(l.f), null);
            }
        }, null, null, null);
    }

    private static Map<Integer, Set<Integer>> a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
        if (optJSONArray.length() == 0) {
            return null;
        }
        Map<Integer, Set<Integer>> hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("code");
                if (optInt != 0) {
                    Object obj;
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        obj = null;
                    } else {
                        Set hashSet = new HashSet();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                        obj = hashSet;
                    }
                    hashMap.put(Integer.valueOf(optInt), obj);
                }
            }
        }
        return hashMap;
    }

    public static l a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String str = null;
        String str2 = null;
        String str3 = null;
        Map map = null;
        Map map2 = null;
        Map map3 = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("name");
                if (optString != null) {
                    if (optString.equalsIgnoreCase(j)) {
                        str3 = optJSONObject.optString(h, null);
                        map3 = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase(k)) {
                        str2 = optJSONObject.optString(h, null);
                        map2 = a(optJSONObject);
                    } else if (optString.equalsIgnoreCase(l)) {
                        str = optJSONObject.optString(h, null);
                        map = a(optJSONObject);
                    }
                }
            }
        }
        return new l(map3, map2, map, str3, str2, str);
    }
}

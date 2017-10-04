package com.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.a.a.a.b;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class p implements Map<String, Object> {
    private final Map<String, Object> a;

    static class a<T extends p> {
        private final SharedPreferences a;
        private final c b;
        private final String c;
        private final Class<T> d;
        private T e;

        a(Context context, c cVar, String str, Class<T> cls) {
            this.b = cVar;
            this.a = b.a(context);
            this.c = str;
            this.d = cls;
        }

        T a() {
            if (this.e == null) {
                String string = this.a.getString(this.c, null);
                if (b.a((CharSequence) string)) {
                    return null;
                }
                try {
                    this.e = b(this.b.a(string));
                } catch (IOException e) {
                    return null;
                }
            }
            return this.e;
        }

        boolean b() {
            return this.a.contains(this.c);
        }

        T b(Map<String, Object> map) {
            return p.a((Map) map, this.d);
        }

        void a(T t) {
            this.e = t;
            try {
                this.a.edit().putString(this.c, this.b.a((Map) t)).apply();
            } catch (Throwable e) {
                Log.d("analytics-android", "IOException " + e.getMessage(), e);
            }
        }

        void c() {
            this.a.edit().remove(this.c).apply();
        }
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return c((String) obj, obj2);
    }

    static <T extends p> T a(Map map, Class<T> cls) {
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Map.class});
            declaredConstructor.setAccessible(true);
            return (p) declaredConstructor.newInstance(new Object[]{map});
        } catch (Exception e) {
            throw new AssertionError("Could not create instance of " + cls.getCanonicalName() + ".\n" + e);
        }
    }

    public p() {
        this.a = new LinkedHashMap();
    }

    public p(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null.");
        }
        this.a = map;
    }

    public void clear() {
        this.a.clear();
    }

    public boolean containsKey(Object obj) {
        return this.a.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    public Set<Entry<String, Object>> entrySet() {
        return this.a.entrySet();
    }

    public Object get(Object obj) {
        return this.a.get(obj);
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public Set<String> keySet() {
        return this.a.keySet();
    }

    public Object c(String str, Object obj) {
        return this.a.put(str, obj);
    }

    public void putAll(Map<? extends String, ?> map) {
        this.a.putAll(map);
    }

    public Object remove(Object obj) {
        return this.a.remove(obj);
    }

    public int size() {
        return this.a.size();
    }

    public Collection<Object> values() {
        return this.a.values();
    }

    public boolean equals(Object obj) {
        return obj == this || this.a.equals(obj);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return this.a.toString();
    }

    public p b(String str, Object obj) {
        this.a.put(str, obj);
        return this;
    }

    public long a(String str, long j) {
        Object obj = get(str);
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (!(obj instanceof String)) {
            return j;
        }
        try {
            return Long.valueOf((String) obj).longValue();
        } catch (NumberFormatException e) {
            return j;
        }
    }

    public String c(String str) {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public boolean b(String str, boolean z) {
        Object obj = get(str);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        if (obj instanceof String) {
            return Boolean.valueOf((String) obj).booleanValue();
        }
        return z;
    }

    public <T extends Enum<T>> T a(Class<T> cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("enumType may not be null");
        }
        Object obj = get(str);
        if (cls.isInstance(obj)) {
            return (Enum) obj;
        }
        if (obj instanceof String) {
            return Enum.valueOf(cls, (String) obj);
        }
        return null;
    }

    public p a(Object obj) {
        Object obj2 = get(obj);
        if (obj2 instanceof p) {
            return (p) obj2;
        }
        if (obj2 instanceof Map) {
            return new p((Map) obj2);
        }
        return null;
    }

    public <T extends p> T a(String str, Class<T> cls) {
        return a(get(str), (Class) cls);
    }

    private <T extends p> T a(Object obj, Class<T> cls) {
        if (obj == null) {
            return null;
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return (p) obj;
        }
        return obj instanceof Map ? a((Map) obj, (Class) cls) : null;
    }
}

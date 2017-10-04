package com.here.a.a.a.a;

import com.here.a.a.a.d;
import com.here.a.a.a.e;
import com.here.a.a.a.f;
import java.util.ArrayList;

public class o {
    private static Class<? extends f> a;
    private f b;

    private static Class<?> b() {
        String property = System.getProperty("TRANSIT_SDK_JSON_OBJECT_IMPL");
        if (property == null || property.isEmpty()) {
            property = "com.here.api.transit.sdk.GsonJSONObjectImpl";
        }
        try {
            return Class.forName(property);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find an implementation of JSONObjectImpl: " + property);
        }
    }

    public static void a(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException("JSONImplClass can't be null.");
        } else if (f.class.equals(cls.getSuperclass())) {
            a = cls;
        } else {
            throw new RuntimeException(String.format("Class %s is not a subclass of JSONObjectImpl", new Object[]{cls.getName()}));
        }
    }

    public static o a(String str) {
        if (a == null) {
            a(b());
        }
        if (a == null) {
            throw new RuntimeException("JSONObjectImpl class not initialized.");
        }
        try {
            return new o((f) a.getDeclaredConstructor(new Class[]{String.class}).newInstance(new Object[]{str}));
        } catch (Exception e) {
            throw new d("Unable to create an instance of " + a.getName());
        }
    }

    protected o(f fVar) {
        if (fVar == null) {
            throw new NullPointerException("JSONObjectImpl can't be null.");
        }
        this.b = fVar;
    }

    public boolean b(String str) {
        try {
            return this.b.a(str) == null;
        } catch (e e) {
            return true;
        }
    }

    public o c(String str) {
        Object a = this.b.a(str);
        if (a instanceof f) {
            return new o((f) a);
        }
        throw new d("JSONObject[" + str + "] is not a JSONObject.");
    }

    public p d(String str) {
        Object a = this.b.a(str);
        if (a instanceof Iterable) {
            return new p((Iterable) a);
        }
        if (a instanceof f) {
            Iterable arrayList = new ArrayList();
            arrayList.add(new o((f) a));
            return new p(arrayList);
        }
        throw new d("JSONObject[" + str + "] is not a JSONArray nor JSONObject.");
    }

    public p e(String str) {
        try {
            return d(str);
        } catch (d e) {
            return null;
        }
    }

    public o f(String str) {
        try {
            return c(str);
        } catch (d e) {
            return null;
        }
    }

    public Float g(String str) {
        Object a = this.b.a(str);
        try {
            float floatValue;
            if (a instanceof Number) {
                floatValue = ((Number) a).floatValue();
            } else {
                floatValue = Float.parseFloat((String) a);
            }
            return Float.valueOf(floatValue);
        } catch (NumberFormatException e) {
            throw new d("JSONObject[" + str + "] is not a Float.");
        }
    }

    public Double h(String str) {
        Object a = this.b.a(str);
        try {
            return Double.valueOf(a instanceof Number ? ((Number) a).doubleValue() : Double.parseDouble((String) a));
        } catch (NumberFormatException e) {
            throw new d("JSONObject[" + str + "] is not a Double.");
        }
    }

    public String i(String str) {
        Object a = this.b.a(str);
        if (a instanceof String) {
            return (String) a;
        }
        throw new d("JSONObject[" + str + "] not a string.");
    }

    public String a(String str, String str2) {
        Object a;
        try {
            a = this.b.a(str);
        } catch (d e) {
            a = null;
        }
        return a == null ? str2 : a.toString();
    }

    public Integer j(String str) {
        Object a = this.b.a(str);
        try {
            return Integer.valueOf(a instanceof Number ? ((Number) a).intValue() : Integer.parseInt((String) a));
        } catch (NumberFormatException e) {
            throw new d("JSONObject[" + str + "] is not an Integer.");
        }
    }

    public o a() {
        f a = this.b.a();
        return a != null ? new o(a) : null;
    }

    public String toString() {
        return this.b.toString();
    }
}

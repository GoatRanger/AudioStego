package dji.thirdparty.afinal.d.a;

import java.util.HashMap;

public class b {
    private HashMap<String, Object> a = new HashMap();

    public Object a(String str) {
        return this.a.get(str);
    }

    public String b(String str) {
        return String.valueOf(a(str));
    }

    public int c(String str) {
        return Integer.valueOf(b(str)).intValue();
    }

    public boolean d(String str) {
        return Boolean.valueOf(b(str)).booleanValue();
    }

    public double e(String str) {
        return Double.valueOf(b(str)).doubleValue();
    }

    public float f(String str) {
        return Float.valueOf(b(str)).floatValue();
    }

    public long g(String str) {
        return Long.valueOf(b(str)).longValue();
    }

    public void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    public HashMap<String, Object> a() {
        return this.a;
    }
}

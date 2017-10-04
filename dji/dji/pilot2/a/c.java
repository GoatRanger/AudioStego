package dji.pilot2.a;

import java.util.HashMap;
import java.util.Map;

public class c {
    private Map<String, Object> a = new HashMap();

    public void a(String str, Object obj) {
        this.a.put(str, obj);
    }

    public Object a(String str) {
        return this.a.get(str);
    }
}

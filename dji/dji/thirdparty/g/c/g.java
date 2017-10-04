package dji.thirdparty.g.c;

import java.util.Map;

public class g {
    public static boolean a(Map map, Object obj, boolean z) {
        Object obj2 = map == null ? null : map.get(obj);
        if (obj2 == null || !(obj2 instanceof Boolean)) {
            return z;
        }
        return ((Boolean) obj2).booleanValue();
    }
}

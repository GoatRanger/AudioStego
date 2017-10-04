package dji.sdksharedlib.b;

import dji.sdksharedlib.b.b.a;
import dji.sdksharedlib.hardware.abstractions.DJISDKCacheUpdateType;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class d {
    public static final String ci = "None";
    @dji.sdksharedlib.b.b.d(a = String.class, c = 1, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String cj = "FirmwareVersion";
    @dji.sdksharedlib.b.b.d(a = Boolean.class, c = 4, d = DJISDKCacheUpdateType.USER_DRIVEN)
    public static final String ck = "Connection";
    public static Map<Class<? extends d>, Map<String, a>> cl;
    private String a;

    public d(String str) {
        this.a = str;
    }

    public String b() {
        return this.a;
    }

    protected String a() {
        return null;
    }

    public static Map<String, a> a(Class<? extends d> cls) {
        int i = 0;
        if (cls == null) {
            return null;
        }
        if (cl == null) {
            cl = new HashMap();
        }
        if (!cl.containsKey(cls)) {
            Field field;
            Map hashMap = new HashMap();
            for (Field field2 : d.class.getDeclaredFields()) {
                if (field2.getType() == String.class && a(field2.getModifiers()) && (field2.isAnnotationPresent(a.class) || field2.isAnnotationPresent(dji.sdksharedlib.b.b.d.class))) {
                    try {
                        hashMap.put((String) field2.get(null), new a((a) field2.getAnnotation(a.class), (dji.sdksharedlib.b.b.d) field2.getAnnotation(dji.sdksharedlib.b.b.d.class)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            while (i < length) {
                field2 = declaredFields[i];
                if (field2.getType() == String.class && a(field2.getModifiers()) && (field2.isAnnotationPresent(a.class) || field2.isAnnotationPresent(dji.sdksharedlib.b.b.d.class))) {
                    try {
                        hashMap.put((String) field2.get(null), new a((a) field2.getAnnotation(a.class), (dji.sdksharedlib.b.b.d) field2.getAnnotation(dji.sdksharedlib.b.b.d.class)));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                i++;
            }
            cl.put(cls, hashMap);
        }
        return (Map) cl.get(cls);
    }

    public static boolean a(int i) {
        return (i & 8) != 0;
    }
}

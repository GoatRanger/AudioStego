package com.alipay.e.a.a.b.b;

import com.alipay.e.a.a.b.a;
import java.lang.reflect.Method;

public class h {
    public static Object a(Object obj, Method method, Object[] objArr) {
        Object obj2 = null;
        if (!(obj == null || method == null || objArr == null)) {
            try {
                method.setAccessible(true);
                obj2 = method.invoke(obj, objArr);
            } catch (Exception e) {
                new StringBuilder("[-] can't invoke method ").append(method.getName()).append(" on target ").append(obj).append(", ").append(e);
            }
        }
        return obj2;
    }

    public static Method a(String str, Class<?> cls, Class<?> cls2) {
        Method method = null;
        if (!(a.a(str) || cls == null || cls2 == null)) {
            String str2;
            try {
                str2 = "set" + Character.toTitleCase(str.charAt(0)) + str.substring(1, str.length());
                try {
                    method = cls.getMethod(str2, new Class[]{cls2});
                } catch (NoSuchMethodException e) {
                    new StringBuilder("[-] Not Found Method ").append(str2).append(" in class ").append(cls.getName());
                    return method;
                }
            } catch (NoSuchMethodException e2) {
                str2 = method;
                new StringBuilder("[-] Not Found Method ").append(str2).append(" in class ").append(cls.getName());
                return method;
            }
        }
        return method;
    }
}

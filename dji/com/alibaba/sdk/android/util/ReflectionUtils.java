package com.alibaba.sdk.android.util;

import com.alibaba.sdk.android.trace.AliSDKLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtils {
    private static final Map<String, Class<?>> a = new HashMap();
    private static final String b = ReflectionUtils.class.getSimpleName();

    static {
        a.put("short", Short.TYPE);
        a.put("int", Integer.TYPE);
        a.put("long", Long.TYPE);
        a.put("double", Double.TYPE);
        a.put("float", Float.TYPE);
        a.put("char", Character.TYPE);
    }

    public static Object invoke(String str, String str2, String[] strArr, Object obj, Object[] objArr) {
        try {
            Method method;
            Class cls = Class.forName(str);
            if (strArr == null || strArr.length == 0) {
                method = cls.getMethod(str2, new Class[0]);
            } else {
                method = cls.getMethod(str2, toClasses(strArr));
            }
            return method.invoke(obj, objArr);
        } catch (Throwable e) {
            AliSDKLogger.e(b, "Fail to invoke the " + str + "." + str2 + ", the error is " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void set(Object obj, String str, Object obj2) {
        try {
            Field field = obj.getClass().getField(str);
            field.setAccessible(true);
            field.set(obj, obj2);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public static <T> T newInstance(Class<T> cls, Class<?>[] clsArr, Object[] objArr) {
        if (clsArr != null) {
            try {
                if (clsArr.length != 0) {
                    return cls.getConstructor(clsArr).newInstance(objArr);
                }
            } catch (Throwable e) {
                AliSDKLogger.e(b, "Fail to create the instance of type " + cls.getName() + ", the error is " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return cls.newInstance();
    }

    public static Object newInstance(String str, String[] strArr, Object[] objArr) {
        try {
            return newInstance(Class.forName(str), toClasses(strArr), objArr);
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e2) {
            AliSDKLogger.e(b, "Fail to create the instance of type " + str + ", the error is " + e2.getMessage());
            throw new RuntimeException(e2);
        }
    }

    public static Class<?>[] toClasses(String[] strArr) throws Exception {
        if (strArr == null) {
            return null;
        }
        Class<?>[] clsArr = new Class[strArr.length];
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            if (str.length() < 7) {
                clsArr[i] = (Class) a.get(str);
            }
            if (clsArr[i] == null) {
                clsArr[i] = Class.forName(str);
            }
        }
        return clsArr;
    }

    public static Class<?> loadClassQuietly(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Field getField(Class<?> cls, String str) {
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                declaredField.setAccessible(true);
                return declaredField;
            } catch (Exception e) {
                cls = cls.getSuperclass();
            }
        }
        return null;
    }

    public static Method getMethod(Class<?> cls, String str, Class<?>... clsArr) {
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Exception e) {
                cls = cls.getSuperclass();
                clsArr = new Class[0];
            }
        }
        return null;
    }
}

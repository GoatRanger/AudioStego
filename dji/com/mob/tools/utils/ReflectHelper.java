package com.mob.tools.utils;

import com.mob.tools.gui.CachePool;
import dji.pilot.usercenter.protocol.d;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ReflectHelper {
    private static CachePool<String, Constructor<?>> cachedConstr = new CachePool(5);
    private static CachePool<String, Method> cachedMethod = new CachePool(25);
    private static HashMap<String, Class<?>> classMap = new HashMap();
    private static HashMap<Class<?>, String> nameMap = new HashMap();
    private static HashSet<String> packageSet = new HashSet();

    public interface ReflectRunnable {
        Object run(Object obj);
    }

    static {
        packageSet.add("java.lang");
        packageSet.add("java.io");
        packageSet.add("java.nio");
        packageSet.add("java.net");
        packageSet.add("java.util");
        packageSet.add("com.mob.tools");
        packageSet.add("com.mob.tools.gui");
        packageSet.add("com.mob.tools.log");
        packageSet.add("com.mob.tools.network");
        packageSet.add("com.mob.tools.utils");
    }

    public static String importClass(String str) throws Throwable {
        return importClass(null, str);
    }

    public static synchronized String importClass(String str, String str2) throws Throwable {
        String str3;
        synchronized (ReflectHelper.class) {
            if (str2.endsWith(".*")) {
                packageSet.add(str2.substring(0, str2.length() - 2));
                str3 = "*";
            } else {
                Class cls = Class.forName(str2);
                if (str == null) {
                    str3 = cls.getSimpleName();
                } else {
                    str3 = str;
                }
                classMap.put(str3, cls);
                nameMap.put(cls, str3);
            }
        }
        return str3;
    }

    private static synchronized Class<?> getImportedClass(String str) {
        Class<?> cls;
        synchronized (ReflectHelper.class) {
            cls = (Class) classMap.get(str);
            if (cls == null) {
                Iterator it = packageSet.iterator();
                while (it.hasNext()) {
                    try {
                        importClass(((String) it.next()) + "." + str);
                    } catch (Throwable th) {
                    }
                    cls = (Class) classMap.get(str);
                    if (cls != null) {
                        break;
                    }
                }
            }
        }
        return cls;
    }

    private static Class<?>[] getTypes(Object[] objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    private static boolean primitiveEquals(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || ((cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class)))))));
    }

    private static boolean matchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        int i = 0;
        while (i < clsArr2.length) {
            if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static boolean tryMatchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length - clsArr2.length != 1) {
            return false;
        }
        boolean z;
        int i = 0;
        while (i < clsArr2.length) {
            if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                z = false;
                break;
            }
            i++;
        }
        z = true;
        if (z && clsArr[clsArr.length - 1].isArray()) {
            return true;
        }
        return false;
    }

    public static Object newInstance(String str, Object... objArr) throws Throwable {
        try {
            return onNewInstance(str, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + str + ", methodName: <init>", th);
            }
        }
    }

    private static Object onNewInstance(String str, Object... objArr) throws Throwable {
        if (str.startsWith(d.G)) {
            return newArray(str, objArr);
        }
        String str2 = str + "#" + objArr.length;
        Constructor constructor = (Constructor) cachedConstr.get(str2);
        Class[] types = getTypes(objArr);
        if (constructor == null || !matchParams(constructor.getParameterTypes(), types)) {
            Constructor[] declaredConstructors = getImportedClass(str).getDeclaredConstructors();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Constructor constructor2 : declaredConstructors) {
                Object parameterTypes = constructor2.getParameterTypes();
                if (matchParams(parameterTypes, types)) {
                    cachedConstr.put(str2, constructor2);
                    constructor2.setAccessible(true);
                    return constructor2.newInstance(objArr);
                }
                if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && types.length >= parameterTypes.length - 1) {
                    arrayList.add(constructor2);
                    arrayList2.add(parameterTypes);
                }
            }
            for (int i = 0; i < arrayList2.size(); i++) {
                Class[] clsArr = (Class[]) arrayList2.get(i);
                Class componentType = clsArr[clsArr.length - 1].getComponentType();
                Object obj;
                if (tryMatchParams(clsArr, types)) {
                    obj = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, obj, 0, objArr.length);
                    obj[objArr.length] = Array.newInstance(componentType, 0);
                    constructor = (Constructor) arrayList.get(i);
                    constructor.setAccessible(true);
                    return constructor.newInstance(objArr);
                }
                int length;
                for (length = clsArr.length - 1; length < types.length; length++) {
                    if (!types[length].equals(componentType)) {
                        length = 0;
                        break;
                    }
                }
                boolean z = true;
                if (length != 0) {
                    int length2 = (types.length - clsArr.length) + 1;
                    Object newInstance = Array.newInstance(componentType, length2);
                    for (length = 0; length < length2; length++) {
                        Array.set(newInstance, length, objArr[(clsArr.length - 1) + length]);
                    }
                    obj = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, obj, 0, objArr.length);
                    obj[objArr.length] = newInstance;
                    constructor = (Constructor) arrayList.get(i);
                    constructor.setAccessible(true);
                    return constructor.newInstance(objArr);
                }
            }
            throw new NoSuchMethodException("className: " + str + ", methodName: <init>");
        }
        constructor.setAccessible(true);
        return constructor.newInstance(objArr);
    }

    private static Object newArray(String str, Object... objArr) throws Throwable {
        int[] iArr;
        int i = 0;
        int i2 = 0;
        String str2 = str;
        while (str2.startsWith(d.G)) {
            i2++;
            str2 = str2.substring(1);
        }
        if (i2 == objArr.length) {
            int[] iArr2 = new int[i2];
            while (i < i2) {
                try {
                    iArr2[i] = Integer.parseInt(String.valueOf(objArr[i]));
                    i++;
                } catch (Throwable th) {
                    iArr = null;
                }
            }
            iArr = iArr2;
        } else {
            iArr = null;
        }
        if (iArr != null) {
            Class cls;
            if ("B".equals(str2)) {
                cls = Byte.TYPE;
            } else if ("S".equals(str2)) {
                cls = Short.TYPE;
            } else if ("I".equals(str2)) {
                cls = Integer.TYPE;
            } else if ("J".equals(str2)) {
                cls = Long.TYPE;
            } else if ("F".equals(str2)) {
                cls = Float.TYPE;
            } else if ("D".equals(str2)) {
                cls = Double.TYPE;
            } else if ("Z".equals(str2)) {
                cls = Boolean.TYPE;
            } else if ("C".equals(str2)) {
                cls = Character.TYPE;
            } else {
                cls = getImportedClass(str2);
            }
            if (cls != null) {
                return Array.newInstance(cls, iArr);
            }
        }
        throw new NoSuchMethodException("className: [" + str + ", methodName: <init>");
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object... objArr) throws Throwable {
        try {
            return invokeMethod(str, null, str2, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + str + ", methodName: " + str2, th);
            }
        }
    }

    private static <T> T invokeMethod(String str, Object obj, String str2, Object... objArr) throws Throwable {
        Class importedClass;
        int i;
        if (obj == null) {
            importedClass = getImportedClass(str);
        } else {
            importedClass = obj.getClass();
        }
        String str3 = importedClass.getName() + "#" + str2 + "#" + objArr.length;
        Method method = (Method) cachedMethod.get(str3);
        Class[] types = getTypes(objArr);
        if (method != null) {
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if (obj != null) {
                isStatic = !isStatic;
            }
            if (isStatic && matchParams(method.getParameterTypes(), types)) {
                method.setAccessible(true);
                if (method.getReturnType() != Void.TYPE) {
                    return method.invoke(obj, objArr);
                }
                method.invoke(obj, objArr);
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        while (importedClass != null) {
            arrayList.add(importedClass);
            importedClass = importedClass.getSuperclass();
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (Method method2 : ((Class) it.next()).getDeclaredMethods()) {
                boolean isStatic2 = Modifier.isStatic(method2.getModifiers());
                if (obj != null) {
                    isStatic2 = !isStatic2;
                }
                if (method2.getName().equals(str2) && r0) {
                    Object parameterTypes = method2.getParameterTypes();
                    if (matchParams(parameterTypes, types)) {
                        cachedMethod.put(str3, method2);
                        method2.setAccessible(true);
                        if (method2.getReturnType() != Void.TYPE) {
                            return method2.invoke(obj, objArr);
                        }
                        method2.invoke(obj, objArr);
                        return null;
                    } else if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && types.length >= parameterTypes.length - 1) {
                        arrayList2.add(method2);
                        arrayList3.add(parameterTypes);
                    }
                }
            }
        }
        for (i = 0; i < arrayList3.size(); i++) {
            Class[] clsArr = (Class[]) arrayList3.get(i);
            Class componentType = clsArr[clsArr.length - 1].getComponentType();
            Object obj2;
            if (tryMatchParams(clsArr, types)) {
                obj2 = new Object[(objArr.length + 1)];
                System.arraycopy(objArr, 0, obj2, 0, objArr.length);
                obj2[objArr.length] = Array.newInstance(componentType, 0);
                method = (Method) arrayList2.get(i);
                method.setAccessible(true);
                if (method.getReturnType() != Void.TYPE) {
                    return method.invoke(obj, obj2);
                }
                method.invoke(obj, obj2);
                return null;
            }
            int length;
            for (length = clsArr.length - 1; length < types.length; length++) {
                if (!types[length].equals(componentType)) {
                    obj2 = null;
                    break;
                }
            }
            length = 1;
            if (obj2 != null) {
                int length2 = (types.length - clsArr.length) + 1;
                Object newInstance = Array.newInstance(componentType, length2);
                for (length = 0; length < length2; length++) {
                    Array.set(newInstance, length, objArr[(clsArr.length - 1) + length]);
                }
                obj2 = new Object[clsArr.length];
                System.arraycopy(objArr, 0, obj2, 0, clsArr.length - 1);
                obj2[clsArr.length - 1] = newInstance;
                method = (Method) arrayList2.get(i);
                method.setAccessible(true);
                if (method.getReturnType() != Void.TYPE) {
                    return method.invoke(obj, obj2);
                }
                method.invoke(obj, obj2);
                return null;
            }
        }
        throw new NoSuchMethodException("className: " + obj.getClass() + ", methodName: " + str2);
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) throws Throwable {
        try {
            return invokeMethod(null, obj, str, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", methodName: " + str, th);
            }
        }
    }

    public static <T> T getStaticField(String str, String str2) throws Throwable {
        try {
            return onGetStaticField(str, str2);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + str + ", fieldName: " + str2, th);
            }
        }
    }

    private static <T> T onGetStaticField(String str, String str2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                return declaredField.get(null);
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2);
    }

    public static void setStaticField(String str, String str2, Object obj) throws Throwable {
        try {
            onSetStaticField(str, str2, obj);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj), th);
            }
        }
    }

    private static void onSetStaticField(String str, String str2, Object obj) throws Throwable {
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                declaredField.set(null, obj);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj));
    }

    public static <T> T getInstanceField(Object obj, String str) throws Throwable {
        try {
            return onGetInstanceField(obj, str);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", fieldName: " + str, th);
            }
        }
    }

    private static <T> T onGetInstanceField(Object obj, String str) throws Throwable {
        if (obj.getClass().isArray()) {
            return onGetElement(obj, str);
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && !Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    private static Object onGetElement(Object obj, String str) throws Throwable {
        if (DJIPhotoPreveiwActivity.I.equals(str)) {
            return Integer.valueOf(Array.getLength(obj));
        }
        if (str.startsWith(d.G) && str.endsWith(d.H)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable th) {
                parseInt = -1;
            }
            if (parseInt != -1) {
                return Array.get(obj, parseInt);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    public static void setInstanceField(Object obj, String str, Object obj2) throws Throwable {
        try {
            onSetInstanceField(obj, str, obj2);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchFieldException)) {
                Throwable th2 = new Throwable("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2), th);
            }
        }
    }

    private static void onSetInstanceField(Object obj, String str, Object obj2) throws Throwable {
        if (obj.getClass().isArray()) {
            onSetElement(obj, str, obj2);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field declaredField;
            try {
                declaredField = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable th) {
                declaredField = null;
            }
            if (declaredField != null && !Modifier.isStatic(declaredField.getModifiers())) {
                declaredField.setAccessible(true);
                declaredField.set(obj, obj2);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    private static void onSetElement(Object obj, String str, Object obj2) throws Throwable {
        Object obj3 = null;
        if (str.startsWith(d.G) && str.endsWith(d.H)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable th) {
                parseInt = -1;
            }
            if (parseInt != -1) {
                String name = obj.getClass().getName();
                while (name.startsWith(d.G)) {
                    name = name.substring(1);
                }
                Class cls = obj2.getClass();
                if ("B".equals(name)) {
                    if (cls == Byte.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if ("S".equals(name)) {
                    if (cls == Short.class) {
                        obj3 = obj2;
                    } else if (cls == Byte.class) {
                        obj3 = Short.valueOf((short) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("I".equals(name)) {
                    if (cls == Integer.class) {
                        obj3 = obj2;
                    } else if (cls == Short.class) {
                        obj3 = Integer.valueOf(((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Integer.valueOf(((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("J".equals(name)) {
                    if (cls == Long.class) {
                        obj3 = obj2;
                    } else if (cls == Integer.class) {
                        obj3 = Long.valueOf((long) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Long.valueOf((long) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Long.valueOf((long) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("F".equals(name)) {
                    if (cls == Float.class) {
                        obj3 = obj2;
                    } else if (cls == Long.class) {
                        obj3 = Float.valueOf((float) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj3 = Float.valueOf((float) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Float.valueOf((float) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj3 = Float.valueOf((float) ((Byte) obj2).byteValue());
                    }
                    if (obj3 != null) {
                        Array.set(obj, parseInt, obj3);
                        return;
                    }
                } else if ("D".equals(name)) {
                    Object obj4;
                    if (cls == Double.class) {
                        obj4 = obj2;
                    } else if (cls == Float.class) {
                        obj4 = Double.valueOf((double) ((Float) obj2).floatValue());
                    } else if (cls == Long.class) {
                        obj4 = Double.valueOf((double) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj4 = Double.valueOf((double) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj4 = Double.valueOf((double) ((Short) obj2).shortValue());
                    } else if (cls == Byte.class) {
                        obj4 = Double.valueOf((double) ((Byte) obj2).byteValue());
                    } else {
                        obj4 = null;
                    }
                    if (obj4 != null) {
                        Array.set(obj, parseInt, obj4);
                        return;
                    }
                } else if ("Z".equals(name)) {
                    if (cls == Boolean.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if ("C".equals(name)) {
                    if (cls == Character.class) {
                        Array.set(obj, parseInt, obj2);
                        return;
                    }
                } else if (name.equals(cls.getName())) {
                    Array.set(obj, parseInt, obj2);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    public static Class<?> getClass(String str) throws Throwable {
        Class<?> importedClass = getImportedClass(str);
        if (importedClass == null) {
            try {
                importedClass = Class.forName(str);
                if (importedClass != null) {
                    classMap.put(str, importedClass);
                }
            } catch (Throwable th) {
            }
        }
        return importedClass;
    }

    public static String getName(Class<?> cls) throws Throwable {
        String str = (String) nameMap.get(cls);
        if (str != null) {
            return str;
        }
        str = cls.getSimpleName();
        if (classMap.containsKey(str)) {
            return null;
        }
        classMap.put(str, cls);
        nameMap.put(cls, str);
        return str;
    }

    public static Object createProxy(final HashMap<String, ReflectRunnable> hashMap, Class<?>... clsArr) throws Throwable {
        return Proxy.newProxyInstance(hashMap.getClass().getClassLoader(), clsArr, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                ReflectRunnable reflectRunnable = (ReflectRunnable) hashMap.get(method.getName());
                if (reflectRunnable != null) {
                    return reflectRunnable.run(objArr);
                }
                throw new NoSuchMethodException();
            }
        });
    }
}

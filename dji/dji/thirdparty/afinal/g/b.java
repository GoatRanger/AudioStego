package dji.thirdparty.afinal.g;

import dji.thirdparty.afinal.a.a.a;
import dji.thirdparty.afinal.a.a.c;
import dji.thirdparty.afinal.a.a.d;
import dji.thirdparty.afinal.a.a.f;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class b {
    public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Method a(Class<?> cls, Field field) {
        String name = field.getName();
        Method method = null;
        if (field.getType() == Boolean.TYPE) {
            method = a((Class) cls, name);
        }
        if (method == null) {
            return b((Class) cls, name);
        }
        return method;
    }

    public static Method a(Class<?> cls, String str) {
        String str2 = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        if (!b(str)) {
            str = str2;
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method b(Class<?> cls, Field field) {
        String name = field.getName();
        String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        if (b(field.getName())) {
            str = "set" + name.substring(2, 3).toUpperCase() + name.substring(3);
        }
        try {
            return cls.getDeclaredMethod(str, new Class[]{field.getType()});
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static boolean b(String str) {
        if (str == null || str.trim().length() == 0 || !str.startsWith("is") || Character.isLowerCase(str.charAt(2))) {
            return false;
        }
        return true;
    }

    public static Method b(Class<?> cls, String str) {
        try {
            return cls.getDeclaredMethod("get" + str.substring(0, 1).toUpperCase() + str.substring(1), new Class[0]);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method c(Class<?> cls, Field field) {
        String name = field.getName();
        try {
            return cls.getDeclaredMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), new Class[]{field.getType()});
        } catch (NoSuchMethodException e) {
            if (field.getType() == Boolean.TYPE) {
                return b((Class) cls, field);
            }
            return null;
        }
    }

    public static Method c(Class<?> cls, String str) {
        try {
            return c((Class) cls, cls.getDeclaredField(str));
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Object a(Object obj, Field field) {
        return a(obj, a(obj.getClass(), field));
    }

    public static Object a(Object obj, String str) {
        return a(obj, b(obj.getClass(), str));
    }

    public static void a(Object obj, Field field, Object obj2) {
        try {
            Method c = c(obj.getClass(), field);
            if (c != null) {
                c.setAccessible(true);
                Class type = field.getType();
                if (type == String.class) {
                    c.invoke(obj, new Object[]{obj2.toString()});
                } else if (type == Integer.TYPE || type == Integer.class) {
                    int intValue;
                    r1 = new Object[1];
                    if (obj2 == null) {
                        intValue = ((Integer) null).intValue();
                    } else {
                        intValue = Integer.parseInt(obj2.toString());
                    }
                    r1[0] = Integer.valueOf(intValue);
                    c.invoke(obj, r1);
                } else if (type == Float.TYPE || type == Float.class) {
                    r1 = new Object[1];
                    r1[0] = Float.valueOf(obj2 == null ? ((Float) null).floatValue() : Float.parseFloat(obj2.toString()));
                    c.invoke(obj, r1);
                } else if (type == Long.TYPE || type == Long.class) {
                    Object[] objArr = new Object[1];
                    objArr[0] = Long.valueOf(obj2 == null ? ((Long) null).longValue() : Long.parseLong(obj2.toString()));
                    c.invoke(obj, objArr);
                } else if (type == Date.class) {
                    r1 = new Object[1];
                    r1[0] = obj2 == null ? (Date) null : a(obj2.toString());
                    c.invoke(obj, r1);
                } else {
                    c.invoke(obj, new Object[]{obj2});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Field d(Class<?> cls, String str) {
        if (str == null) {
            return null;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return null;
        }
        Field c;
        Field field;
        if (str.equals(a.b(cls))) {
            c = a.c(cls);
        } else {
            c = null;
        }
        if (c == null) {
            for (Field field2 : declaredFields) {
                d dVar = (d) field2.getAnnotation(d.class);
                if (dVar != null && str.equals(dVar.a())) {
                    field = field2;
                    break;
                }
                dji.thirdparty.afinal.a.a.b bVar = (dji.thirdparty.afinal.a.a.b) field2.getAnnotation(dji.thirdparty.afinal.a.a.b.class);
                if (bVar != null && bVar.a().trim().length() != 0) {
                    field = field2;
                    break;
                }
            }
        }
        field = c;
        if (field == null) {
            return e(cls, str);
        }
        return field;
    }

    public static Field e(Class<?> cls, String str) {
        Field field = null;
        if (str != null) {
            try {
                field = cls.getDeclaredField(str);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
        }
        return field;
    }

    public static String a(Field field) {
        d dVar = (d) field.getAnnotation(d.class);
        if (dVar != null && dVar.a().trim().length() != 0) {
            return dVar.a();
        }
        dji.thirdparty.afinal.a.a.b bVar = (dji.thirdparty.afinal.a.a.b) field.getAnnotation(dji.thirdparty.afinal.a.a.b.class);
        if (bVar != null && bVar.a().trim().length() != 0) {
            return bVar.a();
        }
        c cVar = (c) field.getAnnotation(c.class);
        if (cVar != null && cVar.a() != null && cVar.a().trim().length() != 0) {
            return cVar.a();
        }
        a aVar = (a) field.getAnnotation(a.class);
        if (aVar == null || aVar.a().trim().length() == 0) {
            return field.getName();
        }
        return aVar.a();
    }

    public static String b(Field field) {
        d dVar = (d) field.getAnnotation(d.class);
        if (dVar == null || dVar.b().trim().length() == 0) {
            return null;
        }
        return dVar.b();
    }

    public static boolean c(Field field) {
        return field.getAnnotation(f.class) != null;
    }

    private static Object a(Object obj, Method method) {
        Object obj2 = null;
        if (!(obj == null || method == null)) {
            try {
                obj2 = method.invoke(obj, new Object[0]);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return obj2;
    }

    public static boolean d(Field field) {
        return field.getAnnotation(dji.thirdparty.afinal.a.a.b.class) != null;
    }

    public static boolean e(Field field) {
        return field.getAnnotation(c.class) != null;
    }

    public static boolean f(Field field) {
        return d(field) || e(field);
    }

    public static boolean g(Field field) {
        Class type = field.getType();
        if (type.equals(String.class) || type.equals(Integer.class) || type.equals(Byte.class) || type.equals(Long.class) || type.equals(Double.class) || type.equals(Float.class) || type.equals(Character.class) || type.equals(Short.class) || type.equals(Boolean.class) || type.equals(Date.class) || type.equals(Date.class) || type.equals(java.sql.Date.class) || type.isPrimitive()) {
            return true;
        }
        return false;
    }

    public static Date a(String str) {
        if (str != null) {
            try {
                return a.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

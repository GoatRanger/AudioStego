package dji.thirdparty.afinal.g;

import dji.thirdparty.afinal.a.a.e;
import dji.thirdparty.afinal.d.b.c;
import dji.thirdparty.afinal.d.b.d;
import dji.thirdparty.afinal.e.b;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static String a(Class<?> cls) {
        e eVar = (e) cls.getAnnotation(e.class);
        if (eVar == null || eVar.a().trim().length() == 0) {
            return cls.getName().replace('.', '_');
        }
        return eVar.a();
    }

    public static Object a(Object obj) {
        return b.a(obj, c(obj.getClass()));
    }

    public static String b(Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields != null) {
            dji.thirdparty.afinal.a.a.a aVar = null;
            for (Field field : declaredFields) {
                aVar = (dji.thirdparty.afinal.a.a.a) field.getAnnotation(dji.thirdparty.afinal.a.a.a.class);
                if (aVar != null) {
                    break;
                }
            }
            Field field2 = null;
            if (aVar != null) {
                String a = aVar.a();
                if (a == null || a.trim().length() == 0) {
                    return field2.getName();
                }
                return a;
            }
            for (Field name : declaredFields) {
                if ("_id".equals(name.getName())) {
                    return "_id";
                }
            }
            for (Field name2 : declaredFields) {
                if ("id".equals(name2.getName())) {
                    return "id";
                }
            }
            return null;
        }
        throw new RuntimeException("this model[" + cls + "] has no field");
    }

    public static Field c(Class<?> cls) {
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields != null) {
            for (Field field : declaredFields) {
                if (field.getAnnotation(dji.thirdparty.afinal.a.a.a.class) != null) {
                    break;
                }
            }
            Field field2 = null;
            if (field2 == null) {
                for (Field field3 : declaredFields) {
                    if ("_id".equals(field3.getName())) {
                        break;
                    }
                }
            }
            Field field32 = field2;
            if (field32 == null) {
                for (Field field4 : declaredFields) {
                    if ("id".equals(field4.getName())) {
                        return field4;
                    }
                }
            }
            return field32;
        }
        throw new RuntimeException("this model[" + cls + "] has no field");
    }

    public static String d(Class<?> cls) {
        Field c = c(cls);
        return c == null ? null : c.getName();
    }

    public static List<dji.thirdparty.afinal.d.b.e> e(Class<?> cls) {
        List<dji.thirdparty.afinal.d.b.e> arrayList = new ArrayList();
        try {
            Field[] declaredFields = cls.getDeclaredFields();
            String d = d(cls);
            for (Field field : declaredFields) {
                if (!(b.c(field) || !b.g(field) || field.getName().equals(d))) {
                    dji.thirdparty.afinal.d.b.e eVar = new dji.thirdparty.afinal.d.b.e();
                    eVar.b(b.a(field));
                    eVar.a(field.getName());
                    eVar.b(field.getType());
                    eVar.c(b.b(field));
                    eVar.b(b.c((Class) cls, field));
                    eVar.a(b.a((Class) cls, field));
                    eVar.a(field);
                    arrayList.add(eVar);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static List<c> f(Class<?> cls) {
        List<c> arrayList = new ArrayList();
        try {
            for (Field field : cls.getDeclaredFields()) {
                if (!b.c(field) && b.d(field)) {
                    c cVar = new c();
                    if (field.getType() == dji.thirdparty.afinal.d.a.c.class) {
                        Class cls2 = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1];
                        if (cls2 != null) {
                            cVar.a(cls2);
                        }
                    } else {
                        cVar.a(field.getType());
                    }
                    cVar.b(b.a(field));
                    cVar.a(field.getName());
                    cVar.b(field.getType());
                    cVar.b(b.c((Class) cls, field));
                    cVar.a(b.a((Class) cls, field));
                    arrayList.add(cVar);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static List<d> g(Class<?> cls) {
        List<d> arrayList = new ArrayList();
        try {
            for (Field field : cls.getDeclaredFields()) {
                if (!b.c(field) && b.e(field)) {
                    d dVar = new d();
                    dVar.b(b.a(field));
                    dVar.a(field.getName());
                    if (field.getGenericType() instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                        Class cls2;
                        if (parameterizedType.getActualTypeArguments().length == 1) {
                            cls2 = (Class) parameterizedType.getActualTypeArguments()[0];
                            if (cls2 != null) {
                                dVar.a(cls2);
                            }
                        } else {
                            cls2 = (Class) parameterizedType.getActualTypeArguments()[1];
                            if (cls2 != null) {
                                dVar.a(cls2);
                            }
                        }
                        dVar.b(field.getType());
                        dVar.b(b.c((Class) cls, field));
                        dVar.a(b.a((Class) cls, field));
                        arrayList.add(dVar);
                    } else {
                        throw new b("getOneToManyList Exception:" + field.getName() + "'s type is null");
                    }
                }
            }
            return arrayList;
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}

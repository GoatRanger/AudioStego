package dji.thirdparty.afinal.d.a;

import android.text.TextUtils;
import android.util.Log;
import dji.thirdparty.afinal.d.b.a;
import dji.thirdparty.afinal.d.b.b;
import dji.thirdparty.afinal.d.b.c;
import dji.thirdparty.afinal.d.b.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class e {
    public static f a(Object obj) {
        List<b> c = c(obj);
        StringBuffer stringBuffer = new StringBuffer();
        if (c == null || c.size() <= 0) {
            return null;
        }
        f fVar = new f();
        stringBuffer.append("INSERT INTO ");
        stringBuffer.append(f.a(obj.getClass()).b());
        stringBuffer.append(" (");
        for (b bVar : c) {
            stringBuffer.append(bVar.a()).append(",");
            fVar.a(bVar.b());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(") VALUES ( ");
        int size = c.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append("?,");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(")");
        fVar.a(stringBuffer.toString());
        return fVar;
    }

    public static String b(Object obj) {
        List<b> c = c(obj);
        StringBuffer stringBuffer = new StringBuffer();
        if (c != null && c.size() > 0) {
            stringBuffer.append("INSERT OR REPLACE INTO ");
            stringBuffer.append(f.a(obj.getClass()).b());
            stringBuffer.append(" (");
            for (b a : c) {
                stringBuffer.append(a.a()).append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(") VALUES ( ");
            int size = c.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append("?,");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(")");
        }
        return stringBuffer.toString();
    }

    public static String a(Object obj, String str) {
        List<b> d = d(obj);
        String a = ((b) d.get(0)).a();
        String b = f.a(obj.getClass()).b();
        StringBuffer stringBuffer = new StringBuffer();
        if (d != null && d.size() > 0) {
            stringBuffer.append("INSERT OR REPLACE INTO ");
            stringBuffer.append(b);
            stringBuffer.append(" (");
            for (b a2 : d) {
                stringBuffer.append(a2.a()).append(",");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(") VALUES ( ");
            d.size();
            for (b a22 : d) {
                if (a22.a().equals(a)) {
                    stringBuffer.append("(select " + a + " from " + b + " where " + str + " = ?),");
                } else {
                    stringBuffer.append("?,");
                }
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            stringBuffer.append(")");
        }
        Log.d("", "ttttttt=" + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public static List<b> c(Object obj) {
        List<b> arrayList = new ArrayList();
        f a = f.a(obj.getClass());
        Object a2 = a.c().a(obj);
        if (!((a2 instanceof Integer) || !(a2 instanceof String) || a2 == null)) {
            arrayList.add(new b(a.c().c(), a2));
        }
        for (dji.thirdparty.afinal.d.b.e a3 : a.a.values()) {
            b a4 = a(a3, obj);
            if (a4 != null) {
                arrayList.add(a4);
            }
        }
        for (c a5 : a.c.values()) {
            a4 = a(a5, obj);
            if (a4 != null) {
                arrayList.add(a4);
            }
        }
        return arrayList;
    }

    public static List<b> d(Object obj) {
        List<b> arrayList = new ArrayList();
        f a = f.a(obj.getClass());
        arrayList.add(new b(a.c().c(), a.c().a(obj)));
        for (dji.thirdparty.afinal.d.b.e a2 : a.a.values()) {
            b a3 = a(a2, obj);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        for (c a4 : a.c.values()) {
            a3 = a(a4, obj);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        return arrayList;
    }

    public static String[] a(f fVar, Object obj) {
        Collection<dji.thirdparty.afinal.d.b.e> values = fVar.a.values();
        String[] strArr = new String[values.size()];
        int i = 0;
        for (dji.thirdparty.afinal.d.b.e a : values) {
            b a2 = a(a, obj);
            if (a2 != null) {
                strArr[i] = a2.b().toString();
            }
            i++;
        }
        return strArr;
    }

    public static String[] a(f fVar, Object obj, String str, String str2) {
        Collection<dji.thirdparty.afinal.d.b.e> values = fVar.a.values();
        String[] strArr = new String[(values.size() + 1)];
        strArr[0] = str2;
        int i = 1;
        for (dji.thirdparty.afinal.d.b.e a : values) {
            b a2 = a(a, obj);
            if (a2 != null) {
                strArr[i] = a2.b().toString();
            }
            i++;
        }
        return strArr;
    }

    private static String a(String str) {
        return "DELETE FROM " + str;
    }

    public static f e(Object obj) {
        f a = f.a(obj.getClass());
        a c = a.c();
        Object a2 = c.a(obj);
        if (a2 == null) {
            throw new dji.thirdparty.afinal.e.b("getDeleteSQL:" + obj.getClass() + " id value is null");
        }
        StringBuffer stringBuffer = new StringBuffer(a(a.b()));
        stringBuffer.append(" WHERE ").append(c.c()).append("=?");
        f fVar = new f();
        fVar.a(stringBuffer.toString());
        fVar.a(a2);
        return fVar;
    }

    public static f a(Class<?> cls, Object obj) {
        f a = f.a((Class) cls);
        a c = a.c();
        if (obj == null) {
            throw new dji.thirdparty.afinal.e.b("getDeleteSQL:idValue is null");
        }
        StringBuffer stringBuffer = new StringBuffer(a(a.b()));
        stringBuffer.append(" WHERE ").append(c.c()).append("=?");
        f fVar = new f();
        fVar.a(stringBuffer.toString());
        fVar.a(obj);
        return fVar;
    }

    public static String a(Class<?> cls, String str) {
        StringBuffer stringBuffer = new StringBuffer(a(f.a((Class) cls).b()));
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(" WHERE ");
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    private static String b(String str) {
        return new StringBuffer("SELECT * FROM ").append(str).toString();
    }

    public static String b(Class<?> cls, Object obj) {
        f a = f.a((Class) cls);
        StringBuffer stringBuffer = new StringBuffer(b(a.b()));
        stringBuffer.append(" WHERE ");
        stringBuffer.append(a(a.c().c(), obj));
        return stringBuffer.toString();
    }

    public static f c(Class<?> cls, Object obj) {
        f a = f.a((Class) cls);
        StringBuffer stringBuffer = new StringBuffer(b(a.b()));
        stringBuffer.append(" WHERE ").append(a.c().c()).append("=?");
        f fVar = new f();
        fVar.a(stringBuffer.toString());
        fVar.a(obj);
        return fVar;
    }

    public static String a(Class<?> cls) {
        return b(f.a((Class) cls).b());
    }

    public static String b(Class<?> cls, String str) {
        StringBuffer stringBuffer = new StringBuffer(b(f.a((Class) cls).b()));
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(" WHERE ").append(str);
        }
        return stringBuffer.toString();
    }

    public static f f(Object obj) {
        f a = f.a(obj.getClass());
        Object a2 = a.c().a(obj);
        if (a2 == null) {
            throw new dji.thirdparty.afinal.e.b("this entity[" + obj.getClass() + "]'s id value is null");
        }
        List<b> arrayList = new ArrayList();
        for (dji.thirdparty.afinal.d.b.e a3 : a.a.values()) {
            b a4 = a(a3, obj);
            if (a4 != null) {
                arrayList.add(a4);
            }
        }
        for (c a5 : a.c.values()) {
            a4 = a(a5, obj);
            if (a4 != null) {
                arrayList.add(a4);
            }
        }
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        f fVar = new f();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(a.b());
        stringBuffer.append(" SET ");
        for (b a42 : arrayList) {
            stringBuffer.append(a42.a()).append("=?,");
            fVar.a(a42.b());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" WHERE ").append(a.c().c()).append("=?");
        fVar.a(a2);
        fVar.a(stringBuffer.toString());
        return fVar;
    }

    public static f b(Object obj, String str) {
        f a = f.a(obj.getClass());
        List<b> arrayList = new ArrayList();
        for (dji.thirdparty.afinal.d.b.e a2 : a.a.values()) {
            b a3 = a(a2, obj);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        for (c a4 : a.c.values()) {
            a3 = a(a4, obj);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        if (arrayList == null || arrayList.size() == 0) {
            throw new dji.thirdparty.afinal.e.b("this entity[" + obj.getClass() + "] has no property");
        }
        f fVar = new f();
        StringBuffer stringBuffer = new StringBuffer("UPDATE ");
        stringBuffer.append(a.b());
        stringBuffer.append(" SET ");
        for (b a32 : arrayList) {
            stringBuffer.append(a32.a()).append("=?,");
            fVar.a(a32.b());
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        if (!TextUtils.isEmpty(str)) {
            stringBuffer.append(" WHERE ").append(str);
        }
        fVar.a(stringBuffer.toString());
        return fVar;
    }

    public static String b(Class<?> cls) {
        f a = f.a((Class) cls);
        a c = a.c();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CREATE TABLE IF NOT EXISTS ");
        stringBuffer.append(a.b());
        stringBuffer.append(" ( ");
        Class e = c.e();
        if (e == Integer.TYPE || e == Integer.class || e == Long.TYPE || e == Long.class) {
            stringBuffer.append(c.c()).append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        } else {
            stringBuffer.append(c.c()).append(" TEXT PRIMARY KEY,");
        }
        for (dji.thirdparty.afinal.d.b.e eVar : a.a.values()) {
            stringBuffer.append(eVar.c());
            Class e2 = eVar.e();
            if (e2 == Integer.TYPE || e2 == Integer.class || e2 == Long.TYPE || e2 == Long.class) {
                stringBuffer.append(" INTEGER");
            } else if (e2 == Float.TYPE || e2 == Float.class || e2 == Double.TYPE || e2 == Double.class) {
                stringBuffer.append(" REAL");
            } else if (e2 == Boolean.TYPE || e2 == Boolean.class) {
                stringBuffer.append(" NUMERIC");
            }
            stringBuffer.append(",");
        }
        for (c c2 : a.c.values()) {
            stringBuffer.append(c2.c()).append(" INTEGER").append(",");
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        stringBuffer.append(" )");
        return stringBuffer.toString();
    }

    private static String a(String str, Object obj) {
        StringBuffer append = new StringBuffer(str).append("=");
        if ((obj instanceof String) || (obj instanceof Date) || (obj instanceof java.sql.Date)) {
            append.append("'").append(obj).append("'");
        } else {
            append.append(obj);
        }
        return append.toString();
    }

    private static b a(dji.thirdparty.afinal.d.b.e eVar, Object obj) {
        String c = eVar.c();
        Object a = eVar.a(obj);
        if (a != null) {
            return new b(c, a);
        }
        if (eVar.d() == null || eVar.d().trim().length() == 0) {
            return null;
        }
        return new b(c, eVar.d());
    }

    private static b a(c cVar, Object obj) {
        String c = cVar.c();
        Object a = cVar.a(obj);
        if (a != null) {
            Object a2;
            if (a.getClass() == c.class) {
                a2 = f.a(cVar.a()).c().a(((c) a).a());
            } else {
                a2 = f.a(a.getClass()).c().a(a);
            }
            if (!(c == null || a2 == null)) {
                return new b(c, a2);
            }
        }
        return null;
    }
}

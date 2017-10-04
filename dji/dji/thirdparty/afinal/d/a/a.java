package dji.thirdparty.afinal.d.a;

import android.database.Cursor;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.d.b.c;
import dji.thirdparty.afinal.d.b.d;
import dji.thirdparty.afinal.d.b.e;
import dji.thirdparty.afinal.d.b.f;
import java.util.HashMap;
import java.util.Map.Entry;

public class a {
    public static <T> T a(Cursor cursor, Class<T> cls, b bVar) {
        if (cursor != null) {
            try {
                f a = f.a((Class) cls);
                int columnCount = cursor.getColumnCount();
                if (columnCount > 0) {
                    T newInstance = cls.newInstance();
                    for (int i = 0; i < columnCount; i++) {
                        String columnName = cursor.getColumnName(i);
                        e eVar = (e) a.a.get(columnName);
                        if (eVar != null) {
                            eVar.a(newInstance, cursor, i);
                        } else if (a.c().c().equals(columnName)) {
                            a.c().a(newInstance, cursor, i);
                        }
                    }
                    for (d dVar : a.b.values()) {
                        if (dVar.e() == d.class) {
                            dVar.a(newInstance, new d(newInstance, cls, dVar.a(), bVar));
                        }
                    }
                    for (c cVar : a.c.values()) {
                        if (cVar.e() == c.class) {
                            c cVar2 = new c(newInstance, cls, cVar.a(), bVar);
                            cVar2.b(Integer.valueOf(cursor.getInt(cursor.getColumnIndex(cVar.c()))));
                            cVar.a(newInstance, cVar2);
                        }
                    }
                    return newInstance;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static b a(Cursor cursor) {
        if (cursor == null || cursor.getColumnCount() <= 0) {
            return null;
        }
        b bVar = new b();
        int columnCount = cursor.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            bVar.a(cursor.getColumnName(i), cursor.getString(i));
        }
        return bVar;
    }

    public static <T> T a(b bVar, Class<?> cls) {
        if (bVar != null) {
            HashMap a = bVar.a();
            try {
                T newInstance = cls.newInstance();
                for (Entry entry : a.entrySet()) {
                    String str = (String) entry.getKey();
                    f a2 = f.a((Class) cls);
                    e eVar = (e) a2.a.get(str);
                    if (eVar != null) {
                        eVar.a(newInstance, entry.getValue() == null ? null : entry.getValue().toString());
                    } else if (a2.c().c().equals(str)) {
                        a2.c().a(newInstance, entry.getValue() == null ? null : entry.getValue().toString());
                    }
                }
                return newInstance;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

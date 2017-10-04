package com.dji.a.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.dji.a.b;
import java.util.HashMap;

public class a {
    private c a;
    private b b;

    private static final class a {
        private static final a a = new a();
    }

    public static a a() {
        return a.a;
    }

    private a() {
        this.a = null;
        this.b = null;
    }

    public void b() {
        if (this.a != null) {
            synchronized (this) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("state", Integer.valueOf(0));
                this.a.a("dji_analytics_reports", contentValues, "state = 1", null);
            }
        }
    }

    public void a(Context context) {
        this.a = new c(context);
        this.b = com.dji.a.a.a();
        if (com.dji.a.a.b) {
            com.dji.a.a.c.a(com.dji.a.a.a, a.class.getSimpleName() + "init success");
        }
    }

    public boolean a(com.dji.a.a.a aVar) {
        if (this.a == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("report", aVar.c());
        contentValues.put("reportid", aVar.b());
        contentValues.put("state", Integer.valueOf(0));
        return this.a.a("dji_analytics_reports", null, contentValues);
    }

    public boolean a(HashMap<String, com.dji.a.a.a> hashMap) {
        if (hashMap.size() <= 0 || this.a == null) {
            return false;
        }
        Object[] toArray = hashMap.keySet().toArray();
        String[] strArr = new String[toArray.length];
        for (int i = 0; i < toArray.length; i++) {
            strArr[i] = (String) toArray[i];
        }
        return this.a.a("dji_analytics_reports", "reportid in (" + a(strArr) + ")", null);
    }

    public HashMap<String, com.dji.a.a.a> c() {
        HashMap<String, com.dji.a.a.a> hashMap = null;
        if (this.a != null) {
            String str = "select reportid,report from dji_analytics_reports where state = 0 limit " + this.b.f() + " offset 0";
            synchronized (this) {
                Cursor a = this.a.a(str);
                int count = a.getCount();
                if (count <= 0) {
                } else {
                    hashMap = new HashMap();
                    String[] strArr = new String[count];
                    for (int i = 0; i < count; i++) {
                        String string = a.getString(a.getColumnIndex("reportid"));
                        hashMap.put(string, new com.dji.a.a.a(a.getBlob(a.getColumnIndex("report"))));
                        strArr[i] = string;
                        if (a.isLast()) {
                            break;
                        }
                        a.moveToNext();
                    }
                    a(strArr, 1);
                    if (!a.isClosed()) {
                        a.close();
                    }
                }
            }
        }
        return hashMap;
    }

    private static String a(String[] strArr) {
        if (strArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("'");
        stringBuilder.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append("','");
            stringBuilder.append(strArr[i]);
        }
        stringBuilder.append("'");
        return stringBuilder.toString();
    }

    public boolean a(HashMap<String, com.dji.a.a.a> hashMap, int i) {
        Object[] toArray = hashMap.keySet().toArray();
        String[] strArr = new String[toArray.length];
        for (int i2 = 0; i2 < toArray.length; i2++) {
            strArr[i2] = (String) toArray[i2];
        }
        return a(strArr, i);
    }

    private boolean a(String[] strArr, int i) {
        if (this.a == null) {
            return false;
        }
        boolean a;
        synchronized (this) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", Integer.valueOf(i));
            a = this.a.a("dji_analytics_reports", contentValues, "reportid in (" + a(strArr) + ")", null);
        }
        return a;
    }
}

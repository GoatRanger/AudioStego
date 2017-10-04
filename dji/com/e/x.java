package com.e;

import android.database.sqlite.SQLiteDatabase;

public class x implements e {
    private static x a;

    private x() {
    }

    public static synchronized x c() {
        x xVar;
        synchronized (x.class) {
            if (a == null) {
                a = new x();
            }
            xVar = a;
        }
        return xVar;
    }

    public String a() {
        return "dynamicamapfile.db";
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sdkname  varchar(20), filename varchar(100),md5 varchar(20),version varchar(20),dynamicversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            dg.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public int b() {
        return 1;
    }
}

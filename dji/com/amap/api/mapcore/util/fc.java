package com.amap.api.mapcore.util;

import android.database.sqlite.SQLiteDatabase;

public class fc implements ej {
    private static fc a;

    public static synchronized fc a() {
        fc fcVar;
        synchronized (fc.class) {
            if (a == null) {
                a = new fc();
            }
            fcVar = a;
        }
        return fcVar;
    }

    private fc() {
    }

    public String b() {
        return "dynamicamapfile.db";
    }

    public int c() {
        return 1;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS file (_id integer primary key autoincrement, sdkname  varchar(20), filename varchar(100),md5 varchar(20),version varchar(20),dynamicversion varchar(20),status varchar(20),reservedfield varchar(20));");
        } catch (Throwable th) {
            eb.a(th, "DynamicFileDBCreator", "onCreate");
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}

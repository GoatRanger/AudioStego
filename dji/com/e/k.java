package com.e;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class k extends SQLiteOpenHelper {
    private e a;

    public k(Context context, String str, CursorFactory cursorFactory, int i, e eVar) {
        super(context, str, cursorFactory, i);
        this.a = eVar;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.a.a(sQLiteDatabase, i, i2);
    }
}

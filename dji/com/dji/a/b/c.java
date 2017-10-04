package com.dji.a.b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.dji.a.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Arrays;

class c extends SQLiteOpenHelper {
    private final String a = "CREATE TABLE dji_analytics_reports (_id INTEGER PRIMARY KEY AUTOINCREMENT, reportid VARCHAR(50), report BLOB, state INTEGER)";
    private final String b = "DROP TABLE IF EXISTS dji_analytics_reports";
    private final String c = "CREATE INDEX reportid ON dji_analytics_reports(reportid)";

    c(Context context) {
        super(context, "com.dji.dji_analytics_a_db", null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(this.a);
            sQLiteDatabase.execSQL(this.c);
        } catch (SQLiteException e) {
            a.c.c(a.a, "Error in create db." + e.toString());
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(this.b);
        onCreate(sQLiteDatabase);
    }

    boolean a(String str, String str2, ContentValues contentValues) {
        try {
            getWritableDatabase().insert(str, str2, contentValues);
            if (a.b) {
                a.c.a(a.a, "insert " + contentValues.toString() + " into " + str);
            }
            return true;
        } catch (SQLException e) {
            if (a.b) {
                a.c.c(a.a, "Error in insert data " + e);
            }
            return false;
        }
    }

    boolean a(String str, String str2, String[] strArr) {
        try {
            getWritableDatabase().delete(str, str2, strArr);
            if (a.b) {
                a.c.a(a.a, "delete from  " + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.toString(strArr) + " into " + str);
            }
            return true;
        } catch (SQLException e) {
            if (a.b) {
                a.c.c(a.a, "Error in delete data " + e);
            }
            return false;
        }
    }

    boolean a(String str, ContentValues contentValues, String str2, String[] strArr) {
        try {
            getWritableDatabase().update(str, contentValues, str2, strArr);
            if (a.b) {
                a.c.a(a.a, "update from  " + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.toString(strArr) + " into " + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + contentValues.toString());
            }
            return true;
        } catch (SQLException e) {
            if (a.b) {
                a.c.c(a.a, "Error in delete data " + e);
            }
            return false;
        }
    }

    Cursor a(String str) {
        try {
            Cursor rawQuery = getReadableDatabase().rawQuery(str, null);
            rawQuery.moveToFirst();
            if (!a.b) {
                return rawQuery;
            }
            a.c.a(a.a, "exce sql" + str + " data len is " + rawQuery.getCount());
            return rawQuery;
        } catch (SQLException e) {
            if (a.b) {
                a.c.c(a.a, "Error in insert data " + e);
            }
            return null;
        }
    }
}

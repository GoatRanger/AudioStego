package com.tencent.bugly.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.c;
import java.io.File;

public class r extends SQLiteOpenHelper {
    protected final int a = 5;
    protected Context b;

    public r(Context context) {
        super(context, "bugly_db_" + a.a(context).i(), null, 4);
        this.b = context;
    }

    public synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" CREATE TABLE ").append("t_cr").append(" ( ").append("_id").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("INTEGER PRIMARY KEY").append(" , ").append("_tm").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_s1").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("text").append(" , ").append("_up").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_me").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_uc").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_dt").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("blob").append(" ) ");
            z.c("create %s", stringBuilder.toString());
            sQLiteDatabase.execSQL(r1);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE ").append("t_ui").append(" ( ").append("_id").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("INTEGER PRIMARY KEY").append(" , ").append("_tm").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_ut").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_tp").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_dt").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("blob").append(" , ").append("_pc").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("text").append(" ) ");
            z.c("create %s", stringBuilder.toString());
            sQLiteDatabase.execSQL(r1);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE ").append("t_lr").append(" ( ").append("_id").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("INTEGER PRIMARY KEY").append(" , ").append("_tp").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_tm").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("int").append(" , ").append("_pc").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("text").append(" , ").append("_th").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("text").append(" , ").append("_dt").append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append("blob").append(" ) ");
            z.c("create %s", stringBuilder.toString());
            sQLiteDatabase.execSQL(r1);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE ").append("t_aclf").append(" ( ").append("_id").append(" integer PRIMARY KEY AUTOINCREMENT NOT NULL, ").append("_pn").append(" text(256) NOT NULL, ").append("_pid").append(" text(32) NOT NULL, ").append("_sspc").append(" integer, ").append("_sid").append(" text(64), ").append("_prsid").append(" text(64), ").append("_tr").append(" text(64) DEFAULT '-1', ").append("_tp").append(" text(64) DEFAULT '-1', ").append("_du").append(" text(64) DEFAULT '-1', ").append("_st").append(" INTEGER DEFAULT 0, ").append("_stda").append(" text(128), ").append("_or").append(" integer, ").append("_inst").append(" integer, ").append("_vf").append(" text ").append(")");
            z.c("create %s", stringBuilder.toString());
            sQLiteDatabase.execSQL(r1);
            stringBuilder.setLength(0);
            stringBuilder.append(" CREATE TABLE ").append("t_ss").append(" ( ").append("_id").append(" text PRIMARY KEY NOT NULL, ").append("_dt").append(" blob").append(")");
            z.c("create %s", stringBuilder.toString());
            sQLiteDatabase.execSQL(r0);
        } catch (Throwable th) {
            if (!z.b(th)) {
                th.printStackTrace();
            }
        }
    }

    protected synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        synchronized (this) {
            try {
                for (String str : new String[]{"t_cr", "t_lr", "t_ui", "t_aclf", "t_ss"}) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
                }
            } catch (Throwable th) {
                if (!z.b(th)) {
                    th.printStackTrace();
                }
                z = false;
            }
        }
        return z;
    }

    public synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        z.d("upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
        if (a(sQLiteDatabase)) {
            onCreate(sQLiteDatabase);
        } else {
            z.d("drop fail delete db", new Object[0]);
            File databasePath = this.b.getDatabasePath("bugly_db");
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (b.c() >= 11) {
            z.d("drowngrade %d to %d drop tables!}", Integer.valueOf(i), Integer.valueOf(i2));
            if (a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                z.d("drop fail delete db", new Object[0]);
                File databasePath = this.b.getDatabasePath("bugly_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    z.d("try db count %d", Integer.valueOf(i));
                    if (i == 5) {
                        z.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th = null;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable th2) {
                    th = th2;
                    z.d("try db %d", Integer.valueOf(i));
                    if (i == 5) {
                        z.e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sQLiteDatabase == null) {
                z.d("db error delay error record 1min", new Object[0]);
                final String str = th == null ? "" : th.getClass().getName() + ":" + th.getMessage();
                y.a().a(new Runnable(this) {
                    final /* synthetic */ r b;

                    public void run() {
                        c.a().a(str);
                    }
                }, 60000);
            }
        }
        return sQLiteDatabase;
    }
}

package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.UserInfoBean;
import com.tencent.bugly.crashreport.common.strategy.a;
import com.tencent.bugly.crashreport.common.strategy.c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.util.ArrayList;
import java.util.List;

public class q {
    private static q a = null;
    private static r b = null;

    private q(Context context) {
        b = new r(context);
    }

    public static synchronized q a(Context context) {
        q qVar;
        synchronized (q.class) {
            if (a == null) {
                a = new q(context);
            }
            qVar = a;
        }
        return qVar;
    }

    public void a(StrategyBean strategyBean) {
        if (strategyBean != null) {
            a aVar = new a();
            aVar.b = 2;
            aVar.a = strategyBean.b;
            aVar.c = null;
            aVar.d = null;
            aVar.e = strategyBean.c;
            aVar.f = ad.a((Parcelable) strategyBean);
            b(aVar);
        }
    }

    public StrategyBean a() {
        List a = a(2);
        if (a != null && a.size() > 0) {
            a aVar = (a) a.get(0);
            if (aVar.f != null) {
                return (StrategyBean) ad.a(aVar.f, StrategyBean.CREATOR);
            }
        }
        return null;
    }

    public void b() {
        b(2);
    }

    protected ContentValues a(CrashDetailBean crashDetailBean) {
        int i = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            int i2;
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            String str = "_up";
            if (crashDetailBean.d) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            contentValues.put(str, Integer.valueOf(i2));
            String str2 = "_me";
            if (!crashDetailBean.j) {
                i = 0;
            }
            contentValues.put(str2, Integer.valueOf(i));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", ad.a((Parcelable) crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    protected CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) ad.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public void b(CrashDetailBean crashDetailBean) {
        if (crashDetailBean != null) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    ContentValues a = a(crashDetailBean);
                    if (a != null) {
                        long replace = writableDatabase.replace("t_cr", "_id", a);
                        if (replace >= 0) {
                            z.c("insert %s success!", "t_cr");
                            crashDetailBean.a = replace;
                            return;
                        }
                        return;
                    }
                    return;
                }
                c.a().a("save crash fail db null", true);
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
                c.a().a("save crash fail error " + th.getClass().getName() + ":" + th.getMessage(), false);
            }
        }
    }

    public void a(List<CrashDetailBean> list) {
        if (list != null && list.size() != 0) {
            try {
                SQLiteDatabase writableDatabase = b.getWritableDatabase();
                if (writableDatabase != null) {
                    for (CrashDetailBean crashDetailBean : list) {
                        ContentValues a = a(crashDetailBean);
                        if (a != null) {
                            long replace = writableDatabase.replace("t_cr", "_id", a);
                            if (replace >= 0) {
                                z.c("insert %s success!", "t_cr");
                                crashDetailBean.a = replace;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public List<CrashDetailBean> b(List<com.tencent.bugly.crashreport.crash.a> list) {
        Cursor query;
        Throwable th;
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (com.tencent.bugly.crashreport.crash.a aVar : list) {
                stringBuilder.append(" or ").append("_id").append(" = ").append(aVar.a);
            }
            String stringBuilder2 = stringBuilder.toString();
            if (stringBuilder2.length() > 0) {
                stringBuilder2 = stringBuilder2.substring(" or ".length());
            }
            stringBuilder.setLength(0);
            try {
                query = writableDatabase.query("t_cr", null, stringBuilder2, null, null, null, null);
                if (query == null) {
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return null;
                }
                try {
                    List<CrashDetailBean> arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        CrashDetailBean a = a(query);
                        if (a != null) {
                            arrayList.add(a);
                        } else {
                            try {
                                stringBuilder.append(" or ").append("_id").append(" = ").append(query.getLong(query.getColumnIndex("_id")));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                    stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        int delete = writableDatabase.delete("t_cr", stringBuilder2.substring(" or ".length()), null);
                        z.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(delete));
                    }
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th22) {
                    th = th22;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
        }
        return null;
    }

    protected com.tencent.bugly.crashreport.crash.a b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            com.tencent.bugly.crashreport.crash.a aVar = new com.tencent.bugly.crashreport.crash.a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            aVar.e = z;
            aVar.f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public List<com.tencent.bugly.crashreport.crash.a> c() {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            Cursor query;
            try {
                query = writableDatabase.query("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, null, null);
                if (query == null) {
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return null;
                }
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    List<com.tencent.bugly.crashreport.crash.a> arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        com.tencent.bugly.crashreport.crash.a b = b(query);
                        if (b != null) {
                            arrayList.add(b);
                        } else {
                            try {
                                stringBuilder.append(" or ").append("_id").append(" = ").append(query.getLong(query.getColumnIndex("_id")));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        int delete = writableDatabase.delete("t_cr", stringBuilder2.substring(" or ".length()), null);
                        z.d("deleted %s illegle data %d", "t_cr", Integer.valueOf(delete));
                    }
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th22) {
                    th = th22;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
        }
        return null;
    }

    public void c(List<com.tencent.bugly.crashreport.crash.a> list) {
        if (list != null && list.size() != 0) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (com.tencent.bugly.crashreport.crash.a aVar : list) {
                    stringBuilder.append(" or ").append("_id").append(" = ").append(aVar.a);
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    stringBuilder2 = stringBuilder2.substring(" or ".length());
                }
                stringBuilder.setLength(0);
                try {
                    int delete = writableDatabase.delete("t_cr", stringBuilder2, null);
                    z.c("deleted %s data %d", "t_cr", Integer.valueOf(delete));
                } catch (Throwable th) {
                    if (!z.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public void d(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() != 0) {
                    SQLiteDatabase writableDatabase = b.getWritableDatabase();
                    if (writableDatabase != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (CrashDetailBean crashDetailBean : list) {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(crashDetailBean.a);
                        }
                        String stringBuilder2 = stringBuilder.toString();
                        if (stringBuilder2.length() > 0) {
                            stringBuilder2 = stringBuilder2.substring(" or ".length());
                        }
                        stringBuilder.setLength(0);
                        int delete = writableDatabase.delete("t_cr", stringBuilder2, null);
                        z.c("deleted %s data %d", "t_cr", Integer.valueOf(delete));
                    }
                }
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.b));
            contentValues.put("_pc", userInfoBean.c);
            contentValues.put("_dt", ad.a((Parcelable) userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public void b(UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                ContentValues a = a(userInfoBean);
                if (a != null) {
                    long replace = writableDatabase.replace("t_ui", "_id", a);
                    if (replace >= 0) {
                        z.c("insert %s success! %d", "t_ui", Long.valueOf(replace));
                        userInfoBean.a = replace;
                        return;
                    }
                    return;
                }
                return;
            }
            z.d("db error delay error record 1min", new Object[0]);
            c.a().a("save ui fail db null", true);
        }
    }

    public void e(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                for (UserInfoBean userInfoBean : list) {
                    ContentValues a = a(userInfoBean);
                    if (a != null) {
                        long replace = writableDatabase.replace("t_ui", "_id", a);
                        if (replace >= 0) {
                            z.c("insert %s success!", "t_ui");
                            userInfoBean.a = replace;
                        }
                    }
                }
                return;
            }
            c.a().a("save ui list fail db null", true);
        }
    }

    protected UserInfoBean c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) ad.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public List<UserInfoBean> a(String str) {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            Cursor query;
            try {
                query = writableDatabase.query("t_ui", null, ag.b(str) ? null : "_pc = '" + str + "'", null, null, null, null);
                if (query == null) {
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return null;
                }
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    List<UserInfoBean> arrayList = new ArrayList();
                    while (query.moveToNext()) {
                        UserInfoBean c = c(query);
                        if (c != null) {
                            arrayList.add(c);
                        } else {
                            try {
                                stringBuilder.append(" or ").append("_id").append(" = ").append(query.getLong(query.getColumnIndex("_id")));
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                    String stringBuilder2 = stringBuilder.toString();
                    if (stringBuilder2.length() > 0) {
                        int delete = writableDatabase.delete("t_ui", stringBuilder2.substring(" or ".length()), null);
                        z.d("deleted %s illegle data %d", "t_ui", Integer.valueOf(delete));
                    }
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return arrayList;
                } catch (Throwable th22) {
                    th = th22;
                }
            } catch (Throwable th3) {
                th = th3;
                query = null;
                query.close();
                throw th;
            }
        }
        return null;
    }

    public void f(List<UserInfoBean> list) {
        if (list != null && list.size() != 0) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (UserInfoBean userInfoBean : list) {
                    stringBuilder.append(" or ").append("_id").append(" = ").append(userInfoBean.a);
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    stringBuilder2 = stringBuilder2.substring(" or ".length());
                }
                stringBuilder.setLength(0);
                try {
                    int delete = writableDatabase.delete("t_ui", stringBuilder2, null);
                    z.c("deleted %s data %d", "t_ui", Integer.valueOf(delete));
                } catch (Throwable th) {
                    if (!z.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    protected ContentValues a(a aVar) {
        if (aVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (aVar.a > 0) {
                contentValues.put("_id", Long.valueOf(aVar.a));
            }
            contentValues.put("_tp", Integer.valueOf(aVar.b));
            contentValues.put("_pc", aVar.c);
            contentValues.put("_th", aVar.d);
            contentValues.put("_tm", Long.valueOf(aVar.e));
            if (aVar.f != null) {
                contentValues.put("_dt", aVar.f);
            }
            return contentValues;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public boolean b(a aVar) {
        if (aVar == null) {
            return false;
        }
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase == null) {
            return false;
        }
        ContentValues a = a(aVar);
        if (a == null) {
            return false;
        }
        long replace = writableDatabase.replace("t_lr", "_id", a);
        if (replace < 0) {
            return false;
        }
        z.c("insert %s success!", "t_lr");
        aVar.a = replace;
        return true;
    }

    protected a d(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            a aVar = new a();
            aVar.a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.c = cursor.getString(cursor.getColumnIndex("_pc"));
            aVar.d = cursor.getString(cursor.getColumnIndex("_th"));
            aVar.e = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.f = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return aVar;
        } catch (Throwable th) {
            if (z.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public List<a> a(int i) {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            String str;
            Cursor cursor2;
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th2) {
                    th = th2;
                    cursor2 = null;
                    cursor2.close();
                    throw th;
                }
            }
            str = null;
            cursor2 = writableDatabase.query("t_lr", null, str, null, null, null, null);
            if (cursor2 == null) {
                if (!(cursor2 == null || cursor2.isClosed())) {
                    cursor2.close();
                }
                return null;
            }
            try {
                StringBuilder stringBuilder = new StringBuilder();
                List<a> arrayList = new ArrayList();
                while (cursor2.moveToNext()) {
                    a d = d(cursor2);
                    if (d != null) {
                        arrayList.add(d);
                    } else {
                        try {
                            stringBuilder.append(" or ").append("_id").append(" = ").append(cursor2.getLong(cursor2.getColumnIndex("_id")));
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                }
                str = stringBuilder.toString();
                if (str.length() > 0) {
                    int delete = writableDatabase.delete("t_lr", str.substring(" or ".length()), null);
                    z.d("deleted %s illegle data %d", "t_lr", Integer.valueOf(delete));
                }
                if (!(cursor2 == null || cursor2.isClosed())) {
                    cursor2.close();
                }
                return arrayList;
            } catch (Throwable th32) {
                th = th32;
            }
        }
        return null;
    }

    public void g(List<a> list) {
        if (list != null && list.size() != 0) {
            SQLiteDatabase writableDatabase = b.getWritableDatabase();
            if (writableDatabase != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (a aVar : list) {
                    stringBuilder.append(" or ").append("_id").append(" = ").append(aVar.a);
                }
                String stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() > 0) {
                    stringBuilder2 = stringBuilder2.substring(" or ".length());
                }
                stringBuilder.setLength(0);
                try {
                    int delete = writableDatabase.delete("t_lr", stringBuilder2, null);
                    z.c("deleted %s data %d", "t_lr", Integer.valueOf(delete));
                } catch (Throwable th) {
                    if (!z.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public void b(int i) {
        String str = null;
        SQLiteDatabase writableDatabase = b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!z.a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            int delete = writableDatabase.delete("t_lr", str, null);
            z.c("deleted %s data %d", "t_lr", Integer.valueOf(delete));
        }
    }
}

package dji.thirdparty.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import dji.thirdparty.afinal.d.a.e;
import dji.thirdparty.afinal.d.b.d;
import dji.thirdparty.afinal.d.b.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class b {
    private static final String a = "FinalDb";
    private static HashMap<String, b> b = new HashMap();
    private SQLiteDatabase c;
    private a d;
    private f e;
    private SQLiteStatement f;
    private SQLiteStatement g;
    private String h;

    public interface b {
        void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

        void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);
    }

    public static class a {
        private Context a = null;
        private String b = "dji.db";
        private int c = 1;
        private boolean d = true;
        private b e;
        private String f;

        public Context a() {
            return this.a;
        }

        public void a(Context context) {
            this.a = context;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public int c() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public boolean d() {
            return this.d;
        }

        public void a(boolean z) {
            this.d = z;
        }

        public b e() {
            return this.e;
        }

        public void a(b bVar) {
            this.e = bVar;
        }

        public String f() {
            return this.f;
        }

        public void b(String str) {
            this.f = str;
        }
    }

    class c extends SQLiteOpenHelper {
        final /* synthetic */ b a;
        private b b;

        public c(b bVar, Context context, String str, int i, b bVar2) {
            this.a = bVar;
            super(context, str, null, i);
            this.b = bVar2;
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (this.b != null) {
                this.b.onUpgrade(sQLiteDatabase, i, i2);
                return;
            }
            this.a.c = sQLiteDatabase;
            this.a.c();
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (this.b != null) {
                this.b.onDowngrade(sQLiteDatabase, i, i2);
                return;
            }
            this.a.c = sQLiteDatabase;
            this.a.c();
        }
    }

    private <T> java.util.List<T> d(java.lang.Class<T> r5, java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r1 = 0;
        r4.d(r5);
        r4.c(r6);
        r0 = r4.c;
        r2 = r0.rawQuery(r6, r1);
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
        r0.<init>();	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
    L_0x0012:
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
        if (r3 == 0) goto L_0x002b;	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
    L_0x0018:
        r3 = dji.thirdparty.afinal.d.a.a.a(r2, r5, r4);	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
        r0.add(r3);	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
        goto L_0x0012;
    L_0x0020:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0020, all -> 0x0031 }
        if (r2 == 0) goto L_0x0029;
    L_0x0026:
        r2.close();
    L_0x0029:
        r0 = r1;
    L_0x002a:
        return r0;
    L_0x002b:
        if (r2 == 0) goto L_0x002a;
    L_0x002d:
        r2.close();
        goto L_0x002a;
    L_0x0031:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0037;
    L_0x0034:
        r2.close();
    L_0x0037:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.thirdparty.afinal.b.d(java.lang.Class, java.lang.String):java.util.List<T>");
    }

    private b(a aVar) {
        if (aVar == null) {
            throw new dji.thirdparty.afinal.e.b("daoConfig is null");
        } else if (aVar.a() == null) {
            throw new dji.thirdparty.afinal.e.b("android context is null");
        } else {
            if (aVar.f() == null || aVar.f().trim().length() <= 0) {
                this.c = new c(this, aVar.a().getApplicationContext(), aVar.b(), aVar.c(), aVar.e()).getWritableDatabase();
            } else {
                this.c = a(aVar.f(), aVar.b(), aVar.c(), aVar.e());
            }
            this.d = aVar;
        }
    }

    private static synchronized b b(a aVar) {
        b bVar;
        synchronized (b.class) {
            bVar = (b) b.get(aVar.b());
            if (bVar == null) {
                bVar = new b(aVar);
                b.put(aVar.b(), bVar);
            }
        }
        return bVar;
    }

    public static b a(Context context) {
        a aVar = new a();
        aVar.a(context);
        return a(aVar);
    }

    public static b a(Context context, boolean z) {
        a aVar = new a();
        aVar.a(context);
        aVar.a(z);
        return a(aVar);
    }

    public static b a(Context context, String str) {
        a aVar = new a();
        aVar.a(context);
        aVar.a(str);
        return a(aVar);
    }

    public static b a(Context context, String str, boolean z) {
        a aVar = new a();
        aVar.a(context);
        aVar.a(str);
        aVar.a(z);
        return a(aVar);
    }

    public static b a(Context context, String str, String str2) {
        a aVar = new a();
        aVar.a(context);
        aVar.a(str2);
        aVar.b(str);
        return a(aVar);
    }

    public static b a(Context context, String str, String str2, boolean z) {
        a aVar = new a();
        aVar.a(context);
        aVar.b(str);
        aVar.a(str2);
        aVar.a(z);
        return a(aVar);
    }

    public static b a(Context context, String str, boolean z, int i, b bVar) {
        a aVar = new a();
        aVar.a(context);
        aVar.a(str);
        aVar.a(z);
        aVar.a(i);
        aVar.a(bVar);
        return a(aVar);
    }

    public static b a(Context context, String str, String str2, boolean z, int i, b bVar) {
        a aVar = new a();
        aVar.a(context);
        aVar.b(str);
        aVar.a(str2);
        aVar.a(z);
        aVar.a(i);
        aVar.a(bVar);
        return a(aVar);
    }

    public static b a(a aVar) {
        return b(aVar);
    }

    public void a(Object obj) {
        d(obj.getClass());
        a(e.a(obj));
    }

    public SQLiteStatement b(Object obj) {
        this.e = d(obj.getClass());
        this.c.beginTransaction();
        this.f = this.c.compileStatement(e.b(obj));
        return this.f;
    }

    public SQLiteStatement a(Object obj, String str) {
        this.e = d(obj.getClass());
        this.c.beginTransaction();
        this.g = this.c.compileStatement(e.a(obj, str));
        this.h = str;
        return this.g;
    }

    public void c(Object obj) {
        if (this.f != null) {
            this.f.acquireReference();
            this.f.clearBindings();
            this.f.bindAllArgsAsStrings(e.a(this.e, obj));
            this.f.executeUpdateDelete();
            this.f.releaseReference();
        }
    }

    public void b(Object obj, String str) {
        if (this.g != null) {
            this.g.acquireReference();
            this.g.clearBindings();
            this.g.bindAllArgsAsStrings(e.a(this.e, obj, this.h, str));
            this.g.executeUpdateDelete();
            this.g.releaseReference();
        }
    }

    public void a() {
        if (this.f != null) {
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
            this.e = null;
            this.f = null;
        }
    }

    public void b() {
        if (this.g != null) {
            this.c.setTransactionSuccessful();
            this.c.endTransaction();
            this.e = null;
            this.g = null;
        }
    }

    public boolean d(Object obj) {
        d(obj.getClass());
        List c = e.c(obj);
        if (c == null || c.size() <= 0) {
            return false;
        }
        f a = f.a(obj.getClass());
        ContentValues contentValues = new ContentValues();
        a(c, contentValues);
        Long valueOf = Long.valueOf(this.c.insert(a.b(), null, contentValues));
        if (valueOf.longValue() == -1) {
            return false;
        }
        a.c().a(obj, valueOf);
        return true;
    }

    private void a(List<dji.thirdparty.afinal.d.b.b> list, ContentValues contentValues) {
        if (list == null || contentValues == null) {
            Log.w(a, "insertContentValues: List<KeyValue> is empty or ContentValues is empty!");
            return;
        }
        for (dji.thirdparty.afinal.d.b.b bVar : list) {
            contentValues.put(bVar.a(), bVar.b().toString());
        }
    }

    public void e(Object obj) {
        d(obj.getClass());
        a(e.f(obj));
    }

    public void c(Object obj, String str) {
        d(obj.getClass());
        a(e.b(obj, str));
    }

    public void f(Object obj) {
        d(obj.getClass());
        a(e.e(obj));
    }

    public void a(Class<?> cls, Object obj) {
        d((Class) cls);
        a(e.a((Class) cls, obj));
    }

    public void a(Class<?> cls, String str) {
        d((Class) cls);
        String a = e.a((Class) cls, str);
        c(a);
        this.c.execSQL(a);
    }

    public void a(Class<?> cls) {
        d((Class) cls);
        String a = e.a((Class) cls, null);
        c(a);
        this.c.execSQL(a);
    }

    public void b(Class<?> cls) {
        d((Class) cls);
        String str = "DROP TABLE " + f.a((Class) cls).b();
        c(str);
        this.c.execSQL(str);
    }

    public void c() {
        Cursor rawQuery = this.c.rawQuery("SELECT name FROM sqlite_master WHERE type ='table' AND name != 'sqlite_sequence'", null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                this.c.execSQL("DROP TABLE " + rawQuery.getString(0));
            }
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
    }

    private void a(dji.thirdparty.afinal.d.a.f fVar) {
        if (fVar != null) {
            c(fVar.a());
            this.c.execSQL(fVar.a(), fVar.c());
            return;
        }
        Log.e(a, "sava error:sqlInfo is null");
    }

    public <T> T a(Object obj, Class<T> cls) {
        T a;
        d((Class) cls);
        dji.thirdparty.afinal.d.a.f c = e.c(cls, obj);
        if (c != null) {
            c(c.a());
            Cursor rawQuery = this.c.rawQuery(c.a(), c.d());
            try {
                if (rawQuery.moveToNext()) {
                    a = dji.thirdparty.afinal.d.a.a.a(rawQuery, cls, this);
                    return a;
                }
                rawQuery.close();
            } catch (Exception e) {
                a = e;
                a.printStackTrace();
            } finally {
                rawQuery.close();
            }
        }
        return null;
    }

    public <T> T b(Object obj, Class<T> cls) {
        d((Class) cls);
        String b = e.b((Class) cls, obj);
        c(b);
        dji.thirdparty.afinal.d.a.b a = a(b);
        if (a != null) {
            return a(a, dji.thirdparty.afinal.d.a.a.a(a, cls), (Class) cls, new Class[0]);
        }
        return null;
    }

    public <T> T a(Object obj, Class<T> cls, Class<?>... clsArr) {
        d((Class) cls);
        String b = e.b((Class) cls, obj);
        c(b);
        dji.thirdparty.afinal.d.a.b a = a(b);
        if (a != null) {
            return a(a, dji.thirdparty.afinal.d.a.a.a(a, cls), (Class) cls, (Class[]) clsArr);
        }
        return null;
    }

    public <T> T a(dji.thirdparty.afinal.d.a.b bVar, T t, Class<T> cls, Class<?>... clsArr) {
        if (t != null) {
            try {
                for (dji.thirdparty.afinal.d.b.c cVar : f.a((Class) cls).c.values()) {
                    Object a;
                    if (bVar != null) {
                        a = bVar.a(cVar.c());
                    } else if (cVar.a((Object) t).getClass() != dji.thirdparty.afinal.d.a.c.class || cVar.a((Object) t) == null) {
                        a = null;
                    } else {
                        a = ((dji.thirdparty.afinal.d.a.c) cVar.a((Object) t)).b();
                    }
                    if (a != null) {
                        Object obj;
                        if (clsArr == null || clsArr.length == 0) {
                            obj = 1;
                        } else {
                            obj = null;
                        }
                        for (Class<?> cls2 : clsArr) {
                            if (cVar.a() == cls2) {
                                obj = 1;
                                break;
                            }
                        }
                        if (obj != null) {
                            obj = a(Integer.valueOf(a.toString()), cVar.a());
                            if (obj != null) {
                                if (cVar.a((Object) t).getClass() == dji.thirdparty.afinal.d.a.c.class) {
                                    if (cVar.a((Object) t) == null) {
                                        cVar.a(t, new dji.thirdparty.afinal.d.a.c(t, cls, cVar.a(), this));
                                    }
                                    ((dji.thirdparty.afinal.d.a.c) cVar.a((Object) t)).a(obj);
                                } else {
                                    cVar.a(t, obj);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public <T> T c(Object obj, Class<T> cls) {
        d((Class) cls);
        String b = e.b((Class) cls, obj);
        c(b);
        dji.thirdparty.afinal.d.a.b a = a(b);
        if (a != null) {
            return c(dji.thirdparty.afinal.d.a.a.a(a, cls), cls, new Class[0]);
        }
        return null;
    }

    public <T> T b(Object obj, Class<T> cls, Class<?>... clsArr) {
        d((Class) cls);
        String b = e.b((Class) cls, obj);
        c(b);
        dji.thirdparty.afinal.d.a.b a = a(b);
        if (a != null) {
            return c(dji.thirdparty.afinal.d.a.a.a(a, cls), cls, clsArr);
        }
        return null;
    }

    public <T> T c(T t, Class<T> cls, Class<?>... clsArr) {
        if (t != null) {
            try {
                Collection<d> values = f.a((Class) cls).b.values();
                Object a = f.a((Class) cls).c().a((Object) t);
                for (d dVar : values) {
                    Object obj;
                    if (clsArr == null || clsArr.length == 0) {
                        obj = 1;
                    } else {
                        obj = null;
                    }
                    for (Class<?> cls2 : clsArr) {
                        if (dVar.a() == cls2) {
                            obj = 1;
                            break;
                        }
                    }
                    if (obj != null) {
                        List c = c(dVar.a(), dVar.c() + "=" + a);
                        if (c == null) {
                            continue;
                        } else if (dVar.e() == dji.thirdparty.afinal.d.a.d.class) {
                            ((dji.thirdparty.afinal.d.a.d) dVar.a((Object) t)).a(c);
                        } else {
                            dVar.a(t, c);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public <T> List<T> c(Class<T> cls) {
        d((Class) cls);
        return d(cls, e.a((Class) cls));
    }

    public <T> List<T> b(Class<T> cls, String str) {
        d((Class) cls);
        return d(cls, e.a((Class) cls) + " ORDER BY " + str);
    }

    public <T> List<T> a(Class<T> cls, String str, String str2) {
        d((Class) cls);
        return d(cls, e.a((Class) cls) + " ORDER BY " + str + " LIMIT " + str2);
    }

    public <T> List<T> c(Class<T> cls, String str) {
        d((Class) cls);
        return d(cls, e.b((Class) cls, str));
    }

    public <T> List<T> b(Class<T> cls, String str, String str2) {
        d((Class) cls);
        return d(cls, e.b((Class) cls, str) + " ORDER BY " + str2);
    }

    public dji.thirdparty.afinal.d.a.b a(String str) {
        dji.thirdparty.afinal.d.a.b bVar = null;
        c(str);
        Cursor rawQuery = this.c.rawQuery(str, null);
        try {
            if (rawQuery.moveToNext()) {
                bVar = dji.thirdparty.afinal.d.a.a.a(rawQuery);
            } else {
                rawQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rawQuery.close();
        }
        return bVar;
    }

    public List<dji.thirdparty.afinal.d.a.b> b(String str) {
        c(str);
        Cursor rawQuery = this.c.rawQuery(str, null);
        List<dji.thirdparty.afinal.d.a.b> arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            try {
                arrayList.add(dji.thirdparty.afinal.d.a.a.a(rawQuery));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rawQuery.close();
            }
        }
        return arrayList;
    }

    private f d(Class<?> cls) {
        f a = f.a((Class) cls);
        if (!a(a)) {
            String b = e.b((Class) cls);
            c(b);
            Log.d(a, "db  isReadOnly=" + this.c.isReadOnly());
            this.c.execSQL(b);
        }
        return a;
    }

    private boolean a(f fVar) {
        Cursor cursor = null;
        if (fVar.d()) {
            return true;
        }
        try {
            String str = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + fVar.b() + "' ";
            c(str);
            cursor = this.c.rawQuery(str, null);
            if (cursor == null || !cursor.moveToNext() || cursor.getInt(0) <= 0) {
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            }
            fVar.a(true);
            if (cursor == null) {
                return true;
            }
            cursor.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void c(String str) {
        if (this.d != null && this.d.d()) {
            Log.d("Debug SQL", ">>>>>>  " + str);
        }
    }

    private SQLiteDatabase a(String str, String str2, int i, b bVar) {
        SQLiteDatabase sQLiteDatabase = null;
        File file = new File(str, str2);
        if (file.exists()) {
            sQLiteDatabase = SQLiteDatabase.openDatabase(file.getPath(), null, 16, null);
        } else {
            try {
                if (file.createNewFile()) {
                    sQLiteDatabase = SQLiteDatabase.openDatabase(file.getPath(), null, 16, null);
                }
            } catch (Throwable e) {
                throw new dji.thirdparty.afinal.e.b("数据库文件创建失败", e);
            }
        }
        Log.d(a, "db null");
        if (sQLiteDatabase != null) {
            int version = sQLiteDatabase.getVersion();
            Log.d(a, "db cur version=" + version + " isReadOnly=" + sQLiteDatabase.isReadOnly());
            if (version != i) {
                if (sQLiteDatabase.isReadOnly()) {
                    throw new SQLiteException("Can't upgrade read-only database from version " + sQLiteDatabase.getVersion() + " to " + i + ": " + str2);
                }
                sQLiteDatabase.beginTransaction();
                if (i != 0) {
                    if (bVar != null) {
                        Log.d(a, "db new version=" + i);
                        if (version > i) {
                            bVar.onDowngrade(sQLiteDatabase, version, i);
                        } else {
                            bVar.onUpgrade(sQLiteDatabase, version, i);
                        }
                    } else {
                        c();
                    }
                }
                try {
                    sQLiteDatabase.setVersion(i);
                    sQLiteDatabase.setTransactionSuccessful();
                } finally {
                    sQLiteDatabase.endTransaction();
                }
            }
        }
        return sQLiteDatabase;
    }
}

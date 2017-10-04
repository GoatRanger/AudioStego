package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.mapcore.util.fa.a;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;

class fi extends fg {
    public void a(String str, String str2) throws Exception {
        try {
            b();
            this.c = DexFile.loadDex(str, str2, 0);
        } catch (Throwable e) {
            eb.a(e, "DynamicClassLoader", "loadDexFile()");
            throw new Exception("load dex fail");
        }
    }

    private boolean a(ek ekVar, dv dvVar, String str) {
        return fa.a(ekVar, fa.a(this.a, dvVar.a(), dvVar.b()), str, dvVar);
    }

    private boolean a(ek ekVar, String str, String str2) {
        String a = fa.a(this.a, str);
        if (fa.a(ekVar, str, a, this.e)) {
            return true;
        }
        if (a.a(ekVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            a.a(ekVar, new fd.a(str, ds.a(a), this.e.a(), this.e.b(), str2).a("useodex").a(), fd.b(str));
        }
        return true;
    }

    public fi(final Context context, dv dvVar, boolean z) throws Exception {
        super(context, dvVar, z);
        final String b = fa.b(context, dvVar.a(), dvVar.b());
        final String a = fa.a(context);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        File file = new File(b);
        File parentFile = file.getParentFile();
        if (!file.exists()) {
            if (parentFile != null && parentFile.exists()) {
                fa.c(context, dvVar.a(), dvVar.b());
            }
            throw new Exception("file not exist!");
        } else if (z) {
            a(b, a + File.separator + fa.a(file.getName()));
            new Thread(this) {
                final /* synthetic */ fi d;

                public void run() {
                    try {
                        this.d.a(context, b, a);
                    } catch (Throwable th) {
                        eb.a(th, "DynamicClassLoader", "run()");
                    }
                }
            }.start();
        }
    }

    protected Class<?> findClass(String str) throws ClassNotFoundException {
        try {
            if (this.c == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls = (Class) this.b.get(str);
            if (cls == null) {
                cls = this.c.loadClass(str, this);
                this.b.put(str, cls);
                if (cls == null) {
                    throw new ClassNotFoundException(str);
                }
            }
            return cls;
        } catch (Throwable th) {
            eb.a(th, "DynamicClassLoader", "findClass()");
            ClassNotFoundException classNotFoundException = new ClassNotFoundException(str);
        }
    }

    void a(Context context, String str, String str2) throws Exception {
        new Date().getTime();
        try {
            ek ekVar = new ek(context, fc.a());
            File file = new File(str);
            fd a = a.a(ekVar, file.getName());
            if (a != null) {
                this.f = a.d();
            }
            if (!a(ekVar, this.e, file.getAbsolutePath())) {
                this.d = false;
                fa.a(this.a, ekVar, file.getName());
                Object a2 = fa.a(this.a, ekVar, this.e);
                if (!TextUtils.isEmpty(a2)) {
                    this.f = a2;
                    a(this.a, this.e);
                }
            }
            if (file.exists()) {
                String str3 = str2 + File.separator + fa.a(file.getName());
                File file2 = new File(str3);
                if (file2.exists() && !a(ekVar, fa.a(file.getName()), this.f)) {
                    a(str, str2 + File.separator + fa.a(file.getName()));
                    a(file2, str3, this.f, ekVar);
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            eb.a(th, "DynamicClassLoader", "verifyDynamicSDK()");
        }
    }

    protected void a(File file, String str, String str2, ek ekVar) {
        if (!TextUtils.isEmpty(this.f) || !file.exists()) {
            String a = ds.a(str);
            String name = file.getName();
            a.a(ekVar, new fd.a(name, a, this.e.a(), this.e.b(), str2).a("useodex").a(), fd.b(name));
        }
    }
}

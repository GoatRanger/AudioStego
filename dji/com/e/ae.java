package com.e;

import android.content.Context;
import android.text.TextUtils;
import com.e.v.a;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;

class ae extends ac {
    public ae(final Context context, dc dcVar, boolean z) throws Exception {
        super(context, dcVar, z);
        final String b = v.b(context, dcVar.a(), dcVar.b());
        final String a = v.a(context);
        if (TextUtils.isEmpty(b) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        File file = new File(b);
        File parentFile = file.getParentFile();
        if (!file.exists()) {
            if (parentFile != null && parentFile.exists()) {
                v.c(context, dcVar.a(), dcVar.b());
            }
            throw new Exception("file not exist!");
        } else if (z) {
            a(b, a + File.separator + v.a(file.getName()));
            new Thread(this) {
                final /* synthetic */ ae d;

                public void run() {
                    try {
                        this.d.a(context, b, a);
                    } catch (Throwable th) {
                        dg.a(th, "DynamicClassLoader", "run()");
                    }
                }
            }.start();
        }
    }

    private boolean a(f fVar, dc dcVar, String str) {
        return v.a(fVar, v.a(this.a, dcVar.a(), dcVar.b()), str, dcVar);
    }

    private boolean a(f fVar, String str, String str2) {
        String a = v.a(this.a, str);
        if (v.a(fVar, str, a, this.e)) {
            return true;
        }
        if (a.a(fVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            a.a(fVar, new y.a(str, cz.a(a), this.e.a(), this.e.b(), str2).a("useodex").a(), y.b(str));
        }
        return true;
    }

    void a(Context context, String str, String str2) throws Exception {
        new Date().getTime();
        try {
            f fVar = new f(context, x.c());
            File file = new File(str);
            y a = a.a(fVar, file.getName());
            if (a != null) {
                this.f = a.d();
            }
            if (!a(fVar, this.e, file.getAbsolutePath())) {
                this.d = false;
                v.a(this.a, fVar, file.getName());
                Object a2 = v.a(this.a, fVar, this.e);
                if (!TextUtils.isEmpty(a2)) {
                    this.f = a2;
                    a(this.a, this.e);
                }
            }
            if (file.exists()) {
                String str3 = str2 + File.separator + v.a(file.getName());
                File file2 = new File(str3);
                if (file2.exists() && !a(fVar, v.a(file.getName()), this.f)) {
                    a(str, str2 + File.separator + v.a(file.getName()));
                    a(file2, str3, this.f, fVar);
                }
                new Date().getTime();
            }
        } catch (Throwable th) {
            dg.a(th, "DynamicClassLoader", "verifyDynamicSDK()");
        }
    }

    protected void a(File file, String str, String str2, f fVar) {
        if (!TextUtils.isEmpty(this.f) || !file.exists()) {
            String a = cz.a(str);
            String name = file.getName();
            a.a(fVar, new y.a(name, a, this.e.a(), this.e.b(), str2).a("useodex").a(), y.b(name));
        }
    }

    public void a(String str, String str2) throws Exception {
        try {
            b();
            this.c = DexFile.loadDex(str, str2, 0);
        } catch (Throwable e) {
            dg.a(e, "DynamicClassLoader", "loadDexFile()");
            throw new Exception("load dex fail");
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
            dg.a(th, "DynamicClassLoader", "findClass()");
            ClassNotFoundException classNotFoundException = new ClassNotFoundException(str);
        }
    }
}

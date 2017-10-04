package com.e;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

abstract class ac extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected dc e;
    protected String f;

    public ac(Context context, dc dcVar, boolean z) {
        super(context.getClassLoader());
        this.a = context;
        this.e = dcVar;
    }

    protected void a(Context context, dc dcVar) {
        String b = v.b(context, dcVar.a(), dcVar.b());
        Object a = v.a(context);
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(a)) {
            w.a(context, dcVar);
            try {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String str = a + File.separator + v.a(file.getName());
                    DexFile loadDex = DexFile.loadDex(b, str, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        a(new File(str), str, this.f, new f(context, x.c()));
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    v.c(context, dcVar.a(), dcVar.b());
                }
            } catch (Throwable th) {
                dg.a(th, "DynamicClassLoader", "getInstanceByThread()");
            }
        }
    }

    abstract void a(File file, String str, String str2, f fVar);

    public boolean a() {
        return this.c != null;
    }

    protected void b() {
        try {
            this.b.clear();
            if (this.c != null) {
                this.c.close();
            }
        } catch (Throwable th) {
            dg.a(th, "DynamicClassLoader", "preReleaseDexFile()");
        }
    }
}

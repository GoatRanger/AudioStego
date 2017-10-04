package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

abstract class fg extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b = new HashMap();
    protected DexFile c = null;
    volatile boolean d = true;
    protected dv e;
    protected String f;

    abstract void a(File file, String str, String str2, ek ekVar);

    public fg(Context context, dv dvVar, boolean z) {
        super(context.getClassLoader());
        this.a = context;
        this.e = dvVar;
    }

    public boolean a() {
        return this.c != null;
    }

    protected void a(Context context, dv dvVar) {
        String b = fa.b(context, dvVar.a(), dvVar.b());
        Object a = fa.a(context);
        if (!TextUtils.isEmpty(b) && !TextUtils.isEmpty(a)) {
            fb.a(context, dvVar);
            try {
                File file = new File(b);
                File parentFile = file.getParentFile();
                if (file.exists()) {
                    String str = a + File.separator + fa.a(file.getName());
                    DexFile loadDex = DexFile.loadDex(b, str, 0);
                    if (loadDex != null) {
                        loadDex.close();
                        a(new File(str), str, this.f, new ek(context, fc.a()));
                    }
                } else if (parentFile != null && parentFile.exists()) {
                    fa.c(context, dvVar.a(), dvVar.b());
                }
            } catch (Throwable th) {
                eb.a(th, "DynamicClassLoader", "getInstanceByThread()");
            }
        }
    }

    protected void b() {
        try {
            this.b.clear();
            if (this.c != null) {
                this.c.close();
            }
        } catch (Throwable th) {
            eb.a(th, "DynamicClassLoader", "preReleaseDexFile()");
        }
    }
}

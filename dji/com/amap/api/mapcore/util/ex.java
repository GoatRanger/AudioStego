package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class ex {
    private static final ex a = new ex();
    private final Map<String, fg> b = new HashMap();

    private ex() {
    }

    public static ex a() {
        return a;
    }

    public synchronized fg a(Context context, dv dvVar) throws Exception {
        fg fgVar;
        if (a(dvVar)) {
            String a = dvVar.a();
            fgVar = (fg) this.b.get(a);
            if (fgVar == null) {
                try {
                    fg fiVar = new fi(context.getApplicationContext(), dvVar, true);
                    try {
                        this.b.put(a, fiVar);
                        fb.a(context, dvVar);
                        fgVar = fiVar;
                    } catch (Throwable th) {
                        fgVar = fiVar;
                    }
                } catch (Throwable th2) {
                }
            }
        } else {
            throw new Exception("sdkInfo referance is null");
        }
        return fgVar;
    }

    public fg b(Context context, dv dvVar) throws Exception {
        fg fgVar = (fg) this.b.get(dvVar.a());
        if (fgVar != null) {
            fgVar.a(context, dvVar);
            return fgVar;
        }
        fgVar = new fi(context.getApplicationContext(), dvVar, false);
        fgVar.a(context, dvVar);
        this.b.put(dvVar.a(), fgVar);
        fb.a(context, dvVar);
        return fgVar;
    }

    private boolean a(dv dvVar) {
        if (dvVar == null || TextUtils.isEmpty(dvVar.b()) || TextUtils.isEmpty(dvVar.a())) {
            return false;
        }
        return true;
    }
}

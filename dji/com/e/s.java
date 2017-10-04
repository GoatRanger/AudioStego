package com.e;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class s {
    private static final s a = new s();
    private final Map<String, ac> b = new HashMap();

    private s() {
    }

    public static s a() {
        return a;
    }

    private boolean a(dc dcVar) {
        return (dcVar == null || TextUtils.isEmpty(dcVar.b()) || TextUtils.isEmpty(dcVar.a())) ? false : true;
    }

    public synchronized ac a(Context context, dc dcVar) throws Exception {
        ac acVar;
        if (a(dcVar)) {
            String a = dcVar.a();
            acVar = (ac) this.b.get(a);
            if (acVar == null) {
                try {
                    ac aeVar = new ae(context.getApplicationContext(), dcVar, true);
                    try {
                        this.b.put(a, aeVar);
                        w.a(context, dcVar);
                        acVar = aeVar;
                    } catch (Throwable th) {
                        acVar = aeVar;
                    }
                } catch (Throwable th2) {
                }
            }
        } else {
            throw new Exception("sdkInfo referance is null");
        }
        return acVar;
    }

    public ac b(Context context, dc dcVar) throws Exception {
        ac acVar = (ac) this.b.get(dcVar.a());
        if (acVar != null) {
            acVar.a(context, dcVar);
            return acVar;
        }
        acVar = new ae(context.getApplicationContext(), dcVar, false);
        acVar.a(context, dcVar);
        this.b.put(dcVar.a(), acVar);
        w.a(context, dcVar);
        return acVar;
    }
}

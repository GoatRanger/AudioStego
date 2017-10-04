package com.alipay.sdk.c;

import android.content.Context;
import com.alipay.sdk.f.d;
import com.alipay.sdk.j.j;
import com.alipay.sdk.j.k;
import org.json.JSONObject;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public final void run() {
        try {
            d bVar = new com.alipay.sdk.f.a.b();
            Context context = this.a;
            com.alipay.sdk.f.b a = bVar.a(context, "", k.a(context), true);
            if (a != null) {
                this.b.a(a.b);
                a aVar = this.b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(a.f, aVar.a());
                    jSONObject.put(a.h, aVar.i);
                    j.a(com.alipay.sdk.h.b.a().a, a.e, jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th) {
        }
    }
}

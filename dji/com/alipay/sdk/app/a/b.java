package com.alipay.sdk.app.a;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.f.a.c;
import com.alipay.sdk.j.j;
import java.io.IOException;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void run() {
        c cVar = new c();
        try {
            String b = j.b(this.a, a.a, null);
            if (!(TextUtils.isEmpty(b) || cVar.a(this.a, b) == null)) {
                Context context = this.a;
                try {
                    PreferenceManager.getDefaultSharedPreferences(context).edit().remove(a.a).commit();
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
        try {
            if (!TextUtils.isEmpty(this.b)) {
                cVar.a(this.a, this.b);
            }
        } catch (IOException e) {
            j.a(this.a, a.a, this.b);
        } catch (Throwable th3) {
        }
    }
}

package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.c.c;
import com.alipay.sdk.h.b;
import com.alipay.sdk.j.f;
import com.alipay.sdk.j.l;
import com.alipay.sdk.k.a;
import java.io.IOException;
import java.util.List;

public class AuthTask {
    static final Object a = f.class;
    private static final int b = 73;
    private Activity c;
    private a d;

    public AuthTask(Activity activity) {
        this.c = activity;
        b a = b.a();
        Context context = this.c;
        c.a();
        a.a(context);
        com.alipay.sdk.app.a.a.a(activity);
        this.d = new a(activity, a.c);
    }

    private f.a a() {
        return new a(this);
    }

    private void b() {
        if (this.d != null) {
            this.d.a();
        }
    }

    private void c() {
        if (this.d != null) {
            this.d.b();
        }
    }

    public synchronized String auth(String str, boolean z) {
        String a;
        if (z) {
            b();
        }
        b a2 = b.a();
        Context context = this.c;
        c.a();
        a2.a(context);
        String a3 = h.a();
        try {
            Context context2 = this.c;
            String a4 = new com.alipay.sdk.h.a(this.c).a(str);
            if (a(context2)) {
                a = new f(context2, new a(this)).a(a4);
                if (!TextUtils.equals(a, f.b)) {
                    if (TextUtils.isEmpty(a)) {
                        a = h.a();
                    }
                    com.alipay.sdk.c.a.b().a(this.c);
                    c();
                    com.alipay.sdk.app.a.a.a(this.c, str);
                }
            }
            a = b(context2, a4);
            com.alipay.sdk.c.a.b().a(this.c);
            c();
            com.alipay.sdk.app.a.a.a(this.c, str);
        } catch (Exception e) {
            com.alipay.sdk.c.a.b().a(this.c);
            c();
            com.alipay.sdk.app.a.a.a(this.c, str);
            a = a3;
        } catch (Throwable th) {
            com.alipay.sdk.c.a.b().a(this.c);
            c();
            com.alipay.sdk.app.a.a.a(this.c, str);
        }
        return a;
    }

    private String a(Activity activity, String str) {
        String a = new com.alipay.sdk.h.a(this.c).a(str);
        if (!a((Context) activity)) {
            return b(activity, a);
        }
        String a2 = new f(activity, new a(this)).a(a);
        if (TextUtils.equals(a2, f.b)) {
            return b(activity, a);
        }
        if (TextUtils.isEmpty(a2)) {
            return h.a();
        }
        return a2;
    }

    private String b(Activity activity, String str) {
        String a;
        i iVar;
        b();
        i iVar2 = null;
        try {
            List a2 = com.alipay.sdk.g.b.a(new com.alipay.sdk.f.a.a().a((Context) activity, str).a().optJSONObject(com.alipay.sdk.b.c.c).optJSONObject(com.alipay.sdk.b.c.d));
            c();
            for (int i = 0; i < a2.size(); i++) {
                if (((com.alipay.sdk.g.b) a2.get(i)).a == com.alipay.sdk.g.a.WapPay) {
                    a = a((com.alipay.sdk.g.b) a2.get(i));
                    return a;
                }
            }
            c();
            iVar = null;
        } catch (IOException e) {
            a = e;
            iVar2 = i.a(i.NETWORK_ERROR.h);
            com.alipay.sdk.app.a.a.a(com.alipay.sdk.app.a.c.a, (Throwable) a);
            iVar = iVar2;
            if (iVar == null) {
                iVar = i.a(i.FAILED.h);
            }
            return h.a(iVar.h, iVar.i, "");
        } catch (Throwable th) {
            a = th;
            com.alipay.sdk.app.a.a.a(com.alipay.sdk.app.a.c.b, com.alipay.sdk.app.a.c.h, (Throwable) a);
            iVar = iVar2;
            if (iVar == null) {
                iVar = i.a(i.FAILED.h);
            }
            return h.a(iVar.h, iVar.i, "");
        } finally {
            c();
        }
        if (iVar == null) {
            iVar = i.a(i.FAILED.h);
        }
        return h.a(iVar.h, iVar.i, "");
    }

    private String a(com.alipay.sdk.g.b bVar) {
        String[] strArr = bVar.b;
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        Intent intent = new Intent(this.c, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.c.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(l.b, 128);
            if (packageInfo != null && packageInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}

package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.alipay.sdk.c.c;
import com.alipay.sdk.h.b;
import com.alipay.sdk.k.a;

public final class j {
    private static final String a = "com.eg.android.AlipayGphone";
    private static final int b = 65;
    private static a c = null;
    private static String d = null;

    private static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
            if (packageInfo != null && packageInfo.versionCode >= 65) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void a(Activity activity, a aVar) {
        b a = b.a();
        c.a();
        a.a((Context) activity);
        if (a((Context) activity)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("alipayauth://platformapi/startapp");
            stringBuilder.append("?appId=20000122");
            stringBuilder.append("&approveType=005");
            stringBuilder.append("&scope=kuaijie");
            stringBuilder.append("&productId=");
            stringBuilder.append(aVar.b());
            stringBuilder.append("&thirdpartyId=");
            stringBuilder.append(aVar.a());
            stringBuilder.append("&redirectUri=");
            stringBuilder.append(aVar.d());
            a(activity, stringBuilder.toString());
            return;
        }
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    a aVar2 = new a(activity, a.a);
                    c = aVar2;
                    aVar2.a();
                }
            } catch (Exception e) {
                c = null;
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("app_id=");
        stringBuilder.append(aVar.a());
        stringBuilder.append("&partner=");
        stringBuilder.append(aVar.c());
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&login_goal=auth");
        stringBuilder.append("&redirect_url=");
        stringBuilder.append(aVar.d());
        stringBuilder.append("&view=wap");
        stringBuilder.append("&prod_code=");
        stringBuilder.append(aVar.b());
        new Thread(new k(activity, stringBuilder, aVar)).start();
    }

    private static void b(Activity activity, a aVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alipayauth://platformapi/startapp");
        stringBuilder.append("?appId=20000122");
        stringBuilder.append("&approveType=005");
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&productId=");
        stringBuilder.append(aVar.b());
        stringBuilder.append("&thirdpartyId=");
        stringBuilder.append(aVar.a());
        stringBuilder.append("&redirectUri=");
        stringBuilder.append(aVar.d());
        a(activity, stringBuilder.toString());
    }

    private static void c(Activity activity, a aVar) {
        if (activity != null) {
            try {
                if (!activity.isFinishing()) {
                    a aVar2 = new a(activity, a.a);
                    c = aVar2;
                    aVar2.a();
                }
            } catch (Exception e) {
                c = null;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("app_id=");
        stringBuilder.append(aVar.a());
        stringBuilder.append("&partner=");
        stringBuilder.append(aVar.c());
        stringBuilder.append("&scope=kuaijie");
        stringBuilder.append("&login_goal=auth");
        stringBuilder.append("&redirect_url=");
        stringBuilder.append(aVar.d());
        stringBuilder.append("&view=wap");
        stringBuilder.append("&prod_code=");
        stringBuilder.append(aVar.b());
        new Thread(new k(activity, stringBuilder, aVar)).start();
    }

    public static void a(Activity activity, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
        }
    }
}

package com.dji.frame.c;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.dji.frame.R;
import java.util.ArrayList;
import java.util.List;

public class b {
    public static final int a = 1;
    public static final int b = 11;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 7;
    public static final int g = 5;
    public static final int h = 6;

    public static List<PackageInfo> a(Context context, Boolean bool) {
        List<PackageInfo> arrayList = new ArrayList();
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = (PackageInfo) installedPackages.get(i);
            if ((packageInfo.applicationInfo.flags & 1) <= 0 || !bool.booleanValue()) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public static List<ResolveInfo> a(Context context) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.SEND", null);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("text/plain");
        return context.getPackageManager().queryIntentActivities(intent, 0);
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            ComponentName componentName = new ComponentName(str, str2);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void a(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(131072);
        context.startActivity(intent);
    }

    public static void a(Context context, Class<?> cls, int i) {
        context.startActivity(new Intent(context, cls));
        a(context, i);
    }

    public static void a(Context context, Class<?> cls, Bundle bundle, int i) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(131072);
        intent.putExtras(bundle);
        context.startActivity(intent);
        a(context, i);
    }

    public static void a(Context context, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(131072);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void a(Context context, int i) {
        switch (i) {
            case 1:
                ((Activity) context).overridePendingTransition(R.anim.core_push_left_in, R.anim.core_push_left_out);
                return;
            case 2:
                ((Activity) context).overridePendingTransition(R.anim.core_push_up_in, R.anim.core_push_up_out);
                return;
            case 3:
                ((Activity) context).overridePendingTransition(17432576, 17432577);
                return;
            case 4:
                ((Activity) context).overridePendingTransition(R.anim.core_zoom_enter, R.anim.core_zoom_exit);
                return;
            case 5:
                ((Activity) context).overridePendingTransition(R.anim.core_hyperspace_in, R.anim.core_hyperspace_out);
                return;
            case 6:
                ((Activity) context).overridePendingTransition(R.anim.core_push_right_in, R.anim.core_push_right_out);
                return;
            case 7:
                ((Activity) context).overridePendingTransition(R.anim.core_zoom_enter_back, R.anim.core_zoom_exit_back);
                return;
            case 11:
                ((Activity) context).overridePendingTransition(R.anim.core_push_left_in, 0);
                return;
            default:
                ((Activity) context).overridePendingTransition(R.anim.core_other, 0);
                return;
        }
    }

    public static String b(Context context) {
        String str = "null";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.facebook.c;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import bolts.AppLinks;
import com.facebook.c.a.a;
import com.facebook.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class b {
    public static final String a = "com.facebook.orca";
    public static final String b = "com.facebook.orca.extra.PROTOCOL_VERSION";
    public static final String c = "com.facebook.orca.extra.APPLICATION_ID";
    public static final String d = "com.facebook.orca.extra.REPLY_TOKEN";
    public static final String e = "com.facebook.orca.extra.THREAD_TOKEN";
    public static final String f = "com.facebook.orca.extra.METADATA";
    public static final String g = "com.facebook.orca.extra.EXTERNAL_URI";
    public static final String h = "com.facebook.orca.extra.PARTICIPANTS";
    public static final String i = "com.facebook.orca.extra.IS_REPLY";
    public static final String j = "com.facebook.orca.extra.IS_COMPOSE";
    public static final int k = 20150314;
    public static final String l = "com.facebook.orca.category.PLATFORM_THREAD_20150314";
    private static final String m = "MessengerUtils";

    public static void a(Activity activity, int i, c cVar) {
        if (!a((Context) activity)) {
            b(activity);
        } else if (c(activity).contains(Integer.valueOf(k))) {
            b(activity, i, cVar);
        } else {
            b(activity);
        }
    }

    private static void b(Activity activity, int i, c cVar) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(1);
            intent.setPackage(a);
            intent.putExtra("android.intent.extra.STREAM", cVar.d);
            intent.setType(cVar.e);
            String k = o.k();
            if (k != null) {
                intent.putExtra(b, k);
                intent.putExtra(c, k);
                intent.putExtra(f, cVar.f);
                intent.putExtra(g, cVar.g);
            }
            activity.startActivityForResult(intent, i);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage(a));
        }
    }

    public static a a(Intent intent) {
        Set categories = intent.getCategories();
        if (categories == null || !categories.contains(l)) {
            return null;
        }
        Bundle appLinkExtras = AppLinks.getAppLinkExtras(intent);
        String string = appLinkExtras.getString(e);
        String string2 = appLinkExtras.getString(f);
        String string3 = appLinkExtras.getString(h);
        boolean z = appLinkExtras.getBoolean(i);
        boolean z2 = appLinkExtras.getBoolean(j);
        a aVar = a.UNKNOWN;
        if (z) {
            aVar = a.REPLY_FLOW;
        } else if (z2) {
            aVar = a.COMPOSE_FLOW;
        }
        return new a(aVar, string, string2, a(string3));
    }

    public static void a(Activity activity, c cVar) {
        Intent intent = activity.getIntent();
        Set categories = intent.getCategories();
        if (categories == null) {
            activity.setResult(0, null);
            activity.finish();
        } else if (categories.contains(l)) {
            Bundle appLinkExtras = AppLinks.getAppLinkExtras(intent);
            Intent intent2 = new Intent();
            if (categories.contains(l)) {
                intent2.putExtra(b, k);
                intent2.putExtra(e, appLinkExtras.getString(e));
                intent2.setDataAndType(cVar.d, cVar.e);
                intent2.setFlags(1);
                intent2.putExtra(c, o.k());
                intent2.putExtra(f, cVar.f);
                intent2.putExtra(g, cVar.g);
                activity.setResult(-1, intent2);
                activity.finish();
                return;
            }
            throw new RuntimeException();
        } else {
            activity.setResult(0, null);
            activity.finish();
        }
    }

    public static boolean a(Context context) {
        try {
            context.getPackageManager().getPackageInfo(a, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void b(Context context) {
        try {
            a(context, "market://details?id=com.facebook.orca");
        } catch (ActivityNotFoundException e) {
            a(context, "http://play.google.com/store/apps/details?id=com.facebook.orca");
        }
    }

    private static Set<Integer> c(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Set<Integer> hashSet = new HashSet();
        Cursor query = contentResolver.query(Uri.parse("content://com.facebook.orca.provider.MessengerPlatformProvider/versions"), new String[]{"version"}, null, null, null);
        if (query != null) {
            try {
                int columnIndex = query.getColumnIndex("version");
                while (query.moveToNext()) {
                    hashSet.add(Integer.valueOf(query.getInt(columnIndex)));
                }
            } finally {
                query.close();
            }
        }
        return hashSet;
    }

    private static void a(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    private static List<String> a(String str) {
        if (str == null || str.length() == 0) {
            return Collections.emptyList();
        }
        String[] split = str.split(",");
        List<String> arrayList = new ArrayList();
        for (String trim : split) {
            arrayList.add(trim.trim());
        }
        return arrayList;
    }
}

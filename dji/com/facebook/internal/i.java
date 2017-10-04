package com.facebook.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.facebook.FacebookActivity;
import com.facebook.a.b;
import com.facebook.k;
import com.facebook.o;

public class i {

    public interface a {
        Bundle a();

        Bundle b();
    }

    public static void a(b bVar) {
        a(bVar, new k("Unable to show the provided content via the web or the installed version of the Facebook app. Some dialogs are only supported starting API 14."));
    }

    public static void a(b bVar, k kVar) {
        b(bVar, kVar);
    }

    public static void a(b bVar, Activity activity) {
        activity.startActivityForResult(bVar.b(), bVar.d());
        bVar.e();
    }

    public static void a(b bVar, o oVar) {
        oVar.a(bVar.b(), bVar.d());
        bVar.e();
    }

    public static boolean a(h hVar) {
        return c(hVar) != -1;
    }

    public static boolean b(h hVar) {
        return d(hVar) != null;
    }

    public static void b(b bVar, k kVar) {
        if (kVar != null) {
            ai.b(o.h());
            Intent intent = new Intent();
            intent.setClass(o.h(), FacebookActivity.class);
            intent.setAction(FacebookActivity.a);
            ab.a(intent, bVar.c().toString(), null, ab.a(), ab.a(kVar));
            bVar.a(intent);
        }
    }

    public static void a(b bVar, String str, Bundle bundle) {
        ai.b(o.h());
        ai.a(o.h());
        Bundle bundle2 = new Bundle();
        bundle2.putString("action", str);
        bundle2.putBundle("params", bundle);
        Intent intent = new Intent();
        ab.a(intent, bVar.c().toString(), str, ab.a(), bundle2);
        intent.setClass(o.h(), FacebookActivity.class);
        intent.setAction(k.a);
        bVar.a(intent);
    }

    public static void a(b bVar, Bundle bundle, h hVar) {
        ai.b(o.h());
        ai.a(o.h());
        String name = hVar.name();
        Uri d = d(hVar);
        if (d == null) {
            throw new k("Unable to fetch the Url for the DialogFeature : '" + name + "'");
        }
        Bundle a = af.a(bVar.c().toString(), ab.a(), bundle);
        if (a == null) {
            throw new k("Unable to fetch the app's key-hash");
        }
        Uri a2;
        if (d.isRelative()) {
            a2 = ah.a(af.a(), d.toString(), a);
        } else {
            a2 = ah.a(d.getAuthority(), d.getPath(), a);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("url", a2.toString());
        bundle2.putBoolean(ab.aA, true);
        Intent intent = new Intent();
        ab.a(intent, bVar.c().toString(), hVar.a(), ab.a(), bundle2);
        intent.setClass(o.h(), FacebookActivity.class);
        intent.setAction(k.a);
        bVar.a(intent);
    }

    public static void a(b bVar, a aVar, h hVar) {
        Context h = o.h();
        String a = hVar.a();
        int c = c(hVar);
        if (c == -1) {
            throw new k("Cannot present this dialog. This likely means that the Facebook app is not installed.");
        }
        Bundle a2;
        if (ab.a(c)) {
            a2 = aVar.a();
        } else {
            a2 = aVar.b();
        }
        if (a2 == null) {
            a2 = new Bundle();
        }
        Intent a3 = ab.a(h, bVar.c().toString(), a, c, a2);
        if (a3 == null) {
            throw new k("Unable to create Intent; this likely means theFacebook app is not installed.");
        }
        bVar.a(a3);
    }

    private static Uri d(h hVar) {
        String name = hVar.name();
        com.facebook.internal.ah.a a = ah.a(o.k(), hVar.a(), name);
        if (a != null) {
            return a.c();
        }
        return null;
    }

    public static int c(h hVar) {
        String k = o.k();
        String a = hVar.a();
        return ab.a(a, a(k, a, hVar));
    }

    private static int[] a(String str, String str2, h hVar) {
        com.facebook.internal.ah.a a = ah.a(str, str2, hVar.name());
        if (a != null) {
            return a.d();
        }
        return new int[]{hVar.b()};
    }

    public static void a(Context context, String str, String str2) {
        b c = b.c(context);
        Bundle bundle = new Bundle();
        bundle.putString(a.q, str2);
        c.a(str, null, bundle);
    }
}

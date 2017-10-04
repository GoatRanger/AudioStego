package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.facebook.FacebookActivity;
import com.facebook.k;
import com.facebook.o;
import com.facebook.p;
import java.util.Collection;

public final class ai {
    private static final String a = ai.class.getName();
    private static final String b = "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.";
    private static final String c = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
    private static final String d = "A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.";
    private static final String e = "com.facebook.app.FacebookContentProvider";

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("Argument '" + str + "' cannot be null");
        }
    }

    public static <T> void a(Collection<T> collection, String str) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + str + "' cannot be empty");
        }
    }

    public static <T> void b(Collection<T> collection, String str) {
        a((Object) collection, str);
        for (T t : collection) {
            if (t == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
        }
    }

    public static void c(Collection<String> collection, String str) {
        a((Object) collection, str);
        for (String str2 : collection) {
            if (str2 == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            } else if (str2.length() == 0) {
                throw new IllegalArgumentException("Container '" + str + "' cannot contain empty values");
            }
        }
    }

    public static <T> void d(Collection<T> collection, String str) {
        b((Collection) collection, str);
        a((Collection) collection, str);
    }

    public static void a() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new k("This method should be called from the UI thread");
        }
    }

    public static void a(String str, String str2) {
        if (ah.a(str)) {
            throw new IllegalArgumentException("Argument '" + str2 + "' cannot be null or empty");
        }
    }

    public static void a(Object obj, String str, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2 != null) {
                if (obj2.equals(obj)) {
                    return;
                }
            } else if (obj == null) {
                return;
            }
        }
        throw new IllegalArgumentException("Argument '" + str + "' was not one of the allowed values");
    }

    public static void b() {
        if (!o.a()) {
            throw new p("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }

    public static String c() {
        String k = o.k();
        if (k != null) {
            return k;
        }
        throw new IllegalStateException("No App ID found, please set the App ID.");
    }

    public static void a(Context context) {
        a(context, true);
    }

    public static void a(Context context, boolean z) {
        a((Object) context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") != -1) {
            return;
        }
        if (z) {
            throw new IllegalStateException(b);
        }
        Log.w(a, b);
    }

    public static void b(Context context) {
        b(context, true);
    }

    public static void b(Context context, boolean z) {
        a((Object) context, "context");
        PackageManager packageManager = context.getPackageManager();
        ActivityInfo activityInfo = null;
        if (packageManager != null) {
            try {
                activityInfo = packageManager.getActivityInfo(new ComponentName(context, FacebookActivity.class), 1);
            } catch (NameNotFoundException e) {
            }
        }
        if (activityInfo != null) {
            return;
        }
        if (z) {
            throw new IllegalStateException(c);
        }
        Log.w(a, c);
    }

    public static void c(Context context) {
        a((Object) context, "context");
        String c = c();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            if (packageManager.resolveContentProvider(e + c, 0) == null) {
                throw new IllegalStateException(String.format(d, new Object[]{c}));
            }
        }
    }
}

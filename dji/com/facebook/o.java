package com.facebook;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import com.facebook.a.b;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.ab;
import com.facebook.internal.ah;
import com.facebook.internal.ai;
import com.facebook.internal.aj;
import com.facebook.internal.w;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class o {
    private static final String A = "com.facebook.sdk.attributionTracking";
    private static final String B = "%s/activities";
    private static final BlockingQueue<Runnable> C = new LinkedBlockingQueue(10);
    private static final ThreadFactory D = new ThreadFactory() {
        private final AtomicInteger a = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "FacebookSdk #" + this.a.incrementAndGet());
        }
    };
    private static Boolean E = Boolean.valueOf(false);
    static final String a = "The callback request code offset can't be updated once the SDK is initialized.";
    static final String b = "The callback request code offset can't be negative.";
    public static final String c = "com.facebook.sdk.ApplicationId";
    public static final String d = "com.facebook.sdk.ApplicationName";
    public static final String e = "com.facebook.sdk.ClientToken";
    public static final String f = "com.facebook.sdk.WebDialogTheme";
    private static final String g = o.class.getCanonicalName();
    private static final HashSet<y> h = new HashSet(Arrays.asList(new y[]{y.DEVELOPER_ERRORS}));
    private static volatile Executor i = null;
    private static volatile String j = null;
    private static volatile String k = null;
    private static volatile String l = null;
    private static volatile int m = 0;
    private static final String n = "facebook.com";
    private static volatile String o = n;
    private static AtomicLong p = new AtomicLong(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH);
    private static volatile boolean q = false;
    private static boolean r = false;
    private static w<File> s = null;
    private static Context t = null;
    private static final int u = 5;
    private static final int v = 128;
    private static final int w = 1;
    private static int x = 64206;
    private static final Object y = new Object();
    private static final int z = 100;

    public interface a {
        void a();
    }

    public static synchronized void a(Context context, int i) {
        synchronized (o.class) {
            a(context, i, null);
        }
    }

    public static synchronized void a(Context context, int i, a aVar) {
        synchronized (o.class) {
            if (E.booleanValue() && i != x) {
                throw new k(a);
            } else if (i < 0) {
                throw new k(b);
            } else {
                x = i;
                a(context, aVar);
            }
        }
    }

    public static synchronized void a(Context context) {
        synchronized (o.class) {
            a(context, null);
        }
    }

    public static synchronized void a(Context context, final a aVar) {
        synchronized (o.class) {
            if (!E.booleanValue()) {
                ai.a((Object) context, "applicationContext");
                ai.b(context, false);
                ai.a(context, false);
                t = context.getApplicationContext();
                c(t);
                E = Boolean.valueOf(true);
                ah.a(t, j);
                ab.b();
                BoltsMeasurementEventListener.getInstance(t);
                s = new w(new Callable<File>() {
                    public /* synthetic */ Object call() throws Exception {
                        return a();
                    }

                    public File a() throws Exception {
                        return o.t.getCacheDir();
                    }
                });
                f().execute(new FutureTask(new Callable<Void>() {
                    public /* synthetic */ Object call() throws Exception {
                        return a();
                    }

                    public Void a() throws Exception {
                        b.a().c();
                        aa.a().c();
                        if (AccessToken.a() != null && Profile.a() == null) {
                            Profile.b();
                        }
                        if (aVar != null) {
                            aVar.a();
                        }
                        return null;
                    }
                }));
            } else if (aVar != null) {
                aVar.a();
            }
        }
    }

    public static synchronized boolean a() {
        boolean booleanValue;
        synchronized (o.class) {
            booleanValue = E.booleanValue();
        }
        return booleanValue;
    }

    public static Set<y> b() {
        Set<y> unmodifiableSet;
        synchronized (h) {
            unmodifiableSet = Collections.unmodifiableSet(new HashSet(h));
        }
        return unmodifiableSet;
    }

    public static void a(y yVar) {
        synchronized (h) {
            h.add(yVar);
            r();
        }
    }

    public static void b(y yVar) {
        synchronized (h) {
            h.remove(yVar);
        }
    }

    public static void c() {
        synchronized (h) {
            h.clear();
        }
    }

    public static boolean c(y yVar) {
        boolean z;
        synchronized (h) {
            z = d() && h.contains(yVar);
        }
        return z;
    }

    public static boolean d() {
        return q;
    }

    public static void a(boolean z) {
        q = z;
    }

    public static boolean e() {
        return r;
    }

    private static void r() {
        if (h.contains(y.GRAPH_API_DEBUG_INFO) && !h.contains(y.GRAPH_API_DEBUG_WARNING)) {
            h.add(y.GRAPH_API_DEBUG_WARNING);
        }
    }

    public static void b(boolean z) {
        r = z;
    }

    public static Executor f() {
        synchronized (y) {
            if (i == null) {
                i = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return i;
    }

    public static void a(Executor executor) {
        ai.a((Object) executor, "executor");
        synchronized (y) {
            i = executor;
        }
    }

    public static String g() {
        return o;
    }

    public static void a(String str) {
        Log.w(g, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        o = str;
    }

    public static Context h() {
        ai.b();
        return t;
    }

    public static void a(Context context, final String str) {
        final Context applicationContext = context.getApplicationContext();
        f().execute(new Runnable() {
            public void run() {
                o.b(applicationContext, str);
            }
        });
    }

    static com.facebook.v b(android.content.Context r14, java.lang.String r15) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.facebook.o.b(android.content.Context, java.lang.String):com.facebook.v. bs: [B:3:0x0007, B:11:0x005d]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r12 = 0;
        r1 = 0;
        if (r14 == 0) goto L_0x0007;
    L_0x0005:
        if (r15 != 0) goto L_0x0021;
    L_0x0007:
        r0 = new java.lang.IllegalArgumentException;	 Catch:{ Exception -> 0x000f }
        r2 = "Both context and applicationId must be non-null";	 Catch:{ Exception -> 0x000f }
        r0.<init>(r2);	 Catch:{ Exception -> 0x000f }
        throw r0;	 Catch:{ Exception -> 0x000f }
    L_0x000f:
        r0 = move-exception;
        r2 = r0;
        r0 = "Facebook-publish";
        com.facebook.internal.ah.a(r0, r2);
        r0 = new com.facebook.v;
        r3 = new com.facebook.n;
        r3.<init>(r1, r2);
        r0.<init>(r1, r1, r3);
    L_0x0020:
        return r0;
    L_0x0021:
        r0 = com.facebook.internal.d.a(r14);	 Catch:{ Exception -> 0x000f }
        r2 = "com.facebook.sdk.attributionTracking";	 Catch:{ Exception -> 0x000f }
        r3 = 0;	 Catch:{ Exception -> 0x000f }
        r2 = r14.getSharedPreferences(r2, r3);	 Catch:{ Exception -> 0x000f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x000f }
        r3.<init>();	 Catch:{ Exception -> 0x000f }
        r3 = r3.append(r15);	 Catch:{ Exception -> 0x000f }
        r4 = "ping";	 Catch:{ Exception -> 0x000f }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x000f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x000f }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x000f }
        r4.<init>();	 Catch:{ Exception -> 0x000f }
        r4 = r4.append(r15);	 Catch:{ Exception -> 0x000f }
        r5 = "json";	 Catch:{ Exception -> 0x000f }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x000f }
        r4 = r4.toString();	 Catch:{ Exception -> 0x000f }
        r6 = 0;	 Catch:{ Exception -> 0x000f }
        r6 = r2.getLong(r3, r6);	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r5 = r2.getString(r4, r5);	 Catch:{ Exception -> 0x000f }
        r8 = com.facebook.internal.c.a.MOBILE_INSTALL_EVENT;	 Catch:{ JSONException -> 0x00a6 }
        r9 = com.facebook.a.b.d(r14);	 Catch:{ JSONException -> 0x00a6 }
        r10 = b(r14);	 Catch:{ JSONException -> 0x00a6 }
        r0 = com.facebook.internal.c.a(r8, r0, r9, r10, r14);	 Catch:{ JSONException -> 0x00a6 }
        r8 = "%s/activities";	 Catch:{ Exception -> 0x000f }
        r9 = 1;	 Catch:{ Exception -> 0x000f }
        r9 = new java.lang.Object[r9];	 Catch:{ Exception -> 0x000f }
        r10 = 0;	 Catch:{ Exception -> 0x000f }
        r9[r10] = r15;	 Catch:{ Exception -> 0x000f }
        r8 = java.lang.String.format(r8, r9);	 Catch:{ Exception -> 0x000f }
        r9 = 0;	 Catch:{ Exception -> 0x000f }
        r10 = 0;	 Catch:{ Exception -> 0x000f }
        r8 = com.facebook.GraphRequest.a(r9, r8, r0, r10);	 Catch:{ Exception -> 0x000f }
        r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1));
        if (r0 == 0) goto L_0x00bc;
    L_0x0081:
        if (r5 == 0) goto L_0x00e1;
    L_0x0083:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00af }
        r0.<init>(r5);	 Catch:{ JSONException -> 0x00af }
    L_0x0088:
        r2 = r0;
    L_0x0089:
        if (r2 != 0) goto L_0x00b2;
    L_0x008b:
        r0 = "true";	 Catch:{ Exception -> 0x000f }
        r2 = 0;	 Catch:{ Exception -> 0x000f }
        r3 = new com.facebook.u;	 Catch:{ Exception -> 0x000f }
        r4 = 1;	 Catch:{ Exception -> 0x000f }
        r4 = new com.facebook.GraphRequest[r4];	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r4[r5] = r8;	 Catch:{ Exception -> 0x000f }
        r3.<init>(r4);	 Catch:{ Exception -> 0x000f }
        r0 = com.facebook.v.a(r0, r2, r3);	 Catch:{ Exception -> 0x000f }
        r2 = 0;	 Catch:{ Exception -> 0x000f }
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x000f }
        r0 = (com.facebook.v) r0;	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;	 Catch:{ Exception -> 0x000f }
    L_0x00a6:
        r0 = move-exception;	 Catch:{ Exception -> 0x000f }
        r2 = new com.facebook.k;	 Catch:{ Exception -> 0x000f }
        r3 = "An error occurred while publishing install.";	 Catch:{ Exception -> 0x000f }
        r2.<init>(r3, r0);	 Catch:{ Exception -> 0x000f }
        throw r2;	 Catch:{ Exception -> 0x000f }
    L_0x00af:
        r0 = move-exception;	 Catch:{ Exception -> 0x000f }
        r2 = r1;	 Catch:{ Exception -> 0x000f }
        goto L_0x0089;	 Catch:{ Exception -> 0x000f }
    L_0x00b2:
        r0 = new com.facebook.v;	 Catch:{ Exception -> 0x000f }
        r3 = 0;	 Catch:{ Exception -> 0x000f }
        r4 = 0;	 Catch:{ Exception -> 0x000f }
        r5 = 0;	 Catch:{ Exception -> 0x000f }
        r0.<init>(r3, r4, r5, r2);	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;	 Catch:{ Exception -> 0x000f }
    L_0x00bc:
        r0 = r8.m();	 Catch:{ Exception -> 0x000f }
        r2 = r2.edit();	 Catch:{ Exception -> 0x000f }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x000f }
        r2.putLong(r3, r6);	 Catch:{ Exception -> 0x000f }
        r3 = r0.b();	 Catch:{ Exception -> 0x000f }
        if (r3 == 0) goto L_0x00dc;	 Catch:{ Exception -> 0x000f }
    L_0x00d1:
        r3 = r0.b();	 Catch:{ Exception -> 0x000f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x000f }
        r2.putString(r4, r3);	 Catch:{ Exception -> 0x000f }
    L_0x00dc:
        r2.apply();	 Catch:{ Exception -> 0x000f }
        goto L_0x0020;
    L_0x00e1:
        r0 = r1;
        goto L_0x0088;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.o.b(android.content.Context, java.lang.String):com.facebook.v");
    }

    public static String i() {
        return q.a;
    }

    public static boolean b(Context context) {
        ai.b();
        return context.getSharedPreferences(b.a, 0).getBoolean("limitEventUsage", false);
    }

    public static void a(Context context, boolean z) {
        context.getSharedPreferences(b.a, 0).edit().putBoolean("limitEventUsage", z).apply();
    }

    public static long j() {
        ai.b();
        return p.get();
    }

    public static void a(long j) {
        p.set(j);
    }

    static void c(Context context) {
        if (context != null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (j == null) {
                        Object obj = applicationInfo.metaData.get(c);
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (str.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                j = str.substring(2);
                            } else {
                                j = str;
                            }
                        } else if (obj instanceof Integer) {
                            throw new k("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
                        }
                    }
                    if (k == null) {
                        k = applicationInfo.metaData.getString(d);
                    }
                    if (l == null) {
                        l = applicationInfo.metaData.getString(e);
                    }
                    if (m == 0) {
                        a(applicationInfo.metaData.getInt(f));
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
    }

    public static String d(Context context) {
        String str = null;
        ai.b();
        if (context == null) {
            return str;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null || signatureArr.length == 0) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(packageInfo.signatures[0].toByteArray());
                return Base64.encodeToString(instance.digest(), 9);
            } catch (NoSuchAlgorithmException e) {
                return str;
            }
        } catch (NameNotFoundException e2) {
            return str;
        }
    }

    public static String k() {
        ai.b();
        return j;
    }

    public static void b(String str) {
        j = str;
    }

    public static String l() {
        ai.b();
        return k;
    }

    public static void c(String str) {
        k = str;
    }

    public static String m() {
        ai.b();
        return l;
    }

    public static void d(String str) {
        l = str;
    }

    public static int n() {
        ai.b();
        return m;
    }

    public static void a(int i) {
        if (i == 0) {
            i = aj.d;
        }
        m = i;
    }

    public static File o() {
        ai.b();
        return (File) s.a();
    }

    public static void a(File file) {
        s = new w((Object) file);
    }

    public static int p() {
        ai.b();
        return x;
    }

    public static boolean b(int i) {
        return i >= x && i < x + 100;
    }
}

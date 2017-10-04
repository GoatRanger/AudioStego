package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

public class ec {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static final String e = "e";

    public static String a(Context context, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append(a);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static Class<? extends et> a(int i) {
        switch (i) {
            case 0:
                return eo.class;
            case 1:
                return eq.class;
            case 2:
                return en.class;
            default:
                return null;
        }
    }

    public static et b(int i) {
        switch (i) {
            case 0:
                return new eo();
            case 1:
                return new eq();
            case 2:
                return new en();
            default:
                return null;
        }
    }

    public static String c(int i) {
        switch (i) {
            case 0:
                return c;
            case 1:
                return b;
            case 2:
                return d;
            default:
                return "";
        }
    }

    static ei a(Context context, int i) {
        switch (i) {
            case 0:
                return new eg(i);
            case 1:
                return new eh(i);
            case 2:
                return new ef(i);
            default:
                return null;
        }
    }

    static void a(Context context, Throwable th, int i, String str, String str2) {
        try {
            ExecutorService c = ee.c();
            if (c != null && !c.isShutdown()) {
                final Context context2 = context;
                final int i2 = i;
                final Throwable th2 = th;
                final String str3 = str;
                final String str4 = str2;
                c.submit(new Runnable() {
                    public void run() {
                        try {
                            ei a = ec.a(context2, i2);
                            if (a != null) {
                                a.a(context2, th2, str3, str4);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e) {
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
    }

    static void a(Context context) {
        try {
            ei a = a(context, 2);
            if (a != null) {
                a.b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    static void b(final Context context) {
        try {
            ExecutorService c = ee.c();
            if (c != null && !c.isShutdown()) {
                c.submit(new Runnable() {
                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                        r7 = this;
                        r0 = 0;
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
                        r2 = 0;
                        r2 = com.amap.api.mapcore.util.ec.a(r1, r2);	 Catch:{ RejectedExecutionException -> 0x006d, Throwable -> 0x003a, all -> 0x0057 }
                        r1 = r3;	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
                        r3 = 1;
                        r1 = com.amap.api.mapcore.util.ec.a(r1, r3);	 Catch:{ RejectedExecutionException -> 0x00a9, Throwable -> 0x0094, all -> 0x007d }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
                        r4 = 2;
                        r0 = com.amap.api.mapcore.util.ec.a(r3, r4);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x009b, all -> 0x0084 }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r2.c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r1.c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r0.c(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        r3 = r3;	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        com.amap.api.mapcore.util.dz.a(r3);	 Catch:{ RejectedExecutionException -> 0x00ac, Throwable -> 0x00a2, all -> 0x008b }
                        if (r2 == 0) goto L_0x002f;
                    L_0x002c:
                        r2.c();
                    L_0x002f:
                        if (r1 == 0) goto L_0x0034;
                    L_0x0031:
                        r1.c();
                    L_0x0034:
                        if (r0 == 0) goto L_0x0039;
                    L_0x0036:
                        r0.c();
                    L_0x0039:
                        return;
                    L_0x003a:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x0040:
                        r4 = "Log";
                        r5 = "processLog";
                        com.amap.api.mapcore.util.eb.a(r0, r4, r5);	 Catch:{ all -> 0x0092 }
                        if (r3 == 0) goto L_0x004c;
                    L_0x0049:
                        r3.c();
                    L_0x004c:
                        if (r2 == 0) goto L_0x0051;
                    L_0x004e:
                        r2.c();
                    L_0x0051:
                        if (r1 == 0) goto L_0x0039;
                    L_0x0053:
                        r1.c();
                        goto L_0x0039;
                    L_0x0057:
                        r1 = move-exception;
                        r2 = r0;
                        r3 = r0;
                        r6 = r1;
                        r1 = r0;
                        r0 = r6;
                    L_0x005d:
                        if (r3 == 0) goto L_0x0062;
                    L_0x005f:
                        r3.c();
                    L_0x0062:
                        if (r2 == 0) goto L_0x0067;
                    L_0x0064:
                        r2.c();
                    L_0x0067:
                        if (r1 == 0) goto L_0x006c;
                    L_0x0069:
                        r1.c();
                    L_0x006c:
                        throw r0;
                    L_0x006d:
                        r1 = move-exception;
                        r1 = r0;
                        r2 = r0;
                    L_0x0070:
                        if (r2 == 0) goto L_0x0075;
                    L_0x0072:
                        r2.c();
                    L_0x0075:
                        if (r1 == 0) goto L_0x007a;
                    L_0x0077:
                        r1.c();
                    L_0x007a:
                        if (r0 == 0) goto L_0x0039;
                    L_0x007c:
                        goto L_0x0036;
                    L_0x007d:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x005d;
                    L_0x0084:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005d;
                    L_0x008b:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x005d;
                    L_0x0092:
                        r0 = move-exception;
                        goto L_0x005d;
                    L_0x0094:
                        r1 = move-exception;
                        r3 = r2;
                        r2 = r0;
                        r6 = r0;
                        r0 = r1;
                        r1 = r6;
                        goto L_0x0040;
                    L_0x009b:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x0040;
                    L_0x00a2:
                        r3 = move-exception;
                        r6 = r3;
                        r3 = r2;
                        r2 = r1;
                        r1 = r0;
                        r0 = r6;
                        goto L_0x0040;
                    L_0x00a9:
                        r1 = move-exception;
                        r1 = r0;
                        goto L_0x0070;
                    L_0x00ac:
                        r3 = move-exception;
                        goto L_0x0070;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ec.2.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            eb.a(th, "Log", "processLog");
        }
    }
}

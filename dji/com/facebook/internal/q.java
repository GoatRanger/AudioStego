package com.facebook.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.io.Closeable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class q {
    private static final int a = 8;
    private static final int b = 2;
    private static Handler c;
    private static ak d = new ak(8);
    private static ak e = new ak(2);
    private static final Map<d, c> f = new HashMap();

    private static class a implements Runnable {
        private Context a;
        private d b;
        private boolean c;

        a(Context context, d dVar, boolean z) {
            this.a = context;
            this.b = dVar;
            this.c = z;
        }

        public void run() {
            q.b(this.b, this.a, this.c);
        }
    }

    private static class b implements Runnable {
        private Context a;
        private d b;

        b(Context context, d dVar) {
            this.a = context;
            this.b = dVar;
        }

        public void run() {
            q.b(this.b, this.a);
        }
    }

    private static class c {
        com.facebook.internal.ak.a a;
        r b;
        boolean c;

        private c() {
        }
    }

    private static class d {
        private static final int c = 29;
        private static final int d = 37;
        Uri a;
        Object b;

        d(Uri uri, Object obj) {
            this.a = uri;
            this.b = obj;
        }

        public int hashCode() {
            return ((this.a.hashCode() + 1073) * 37) + this.b.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof d)) {
                return false;
            }
            d dVar = (d) obj;
            if (dVar.a == this.a && dVar.b == this.b) {
                return true;
            }
            return false;
        }
    }

    public static void a(r rVar) {
        if (rVar != null) {
            d dVar = new d(rVar.b(), rVar.e());
            synchronized (f) {
                c cVar = (c) f.get(dVar);
                if (cVar != null) {
                    cVar.b = rVar;
                    cVar.c = false;
                    cVar.a.c();
                } else {
                    a(rVar, dVar, rVar.d());
                }
            }
        }
    }

    public static boolean b(r rVar) {
        boolean z;
        d dVar = new d(rVar.b(), rVar.e());
        synchronized (f) {
            c cVar = (c) f.get(dVar);
            if (cVar == null) {
                z = false;
            } else if (cVar.a.a()) {
                f.remove(dVar);
                z = true;
            } else {
                cVar.c = true;
                z = true;
            }
        }
        return z;
    }

    public static void c(r rVar) {
        d dVar = new d(rVar.b(), rVar.e());
        synchronized (f) {
            c cVar = (c) f.get(dVar);
            if (cVar != null) {
                cVar.a.c();
            }
        }
    }

    public static void a(Context context) {
        t.b(context);
        ag.b();
    }

    private static void a(r rVar, d dVar, boolean z) {
        a(rVar, dVar, e, new a(rVar.a(), dVar, z));
    }

    private static void a(r rVar, d dVar) {
        a(rVar, dVar, d, new b(rVar.a(), dVar));
    }

    private static void a(r rVar, d dVar, ak akVar, Runnable runnable) {
        synchronized (f) {
            c cVar = new c();
            cVar.b = rVar;
            f.put(dVar, cVar);
            cVar.a = akVar.a(runnable);
        }
    }

    private static void a(d dVar, Exception exception, Bitmap bitmap, boolean z) {
        c a = a(dVar);
        if (a != null && !a.c) {
            final r rVar = a.b;
            final com.facebook.internal.r.b c = rVar.c();
            if (c != null) {
                final Exception exception2 = exception;
                final boolean z2 = z;
                final Bitmap bitmap2 = bitmap;
                a().post(new Runnable() {
                    public void run() {
                        c.a(new s(rVar, exception2, z2, bitmap2));
                    }
                });
            }
        }
    }

    private static void b(d dVar, Context context, boolean z) {
        Closeable closeable;
        boolean z2;
        c a;
        boolean z3 = false;
        if (z) {
            Uri a2 = ag.a(dVar.a);
            if (a2 != null) {
                InputStream a3 = t.a(a2, context);
                if (a3 != null) {
                    z3 = true;
                }
                boolean z4 = z3;
                closeable = a3;
                z2 = z4;
                if (!z2) {
                    closeable = t.a(dVar.a, context);
                }
                if (closeable == null) {
                    Bitmap decodeStream = BitmapFactory.decodeStream(closeable);
                    ah.a(closeable);
                    a(dVar, null, decodeStream, z2);
                }
                a = a(dVar);
                if (a != null && !a.c) {
                    a(a.b, dVar);
                    return;
                }
                return;
            }
        }
        z2 = false;
        closeable = null;
        if (z2) {
            closeable = t.a(dVar.a, context);
        }
        if (closeable == null) {
            a = a(dVar);
            if (a != null) {
                return;
            }
            return;
        }
        Bitmap decodeStream2 = BitmapFactory.decodeStream(closeable);
        ah.a(closeable);
        a(dVar, null, decodeStream2, z2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(com.facebook.internal.q.d r11, android.content.Context r12) {
        /*
        r3 = 0;
        r2 = 0;
        r1 = 1;
        r0 = new java.net.URL;	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r4 = r11.a;	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r0.<init>(r4);	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r0 = r0.openConnection();	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x00be, all -> 0x00b6 }
        r4 = 0;
        r0.setInstanceFollowRedirects(r4);	 Catch:{ IOException -> 0x00c2, all -> 0x00b9 }
        r4 = r0.getResponseCode();	 Catch:{ IOException -> 0x00c2, all -> 0x00b9 }
        switch(r4) {
            case 200: goto L_0x0087;
            case 301: goto L_0x0051;
            case 302: goto L_0x0051;
            default: goto L_0x001f;
        };	 Catch:{ IOException -> 0x00c2, all -> 0x00b9 }
    L_0x001f:
        r5 = r0.getErrorStream();	 Catch:{ IOException -> 0x00c2, all -> 0x00b9 }
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r6.<init>();	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        if (r5 == 0) goto L_0x00ac;
    L_0x002a:
        r4 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r4.<init>(r5);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r7 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r7 = new char[r7];	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
    L_0x0033:
        r8 = 0;
        r9 = r7.length;	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r8 = r4.read(r7, r8, r9);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        if (r8 <= 0) goto L_0x0093;
    L_0x003b:
        r9 = 0;
        r6.append(r7, r9, r8);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        goto L_0x0033;
    L_0x0040:
        r4 = move-exception;
        r10 = r4;
        r4 = r0;
        r0 = r10;
    L_0x0044:
        com.facebook.internal.ah.a(r5);
        com.facebook.internal.ah.a(r4);
        r4 = r0;
    L_0x004b:
        if (r1 == 0) goto L_0x0050;
    L_0x004d:
        a(r11, r4, r3, r2);
    L_0x0050:
        return;
    L_0x0051:
        r1 = "location";
        r1 = r0.getHeaderField(r1);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r4 = com.facebook.internal.ah.a(r1);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        if (r4 != 0) goto L_0x00d0;
    L_0x005d:
        r1 = android.net.Uri.parse(r1);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r4 = r11.a;	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        com.facebook.internal.ag.a(r4, r1);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r4 = a(r11);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        if (r4 == 0) goto L_0x007d;
    L_0x006c:
        r5 = r4.c;	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        if (r5 != 0) goto L_0x007d;
    L_0x0070:
        r4 = r4.b;	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r5 = new com.facebook.internal.q$d;	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r6 = r11.b;	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r5.<init>(r1, r6);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
        r1 = 0;
        a(r4, r5, r1);	 Catch:{ IOException -> 0x00c9, all -> 0x00b9 }
    L_0x007d:
        r1 = r2;
        r4 = r3;
        r5 = r3;
    L_0x0080:
        com.facebook.internal.ah.a(r5);
        com.facebook.internal.ah.a(r0);
        goto L_0x004b;
    L_0x0087:
        r5 = com.facebook.internal.t.a(r12, r0);	 Catch:{ IOException -> 0x00c2, all -> 0x00b9 }
        r4 = android.graphics.BitmapFactory.decodeStream(r5);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r10 = r4;
        r4 = r3;
        r3 = r10;
        goto L_0x0080;
    L_0x0093:
        com.facebook.internal.ah.a(r4);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
    L_0x0096:
        r4 = new com.facebook.k;	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r6 = r6.toString();	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r4.<init>(r6);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        goto L_0x0080;
    L_0x00a0:
        r1 = move-exception;
        r3 = r5;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x00a5:
        com.facebook.internal.ah.a(r3);
        com.facebook.internal.ah.a(r1);
        throw r0;
    L_0x00ac:
        r4 = com.facebook.R.string.com_facebook_image_download_unknown_error;	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r4 = r12.getString(r4);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        r6.append(r4);	 Catch:{ IOException -> 0x0040, all -> 0x00a0 }
        goto L_0x0096;
    L_0x00b6:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00a5;
    L_0x00b9:
        r1 = move-exception;
        r10 = r1;
        r1 = r0;
        r0 = r10;
        goto L_0x00a5;
    L_0x00be:
        r0 = move-exception;
        r5 = r3;
        r4 = r3;
        goto L_0x0044;
    L_0x00c2:
        r4 = move-exception;
        r5 = r3;
        r10 = r4;
        r4 = r0;
        r0 = r10;
        goto L_0x0044;
    L_0x00c9:
        r1 = move-exception;
        r5 = r3;
        r4 = r0;
        r0 = r1;
        r1 = r2;
        goto L_0x0044;
    L_0x00d0:
        r1 = r2;
        r4 = r3;
        r5 = r3;
        goto L_0x0080;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.q.b(com.facebook.internal.q$d, android.content.Context):void");
    }

    private static synchronized Handler a() {
        Handler handler;
        synchronized (q.class) {
            if (c == null) {
                c = new Handler(Looper.getMainLooper());
            }
            handler = c;
        }
        return handler;
    }

    private static c a(d dVar) {
        c cVar;
        synchronized (f) {
            cVar = (c) f.remove(dVar);
        }
        return cVar;
    }
}

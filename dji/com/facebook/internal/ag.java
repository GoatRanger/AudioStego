package com.facebook.internal;

import android.net.Uri;
import com.facebook.internal.n.d;
import com.facebook.y;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class ag {
    static final String a = ag.class.getSimpleName();
    private static final String b = (a + "_Redirect");
    private static volatile n c;

    ag() {
    }

    static synchronized n a() throws IOException {
        n nVar;
        synchronized (ag.class) {
            if (c == null) {
                c = new n(a, new d());
            }
            nVar = c;
        }
        return nVar;
    }

    static Uri a(Uri uri) {
        Closeable inputStreamReader;
        Throwable th;
        Uri uri2 = null;
        Object obj = null;
        if (uri != null) {
            String uri3 = uri.toString();
            try {
                n a = a();
                String str = uri3;
                Closeable closeable = uri2;
                while (true) {
                    try {
                        InputStream a2 = a.a(str, b);
                        if (a2 == null) {
                            break;
                        }
                        inputStreamReader = new InputStreamReader(a2);
                        try {
                            char[] cArr = new char[128];
                            StringBuilder stringBuilder = new StringBuilder();
                            while (true) {
                                int read = inputStreamReader.read(cArr, 0, cArr.length);
                                if (read <= 0) {
                                    break;
                                }
                                stringBuilder.append(cArr, 0, read);
                            }
                            ah.a(inputStreamReader);
                            str = stringBuilder.toString();
                            closeable = inputStreamReader;
                            int i = 1;
                        } catch (IOException e) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (IOException e2) {
                        inputStreamReader = closeable;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader = closeable;
                    }
                }
                if (obj != null) {
                    uri2 = Uri.parse(str);
                    ah.a(closeable);
                } else {
                    ah.a(closeable);
                }
            } catch (IOException e3) {
                obj = uri2;
                ah.a(inputStreamReader);
                return uri2;
            } catch (Throwable th4) {
                Throwable th5 = th4;
                inputStreamReader = uri2;
                th = th5;
                ah.a(inputStreamReader);
                throw th;
            }
        }
        return uri2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void a(android.net.Uri r5, android.net.Uri r6) {
        /*
        if (r5 == 0) goto L_0x0004;
    L_0x0002:
        if (r6 != 0) goto L_0x0005;
    L_0x0004:
        return;
    L_0x0005:
        r0 = 0;
        r1 = a();	 Catch:{ IOException -> 0x0023, all -> 0x0028 }
        r2 = r5.toString();	 Catch:{ IOException -> 0x0023, all -> 0x0028 }
        r3 = b;	 Catch:{ IOException -> 0x0023, all -> 0x0028 }
        r0 = r1.b(r2, r3);	 Catch:{ IOException -> 0x0023, all -> 0x0028 }
        r1 = r6.toString();	 Catch:{ IOException -> 0x0023, all -> 0x0030 }
        r1 = r1.getBytes();	 Catch:{ IOException -> 0x0023, all -> 0x0030 }
        r0.write(r1);	 Catch:{ IOException -> 0x0023, all -> 0x0030 }
        com.facebook.internal.ah.a(r0);
        goto L_0x0004;
    L_0x0023:
        r1 = move-exception;
        com.facebook.internal.ah.a(r0);
        goto L_0x0004;
    L_0x0028:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
    L_0x002c:
        com.facebook.internal.ah.a(r1);
        throw r0;
    L_0x0030:
        r1 = move-exception;
        r4 = r1;
        r1 = r0;
        r0 = r4;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.ag.a(android.net.Uri, android.net.Uri):void");
    }

    static void b() {
        try {
            a().b();
        } catch (IOException e) {
            x.a(y.CACHE, 5, a, "clearCache failed " + e.getMessage());
        }
    }
}

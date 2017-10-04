package com.alipay.b.g;

import android.content.Context;
import java.util.LinkedList;
import java.util.Map;

public class a {
    private static a a;
    private static Object b = new Object();
    private Context c;
    private volatile boolean d = false;
    private Thread e;
    private LinkedList<b> f = new LinkedList();

    public interface a {
        void a(c cVar);
    }

    private class b {
        final /* synthetic */ a a;
        private int b;
        private String c;
        private String d;
        private String e;
        private a f;

        public b(a aVar, int i, String str, String str2, String str3, a aVar2) {
            this.a = aVar;
            this.b = i;
            this.e = str3;
            if (com.alipay.e.a.a.b.a.a(str)) {
                this.c = com.alipay.b.d.b.a(aVar.c);
            } else {
                this.c = str;
            }
            com.alipay.e.a.a.b.b.a("Utdid = " + this.c);
            this.d = str2;
            this.f = aVar2;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
            r4 = this;
            r3 = 0;
            r0 = r4.a;
            r0 = r0.d;
            if (r0 == 0) goto L_0x000a;
        L_0x0009:
            return;
        L_0x000a:
            r0 = r4.a;
            r1 = 1;
            r0.d = r1;
            r0 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0 = r0.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.b.d.a.a(r0);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0 = r4.b;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.b.d.a.b();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0 = new java.util.HashMap;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.<init>();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = "tid";
            r2 = r4.d;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.put(r1, r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = "utdid";
            r2 = r4.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.put(r1, r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = "umid";
            r2 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = r2.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.b.d.a.a(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = com.alipay.b.d.a.a();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.put(r1, r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = "userId";
            r2 = r4.e;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.put(r1, r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.b.g.e.a(r1, r0);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0 = r4.f;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            if (r0 == 0) goto L_0x00e6;
        L_0x0057:
            r0 = new com.alipay.b.g.a$c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.<init>(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = com.alipay.b.a.a.b(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.c = r1;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = com.alipay.b.a.a.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.b = r1;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.b.d.a.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = com.alipay.b.d.a.a();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.a = r1;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = com.alipay.b.f.b.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r0.d = r1;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = "[*]result.apdid     = ";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = r0.c;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.e.a.a.b.b.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = "[*]result.token     = ";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = r0.b;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.e.a.a.b.b.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = "[*]result.umid      = ";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = r0.a;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.e.a.a.b.b.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = "[*]result.clientKey = ";
            r1.<init>(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r2 = r0.d;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r1.toString();	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            com.alipay.e.a.a.b.b.a(r1);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1 = r4.f;	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
            r1.a(r0);	 Catch:{ Throwable -> 0x00ed, all -> 0x00f5 }
        L_0x00e6:
            r0 = r4.a;
            r0.d = r3;
            goto L_0x0009;
        L_0x00ed:
            r0 = move-exception;
            r0 = r4.a;
            r0.d = r3;
            goto L_0x0009;
        L_0x00f5:
            r0 = move-exception;
            r1 = r4.a;
            r1.d = r3;
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alipay.b.g.a.b.a():void");
        }
    }

    public class c {
        public String a;
        public String b;
        public String c;
        public String d;
        final /* synthetic */ a e;

        public c(a aVar) {
            this.e = aVar;
        }
    }

    private a(Context context) {
        this.c = context;
    }

    public static a getInstance(Context context) {
        a aVar;
        synchronized (b) {
            if (a == null) {
                a = new a(context);
            }
            aVar = a;
        }
        return aVar;
    }

    public String a() {
        return com.alipay.b.a.a.a(this.c);
    }

    public void a(int i, Map<String, String> map, a aVar) {
        String a = com.alipay.e.a.a.b.a.a(map, com.alipay.sdk.b.b.g, "");
        String a2 = com.alipay.e.a.a.b.a.a(map, com.alipay.sdk.b.b.c, "");
        String a3 = com.alipay.e.a.a.b.a.a(map, "userId", "");
        switch (i) {
            case 1:
                com.alipay.e.a.a.a.a.a.a("http://mobilegw.stable.alipay.net/mgw.htm");
                break;
            case 2:
                com.alipay.e.a.a.a.a.a.a("https://mobilegw.alipay.com/mgw.htm");
                break;
            case 3:
                com.alipay.e.a.a.a.a.a.a("http://mobilegw-1-64.test.alipay.net/mgw.htm");
                break;
            default:
                com.alipay.e.a.a.a.a.a.a("https://mobilegw.alipay.com/mgw.htm");
                break;
        }
        this.f.addLast(new b(this, i, a, a2, a3, aVar));
        if (this.e == null) {
            this.e = new Thread(new b(this));
            this.e.setUncaughtExceptionHandler(new c(this));
            this.e.start();
        }
    }

    public c b() {
        c cVar = new c(this);
        try {
            long currentTimeMillis = System.currentTimeMillis();
            cVar.b = com.alipay.b.a.a.a(this.c);
            com.alipay.e.a.a.b.b.a("getLocalApdidToken spend " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
            cVar.c = com.alipay.b.a.a.b(this.c);
            com.alipay.b.d.a.a(this.c);
            cVar.a = com.alipay.b.d.a.a();
            cVar.d = com.alipay.b.f.b.a(this.c);
        } catch (Throwable th) {
        }
        return cVar;
    }
}

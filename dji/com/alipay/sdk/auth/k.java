package com.alipay.sdk.auth;

import android.app.Activity;

final class k implements Runnable {
    final /* synthetic */ Activity a;
    final /* synthetic */ StringBuilder b;
    final /* synthetic */ a c;

    public final void run() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
	at java.util.HashMap$KeyIterator.next(Unknown Source)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:80)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r5 = this;
        r0 = 0;
        r2 = new com.alipay.sdk.f.a.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = 0;
        r3 = r5.a;	 Catch:{ Throwable -> 0x011e }
        r4 = r5.b;	 Catch:{ Throwable -> 0x011e }
        r4 = r4.toString();	 Catch:{ Throwable -> 0x011e }
        r1 = r2.a(r3, r4);	 Catch:{ Throwable -> 0x011e }
    L_0x0013:
        r2 = com.alipay.sdk.auth.j.c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r2 == 0) goto L_0x0023;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0019:
        r2 = com.alipay.sdk.auth.j.c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2.b();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.c = null;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0023:
        if (r1 != 0) goto L_0x0058;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0025:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.d();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "?resultCode=202";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r5.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = com.alipay.sdk.auth.j.d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.a(r0, r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.j.c;
        if (r0 == 0) goto L_0x0057;
    L_0x0050:
        r0 = com.alipay.sdk.auth.j.c;
        r0.b();
    L_0x0057:
        return;
    L_0x0058:
        r1 = r1.a();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = "form";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.optJSONObject(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = "onload";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.optJSONObject(r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.g.b.a(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x006d:
        r0 = r2.size();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r1 >= r0) goto L_0x008d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0073:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = (com.alipay.sdk.g.b) r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r3 = com.alipay.sdk.g.a.WapPay;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r0 != r3) goto L_0x00ca;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x007f:
        r0 = r2.get(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = (com.alipay.sdk.g.b) r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.b;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = 0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0[r1];	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x008d:
        r0 = com.alipay.sdk.auth.j.d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        if (r0 == 0) goto L_0x00ce;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
    L_0x0097:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r1.d();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "?resultCode=202";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.d = r0;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = r5.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = com.alipay.sdk.auth.j.d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        com.alipay.sdk.auth.j.a(r0, r1);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.j.c;
        if (r0 == 0) goto L_0x0057;
    L_0x00c2:
        r0 = com.alipay.sdk.auth.j.c;
        r0.b();
        goto L_0x0057;
    L_0x00ca:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x006d;
    L_0x00ce:
        r0 = new android.content.Intent;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.auth.AuthActivity.class;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.<init>(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "params";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = com.alipay.sdk.auth.j.d;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = "redirectUri";	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = r5.c;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r2 = r2.d();	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0.putExtra(r1, r2);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1 = r5.a;	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r1.startActivity(r0);	 Catch:{ Exception -> 0x00ff, all -> 0x010f }
        r0 = com.alipay.sdk.auth.j.c;
        if (r0 == 0) goto L_0x0057;
    L_0x00f6:
        r0 = com.alipay.sdk.auth.j.c;
        r0.b();
        goto L_0x0057;
    L_0x00ff:
        r0 = move-exception;
        r0 = com.alipay.sdk.auth.j.c;
        if (r0 == 0) goto L_0x0057;
    L_0x0106:
        r0 = com.alipay.sdk.auth.j.c;
        r0.b();
        goto L_0x0057;
    L_0x010f:
        r0 = move-exception;
        r1 = com.alipay.sdk.auth.j.c;
        if (r1 == 0) goto L_0x011d;
    L_0x0116:
        r1 = com.alipay.sdk.auth.j.c;
        r1.b();
    L_0x011d:
        throw r0;
    L_0x011e:
        r2 = move-exception;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.auth.k.run():void");
    }

    k(Activity activity, StringBuilder stringBuilder, a aVar) {
        this.a = activity;
        this.b = stringBuilder;
        this.c = aVar;
    }
}

package com.alipay.sdk.j;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.a.c;

public class f {
    public static final String b = "failed";
    public Activity a;
    private IAlixPay c;
    private final Object d = IAlixPay.class;
    private boolean e;
    private a f;
    private ServiceConnection g = new g(this);
    private IRemoteServiceCallback h = new h(this);

    public interface a {
        void a();
    }

    private java.lang.String a(java.lang.String r12, android.content.Intent r13) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x008a in list [B:51:0x00d7]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
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
        r11 = this;
        r2 = 1;
        r3 = 0;
        r10 = 0;
        r0 = r11.a;
        r5 = com.alipay.sdk.j.l.f(r0);
        r0 = r11.a;	 Catch:{ Throwable -> 0x0080 }
        r0 = r0.getApplicationContext();	 Catch:{ Throwable -> 0x0080 }
        r1 = r11.g;	 Catch:{ Throwable -> 0x0080 }
        r4 = 1;	 Catch:{ Throwable -> 0x0080 }
        r0.bindService(r13, r1, r4);	 Catch:{ Throwable -> 0x0080 }
        r1 = r11.d;
        monitor-enter(r1);
        r0 = r11.c;
        if (r0 != 0) goto L_0x002a;
    L_0x001c:
        r0 = r11.d;	 Catch:{ InterruptedException -> 0x0192 }
        r4 = com.alipay.sdk.c.a.b();	 Catch:{ InterruptedException -> 0x0192 }
        r4 = r4.a();	 Catch:{ InterruptedException -> 0x0192 }
        r6 = (long) r4;	 Catch:{ InterruptedException -> 0x0192 }
        r0.wait(r6);	 Catch:{ InterruptedException -> 0x0192 }
    L_0x002a:
        monitor-exit(r1);
        r0 = r11.c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r0 != 0) goto L_0x0127;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x002f:
        r0 = r11.a;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r6 = com.alipay.sdk.j.l.f(r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r11.a;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r0.getPackageManager();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = 0;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r7 = r0.getInstalledPackages(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r8 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r8.<init>();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = r3;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x0046:
        r0 = r7.size();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r4 >= r0) goto L_0x00df;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x004c:
        r0 = r7.get(r4);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = (android.content.pm.PackageInfo) r0;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r0.applicationInfo;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r1.flags;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r9 = r1 & 1;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r9 != 0) goto L_0x008e;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x005a:
        r1 = r1 & 128;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r1 != 0) goto L_0x008e;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x005e:
        r1 = r2;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x005f:
        if (r1 == 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x0061:
        r1 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r9 = "com.eg.android.AlipayGphone";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r1.equals(r9);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r1 == 0) goto L_0x0090;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x006b:
        r1 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r8.append(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r0.versionCode;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r1.append(r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = "-";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0.append(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x007c:
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x0046;
    L_0x0080:
        r0 = move-exception;
        r1 = "biz";
        r2 = "ClientBindServiceFailed";
        com.alipay.sdk.app.a.a.a(r1, r2, r0);
        r0 = "failed";
    L_0x008a:
        return r0;
    L_0x008b:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x008e:
        r1 = r3;
        goto L_0x005f;
    L_0x0090:
        r1 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r9 = "theme";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r1.contains(r9);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r1 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x009a:
        r1 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r9 = "com.google.";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r1.startsWith(r9);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r1 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x00a4:
        r1 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r9 = "com.android.";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r1.startsWith(r9);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r1 != 0) goto L_0x007c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x00ae:
        r0 = r0.packageName;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r8.append(r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = "-";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0.append(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        goto L_0x007c;
    L_0x00ba:
        r0 = move-exception;
        r1 = "biz";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r2 = "ClientBindException";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        com.alipay.sdk.app.a.a.a(r1, r2, r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = com.alipay.sdk.app.h.a();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r11.a;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r2 = r11.g;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1.unbindService(r2);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x00cd:
        r11.h = r10;
        r11.g = r10;
        r11.c = r10;
        r1 = r11.e;
        if (r1 == 0) goto L_0x008a;
    L_0x00d7:
        r1 = r11.a;
        r1.setRequestedOrientation(r3);
        r11.e = r3;
        goto L_0x008a;
    L_0x00df:
        r0 = r8.toString();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = "biz";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r2 = "ClientBindFailed";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4.<init>();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r5 = "|";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = r4.append(r6);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r5 = "|";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r4 = r4.append(r5);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r4.append(r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        com.alipay.sdk.app.a.a.a(r1, r2, r0);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = "failed";	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r11.a;	 Catch:{ Throwable -> 0x0190 }
        r2 = r11.g;	 Catch:{ Throwable -> 0x0190 }
        r1.unbindService(r2);	 Catch:{ Throwable -> 0x0190 }
    L_0x0114:
        r11.h = r10;
        r11.g = r10;
        r11.c = r10;
        r1 = r11.e;
        if (r1 == 0) goto L_0x008a;
    L_0x011e:
        r1 = r11.a;
        r1.setRequestedOrientation(r3);
        r11.e = r3;
        goto L_0x008a;
    L_0x0127:
        r0 = r11.f;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r0 == 0) goto L_0x0130;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x012b:
        r0 = r11.f;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0.a();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x0130:
        r0 = r11.a;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r0.getRequestedOrientation();	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        if (r0 != 0) goto L_0x0141;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x0138:
        r0 = r11.a;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = 1;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0.setRequestedOrientation(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = 1;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r11.e = r0;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
    L_0x0141:
        r0 = r11.c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r11.h;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0.registerCallback(r1);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r11.c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r0 = r0.Pay(r12);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r11.c;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r2 = r11.h;	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1.unregisterCallback(r2);	 Catch:{ Throwable -> 0x00ba, all -> 0x016f, Throwable -> 0x018b }
        r1 = r11.a;	 Catch:{ Throwable -> 0x018e }
        r2 = r11.g;	 Catch:{ Throwable -> 0x018e }
        r1.unbindService(r2);	 Catch:{ Throwable -> 0x018e }
    L_0x015c:
        r11.h = r10;
        r11.g = r10;
        r11.c = r10;
        r1 = r11.e;
        if (r1 == 0) goto L_0x008a;
    L_0x0166:
        r1 = r11.a;
        r1.setRequestedOrientation(r3);
        r11.e = r3;
        goto L_0x008a;
    L_0x016f:
        r0 = move-exception;
        r1 = r11.a;	 Catch:{ Throwable -> 0x0189 }
        r2 = r11.g;	 Catch:{ Throwable -> 0x0189 }
        r1.unbindService(r2);	 Catch:{ Throwable -> 0x0189 }
    L_0x0177:
        r11.h = r10;
        r11.g = r10;
        r11.c = r10;
        r1 = r11.e;
        if (r1 == 0) goto L_0x0188;
    L_0x0181:
        r1 = r11.a;
        r1.setRequestedOrientation(r3);
        r11.e = r3;
    L_0x0188:
        throw r0;
    L_0x0189:
        r1 = move-exception;
        goto L_0x0177;
    L_0x018b:
        r1 = move-exception;
        goto L_0x00cd;
    L_0x018e:
        r1 = move-exception;
        goto L_0x015c;
    L_0x0190:
        r1 = move-exception;
        goto L_0x0114;
    L_0x0192:
        r0 = move-exception;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.j.f.a(java.lang.String, android.content.Intent):java.lang.String");
    }

    public f(Activity activity, a aVar) {
        this.a = activity;
        this.f = aVar;
    }

    public final String a(String str) {
        Intent intent;
        try {
            com.alipay.sdk.j.l.a a = l.a(this.a, l.b);
            if (a != null) {
                String a2 = l.a(a.a);
                if (!(a2 == null || TextUtils.equals(a2, com.alipay.sdk.b.a.g))) {
                    com.alipay.sdk.app.a.a.a(c.b, c.i, a2);
                    return b;
                }
            }
            if (a.b > 78) {
                intent = new Intent();
                intent.setClassName(l.b, "com.alipay.android.app.TransProcessPayActivity");
                this.a.startActivity(intent);
                Thread.sleep(150);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        intent = new Intent();
        intent.setPackage(l.b);
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        return a(str, intent);
    }

    private void a() {
        this.a = null;
    }
}

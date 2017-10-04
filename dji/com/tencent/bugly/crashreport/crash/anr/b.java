package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.FileObserver;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.android.tpush.common.Constants;
import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import dji.pilot.dji_groundstation.controller.e;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class b implements com.tencent.bugly.crashreport.common.strategy.b {
    private final int a = 0;
    private final int b = 1;
    private final int c = 2;
    private final int d = 3;
    private AtomicInteger e = new AtomicInteger(0);
    private long f = -1;
    private final Context g;
    private final a h;
    private final y i;
    private final c j;
    private final String k;
    private final q l;
    private final com.tencent.bugly.crashreport.crash.b m;
    private final CrashHandleCallback n;
    private FileObserver o;
    private boolean p = true;

    public b(Context context, c cVar, a aVar, y yVar, q qVar, com.tencent.bugly.crashreport.crash.b bVar, CrashHandleCallback crashHandleCallback) {
        this.g = ag.a(context);
        this.k = context.getDir("bugly", 0).getAbsolutePath();
        this.h = aVar;
        this.i = yVar;
        this.j = cVar;
        this.l = qVar;
        this.m = bVar;
        this.n = crashHandleCallback;
    }

    protected void a(a aVar) {
    }

    protected void a(CrashDetailBean crashDetailBean) {
    }

    protected ProcessErrorStateInfo a(Context context, long j) {
        if (j < 0) {
            j = 0;
        }
        z.c("to find!", new Object[0]);
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Constants.FLAG_ACTIVITY_NAME);
        long j2 = j / 500;
        int i = 0;
        while (true) {
            z.c("waiting!", new Object[0]);
            List<ProcessErrorStateInfo> processesInErrorState = activityManager.getProcessesInErrorState();
            if (processesInErrorState != null) {
                for (ProcessErrorStateInfo processErrorStateInfo : processesInErrorState) {
                    if (processErrorStateInfo.condition == 2) {
                        z.c("found!", new Object[0]);
                        return processErrorStateInfo;
                    }
                }
            }
            ag.a(500);
            int i2 = i + 1;
            if (((long) i) >= j2) {
                z.c("end!", new Object[0]);
                return null;
            }
            i = i2;
        }
    }

    protected a a(Context context, ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        File file = new File(context.getFilesDir(), "bugly/bugly_trace_" + j + ".txt");
        a aVar = new a();
        aVar.c = j;
        aVar.d = file.getAbsolutePath();
        aVar.a = processErrorStateInfo.processName;
        aVar.f = processErrorStateInfo.shortMsg;
        aVar.e = processErrorStateInfo.longMsg;
        aVar.b = map;
        if (map != null) {
            for (String str : map.keySet()) {
                if (str.startsWith("main(")) {
                    aVar.g = (String) map.get(str);
                }
            }
        }
        String str2 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        Object[] objArr = new Object[6];
        objArr[0] = Long.valueOf(aVar.c);
        objArr[1] = aVar.d;
        objArr[2] = aVar.a;
        objArr[3] = aVar.f;
        objArr[4] = aVar.e;
        objArr[5] = Integer.valueOf(aVar.b == null ? 0 : aVar.b.size());
        z.c(str2, objArr);
        return aVar;
    }

    protected CrashDetailBean b(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.i();
        crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
        crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.k();
        crashDetailBean.E = this.h.u();
        crashDetailBean.F = this.h.t();
        crashDetailBean.G = this.h.v();
        crashDetailBean.w = d.a(this.g, 20000);
        crashDetailBean.x = ab.a(true);
        crashDetailBean.b = 3;
        crashDetailBean.e = this.h.n();
        crashDetailBean.f = this.h.e();
        crashDetailBean.g = this.h.A();
        crashDetailBean.m = this.h.m();
        crashDetailBean.n = "ANR_EXCEPTION";
        crashDetailBean.o = aVar.f;
        crashDetailBean.q = aVar.g;
        crashDetailBean.O = new HashMap();
        crashDetailBean.O.put("BUGLY_CR_01", aVar.e);
        int indexOf = crashDetailBean.q.indexOf("\n");
        crashDetailBean.p = indexOf > 0 ? crashDetailBean.q.substring(0, indexOf) : "GET_FAIL";
        crashDetailBean.r = aVar.c;
        crashDetailBean.u = ag.a(crashDetailBean.q.getBytes());
        crashDetailBean.y = aVar.b;
        crashDetailBean.z = this.h.E();
        crashDetailBean.A = e.j;
        crashDetailBean.H = this.h.D();
        crashDetailBean.h = this.h.z();
        crashDetailBean.v = aVar.d;
        crashDetailBean.L = this.h.G();
        crashDetailBean.M = this.h.b();
        crashDetailBean.P = this.h.N();
        crashDetailBean.Q = this.h.O();
        crashDetailBean.R = this.h.J();
        crashDetailBean.S = this.h.M();
        return crashDetailBean;
    }

    protected boolean a(String str, String str2, String str3) {
        BufferedWriter bufferedWriter;
        Throwable e;
        c.a a = c.a(str3, str, true);
        if (a == null || a.d == null || a.d.size() <= 0) {
            z.e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (file.exists() && file.canWrite()) {
                BufferedWriter bufferedWriter2 = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = (String[]) a.d.get(e.j);
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                            bufferedWriter.flush();
                        }
                        for (Entry entry : a.d.entrySet()) {
                            if (!(((String) entry.getKey()).equals(e.j) || entry.getValue() == null || ((String[]) entry.getValue()).length < 3)) {
                                String str5 = ((String[]) entry.getValue())[0];
                                bufferedWriter.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                                bufferedWriter.flush();
                            }
                        }
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (Throwable e2) {
                                if (!z.a(e2)) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e2 = e3;
                        bufferedWriter2 = bufferedWriter;
                        try {
                            if (!z.a(e2)) {
                                e2.printStackTrace();
                            }
                            z.e("dump trace fail %s", e2.getClass().getName() + ":" + e2.getMessage());
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.close();
                                } catch (Throwable e22) {
                                    if (!z.a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            e22 = th;
                            bufferedWriter = bufferedWriter2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Throwable e4) {
                                    if (!z.a(e4)) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    if (z.a(e22)) {
                        e22.printStackTrace();
                    }
                    z.e("dump trace fail %s", e22.getClass().getName() + ":" + e22.getMessage());
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    e22 = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw e22;
                }
            }
            z.e("backup file create fail %s", str2);
            return false;
        } catch (Throwable e222) {
            if (!z.a(e222)) {
                e222.printStackTrace();
            }
            z.e("backup file create error! %s  %s", e222.getClass().getName() + ":" + e222.getMessage(), str2);
            return false;
        }
    }

    public boolean a() {
        return this.e.get() != 0;
    }

    protected boolean a(Context context, String str, ProcessErrorStateInfo processErrorStateInfo, long j, Map<String, String> map) {
        this.j.d();
        if (!this.j.b()) {
            z.e("waiting for remote sync", new Object[0]);
            int i = 0;
            while (!this.j.b()) {
                ag.a(500);
                i += 500;
                if (i >= 5000) {
                    break;
                }
            }
        }
        a a = a(context, processErrorStateInfo, j, map);
        if (!this.j.b()) {
            z.e("crash report sync remote fail, will not upload to Bugly , print local for helpful!", new Object[0]);
            a(a);
            this.j.a("noRemoteStrategy_dropANR", false);
            return false;
        } else if (this.j.d().g) {
            z.a("found visiable anr , start to upload!", new Object[0]);
            CrashDetailBean b = b(a);
            if (b == null) {
                z.e("pack anr fail!", new Object[0]);
                return false;
            }
            this.l.b(b);
            if (b.a >= 0) {
                z.a("backup anr record success!", new Object[0]);
            } else {
                z.d("backup anr record fail!", new Object[0]);
            }
            if (str != null && new File(str).exists()) {
                this.e.set(3);
                if (a(str, a.d, a.a)) {
                    z.a("backup trace success", new Object[0]);
                }
            }
            a(b);
            if (!this.m.a(b)) {
                this.m.a(b, (long) OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            }
            if (this.n != null) {
                try {
                    Map onCrashHandleStart = this.n.onCrashHandleStart(4, b.n, b.o, b.q);
                    if (onCrashHandleStart != null && onCrashHandleStart.size() > 0) {
                        z.d("anr will not attach user data size:%d", Integer.valueOf(onCrashHandleStart.size()));
                    }
                } catch (Throwable th) {
                    if (!z.a(th)) {
                        th.printStackTrace();
                    }
                }
            }
            return true;
        } else {
            z.d("ANR Report is closed!", new Object[0]);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = -1;
        r7 = 0;
        monitor-enter(r10);
        r2 = r10.e;	 Catch:{ all -> 0x0068 }
        r2 = r2.get();	 Catch:{ all -> 0x0068 }
        if (r2 == 0) goto L_0x0018;
    L_0x000e:
        r0 = "trace started return ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0068 }
        com.tencent.bugly.proguard.z.c(r0, r1);	 Catch:{ all -> 0x0068 }
        monitor-exit(r10);	 Catch:{ all -> 0x0068 }
    L_0x0017:
        return;
    L_0x0018:
        r2 = r10.e;	 Catch:{ all -> 0x0068 }
        r3 = 1;
        r2.set(r3);	 Catch:{ all -> 0x0068 }
        monitor-exit(r10);	 Catch:{ all -> 0x0068 }
        r2 = "read trace first dump for create time!";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.c(r2, r3);	 Catch:{ Throwable -> 0x00ee }
        r2 = 0;
        r2 = com.tencent.bugly.crashreport.crash.anr.c.a(r11, r2);	 Catch:{ Throwable -> 0x00ee }
        if (r2 == 0) goto L_0x0119;
    L_0x002e:
        r4 = r2.c;	 Catch:{ Throwable -> 0x00ee }
    L_0x0030:
        r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r0 != 0) goto L_0x0045;
    L_0x0034:
        r0 = "trace dump fail could not get time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.d(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = new java.util.Date;	 Catch:{ Throwable -> 0x00ee }
        r0.<init>();	 Catch:{ Throwable -> 0x00ee }
        r4 = r0.getTime();	 Catch:{ Throwable -> 0x00ee }
    L_0x0045:
        r0 = r10.f;	 Catch:{ Throwable -> 0x00ee }
        r0 = r4 - r0;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Throwable -> 0x00ee }
        r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r0 >= 0) goto L_0x006b;
    L_0x0051:
        r0 = "should not process ANR too Fre in %d";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        r2 = 0;
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x00ee }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.d(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x0068:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0068 }
        throw r0;
    L_0x006b:
        r10.f = r4;	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;	 Catch:{ Throwable -> 0x00ee }
        r1 = 1;
        r0.set(r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = 20000; // 0x4e20 float:2.8026E-41 double:9.8813E-320;
        r1 = 0;
        r6 = com.tencent.bugly.crashreport.crash.d.a(r0, r1);	 Catch:{ Throwable -> 0x0090 }
        if (r6 == 0) goto L_0x0082;
    L_0x007c:
        r0 = r6.size();	 Catch:{ Throwable -> 0x00ee }
        if (r0 > 0) goto L_0x00a3;
    L_0x0082:
        r0 = "can't get all thread skip this anr";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.d(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x0090:
        r0 = move-exception;
        com.tencent.bugly.proguard.z.a(r0);	 Catch:{ Throwable -> 0x00ee }
        r0 = "get all thread stack fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.e(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x00a3:
        r0 = r10.g;	 Catch:{ Throwable -> 0x00ee }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = r10.a(r0, r2);	 Catch:{ Throwable -> 0x00ee }
        if (r3 != 0) goto L_0x00bc;
    L_0x00ad:
        r0 = "proc state is unvisiable!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.c(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x00bc:
        r0 = r3.pid;	 Catch:{ Throwable -> 0x00ee }
        r1 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x00ee }
        if (r0 == r1) goto L_0x00d8;
    L_0x00c4:
        r0 = "not mind proc!";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        r2 = 0;
        r3 = r3.processName;	 Catch:{ Throwable -> 0x00ee }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.c(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x00d8:
        r0 = "found visiable anr , start to process!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x00ee }
        com.tencent.bugly.proguard.z.a(r0, r1);	 Catch:{ Throwable -> 0x00ee }
        r1 = r10.g;	 Catch:{ Throwable -> 0x00ee }
        r0 = r10;
        r2 = r11;
        r0.a(r1, r2, r3, r4, r6);	 Catch:{ Throwable -> 0x00ee }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x00ee:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.z.a(r0);	 Catch:{ all -> 0x0112 }
        if (r1 != 0) goto L_0x00f8;
    L_0x00f5:
        r0.printStackTrace();	 Catch:{ all -> 0x0112 }
    L_0x00f8:
        r1 = "handle anr error %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0112 }
        r3 = 0;
        r0 = r0.getClass();	 Catch:{ all -> 0x0112 }
        r0 = r0.toString();	 Catch:{ all -> 0x0112 }
        r2[r3] = r0;	 Catch:{ all -> 0x0112 }
        com.tencent.bugly.proguard.z.e(r1, r2);	 Catch:{ all -> 0x0112 }
        r0 = r10.e;
        r0.set(r7);
        goto L_0x0017;
    L_0x0112:
        r0 = move-exception;
        r1 = r10.e;
        r1.set(r7);
        throw r0;
    L_0x0119:
        r4 = r0;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    protected synchronized void b() {
        if (d()) {
            z.d("start when started!", new Object[0]);
        } else {
            this.o = new FileObserver(this, "/data/anr/", 8) {
                final /* synthetic */ b a;

                public void onEvent(int i, String str) {
                    if (str != null) {
                        String str2 = "/data/anr/" + str;
                        if (str2.contains("trace")) {
                            this.a.a(str2);
                            return;
                        }
                        z.d("not anr file %s", str2);
                    }
                }
            };
            try {
                this.o.startWatching();
                z.a("start anr monitor!", new Object[0]);
                this.i.b(new Runnable(this) {
                    final /* synthetic */ b a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.f();
                    }
                });
            } catch (Throwable th) {
                this.o = null;
                z.d("start anr monitor failed!", new Object[0]);
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    protected synchronized void c() {
        if (d()) {
            try {
                this.o.stopWatching();
                this.o = null;
                z.d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                z.d("stop anr monitor failed!", new Object[0]);
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        } else {
            z.d("close when closed!", new Object[0]);
        }
    }

    protected synchronized boolean d() {
        return this.o != null;
    }

    protected synchronized void a(boolean z) {
        if (z) {
            b();
        } else {
            c();
        }
    }

    public synchronized boolean e() {
        return this.p;
    }

    private synchronized void c(boolean z) {
        if (this.p != z) {
            z.a("user change anr %b", Boolean.valueOf(z));
            this.p = z;
        }
    }

    public void b(boolean z) {
        c(z);
        boolean z2 = c.a().d().g && e();
        if (z2 != d()) {
            z.a("anr changed to %b", Boolean.valueOf(z2));
            a(z2);
        }
    }

    protected void f() {
        long b = ag.b() - 604800000;
        File file = new File(this.k);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            z.e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                z.c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    public synchronized void a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.g != d()) {
                    z.d("server anr changed to %b", Boolean.valueOf(strategyBean.g));
                }
            }
            if (!(strategyBean.g && e())) {
                z = false;
            }
            if (z != d()) {
                z.a("anr changed to %b", Boolean.valueOf(z));
                a(z);
            }
        }
    }

    public void g() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.tencent.bugly.anr.testor");
            this.g.registerReceiver(new BuglyTestANR_Reciver(), intentFilter);
            this.g.sendBroadcast(new Intent("com.tencent.bugly.anr.testor"));
            z.a("try to make a test ANR", new Object[0]);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
    }
}

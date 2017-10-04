package com.flurry.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import com.flurry.android.FlurryEventRecordStatus;
import com.flurry.sdk.jr.a;
import com.tencent.android.tpush.common.Constants;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class hk implements a {
    static int a = 100;
    static int b = 10;
    static int c = 1000;
    static int d = 160000;
    static int e = 50;
    private static final String f = hk.class.getSimpleName();
    private final List<hd> A = new ArrayList();
    private int B = 0;
    private int C = 0;
    private final gh D = new gh();
    private final ii<ho> E = new ii<ho>(this) {
        final /* synthetic */ hk a;

        {
            this.a = r1;
        }

        public void a(ho hoVar) {
            hz.a().b(new kb(this) {
                final /* synthetic */ AnonymousClass8 a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a.a(true, hm.a().d());
                }
            });
        }
    };
    private final AtomicInteger g = new AtomicInteger(0);
    private final AtomicInteger h = new AtomicInteger(0);
    private final AtomicInteger i = new AtomicInteger(0);
    private final ii<jm> j = new ii<jm>(this) {
        final /* synthetic */ hk a;

        {
            this.a = r1;
        }

        public void a(jm jmVar) {
            if (this.a.k == null || jmVar.b == this.a.k.get()) {
                switch (jmVar.c) {
                    case CREATE:
                        this.a.a(jmVar.b, (Context) jmVar.a.get());
                        return;
                    case START:
                        this.a.a((Context) jmVar.a.get());
                        return;
                    case END:
                        this.a.b((Context) jmVar.a.get());
                        return;
                    case FINALIZE:
                        ij.a().b("com.flurry.android.sdk.FlurrySessionEvent", this.a.j);
                        this.a.a(jmVar.d);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    private WeakReference<jl> k;
    private File l;
    private ig<List<hi>> m;
    private boolean n;
    private long o;
    private boolean p;
    private String q;
    private byte r;
    private Long s;
    private final List<hi> t = new ArrayList();
    private final Map<String, List<String>> u = new HashMap();
    private final Map<String, String> v = new HashMap();
    private final Map<String, he> w = new HashMap();
    private final List<hf> x = new ArrayList();
    private boolean y = true;
    private int z = 0;

    public hk() {
        ij.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.j);
    }

    public void a(jl jlVar, Context context) {
        this.k = new WeakReference(jlVar);
        jr a = jq.a();
        this.p = ((Boolean) a.a("LogEvents")).booleanValue();
        a.a("LogEvents", (a) this);
        in.a(4, f, "initSettings, LogEvents = " + this.p);
        this.q = (String) a.a("UserId");
        a.a("UserId", (a) this);
        in.a(4, f, "initSettings, UserId = " + this.q);
        this.r = ((Byte) a.a("Gender")).byteValue();
        a.a("Gender", (a) this);
        in.a(4, f, "initSettings, Gender = " + this.r);
        this.s = (Long) a.a("Age");
        a.a("Age", (a) this);
        in.a(4, f, "initSettings, BirthDate = " + this.s);
        this.l = context.getFileStreamPath(k());
        this.m = new ig(context.getFileStreamPath(l()), ".yflurryreport.", 1, new jk<List<hi>>(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public jh<List<hi>> a(int i) {
                return new jg(new hi.a());
            }
        });
        c(context);
        a(true);
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                gg.a().c().c();
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.i();
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.m();
            }
        });
        if (hn.a().c()) {
            hz.a().b(new kb(this) {
                final /* synthetic */ hk a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.a(true, hm.a().d());
                }
            });
        } else {
            ij.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.E);
        }
    }

    public synchronized void a(Context context) {
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                gg.a().e().e();
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                gg.a().c().c();
            }
        });
    }

    public synchronized void b(Context context) {
        a(false);
        final long d = hm.a().d();
        final long f = hm.a().f();
        final long h = hm.a().h();
        final int a = hm.a().i().a();
        b(hm.a().f());
        hz.a().b(new kb(this) {
            final /* synthetic */ hk b;

            public void a() {
                gg.a().c().a(d);
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.n();
            }
        });
        if (hn.a().c()) {
            hz.a().b(new kb(this) {
                final /* synthetic */ hk e;

                public void a() {
                    hi a = this.e.a(d, f, h, a);
                    this.e.t.clear();
                    this.e.t.add(a);
                    this.e.c();
                }
            });
        }
    }

    public synchronized void a(final long j) {
        ij.a().a(this.E);
        hz.a().b(new kb(this) {
            final /* synthetic */ hk a;

            {
                this.a = r1;
            }

            public void a() {
                gg.a().c().d();
                hz.a().b(new kb(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        gg.a().e().d();
                    }
                });
            }
        });
        if (hn.a().c()) {
            hz.a().b(new kb(this) {
                final /* synthetic */ hk b;

                public void a() {
                    this.b.a(false, j);
                }
            });
        }
        jq.a().b("Gender", (a) this);
        jq.a().b("UserId", (a) this);
        jq.a().b("Age", (a) this);
        jq.a().b("LogEvents", (a) this);
    }

    public void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -1752163738:
                if (str.equals("UserId")) {
                    obj2 = 1;
                    break;
                }
                break;
            case -738063011:
                if (str.equals("LogEvents")) {
                    obj2 = null;
                    break;
                }
                break;
            case 65759:
                if (str.equals("Age")) {
                    obj2 = 3;
                    break;
                }
                break;
            case 2129321697:
                if (str.equals("Gender")) {
                    obj2 = 2;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.p = ((Boolean) obj).booleanValue();
                in.a(4, f, "onSettingUpdate, LogEvents = " + this.p);
                return;
            case 1:
                this.q = (String) obj;
                in.a(4, f, "onSettingUpdate, UserId = " + this.q);
                return;
            case 2:
                this.r = ((Byte) obj).byteValue();
                in.a(4, f, "onSettingUpdate, Gender = " + this.r);
                return;
            case 3:
                this.s = (Long) obj;
                in.a(4, f, "onSettingUpdate, Birthdate = " + this.s);
                return;
            default:
                in.a(6, f, "onSettingUpdate internal error!");
                return;
        }
    }

    public void a() {
        this.n = true;
    }

    private void c(Context context) {
        if (context instanceof Activity) {
            Bundle extras = ((Activity) context).getIntent().getExtras();
            if (extras != null) {
                in.a(3, f, "Launch Options Bundle is present " + extras.toString());
                for (String str : extras.keySet()) {
                    if (str != null) {
                        Object obj = extras.get(str);
                        this.u.put(str, new ArrayList(Arrays.asList(new String[]{obj != null ? obj.toString() : "null"})));
                        in.a(3, f, "Launch options Key: " + str + ". Its value: " + r1);
                    }
                }
            }
        }
    }

    @TargetApi(18)
    private void a(boolean z) {
        boolean z2;
        int intExtra;
        int i = -1;
        if (z) {
            this.v.put("boot.time", Long.toString(System.currentTimeMillis() - SystemClock.elapsedRealtime()));
            StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
            StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            if (VERSION.SDK_INT >= 18) {
                this.v.put("disk.size.total.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.v.put("disk.size.available.internal", Long.toString(statFs.getAvailableBlocksLong()));
                this.v.put("disk.size.total.external", Long.toString(statFs2.getAvailableBlocksLong()));
                this.v.put("disk.size.available.external", Long.toString(statFs2.getAvailableBlocksLong()));
            } else {
                this.v.put("disk.size.total.internal", Long.toString((long) statFs.getAvailableBlocks()));
                this.v.put("disk.size.available.internal", Long.toString((long) statFs.getAvailableBlocks()));
                this.v.put("disk.size.total.external", Long.toString((long) statFs2.getAvailableBlocks()));
                this.v.put("disk.size.available.external", Long.toString((long) statFs2.getAvailableBlocks()));
            }
            this.v.put("carrier.name", hu.a().c());
            this.v.put("carrier.details", hu.a().d());
        }
        ActivityManager activityManager = (ActivityManager) hz.a().c().getSystemService(Constants.FLAG_ACTIVITY_NAME);
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        this.v.put("memory.available" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        if (VERSION.SDK_INT >= 16) {
            this.v.put("memory.total" + (z ? ".start" : ".end"), Long.toString(memoryInfo.availMem));
        }
        Intent registerReceiver = hz.a().c().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra2 = registerReceiver.getIntExtra("status", -1);
            z2 = intExtra2 == 2 || intExtra2 == 5;
            int intExtra3 = registerReceiver.getIntExtra("level", -1);
            intExtra = registerReceiver.getIntExtra("scale", -1);
            i = intExtra3;
        } else {
            z2 = false;
            intExtra = -1;
        }
        float f = ((float) i) / ((float) intExtra);
        this.v.put("battery.charging" + (z ? ".start" : ".end"), Boolean.toString(z2));
        this.v.put("battery.remaining" + (z ? ".start" : ".end"), Float.toString(f));
    }

    private synchronized void b(long j) {
        for (hf hfVar : this.x) {
            if (hfVar.a() && !hfVar.b()) {
                hfVar.a(j);
            }
        }
    }

    synchronized hi a(long j, long j2, long j3, int i) {
        hi hiVar;
        hj hjVar = new hj();
        hjVar.a(hw.a().e());
        hjVar.a(j);
        hjVar.b(j2);
        hjVar.c(j3);
        hjVar.a(this.v);
        hjVar.b(hq.a().c());
        hjVar.c(hq.a().d());
        hjVar.a(i);
        hjVar.b(jx.j());
        hjVar.d(d());
        hjVar.a(hr.a().e());
        hjVar.c(h());
        hjVar.a(this.r);
        hjVar.a(this.s);
        hjVar.b(g());
        hjVar.a(e());
        hjVar.a(this.y);
        hjVar.b(f());
        hjVar.d(this.B);
        try {
            hiVar = new hi(hjVar);
        } catch (IOException e) {
            in.a(5, f, "Error creating analytics session report: " + e);
            hiVar = null;
        }
        if (hiVar == null) {
            in.e(f, "New session report wasn't created");
        }
        return hiVar;
    }

    public synchronized void b() {
        this.C++;
    }

    public synchronized FlurryEventRecordStatus a(String str, Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus;
        FlurryEventRecordStatus flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventRecorded;
        long elapsedRealtime = SystemClock.elapsedRealtime() - hm.a().e();
        String b = jz.b(str);
        if (b.length() == 0) {
            flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        } else {
            he heVar = (he) this.w.get(b);
            if (heVar != null) {
                heVar.a++;
                in.e(f, "Event count incremented: " + b);
                flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
            } else if (this.w.size() < a) {
                heVar = new he();
                heVar.a = 1;
                this.w.put(b, heVar);
                in.e(f, "Event count started: " + b);
                flurryEventRecordStatus = flurryEventRecordStatus2;
            } else {
                in.e(f, "Too many different events. Event not counted: " + b);
                flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventUniqueCountExceeded;
            }
            if (!this.p || this.x.size() >= c || this.z >= d) {
                this.y = false;
            } else {
                Map emptyMap;
                if (map == null) {
                    emptyMap = Collections.emptyMap();
                } else {
                    Map<String, String> map2 = map;
                }
                if (emptyMap.size() > b) {
                    in.e(f, "MaxEventParams exceeded: " + emptyMap.size());
                    flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventParamsCountExceeded;
                } else {
                    hf hfVar = new hf(o(), b, emptyMap, elapsedRealtime, z);
                    if (hfVar.d() + this.z <= d) {
                        this.x.add(hfVar);
                        this.z = hfVar.d() + this.z;
                        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventRecorded;
                    } else {
                        this.z = d;
                        this.y = false;
                        in.e(f, "Event Log size exceeded. No more event details logged.");
                        flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventLogCountExceeded;
                    }
                }
            }
        }
        return flurryEventRecordStatus;
    }

    public synchronized void a(String str, Map<String, String> map) {
        for (hf hfVar : this.x) {
            if (hfVar.a(str)) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - hm.a().e();
                if (map != null && map.size() > 0 && this.z < d) {
                    int d = this.z - hfVar.d();
                    Map hashMap = new HashMap(hfVar.c());
                    hfVar.a((Map) map);
                    if (hfVar.d() + d > d) {
                        hfVar.b(hashMap);
                        this.y = false;
                        this.z = d;
                        in.e(f, "Event Log size exceeded. No more event details logged.");
                    } else if (hfVar.c().size() > b) {
                        in.e(f, "MaxEventParams exceeded on endEvent: " + hfVar.c().size());
                        hfVar.b(hashMap);
                    } else {
                        this.z = d + hfVar.d();
                    }
                }
                hfVar.a(elapsedRealtime);
            }
        }
    }

    public synchronized void a(String str, String str2, String str3, Throwable th) {
        Object obj;
        hd hdVar;
        int i;
        if (str != null) {
            if ("uncaught".equals(str)) {
                obj = 1;
                this.B++;
                if (this.A.size() < e) {
                    hdVar = new hd(p(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
                    this.A.add(hdVar);
                    in.e(f, "Error logged: " + hdVar.c());
                } else if (obj == null) {
                    for (i = 0; i < this.A.size(); i++) {
                        hdVar = (hd) this.A.get(i);
                        if (hdVar.c() == null && !"uncaught".equals(hdVar.c())) {
                            this.A.set(i, new hd(p(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th));
                            break;
                        }
                    }
                } else {
                    in.e(f, "Max errors logged. No more errors logged.");
                }
            }
        }
        obj = null;
        this.B++;
        if (this.A.size() < e) {
            hdVar = new hd(p(), Long.valueOf(System.currentTimeMillis()).longValue(), str, str2, str3, th);
            this.A.add(hdVar);
            in.e(f, "Error logged: " + hdVar.c());
        } else if (obj == null) {
            in.e(f, "Max errors logged. No more errors logged.");
        } else {
            while (i < this.A.size()) {
                hdVar = (hd) this.A.get(i);
                if (hdVar.c() == null) {
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r21, long r22) {
        /*
        r20 = this;
        monitor-enter(r20);
        if (r21 != 0) goto L_0x000f;
    L_0x0003:
        r0 = r20;
        r2 = r0.t;	 Catch:{ all -> 0x0076 }
        r2 = r2.isEmpty();	 Catch:{ all -> 0x0076 }
        if (r2 == 0) goto L_0x000f;
    L_0x000d:
        monitor-exit(r20);
        return;
    L_0x000f:
        r2 = 3;
        r3 = f;	 Catch:{ all -> 0x0076 }
        r4 = "generating agent report";
        com.flurry.sdk.in.a(r2, r3, r4);	 Catch:{ all -> 0x0076 }
        r19 = 0;
        r3 = new com.flurry.sdk.hg;	 Catch:{ Exception -> 0x0079 }
        r2 = com.flurry.sdk.hz.a();	 Catch:{ Exception -> 0x0079 }
        r4 = r2.d();	 Catch:{ Exception -> 0x0079 }
        r2 = com.flurry.sdk.hw.a();	 Catch:{ Exception -> 0x0079 }
        r5 = r2.e();	 Catch:{ Exception -> 0x0079 }
        r0 = r20;
        r6 = r0.n;	 Catch:{ Exception -> 0x0079 }
        r2 = com.flurry.sdk.hn.a();	 Catch:{ Exception -> 0x0079 }
        r7 = r2.e();	 Catch:{ Exception -> 0x0079 }
        r0 = r20;
        r8 = r0.o;	 Catch:{ Exception -> 0x0079 }
        r0 = r20;
        r12 = r0.t;	 Catch:{ Exception -> 0x0079 }
        r2 = com.flurry.sdk.hn.a();	 Catch:{ Exception -> 0x0079 }
        r13 = r2.h();	 Catch:{ Exception -> 0x0079 }
        r0 = r20;
        r2 = r0.D;	 Catch:{ Exception -> 0x0079 }
        r10 = 0;
        r14 = r2.a(r10);	 Catch:{ Exception -> 0x0079 }
        r0 = r20;
        r15 = r0.u;	 Catch:{ Exception -> 0x0079 }
        r2 = com.flurry.sdk.ib.a();	 Catch:{ Exception -> 0x0079 }
        r16 = r2.c();	 Catch:{ Exception -> 0x0079 }
        r17 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0079 }
        r10 = r22;
        r3.<init>(r4, r5, r6, r7, r8, r10, r12, r13, r14, r15, r16, r17);	 Catch:{ Exception -> 0x0079 }
        r2 = r3.a();	 Catch:{ Exception -> 0x0079 }
    L_0x0069:
        if (r2 != 0) goto L_0x0095;
    L_0x006b:
        r2 = f;	 Catch:{ all -> 0x0076 }
        r3 = "Error generating report";
        com.flurry.sdk.in.e(r2, r3);	 Catch:{ all -> 0x0076 }
    L_0x0072:
        r20.j();	 Catch:{ all -> 0x0076 }
        goto L_0x000d;
    L_0x0076:
        r2 = move-exception;
        monitor-exit(r20);
        throw r2;
    L_0x0079:
        r2 = move-exception;
        r3 = f;	 Catch:{ all -> 0x0076 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0076 }
        r4.<init>();	 Catch:{ all -> 0x0076 }
        r5 = "Exception while generating report: ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0076 }
        r2 = r4.append(r2);	 Catch:{ all -> 0x0076 }
        r2 = r2.toString();	 Catch:{ all -> 0x0076 }
        com.flurry.sdk.in.e(r3, r2);	 Catch:{ all -> 0x0076 }
        r2 = r19;
        goto L_0x0069;
    L_0x0095:
        r3 = 3;
        r4 = f;	 Catch:{ all -> 0x0076 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0076 }
        r5.<init>();	 Catch:{ all -> 0x0076 }
        r6 = "generated report of size ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0076 }
        r6 = r2.length;	 Catch:{ all -> 0x0076 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0076 }
        r6 = " with ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0076 }
        r0 = r20;
        r6 = r0.t;	 Catch:{ all -> 0x0076 }
        r6 = r6.size();	 Catch:{ all -> 0x0076 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0076 }
        r6 = " reports.";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0076 }
        r5 = r5.toString();	 Catch:{ all -> 0x0076 }
        com.flurry.sdk.in.a(r3, r4, r5);	 Catch:{ all -> 0x0076 }
        r3 = com.flurry.sdk.gg.a();	 Catch:{ all -> 0x0076 }
        r3 = r3.d();	 Catch:{ all -> 0x0076 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0076 }
        r4.<init>();	 Catch:{ all -> 0x0076 }
        r5 = "";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0076 }
        r5 = com.flurry.sdk.ia.a();	 Catch:{ all -> 0x0076 }
        r4 = r4.append(r5);	 Catch:{ all -> 0x0076 }
        r4 = r4.toString();	 Catch:{ all -> 0x0076 }
        r5 = com.flurry.sdk.hz.a();	 Catch:{ all -> 0x0076 }
        r5 = r5.d();	 Catch:{ all -> 0x0076 }
        r3.b(r2, r5, r4);	 Catch:{ all -> 0x0076 }
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.hk.a(boolean, long):void");
    }

    public synchronized void c() {
        in.a(4, f, "Saving persistent agent data.");
        this.m.a(this.t);
    }

    private synchronized void i() {
        in.a(4, f, "Loading persistent session report data.");
        List list = (List) this.m.a();
        if (list != null) {
            this.t.addAll(list);
        } else if (this.l.exists()) {
            in.a(4, f, "Legacy persistent agent data found, converting.");
            hl a = gj.a(this.l);
            if (a != null) {
                boolean a2 = a.a();
                long b = a.b();
                if (b <= 0) {
                    b = hm.a().d();
                }
                this.n = a2;
                this.o = b;
                n();
                Collection c = a.c();
                if (c != null) {
                    this.t.addAll(c);
                }
            }
            this.l.delete();
            c();
        }
    }

    private void j() {
        this.t.clear();
        this.m.b();
    }

    private String k() {
        return ".flurryagent." + Integer.toString(hz.a().d().hashCode(), 16);
    }

    private String l() {
        return ".yflurryreport." + Long.toString(jz.i(hz.a().d()), 16);
    }

    private void m() {
        SharedPreferences sharedPreferences = hz.a().c().getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0);
        this.n = sharedPreferences.getBoolean("com.flurry.sdk.previous_successful_report", false);
        this.o = sharedPreferences.getLong("com.flurry.sdk.initial_run_time", hm.a().d());
    }

    private void n() {
        Editor edit = hz.a().c().getSharedPreferences("FLURRY_SHARED_PREFERENCES", 0).edit();
        edit.putBoolean("com.flurry.sdk.previous_successful_report", this.n);
        edit.putLong("com.flurry.sdk.initial_run_time", this.o);
        edit.commit();
    }

    private int o() {
        return this.g.incrementAndGet();
    }

    private int p() {
        return this.h.incrementAndGet();
    }

    String d() {
        return this.q == null ? "" : this.q;
    }

    List<hf> e() {
        return this.x;
    }

    List<hd> f() {
        return this.A;
    }

    Map<String, he> g() {
        return this.w;
    }

    int h() {
        return this.C;
    }
}

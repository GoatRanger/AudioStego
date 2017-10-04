package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.bugly.crashreport.CrashReport.CrashHandleCallback;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.strategy.c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.b;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.z;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class a implements NativeExceptionHandler {
    private final Context a;
    private final b b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final c d;
    private final String e;
    private CrashHandleCallback f;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, b bVar, c cVar, CrashHandleCallback crashHandleCallback, String str) {
        this.a = context;
        this.b = bVar;
        this.c = aVar;
        this.d = cVar;
        this.f = crashHandleCallback;
        this.e = str;
    }

    protected void a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(this.a);
        z.e("#++++++++++Simple Record By Bugly++++++++++#", new Object[0]);
        z.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        z.e("# CRASH REPORT CREATED BY NATIVE VERSION %s", str7);
        z.e("# PKG NAME: %s", a.f());
        z.e("# APP VER: %s", a.e());
        z.e("# CRASH TYPE: NATIVE_CRASH", new Object[0]);
        z.e("# CRASH TIME: %s", ag.a());
        z.e("# CRASH PROCESS: %s", a.E());
        z.e("# CRASH THREAD: %s", str);
        z.e("# CRASH TYPE: %s", str2);
        z.e("# CRASH ADDR: %s", str3);
        z.e("# CRASH STACK:", new Object[0]);
        z.e(str4, new Object[0]);
        z.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }

    protected void a(CrashDetailBean crashDetailBean) {
        com.tencent.bugly.crashreport.common.info.a a = com.tencent.bugly.crashreport.common.info.a.a(this.a);
        z.e("#++++++++++Detail Record By Bugly++++++++++#", new Object[0]);
        z.e("# You can go to Bugly(http:\\\\bugly.qq.com) to see more detail of this Report!", new Object[0]);
        z.e("# CRASH REPORT CREATED BY NATIVE VERSION %s", crashDetailBean.L);
        z.e("# REPORT ID: %s", crashDetailBean.c);
        z.e("# PKG NAME: %s", a.f());
        z.e("# APP VER: %s", a.e());
        z.e("# LAUNCH TIME:%s", ag.a(new Date(crashDetailBean.M)));
        z.e("# CRASH TYPE: %s", "NATIVE_CRASH");
        z.e("# CRASH TIME: %s", ag.a(new Date(crashDetailBean.r)));
        z.e("# CRASH PROCESS: %s", a.E());
        z.e("# CRASH THREAD: %s", crashDetailBean.A);
        String str = "# CRASH DEVICE: %s %s";
        Object[] objArr = new Object[2];
        objArr[0] = a.k();
        objArr[1] = a.C().booleanValue() ? "ROOTED" : "UNROOT";
        z.e(str, objArr);
        z.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.B), Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D));
        z.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.E), Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G));
        z.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.K, crashDetailBean.J);
        z.e("# EXCEPTION TYPE: %s", crashDetailBean.n);
        z.e("# EXCEPTION MSG: %s", crashDetailBean.o);
        z.e("# EXCEPTION STACK:\n %s", crashDetailBean.q);
        z.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }

    public CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean l = com.tencent.bugly.crashreport.crash.c.a().l();
        String str10 = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        if (l) {
            z.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.b = 1;
        crashDetailBean.e = this.c.n();
        crashDetailBean.f = this.c.e();
        crashDetailBean.g = this.c.A();
        crashDetailBean.m = this.c.m();
        crashDetailBean.n = str3;
        crashDetailBean.o = str10;
        crashDetailBean.p = str4;
        crashDetailBean.q = str5;
        crashDetailBean.r = j;
        crashDetailBean.u = ag.a(crashDetailBean.q.getBytes());
        crashDetailBean.z = str;
        crashDetailBean.A = str2;
        crashDetailBean.H = this.c.D();
        crashDetailBean.h = this.c.z();
        crashDetailBean.v = str8;
        crashDetailBean.J = str7;
        crashDetailBean.K = str6;
        crashDetailBean.L = str9;
        crashDetailBean.E = this.c.u();
        crashDetailBean.F = this.c.t();
        crashDetailBean.G = this.c.v();
        if (z) {
            crashDetailBean.B = com.tencent.bugly.crashreport.common.info.b.i();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.k();
            crashDetailBean.w = d.a(this.a, 20000);
            crashDetailBean.M = this.c.b();
            crashDetailBean.P = this.c.N();
            crashDetailBean.Q = this.c.O();
            crashDetailBean.R = this.c.J();
            crashDetailBean.S = this.c.M();
            crashDetailBean.y = d.a(20000, false);
            if (str == null) {
                crashDetailBean.z = this.c.E();
            }
            this.b.a(crashDetailBean, this.f);
            crashDetailBean.x = ab.a(true);
        } else {
            crashDetailBean.B = -1;
            crashDetailBean.C = -1;
            crashDetailBean.D = -1;
            crashDetailBean.w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.M = -1;
            crashDetailBean.P = -1;
            crashDetailBean.Q = -1;
            crashDetailBean.R = map;
            crashDetailBean.S = null;
            crashDetailBean.y = null;
            if (str == null) {
                crashDetailBean.z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.x = bArr;
            }
        }
        return crashDetailBean;
    }

    public void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        z.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        z.a("Native Crash Happen v2", new Object[0]);
        try {
            int i7;
            String str8;
            String str9;
            this.d.d();
            if (!this.d.b()) {
                z.e("waiting for remote sync", new Object[0]);
                i7 = 0;
                while (!this.d.b()) {
                    ag.a(500);
                    i7 += 500;
                    if (i7 >= 5000) {
                        break;
                    }
                }
            }
            long j3 = (1000 * j) + (j2 / 1000);
            String b = b.b(str3);
            String str10 = "UNKNOWN";
            String str11 = "UNKNOWN(" + i4 + ")";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else if (i4 > 0) {
                str11 = AppInfo.a(this.a, i4);
                str8 = str5;
                str9 = str;
            } else {
                str8 = str5;
                str9 = str;
            }
            if (!this.d.b()) {
                z.d("no remote but still store!", new Object[0]);
            }
            if (this.d.d().d || !this.d.b()) {
                String str12 = null;
                String str13 = null;
                if (strArr != null) {
                    Map hashMap = new HashMap();
                    for (String split : strArr) {
                        String[] split2 = split.split("=");
                        if (split2.length == 2) {
                            hashMap.put(split2[0], split2[1]);
                        } else {
                            z.e("bad extraMsg %s", split);
                        }
                    }
                    str13 = (String) hashMap.get("ExceptionThreadName");
                    str12 = (String) hashMap.get("ExceptionProcessName");
                } else {
                    z.c("not found extraMsg", new Object[0]);
                }
                if (str12 == null || str12.length() == 0) {
                    str12 = this.c.E();
                } else {
                    z.c("crash process name change to %s", str12);
                }
                if (str13 == null || str13.length() == 0) {
                    str13 = Thread.currentThread().getName();
                } else {
                    z.c("crash thread name change to %s", str13);
                }
                CrashDetailBean packageCrashDatas = packageCrashDatas(str12, str13, j3, str9, str2, b, str8, str11, str4, str7, null, null, true);
                if (packageCrashDatas == null) {
                    z.e("pkg crash datas fail!", new Object[0]);
                    this.d.a("packageFail_dropEXP", false);
                    return;
                }
                a(packageCrashDatas);
                this.d.a(packageCrashDatas);
                if (!this.b.a(packageCrashDatas)) {
                    this.b.a(packageCrashDatas, (long) OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                }
                b.c(this.e);
                return;
            }
            z.e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            a(Thread.currentThread().getName(), str9, str2, b, str8, str11, str7);
            ag.c(str4);
            this.d.a("remoteClose_dropEXP", false);
        } catch (Throwable th) {
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
    }
}

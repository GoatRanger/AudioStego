package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.info.a;
import com.tencent.bugly.crashreport.common.info.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.common.strategy.UserInfoBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class s {
    public static ba a(UserInfoBean userInfoBean, a aVar) {
        if (userInfoBean == null) {
            return null;
        }
        ba baVar = new ba();
        baVar.a = userInfoBean.e;
        baVar.d = userInfoBean.c;
        baVar.c = userInfoBean.d;
        baVar.g = v.a();
        baVar.h = userInfoBean.i == 1;
        switch (userInfoBean.b) {
            case 1:
                baVar.b = (byte) 1;
                break;
            case 2:
                baVar.b = (byte) 4;
                break;
            case 3:
                baVar.b = (byte) 2;
                break;
            case 4:
                baVar.b = (byte) 3;
                break;
            default:
                if (userInfoBean.b >= 10 && userInfoBean.b < 20) {
                    baVar.b = (byte) userInfoBean.b;
                    break;
                }
                z.e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.b));
                return null;
        }
        baVar.f = new HashMap();
        if (userInfoBean.j >= 0) {
            baVar.f.put("C01", "" + userInfoBean.j);
        }
        if (userInfoBean.k >= 0) {
            baVar.f.put("C02", "" + userInfoBean.k);
        }
        if (userInfoBean.l != null && userInfoBean.l.size() > 0) {
            for (Entry entry : userInfoBean.l.entrySet()) {
                baVar.f.put("C03_" + ((String) entry.getKey()), entry.getValue());
            }
        }
        if (userInfoBean.m != null && userInfoBean.m.size() > 0) {
            for (Entry entry2 : userInfoBean.m.entrySet()) {
                baVar.f.put("C04_" + ((String) entry2.getKey()), entry2.getValue());
            }
        }
        z.c("summary type %d vm:%d", Byte.valueOf(baVar.b), Integer.valueOf(baVar.f.size()));
        return baVar;
    }

    public static bb a(List<UserInfoBean> list, a aVar, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        bb bbVar = new bb();
        bbVar.b = aVar.E();
        bbVar.c = aVar.n();
        ArrayList arrayList = new ArrayList();
        for (UserInfoBean a : list) {
            ba a2 = a(a, aVar);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        bbVar.d = arrayList;
        bbVar.e = new HashMap();
        bbVar.e.put("A7", "" + aVar.j());
        bbVar.e.put("A6", "" + aVar.x());
        bbVar.e.put("A5", "" + aVar.w());
        bbVar.e.put("A2", "" + aVar.u());
        bbVar.e.put("A1", "" + aVar.u());
        bbVar.e.put("A24", "" + aVar.l());
        bbVar.e.put("A17", "" + aVar.v());
        bbVar.e.put("A15", "" + aVar.A());
        bbVar.e.put("A13", "" + aVar.C());
        switch (i) {
            case 1:
                bbVar.a = (byte) 1;
                break;
            case 2:
                bbVar.a = (byte) 2;
                break;
            default:
                z.e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return bbVar;
    }

    public static au a(Context context, CrashDetailBean crashDetailBean, a aVar) {
        boolean z = true;
        if (context == null || crashDetailBean == null || aVar == null) {
            z.d("enExp args == null", new Object[0]);
            return null;
        }
        boolean z2;
        au auVar = new au();
        switch (crashDetailBean.b) {
            case 0:
                auVar.a = crashDetailBean.j ? "200" : "100";
                break;
            case 1:
                auVar.a = crashDetailBean.j ? "201" : "101";
                break;
            case 2:
                auVar.a = crashDetailBean.j ? "202" : "102";
                break;
            case 3:
                auVar.a = crashDetailBean.j ? "203" : "103";
                break;
            case 4:
                auVar.a = crashDetailBean.j ? "204" : "104";
                break;
            case 5:
                auVar.a = crashDetailBean.j ? "207" : "107";
                break;
            case 6:
                auVar.a = crashDetailBean.j ? "206" : "106";
                break;
            default:
                z.e("crash type error! %d", Integer.valueOf(crashDetailBean.b));
                break;
        }
        auVar.b = crashDetailBean.r;
        auVar.c = crashDetailBean.n;
        auVar.d = crashDetailBean.o;
        auVar.e = crashDetailBean.p;
        auVar.g = crashDetailBean.q;
        auVar.h = crashDetailBean.y;
        auVar.i = crashDetailBean.c;
        auVar.j = null;
        auVar.l = crashDetailBean.m;
        auVar.m = crashDetailBean.e;
        auVar.f = crashDetailBean.A;
        auVar.t = v.a();
        auVar.n = null;
        if (crashDetailBean.i != null && crashDetailBean.i.size() > 0) {
            auVar.o = new ArrayList();
            for (Entry entry : crashDetailBean.i.entrySet()) {
                ar arVar = new ar();
                arVar.a = ((PlugInBean) entry.getValue()).a;
                arVar.c = ((PlugInBean) entry.getValue()).c;
                arVar.e = ((PlugInBean) entry.getValue()).b;
                auVar.o.add(arVar);
            }
        }
        if (crashDetailBean.h != null && crashDetailBean.h.size() > 0) {
            auVar.p = new ArrayList();
            for (Entry entry2 : crashDetailBean.h.entrySet()) {
                arVar = new ar();
                arVar.a = ((PlugInBean) entry2.getValue()).a;
                arVar.c = ((PlugInBean) entry2.getValue()).c;
                arVar.e = ((PlugInBean) entry2.getValue()).b;
                auVar.p.add(arVar);
            }
        }
        if (crashDetailBean.j) {
            int size;
            auVar.k = crashDetailBean.t;
            if (crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
                if (auVar.q == null) {
                    auVar.q = new ArrayList();
                }
                try {
                    auVar.q.add(new at((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    auVar.q = null;
                }
            }
            String str = "crashcount:%d sz:%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(auVar.k);
            if (auVar.q != null) {
                size = auVar.q.size();
            } else {
                size = 0;
            }
            objArr[1] = Integer.valueOf(size);
            z.c(str, objArr);
        }
        if (crashDetailBean.w != null) {
            if (auVar.q == null) {
                auVar.q = new ArrayList();
            }
            try {
                auVar.q.add(new at((byte) 1, "log.txt", crashDetailBean.w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                auVar.q = null;
            }
        }
        at atVar = new at((byte) 2, "buglylog.zip", crashDetailBean.x);
        if (atVar != null) {
            z.c("attach user log", new Object[0]);
            if (auVar.q == null) {
                auVar.q = new ArrayList();
            }
            auVar.q.add(atVar);
        }
        if (crashDetailBean.b == 3) {
            if (auVar.q == null) {
                auVar.q = new ArrayList();
            }
            if (crashDetailBean.O != null && crashDetailBean.O.containsKey("BUGLY_CR_01")) {
                try {
                    auVar.q.add(new at((byte) 1, "anrMessage.txt", ((String) crashDetailBean.O.get("BUGLY_CR_01")).getBytes("utf-8")));
                    z.c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e22) {
                    e22.printStackTrace();
                    auVar.q = null;
                }
                crashDetailBean.O.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v != null) {
                atVar = a("trace.zip", context, crashDetailBean.v);
                if (atVar != null) {
                    z.c("attach traces", new Object[0]);
                    auVar.q.add(atVar);
                }
            }
        }
        if (crashDetailBean.b == 1) {
            if (auVar.q == null) {
                auVar.q = new ArrayList();
            }
            if (crashDetailBean.v != null) {
                atVar = a("tomb.zip", context, crashDetailBean.v);
                if (atVar != null) {
                    z.c("attach tombs", new Object[0]);
                    auVar.q.add(atVar);
                }
            }
        }
        if (crashDetailBean.T != null && crashDetailBean.T.length > 0) {
            if (auVar.q == null) {
                auVar.q = new ArrayList();
            }
            auVar.q.add(new at((byte) 1, "userExtraByteData", crashDetailBean.T));
            z.c("attach extraData", new Object[0]);
        }
        auVar.r = new HashMap();
        auVar.r.put("A9", "" + crashDetailBean.B);
        auVar.r.put("A11", "" + crashDetailBean.C);
        auVar.r.put("A10", "" + crashDetailBean.D);
        auVar.r.put("A23", "" + crashDetailBean.f);
        auVar.r.put("A7", "" + aVar.j());
        auVar.r.put("A6", "" + aVar.x());
        auVar.r.put("A5", "" + aVar.w());
        auVar.r.put("A22", "" + aVar.n());
        auVar.r.put("A2", "" + crashDetailBean.F);
        auVar.r.put("A1", "" + crashDetailBean.E);
        auVar.r.put("A24", "" + aVar.l());
        auVar.r.put("A17", "" + crashDetailBean.G);
        auVar.r.put("A3", "" + aVar.p());
        auVar.r.put("A16", "" + aVar.r());
        auVar.r.put("A25", "" + aVar.s());
        auVar.r.put("A14", "" + aVar.q());
        auVar.r.put("A15", "" + aVar.A());
        auVar.r.put("A13", "" + aVar.C());
        auVar.r.put("A34", "" + crashDetailBean.z);
        try {
            auVar.r.put("A26", "" + URLEncoder.encode(crashDetailBean.H, "utf-8"));
        } catch (UnsupportedEncodingException e222) {
            e222.printStackTrace();
        }
        if (crashDetailBean.b == 1) {
            auVar.r.put("A27", "" + crashDetailBean.K);
            auVar.r.put("A28", "" + crashDetailBean.J);
            auVar.r.put("A29", "" + crashDetailBean.k);
        }
        auVar.r.put("A30", "" + crashDetailBean.L);
        auVar.r.put("A18", "" + crashDetailBean.M);
        Map map = auVar.r;
        String str2 = "A36";
        StringBuilder append = new StringBuilder().append("");
        if (crashDetailBean.N) {
            z2 = false;
        } else {
            z2 = true;
        }
        map.put(str2, append.append(z2).toString());
        if (crashDetailBean.P >= 0) {
            auVar.r.put("C01", "" + crashDetailBean.P);
        }
        if (crashDetailBean.Q >= 0) {
            auVar.r.put("C02", "" + crashDetailBean.Q);
        }
        if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
            for (Entry entry22 : crashDetailBean.R.entrySet()) {
                auVar.r.put("C03_" + ((String) entry22.getKey()), entry22.getValue());
            }
        }
        if (crashDetailBean.S != null && crashDetailBean.S.size() > 0) {
            for (Entry entry222 : crashDetailBean.S.entrySet()) {
                auVar.r.put("C04_" + ((String) entry222.getKey()), entry222.getValue());
            }
        }
        auVar.s = null;
        if (crashDetailBean.O != null && crashDetailBean.O.size() > 0) {
            auVar.s = crashDetailBean.O;
            z.a("setted message size %d", Integer.valueOf(auVar.s.size()));
        }
        String str3 = "%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d";
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.n;
        objArr2[1] = crashDetailBean.c;
        objArr2[2] = aVar.a();
        objArr2[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.M) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.N);
        objArr2[6] = Boolean.valueOf(crashDetailBean.j);
        if (crashDetailBean.b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.t);
        objArr2[9] = crashDetailBean.s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.d);
        objArr2[11] = Integer.valueOf(auVar.r.size());
        z.c(str3, objArr2);
        return auVar;
    }

    public static av a(Context context, List<CrashDetailBean> list, a aVar) {
        if (context == null || list == null || list.size() == 0 || aVar == null) {
            z.d("enEXPPkg args == null!", new Object[0]);
            return null;
        }
        av avVar = new av();
        avVar.a = new ArrayList();
        for (CrashDetailBean a : list) {
            avVar.a.add(a(context, a, aVar));
        }
        return avVar;
    }

    public static az a(byte[] bArr) {
        try {
            az azVar = new az();
            k kVar = new k(bArr);
            kVar.a("utf-8");
            azVar.a(kVar);
            return azVar;
        } catch (Throwable th) {
            if (!z.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static aw a(Context context, int i, byte[] bArr, a aVar, StrategyBean strategyBean) {
        if (aVar == null || strategyBean == null) {
            z.e("illigle access to create req pkg!", new Object[0]);
            return null;
        }
        try {
            aw awVar = new aw();
            synchronized (aVar) {
                awVar.a = aVar.c();
                awVar.b = aVar.d();
                awVar.c = aVar.f();
                awVar.d = aVar.e();
                awVar.e = aVar.B();
                awVar.f = aVar.h();
                awVar.g = i;
                awVar.h = bArr == null ? "".getBytes() : bArr;
                awVar.i = aVar.k();
                awVar.j = aVar.l();
                awVar.k = new HashMap();
                awVar.l = aVar.a();
                awVar.m = strategyBean.l;
                awVar.o = aVar.n();
                awVar.p = b.e(context);
                awVar.q = System.currentTimeMillis();
                awVar.r = aVar.p();
                awVar.s = v.b();
                awVar.t = aVar.r();
                awVar.u = aVar.q();
                awVar.v = aVar.s();
                awVar.w = awVar.p;
                awVar.n = aVar.g();
            }
            if (bArr != null) {
                awVar.h = ag.a(awVar.h, 2, 1, strategyBean.p);
                if (awVar.h == null) {
                    z.e("reqPkg sbuffer error!", new Object[0]);
                    return null;
                }
            }
            return awVar;
        } catch (Throwable th) {
            if (z.b(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] a(aw awVar) {
        try {
            e eVar = new e();
            eVar.b();
            eVar.a("utf-8");
            eVar.a(1);
            eVar.b("RqdServer");
            eVar.c("sync");
            eVar.a("detail", awVar);
            return eVar.a();
        } catch (Throwable th) {
            if (!z.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static ax a(byte[] bArr, StrategyBean strategyBean) {
        if (bArr != null) {
            try {
                ax axVar;
                e eVar = new e();
                eVar.b();
                eVar.a("utf-8");
                eVar.a(bArr);
                Object b = eVar.b("detail", new ax());
                if (ax.class.isInstance(b)) {
                    axVar = (ax) ax.class.cast(b);
                } else {
                    axVar = null;
                }
                if (axVar == null || axVar.c == null || axVar.c.length <= 0) {
                    return axVar;
                }
                z.c("resp buf %d", Integer.valueOf(axVar.c.length));
                axVar.c = ag.b(axVar.c, 2, 1, StrategyBean.a);
                if (axVar.c != null) {
                    return axVar;
                }
                z.e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!z.b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    public static byte[] a(m mVar) {
        try {
            l lVar = new l();
            lVar.a("utf-8");
            mVar.a(lVar);
            return lVar.b();
        } catch (Throwable th) {
            if (!z.b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public static at a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        if (str2 == null || context == null) {
            z.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        z.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (ag.a(file, file2, 5000)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[1000];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                        byteArrayOutputStream.flush();
                    }
                    z.c("read bytes :%d", Integer.valueOf(byteArrayOutputStream.toByteArray().length));
                    at atVar = new at((byte) 2, file2.getName(), bArr);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e2) {
                            if (!z.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    if (file2 != null && file2.exists()) {
                        z.c("del tmp", new Object[0]);
                        file2.delete();
                    }
                    return atVar;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!z.a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                if (!z.a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                        if (file2 == null && file2.exists()) {
                            z.c("del tmp", new Object[0]);
                            file2.delete();
                            return null;
                        }
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th32) {
                                if (!z.a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        }
                        if (file2 != null && file2.exists()) {
                            z.c("del tmp", new Object[0]);
                            file2.delete();
                        }
                        throw e2;
                    }
                }
            } catch (Throwable th322) {
                fileInputStream = null;
                e2 = th322;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                z.c("del tmp", new Object[0]);
                file2.delete();
                throw e2;
            }
        }
        z.d("zip fail!", new Object[0]);
        return null;
    }
}

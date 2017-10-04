package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import com.here.odnp.config.OdnpConfigStatic;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.ag;
import com.tencent.bugly.proguard.az;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class c {
    private static c a = null;
    private final long b = 0;
    private final long c = 600000;
    private final long d = 21600000;
    private final long e = 604800000;
    private final long f = 3;
    private final Context g;
    private final com.tencent.bugly.crashreport.common.info.a h;
    private final StrategyBean i;
    private final q j;
    private final List<b> k;
    private final w l;
    private final y m;
    private StrategyBean n = null;
    private Boolean o = null;
    private long p = -1;
    private int q = 0;

    protected class a implements Runnable {
        public final int a;
        public final long b;
        public final boolean c;
        final /* synthetic */ c d;

        public a(c cVar, int i) {
            this.d = cVar;
            this.a = i;
            this.b = -1;
            this.c = false;
        }

        public a(c cVar, int i, long j, boolean z) {
            this.d = cVar;
            this.a = i;
            this.b = j;
            this.c = z;
        }

        public void run() {
            try {
                switch (this.a) {
                    case 0:
                        this.d.k();
                        return;
                    case 1:
                        this.d.l();
                        return;
                    case 2:
                        this.d.b(this.b);
                        this.d.k();
                        return;
                    case 3:
                        this.d.i();
                        return;
                    case 4:
                        this.d.b(this.c);
                        return;
                    case 5:
                        this.d.m();
                        return;
                    default:
                        z.e("unknown tasktype :%d", Integer.valueOf(this.a));
                        return;
                }
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
            if (!z.a(th)) {
                th.printStackTrace();
            }
        }
    }

    protected c(Context context, com.tencent.bugly.crashreport.common.info.a aVar, StrategyBean strategyBean, q qVar, w wVar, y yVar) {
        this.g = context;
        this.h = aVar;
        this.i = strategyBean;
        this.k = new ArrayList();
        this.j = qVar;
        this.l = wVar;
        this.m = yVar;
        this.p = ag.b() + 86400000;
        this.m.a(new a(this, 1), (this.p - new Date().getTime()) + OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
    }

    public static synchronized c a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, StrategyBean strategyBean, q qVar, w wVar, y yVar) {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c(context, aVar, strategyBean, qVar, wVar, yVar);
            }
            cVar = a;
        }
        return cVar;
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            cVar = a;
        }
        return cVar;
    }

    public synchronized boolean b() {
        return this.n != null;
    }

    public synchronized StrategyBean c() {
        return this.n;
    }

    public synchronized void a(StrategyBean strategyBean) {
        this.n = strategyBean;
    }

    public StrategyBean d() {
        StrategyBean c = c();
        return c != null ? c : this.i;
    }

    public synchronized void a(b bVar) {
        if (!this.k.contains(bVar)) {
            this.k.add(bVar);
        }
    }

    protected void b(StrategyBean strategyBean) {
        for (b bVar : this.k) {
            try {
                z.c("notify %s", bVar.getClass().getName());
                bVar.a(strategyBean);
            } catch (Throwable th) {
                if (!z.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public void a(StrategyBean strategyBean, boolean z) {
        a(strategyBean);
        if (!z) {
            this.j.b();
            this.j.a(strategyBean);
        }
        b(strategyBean);
    }

    public void a(az azVar) {
        if (azVar != null) {
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.d = azVar.a;
            strategyBean.f = azVar.c;
            strategyBean.e = azVar.b;
            if (!ag.b(azVar.d)) {
                strategyBean.n = azVar.d;
            }
            if (!ag.b(azVar.e)) {
                strategyBean.o = azVar.e;
            }
            if (!(azVar.f == null || ag.b(azVar.f.a))) {
                strategyBean.p = azVar.f.a;
            }
            if (azVar.h != 0) {
                strategyBean.l = azVar.h;
            }
            if (azVar.g != null && azVar.g.size() > 0) {
                String str = (String) azVar.g.get("B11");
                if (str == null || !str.equals("1")) {
                    strategyBean.g = false;
                } else {
                    strategyBean.g = true;
                }
                str = (String) azVar.g.get("B14");
                if (str == null || !str.equals("1")) {
                    strategyBean.j = false;
                } else {
                    strategyBean.j = true;
                }
                str = (String) azVar.g.get("B15");
                if (str == null || !str.equals("1")) {
                    strategyBean.k = false;
                } else {
                    strategyBean.k = true;
                }
                str = (String) azVar.g.get("B16");
                if (str != null && str.length() > 0) {
                    try {
                        Long valueOf = Long.valueOf(Long.parseLong(str));
                        if (valueOf.longValue() > 0) {
                            long longValue;
                            if (valueOf.longValue() > 30000) {
                                longValue = valueOf.longValue();
                            } else {
                                longValue = 30000;
                            }
                            strategyBean.m = Long.valueOf(longValue).longValue();
                        }
                    } catch (Throwable e) {
                        if (!z.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
                str = (String) azVar.g.get("B25");
                if (str == null || !str.equals("1")) {
                    strategyBean.h = false;
                } else {
                    strategyBean.h = true;
                }
            }
            z.a("cr:%b,qu:%b,uin:%b,an:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,h5:%b,lstT:%d", Boolean.valueOf(strategyBean.d), Boolean.valueOf(strategyBean.f), Boolean.valueOf(strategyBean.e), Boolean.valueOf(strategyBean.g), Boolean.valueOf(strategyBean.j), Boolean.valueOf(strategyBean.k), Long.valueOf(strategyBean.m), Boolean.valueOf(strategyBean.h), Boolean.valueOf(strategyBean.i), Long.valueOf(strategyBean.l));
            a(strategyBean, false);
            z.c("ek|%s|%s", strategyBean.p, StrategyBean.a);
        }
    }

    protected UserInfoBean a(int i, int i2) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = i;
        userInfoBean.c = this.h.E();
        userInfoBean.d = this.h.m();
        userInfoBean.e = new Date().getTime();
        userInfoBean.f = -1;
        userInfoBean.h = this.h.e();
        userInfoBean.i = i2;
        return userInfoBean;
    }

    protected void e() {
        this.j.b(a(3, 0));
    }

    public void a(long j) {
        this.m.b(new a(this, 2, j, true));
    }

    public void f() {
        this.m.b(new a(this, 5));
    }

    public void a(boolean z) {
        this.m.b(new a(this, 4, 0, z));
    }

    public boolean a(CrashDetailBean crashDetailBean) {
        if (crashDetailBean.b == 2) {
            return false;
        }
        a aVar = new a();
        aVar.b = 1;
        aVar.c = crashDetailBean.z;
        aVar.d = crashDetailBean.A;
        aVar.e = crashDetailBean.r;
        this.j.b(1);
        return this.j.b(aVar);
    }

    protected synchronized Boolean g() {
        return this.o;
    }

    protected synchronized void a(Boolean bool) {
        this.o = bool;
    }

    public boolean h() {
        Boolean g = g();
        if (g != null) {
            return g.booleanValue();
        }
        String E = this.h.E();
        List<a> a = this.j.a(1);
        List arrayList = new ArrayList();
        if (a == null || a.size() <= 0) {
            a(Boolean.valueOf(false));
            return false;
        }
        for (a aVar : a) {
            if (E.equals(aVar.c)) {
                a(Boolean.valueOf(true));
                arrayList.add(aVar);
            }
        }
        if (arrayList.size() > 0) {
            this.j.g(arrayList);
        }
        return true;
    }

    public final void i() {
        int i = 1;
        StrategyBean c = c();
        if (c == null || c.e) {
            int i2;
            List list;
            String E = this.h.E();
            List arrayList = new ArrayList();
            List a = this.j.a(E);
            if (a != null) {
                UserInfoBean userInfoBean;
                int i3;
                int size = a.size() - 10;
                if (size > 0) {
                    for (i2 = 0; i2 < a.size() - 1; i2++) {
                        for (int i4 = i2 + 1; i4 < a.size(); i4++) {
                            if (((UserInfoBean) a.get(i2)).e > ((UserInfoBean) a.get(i4)).e) {
                                userInfoBean = (UserInfoBean) a.get(i2);
                                a.set(i2, a.get(i4));
                                a.set(i4, userInfoBean);
                            }
                        }
                    }
                    for (i3 = 0; i3 < size; i3++) {
                        arrayList.add(a.get(i3));
                    }
                }
                Iterator it = a.iterator();
                i2 = 0;
                while (it.hasNext()) {
                    userInfoBean = (UserInfoBean) it.next();
                    if (userInfoBean.f != -1) {
                        it.remove();
                        if (userInfoBean.e < ag.b()) {
                            arrayList.add(userInfoBean);
                        }
                    }
                    if (userInfoBean.e <= System.currentTimeMillis() - 600000 || !(userInfoBean.b == 1 || userInfoBean.b == 4)) {
                        i3 = i2;
                    } else {
                        i3 = i2 + 1;
                    }
                    i2 = i3;
                }
                if (i2 > 15) {
                    z.d("[userinfo] userinfo too many times in 10 min: %d", Integer.valueOf(i2));
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                i2 = i3;
                list = a;
            } else {
                list = new ArrayList();
                i2 = 1;
            }
            if (arrayList.size() > 0) {
                this.j.f(arrayList);
                if (null != null) {
                    a(null + " count: " + arrayList.size(), true);
                } else {
                    z.c("remove uploadedUI", new Object[0]);
                }
            }
            if (i2 != 0 && list != null && list.size() > 0) {
                z.c("[userinfo] do userinfo, size: %d", Integer.valueOf(list.size()));
                if (j() != 1) {
                    i = 2;
                }
                this.l.a(list, this, i);
                return;
            }
            return;
        }
        z.d("userinfo close!", new Object[0]);
    }

    protected List<CrashDetailBean> c(StrategyBean strategyBean) {
        if (strategyBean == null) {
            z.d("have not synced remote!", new Object[0]);
            return null;
        } else if (strategyBean.d) {
            long time = new Date().getTime();
            long b = ag.b();
            List c = this.j.c();
            if (c == null || c.size() <= 0) {
                return null;
            }
            List arrayList = new ArrayList();
            Iterator it = c.iterator();
            while (it.hasNext()) {
                com.tencent.bugly.crashreport.crash.a aVar = (com.tencent.bugly.crashreport.crash.a) it.next();
                if (aVar.b < b - 604800000) {
                    it.remove();
                    arrayList.add(aVar);
                } else if (aVar.d) {
                    if (aVar.b >= time - 86400000) {
                        it.remove();
                    } else if (!aVar.e) {
                        it.remove();
                        arrayList.add(aVar);
                    }
                } else if (((long) aVar.f) >= 3 && aVar.b < time - 86400000) {
                    it.remove();
                    arrayList.add(aVar);
                }
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.j.c(arrayList);
                a("dropOldCrash count:" + arrayList.size(), true);
            }
            List arrayList2 = new ArrayList();
            List<CrashDetailBean> b2 = this.j.b(c);
            if (b2 != null && b2.size() > 0) {
                String e = this.h.e();
                Iterator it2 = b2.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean crashDetailBean = (CrashDetailBean) it2.next();
                    if (!e.equals(crashDetailBean.f)) {
                        it2.remove();
                        arrayList2.add(crashDetailBean);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                this.j.d(arrayList2);
                a("dropOldVerCrash count:" + arrayList2.size(), true);
            }
            return b2;
        } else {
            z.d("Crashreport remote closed, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            z.b("[init] WARNING! Crashreport closed by server, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
    }

    protected synchronized int j() {
        return this.q;
    }

    protected synchronized void a(int i) {
        this.q = i;
    }

    protected void k() {
        if (h()) {
            z.c("last session crash ", new Object[0]);
        }
        StrategyBean a = this.j.a();
        StrategyBean c = c();
        long b = ag.b();
        if (c == null && a != null) {
            a(a, true);
        } else if (c != null && a == null) {
            a(c, false);
        } else if (!(c == null || a == null)) {
            if (c.c < a.c) {
                a(a, true);
            } else if (c.c > a.c) {
                a(c, false);
            }
        }
        c = c();
        if (c == null) {
            this.l.a(this);
            return;
        }
        boolean z;
        if (b - c.c >= 604800000) {
            z.c("step req by ovtime && " + c.f, new Object[0]);
            z = c.f;
        } else {
            z = false;
        }
        List c2 = c(c);
        if (z) {
            if (c2 != null && c2.size() > 0) {
                this.l.a(c2, this);
            }
            if (z) {
                this.m.a(new a(this, 0), 21600000);
            } else {
                this.m.a(new a(this, 0), 600000);
            }
        }
        this.l.a(c2, this);
        if (z) {
            this.m.a(new a(this, 0), 21600000);
        } else {
            this.m.a(new a(this, 0), 600000);
        }
    }

    protected synchronized void l() {
        long time = new Date().getTime();
        if (time < this.p) {
            this.m.a(new a(this, 1), (this.p - time) + OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        } else {
            this.p = ag.b() + 86400000;
            e();
            this.m.a(new a(this, 3), (this.p - time) + OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }
    }

    protected void b(boolean z) {
        StrategyBean c = c();
        if (c == null || (c != null && c.e)) {
            this.j.b(a(4, 0));
        }
        if (z) {
            i();
        }
    }

    protected void m() {
        StrategyBean c = c();
        if (c == null || (c != null && c.e)) {
            this.j.b(a(2, 0));
        }
    }

    protected void b(long j) {
        a(j() + 1);
        StrategyBean c = c();
        if (c == null) {
            c = this.j.a();
            if (c != null) {
                a(c, true);
            }
        }
        if (c == null || (c != null && c.e)) {
            z.c("start up delay %d", Long.valueOf(j));
            this.j.b(a(1, 1));
            this.m.a(new a(this, 3), j);
        }
    }

    protected void a(final int i, String str, boolean z) {
        final UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.b = i;
        userInfoBean.c = this.h.E();
        userInfoBean.d = this.h.m() + "|" + i + "|" + str;
        userInfoBean.e = new Date().getTime();
        userInfoBean.f = -1;
        if (z) {
            z.d("to delay record!", new Object[0]);
            this.m.a(new Runnable(this) {
                final /* synthetic */ c c;

                public void run() {
                    z.d("to record! %d", Integer.valueOf(i));
                    this.c.j.b(userInfoBean);
                }
            }, 60000);
            return;
        }
        z.d("to record! %d", Integer.valueOf(i));
        this.j.b(userInfoBean);
    }

    public void a(String str) {
        z.d("on db access fail delay record ", new Object[0]);
        a(10, str, true);
    }

    public void b(String str) {
        a(12, str, false);
        z.d("inner record %s", str);
    }

    public void a(String str, boolean z) {
        a(11, str, z);
        z.d("inner record %s", str);
    }
}

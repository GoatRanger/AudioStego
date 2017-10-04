package com.amap.api.mapcore.util;

import android.content.Context;
import com.alipay.sdk.j.i;
import java.util.ArrayList;
import java.util.List;

public class bx {
    private static volatile bx a;
    private static ek b;
    private Context c;

    public static bx a(Context context) {
        if (a == null) {
            synchronized (bx.class) {
                if (a == null) {
                    a = new bx(context);
                }
            }
        }
        return a;
    }

    private bx(Context context) {
        this.c = context;
        b = b(this.c);
    }

    private ek b(Context context) {
        try {
            return new ek(context, bw.a());
        } catch (Throwable th) {
            ee.a(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean b() {
        if (b == null) {
            b = b(this.c);
        }
        if (b == null) {
            return false;
        }
        return true;
    }

    public ArrayList<bs> a() {
        ArrayList<bs> arrayList = new ArrayList();
        if (!b()) {
            return arrayList;
        }
        for (bs add : b.b("", bs.class)) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public synchronized void a(bs bsVar) {
        if (b()) {
            b.a((Object) bsVar, bv.d(bsVar.g()));
            a(bsVar.g(), bsVar.c());
        }
    }

    private void a(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            String a = bu.a(str);
            if (b.b(a, bu.class).size() > 0) {
                b.a(a, bu.class);
            }
            String[] split = str2.split(i.b);
            List arrayList = new ArrayList();
            for (String buVar : split) {
                arrayList.add(new bu(str, buVar));
            }
            b.a(arrayList);
        }
    }

    public synchronized List<String> a(String str) {
        List arrayList;
        arrayList = new ArrayList();
        if (b()) {
            arrayList.addAll(a(b.b(bu.a(str), bu.class)));
        }
        return arrayList;
    }

    public synchronized List<String> b(String str) {
        List arrayList;
        arrayList = new ArrayList();
        if (b()) {
            arrayList.addAll(a(b.b(bu.b(str), bu.class)));
        }
        return arrayList;
    }

    private List<String> a(List<bu> list) {
        List<String> arrayList = new ArrayList();
        if (list.size() > 0) {
            for (bu a : list) {
                arrayList.add(a.a());
            }
        }
        return arrayList;
    }

    public synchronized void c(String str) {
        if (b()) {
            b.a(bv.d(str), bv.class);
            b.a(bu.a(str), bu.class);
            b.a(bt.a(str), bt.class);
        }
    }

    public void a(String str, int i, long j, long j2, long j3) {
        if (b()) {
            a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    public synchronized void a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (b()) {
            b.a(new bt(str, j, i, jArr[0], jArr2[0]), bt.a(str));
        }
    }

    public synchronized long[] a(String str, int i) {
        long[] jArr;
        if (b()) {
            long a;
            long b;
            List b2 = b.b(bt.a(str), bt.class);
            if (b2.size() > 0) {
                a = ((bt) b2.get(0)).a(i);
                b = ((bt) b2.get(0)).b(i);
            } else {
                b = 0;
                a = 0;
            }
            jArr = new long[]{a, b};
        } else {
            jArr = new long[]{0, 0};
        }
        return jArr;
    }

    public synchronized int d(String str) {
        int i = 0;
        synchronized (this) {
            if (b()) {
                List b = b.b(bt.a(str), bt.class);
                long j = 0;
                if (b.size() > 0) {
                    j = ((bt) b.get(0)).a();
                }
                i = (int) j;
            }
        }
        return i;
    }

    public synchronized String e(String str) {
        String str2;
        str2 = null;
        if (b()) {
            List b = b.b(bv.d(str), bv.class);
            if (b.size() > 0) {
                str2 = ((bv) b.get(0)).f();
            }
        }
        return str2;
    }

    public synchronized boolean f(String str) {
        boolean z = false;
        synchronized (this) {
            if (b()) {
                if (b.b(bt.a(str), bt.class).size() > 0) {
                    z = true;
                }
            }
        }
        return z;
    }
}

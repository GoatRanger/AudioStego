package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.j.i;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

public class bi {
    public static String a = "";
    public static boolean b = false;
    public static String d = "";
    private static volatile bi j;
    CopyOnWriteArrayList<bg> c = new CopyOnWriteArrayList();
    b e = null;
    public bm f;
    bo g;
    bl h = null;
    private Context i;
    private a k;
    private br l;
    private bx m;
    private ExecutorService n = null;
    private ExecutorService o = null;

    public interface a {
        void a(bg bgVar);

        void b(bg bgVar);

        void c(bg bgVar);
    }

    class b extends Handler {
        final /* synthetic */ bi a;

        public b(bi biVar, Looper looper) {
            this.a = biVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof bg) {
                    bg bgVar = (bg) obj;
                    cf.a("OfflineMapHandler handleMessage CitObj  name: " + bgVar.getCity() + " complete: " + bgVar.getcompleteCode() + " status: " + bgVar.getState());
                    if (this.a.k != null) {
                        this.a.k.a(bgVar);
                        return;
                    }
                    return;
                }
                cf.a("Do not callback by CityObject! ");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private bi(Context context) {
        this.i = context;
        f();
    }

    public static bi a(Context context) {
        if (j == null) {
            synchronized (bi.class) {
                if (j == null && !b) {
                    j = new bi(context.getApplicationContext());
                }
            }
        }
        return j;
    }

    private void f() {
        this.m = bx.a(this.i.getApplicationContext());
        this.e = new b(this, this.i.getMainLooper());
        this.f = new bm(this.i, this.e);
        this.l = br.a(1);
        a = dj.b(this.i);
        g();
        this.h = new bl(this.i);
        this.h.start();
        Iterator it = this.f.a().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                this.c.add(new bg(this.i, (OfflineMapCity) it2.next()));
            }
        }
        h();
    }

    private void g() {
        if (!dj.b(this.i).equals("")) {
            String c;
            File file = new File(dj.b(this.i) + "offlinemapv4.png");
            if (file.exists()) {
                c = cf.c(file);
            } else {
                c = cf.a(this.i, "offlinemapv4.png");
            }
            if (c != null) {
                try {
                    f(c);
                } catch (Throwable e) {
                    ee.a(e, "MapDownloadManager", "paseJson io");
                    e.printStackTrace();
                }
            }
        }
    }

    private void f(String str) throws JSONException {
        List b = cf.b(str);
        if (b != null && b.size() != 0) {
            this.f.a(b);
        }
    }

    private void h() {
        Iterator it = this.m.a().iterator();
        while (it.hasNext()) {
            bs bsVar = (bs) it.next();
            if (!(bsVar == null || bsVar.e() == null || bsVar.g().length() < 1)) {
                if (!(bsVar.l == 4 || bsVar.l == 7 || bsVar.l < 0)) {
                    bsVar.l = 3;
                }
                bg g = g(bsVar.e());
                if (g != null) {
                    String f = bsVar.f();
                    if (f == null || f.equals(d)) {
                        g.a(bsVar.l);
                        g.setCompleteCode(bsVar.j());
                    } else {
                        this.m.c(bsVar.g());
                        g.a(7);
                    }
                    List<String> a = this.m.a(bsVar.g());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String append : a) {
                        stringBuffer.append(append);
                        stringBuffer.append(i.b);
                    }
                    g.a(stringBuffer.toString());
                    this.f.a(g);
                }
            }
        }
    }

    public void a(ArrayList<bs> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bs bsVar = (bs) it.next();
            bg g = g(bsVar.e());
            if (g != null) {
                g.a(bsVar);
                c(g);
            }
        }
    }

    public void a(final String str) {
        if (str != null) {
            if (this.n == null) {
                this.n = Executors.newSingleThreadExecutor();
            }
            this.n.execute(new Runnable(this) {
                final /* synthetic */ bi b;

                public void run() {
                    bg a = this.b.g(str);
                    try {
                        if (a.c().equals(a.f)) {
                            String adcode = a.getAdcode();
                            if (adcode.length() > 0) {
                                adcode = this.b.m.e(adcode);
                                if (bi.d.length() > 0 && !adcode.equals(bi.d)) {
                                    a.i();
                                    this.b.k.b(a);
                                    return;
                                }
                            }
                            this.b.i();
                            bj bjVar = (bj) new bk(this.b.i, bi.d).d();
                            if (this.b.k != null) {
                                if (bjVar == null) {
                                    this.b.k.b(a);
                                    return;
                                } else if (bjVar.a()) {
                                    this.b.a();
                                }
                            }
                            this.b.k.b(a);
                        }
                    } catch (Exception e) {
                    } finally {
                        this.b.k.b(a);
                    }
                }
            });
        } else if (this.k != null) {
            this.k.b(null);
        }
    }

    private void i() throws AMapException {
        if (!dj.c(this.i)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    protected void a() throws AMapException {
        bp bpVar = new bp(this.i, "");
        bpVar.a(this.i);
        List list = (List) bpVar.d();
        if (this.c != null) {
            this.f.a(list);
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            bg bgVar = (bg) it.next();
            String version = bgVar.getVersion();
            if (bgVar.getState() == 4 && d.length() > 0 && !version.equals(d)) {
                bgVar.i();
            }
        }
    }

    public boolean b(String str) {
        if (g(str) == null) {
            return false;
        }
        return true;
    }

    public void c(String str) {
        bg g = g(str);
        if (g != null) {
            d(g);
            a(g);
        } else if (this.k != null) {
            this.k.c(g);
        }
    }

    public void a(final bg bgVar) {
        if (this.g == null) {
            this.g = new bo(this.i);
        }
        if (this.o == null) {
            this.o = Executors.newSingleThreadExecutor();
        }
        this.o.execute(new Runnable(this) {
            final /* synthetic */ bi b;

            public void run() {
                if (bgVar.c().equals(bgVar.a)) {
                    this.b.k.c(bgVar);
                } else if (bgVar.getState() == 7 || bgVar.getState() == -1) {
                    this.b.g.a(bgVar);
                } else {
                    this.b.g.a(bgVar);
                    this.b.k.c(bgVar);
                }
            }
        });
    }

    public void b(bg bgVar) {
        try {
            this.l.a(bgVar, this.i, null);
        } catch (dk e) {
            e.printStackTrace();
        }
    }

    public void c(bg bgVar) {
        this.f.a(bgVar);
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.obj = bgVar;
        this.e.sendMessage(obtainMessage);
    }

    public void b() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            bg bgVar = (bg) it.next();
            if (bgVar.c().equals(bgVar.c) || bgVar.c().equals(bgVar.b)) {
                bgVar.f();
            }
        }
    }

    public void c() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            bg bgVar = (bg) it.next();
            if (bgVar.c().equals(bgVar.c)) {
                bgVar.f();
                return;
            }
        }
    }

    public void d() {
        if (!(this.n == null || this.n.isShutdown())) {
            this.n.shutdownNow();
        }
        if (this.h != null) {
            if (this.h.isAlive()) {
                this.h.interrupt();
            }
            this.h = null;
        }
        this.l.b();
        this.f.g();
        e();
        j = null;
        b = true;
    }

    private bg g(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            bg bgVar = (bg) it.next();
            if (str.equals(bgVar.getCity())) {
                return bgVar;
            }
        }
        return null;
    }

    private bg h(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            bg bgVar = (bg) it.next();
            if (str.equals(bgVar.getCode())) {
                return bgVar;
            }
        }
        return null;
    }

    public void d(String str) throws AMapException {
        bg g = g(str);
        if (g != null) {
            g.setVersion(d);
            g.f();
            return;
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    public void e(String str) throws AMapException {
        bg h = h(str);
        if (h != null) {
            h.f();
            return;
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    public void d(bg bgVar) {
        this.l.a((bq) bgVar);
    }

    public void e(bg bgVar) {
        this.l.b(bgVar);
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    public void e() {
        this.k = null;
    }
}

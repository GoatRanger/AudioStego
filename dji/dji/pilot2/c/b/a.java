package dji.pilot2.c.b;

import dji.log.DJILogHelper;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot2.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class a implements j, k {
    public static final int K = -2;
    private static a L = null;
    private List<b> M;
    private dji.pilot2.c.a.a N;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (L == null) {
                L = new a();
            }
            aVar = L;
        }
        return aVar;
    }

    public a() {
        this.N = null;
        this.M = new ArrayList();
        this.N = new dji.pilot2.c.a.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void b(b bVar) {
                String str = j.x;
                if (bVar instanceof dji.pilot2.c.b.a.a.b.a) {
                    str = k.bP_;
                }
                HashMap hashMap = new HashMap();
                String str2 = j.A;
                if (DJINetWorkReceiver.a(b.a.a())) {
                    int a = bVar.a();
                    DJILogHelper.getInstance().LOGE("Lyric", "error code: " + a);
                    hashMap.put(str2, "" + a);
                    e.a(str, hashMap);
                    this.a.c(bVar);
                    return;
                }
                hashMap.put(j.z, "1");
                e.a(str, hashMap);
            }

            public void a(b bVar, String str) {
                String str2 = "";
                if (bVar instanceof dji.pilot2.c.b.a.a.a.a) {
                    e.b(j.w);
                    str2 = "photo";
                } else if (bVar instanceof dji.pilot2.c.b.a.a.b.a) {
                    e.b(k.bO_);
                    str2 = "video";
                }
                dji.pilot2.share.f.b.a(b.a.a(), dji.pilot2.utils.k.d(str2, str), "Android");
                this.a.c(bVar);
            }

            public void a(b bVar) {
            }

            public void c(b bVar) {
                this.a.c(bVar);
            }
        };
    }

    public synchronized b a(String str) {
        b bVar;
        if (!(this.M == null || str == null)) {
            for (b bVar2 : this.M) {
                if (bVar2 != null && str.equals(bVar2.i())) {
                    break;
                }
            }
        }
        bVar2 = null;
        return bVar2;
    }

    public void a(b bVar) {
        if (a(bVar.i()) == null && this.M != null) {
            bVar.a(this.N);
            synchronized (this) {
                this.M.add(bVar);
            }
        }
        bVar.a(this.N);
        bVar.g();
    }

    public void b(b bVar) {
        b a = a(bVar.i());
        DJILogHelper.getInstance().LOGI("Lyric", "remove target: " + a);
        if (a != null && this.M != null) {
            a.h();
            a.f();
        }
    }

    private synchronized void c(b bVar) {
        while (true) {
            b a = a(bVar.i());
            if (a != null) {
                if (this.M != null) {
                    this.M.remove(a);
                }
            }
        }
    }
}

package dji.sdksharedlib.d;

import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.c.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class c {
    private static final String a = "DJISDKCacheStoreLayer";
    private ConcurrentHashMap<dji.sdksharedlib.b.c, a> b = null;
    private g c = null;
    private Lock d = new ReentrantLock();

    public void a(g gVar) {
        this.c = gVar;
        this.b = new ConcurrentHashMap(300);
    }

    public void a() {
    }

    public a a(dji.sdksharedlib.b.c cVar) {
        if (cVar == null || cVar.g() || !this.b.containsKey(cVar)) {
            return null;
        }
        return (a) this.b.get(cVar);
    }

    public List<a> a(dji.sdksharedlib.b.c[] cVarArr) {
        if (cVarArr == null) {
            return null;
        }
        List<a> arrayList = new ArrayList();
        for (dji.sdksharedlib.b.c a : cVarArr) {
            a a2 = a(a);
            if (a2 == null) {
                return null;
            }
            arrayList.add(a2);
        }
        return arrayList;
    }

    public boolean a(a aVar, dji.sdksharedlib.b.c cVar) {
        this.d.lock();
        boolean z = false;
        if (!(aVar == null || cVar == null)) {
            try {
                if (!cVar.g()) {
                    a a = a(cVar);
                    this.b.put(cVar, aVar);
                    if (this.c != null) {
                        this.c.a(cVar, a, aVar);
                    }
                    z = true;
                }
            } catch (Throwable th) {
                this.d.unlock();
            }
        }
        this.d.unlock();
        return z;
    }

    public boolean b(dji.sdksharedlib.b.c cVar) {
        this.d.lock();
        boolean z = false;
        if (cVar != null) {
            try {
                if (this.b.containsKey(cVar)) {
                    a aVar = (a) this.b.get(cVar);
                    this.b.remove(cVar);
                    if (this.c != null) {
                        this.c.a(cVar, aVar, null);
                    }
                    z = true;
                }
            } catch (Throwable th) {
                this.d.unlock();
            }
        }
        this.d.unlock();
        return z;
    }

    public boolean a(String str, int i) {
        this.d.lock();
        try {
            Set<dji.sdksharedlib.b.c> keySet = this.b.keySet();
            List<dji.sdksharedlib.b.c> arrayList = new ArrayList();
            for (dji.sdksharedlib.b.c cVar : keySet) {
                if (cVar.b().equals(str) && cVar.c().intValue() == i) {
                    arrayList.add(cVar);
                }
            }
            boolean z = false;
            for (dji.sdksharedlib.b.c cVar2 : arrayList) {
                a aVar = (a) this.b.get(cVar2);
                this.b.remove(cVar2);
                if (this.c != null) {
                    this.c.a(cVar2, aVar, null);
                }
                z = true;
            }
            return z;
        } finally {
            this.d.unlock();
        }
    }

    public void b() {
        new Timer(true).scheduleAtFixedRate(new TimerTask(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                int nextInt = (new Random().nextInt(5) + 1) * 100;
                this.a.a(new a(Integer.valueOf(nextInt)), new a().a("battery/0/EnergyRemainingPercentage").a());
            }
        }, 0, 2000);
    }
}

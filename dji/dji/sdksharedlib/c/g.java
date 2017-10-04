package dji.sdksharedlib.c;

import dji.sdksharedlib.b.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class g {
    private static final String a = "DJISDKCacheListenerLayer";
    private ConcurrentHashMap<c, ConcurrentHashMap<d, f>> b = null;
    private ArrayList<a> c;
    private d d;
    private Lock e = new ReentrantLock();
    private List<b> f;
    private ReentrantLock g = new ReentrantLock();

    public interface a {
        void a(c cVar);

        void b(c cVar);
    }

    private class b implements Runnable {
        public d a;
        public c b;
        public dji.sdksharedlib.d.a c;
        public dji.sdksharedlib.d.a d;
        final /* synthetic */ g e;

        public b(g gVar) {
            this.e = gVar;
        }

        public void a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }

        public void run() {
            if (this.a != null) {
                this.a.onValueChange(this.b, this.c, this.d);
                if (this.e.f != null) {
                    a();
                    this.e.f.add(this);
                }
            }
        }
    }

    public void a(a aVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(aVar);
    }

    public void a() {
        this.b = new ConcurrentHashMap(300);
        this.f = new ArrayList();
    }

    public void b() {
        this.b = null;
        this.f = null;
    }

    public void a(d dVar) {
        this.d = dVar;
    }

    public boolean a(c cVar, d dVar, boolean z) {
        if (cVar == null || !cVar.h() || dVar == null) {
            return false;
        }
        this.e.lock();
        try {
            boolean z2;
            if (!this.b.containsKey(cVar)) {
                this.b.put(cVar, new ConcurrentHashMap());
            }
            if (((ConcurrentHashMap) this.b.get(cVar)).containsKey(dVar)) {
                RuntimeException runtimeException = new RuntimeException("the KeyPath and Listener had registed");
                z2 = false;
            } else {
                ((ConcurrentHashMap) this.b.get(cVar)).put(dVar, new f(z));
                b(cVar);
                z2 = true;
            }
            this.e.unlock();
            return z2;
        } catch (Throwable th) {
            this.e.unlock();
        }
    }

    public boolean a(c cVar, d dVar) {
        return a(cVar, dVar, true);
    }

    public boolean b(c cVar, d dVar) {
        if (cVar == null || !cVar.h() || dVar == null) {
            return false;
        }
        this.e.lock();
        try {
            boolean z;
            if (this.b.containsKey(cVar)) {
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.b.get(cVar);
                if (concurrentHashMap.containsKey(dVar)) {
                    concurrentHashMap.remove(dVar);
                    c(cVar);
                    z = true;
                    this.e.unlock();
                    return z;
                }
            }
            z = false;
            this.e.unlock();
            return z;
        } catch (Throwable th) {
            this.e.unlock();
        }
    }

    public void b(d dVar) {
        if (dVar != null) {
            this.e.lock();
            try {
                if (this.b != null) {
                    for (c cVar : this.b.keySet()) {
                        if (this.b.containsKey(cVar)) {
                            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.b.get(cVar);
                            if (concurrentHashMap.containsKey(dVar)) {
                                concurrentHashMap.remove(dVar);
                                c(cVar);
                            }
                        }
                    }
                    this.e.unlock();
                }
            } finally {
                this.e.unlock();
            }
        }
    }

    public int a(c cVar) {
        if (this.b.containsKey(cVar)) {
            return ((ConcurrentHashMap) this.b.get(cVar)).size();
        }
        return 0;
    }

    public void a(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar != null && cVar.h()) {
            this.e.lock();
            try {
                ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.b.get(cVar);
                if (concurrentHashMap != null) {
                    for (d dVar : concurrentHashMap.keySet()) {
                        dji.sdksharedlib.e.b.b(a(dVar, cVar, aVar, aVar2), ((f) ((ConcurrentHashMap) this.b.get(cVar)).get(dVar)).a());
                    }
                }
                this.e.unlock();
                if (this.d != null) {
                    this.d.onValueChange(cVar, aVar, aVar2);
                }
            } catch (Throwable th) {
                this.e.unlock();
            }
        }
    }

    private void b(c cVar) {
        if (this.c != null && this.c.size() != 0) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.a(cVar);
                }
            }
        }
    }

    private void c(c cVar) {
        if (this.c != null && this.c.size() != 0) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.b(cVar);
                }
            }
        }
    }

    private Runnable a(d dVar, c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        try {
            Runnable bVar;
            this.g.lock();
            if (this.f == null || this.f.size() <= 0) {
                bVar = new b(this);
            } else {
                bVar = (b) this.f.remove(0);
            }
            this.g.unlock();
            if (bVar == null) {
                bVar = new b(this);
            }
            bVar.a = dVar;
            bVar.b = cVar;
            bVar.c = aVar;
            bVar.d = aVar2;
            return bVar;
        } catch (Throwable th) {
            this.g.unlock();
        }
    }
}

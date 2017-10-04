package com.nokia.maps;

import com.nokia.maps.annotation.Internal;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

@Internal
public class ar {
    private CopyOnWriteArrayList<c> a = new CopyOnWriteArrayList();

    private interface c {
    }

    @Internal
    public interface a extends c {
        boolean a(Object obj, Object obj2);
    }

    @Internal
    public interface b extends c {
        boolean a(Object obj, Object obj2, Object obj3);
    }

    @Internal
    public interface d extends a {
    }

    @Internal
    public interface e extends b {
    }

    @Internal
    public synchronized void a(c cVar) {
        if (cVar != null) {
            this.a.addIfAbsent(cVar);
        }
    }

    @Internal
    public synchronized void b(c cVar) {
        if (cVar != null) {
            this.a.remove(cVar);
        }
    }

    @Internal
    public synchronized boolean a(final Object obj, final Object obj2) {
        boolean z;
        z = false;
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                boolean z2;
                final c cVar = (c) it.next();
                if (cVar instanceof d) {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ ar d;

                        public void run() {
                            this.d.a((a) cVar, obj, obj2);
                        }
                    });
                    z2 = z;
                } else if ((cVar instanceof a) && a((a) cVar, obj, obj2)) {
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    @Internal
    public synchronized boolean a(Object obj, Object obj2, Object obj3) {
        boolean z;
        z = false;
        try {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                boolean z2;
                final c cVar = (c) it.next();
                if (cVar instanceof e) {
                    final Object obj4 = obj;
                    final Object obj5 = obj2;
                    final Object obj6 = obj3;
                    ez.a(new Runnable(this) {
                        final /* synthetic */ ar e;

                        public void run() {
                            this.e.a((b) cVar, obj4, obj5, obj6);
                        }
                    });
                    z2 = z;
                } else if ((cVar instanceof b) && a((b) cVar, obj, obj2, obj3)) {
                    z2 = true;
                } else {
                    z2 = z;
                }
                z = z2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

    @Internal
    public synchronized void a() {
        this.a.clear();
    }

    @Internal
    public synchronized boolean b() {
        return this.a.isEmpty();
    }

    private boolean a(a aVar, Object obj, Object obj2) {
        if (obj2 == null || !(obj2 instanceof Object)) {
            return aVar.a(obj, obj2);
        }
        boolean a;
        synchronized (obj2) {
            a = aVar.a(obj, obj2);
        }
        return a;
    }

    private boolean a(b bVar, Object obj, Object obj2, Object obj3) {
        if (obj2 == null || !(obj2 instanceof Object)) {
            return bVar.a(obj, obj2, obj3);
        }
        boolean a;
        synchronized (obj2) {
            a = bVar.a(obj, obj2, obj3);
        }
        return a;
    }
}

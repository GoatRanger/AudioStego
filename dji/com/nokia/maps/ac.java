package com.nokia.maps;

import android.annotation.SuppressLint;
import com.here.android.mpa.cluster.ClusterTheme;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ac extends Thread {
    private static final String a = ac.class.getSimpleName();
    private static volatile int b;
    private final WeakReference<MapImpl> c;
    private final BlockingQueue<a> d = new LinkedBlockingQueue();
    private Set<Cluster> e = new HashSet();
    private final ClusterRenderer f;
    private volatile ClusterTheme g;
    @SuppressLint({"UseSparseArrays"})
    private final Map<Integer, MapMarker> h = new HashMap();
    private final af i = new GridBasedAlgorithm();
    private boolean j;
    private volatile boolean k;

    private static class a {
        public final a a;
        public final Object b;

        enum a {
            ADD,
            ADD_BATCH,
            REMOVE,
            REMOVE_BATCH,
            MOVE,
            CLUSTER;

            public static a[] a() {
                return (a[]) g.clone();
            }
        }

        a(a aVar, Object obj) {
            this.a = aVar;
            this.b = obj;
        }

        a(a aVar) {
            this(aVar, null);
        }
    }

    ac(MapImpl mapImpl, ClusterTheme clusterTheme) {
        StringBuilder append = new StringBuilder().append("worker");
        int i = b;
        b = i + 1;
        super(append.append(i).toString());
        this.c = new WeakReference(mapImpl);
        this.g = clusterTheme;
        this.f = new ClusterRenderer(mapImpl);
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                a aVar = (a) this.d.take();
                switch (aVar.a) {
                    case ADD:
                        d((MapMarker) aVar.b);
                        break;
                    case ADD_BATCH:
                        d((Collection) aVar.b);
                        break;
                    case REMOVE:
                        c((MapMarker) aVar.b);
                        break;
                    case REMOVE_BATCH:
                        c((Collection) aVar.b);
                        break;
                    case MOVE:
                        try {
                            Thread.sleep(32);
                            this.j = true;
                            break;
                        } catch (InterruptedException e) {
                            bj.e(a, "worker interrupted at move", new Object[0]);
                            interrupt();
                            break;
                        }
                }
                if (this.d.isEmpty()) {
                    MapImpl mapImpl = (MapImpl) this.c.get();
                    if (mapImpl == null) {
                        bj.e(a, "map has been GC-d", new Object[0]);
                        this.f.clearNative();
                        return;
                    }
                    a(mapImpl.getZoomLevel(), this.j);
                    this.j = false;
                    b(this.g);
                }
            } catch (InterruptedException e2) {
                bj.e(a, "worker interrupted at wait", new Object[0]);
            }
        }
        this.f.clearNative();
    }

    private void c(MapMarker mapMarker) {
        Collection linkedList = new LinkedList();
        linkedList.add(mapMarker);
        while (!this.d.isEmpty() && ((a) this.d.peek()).a == a.REMOVE) {
            linkedList.add((MapMarker) ((a) this.d.poll()).b);
        }
        c(linkedList);
    }

    private void c(Collection<MapMarker> collection) {
        MapImpl mapImpl = (MapImpl) this.c.get();
        if (mapImpl == null) {
            bj.e(a, "map has been GC-d", new Object[0]);
            return;
        }
        for (MapMarker f : collection) {
            f(f);
        }
        mapImpl.d(new ArrayList(collection));
        this.j = true;
    }

    private void d(MapMarker mapMarker) {
        Collection linkedList = new LinkedList();
        linkedList.add(mapMarker);
        while (!this.d.isEmpty() && ((a) this.d.peek()).a == a.ADD) {
            linkedList.add((MapMarker) ((a) this.d.poll()).b);
        }
        d(linkedList);
    }

    private void d(Collection<MapMarker> collection) {
        MapImpl mapImpl = (MapImpl) this.c.get();
        if (mapImpl == null) {
            bj.e(a, "map has been GC-d", new Object[0]);
            return;
        }
        for (MapMarker mapMarker : collection) {
            mapMarker.setVisible(false);
            e(mapMarker);
        }
        mapImpl.c(new ArrayList(collection));
        this.j = true;
    }

    private void e(MapMarker mapMarker) {
        this.h.put(Integer.valueOf(MapObjectImpl.d((MapObject) mapMarker).hashCode()), mapMarker);
    }

    private boolean f(MapMarker mapMarker) {
        return this.h.remove(Integer.valueOf(MapObjectImpl.d((MapObject) mapMarker).hashCode())) != null;
    }

    private synchronized void b(ClusterTheme clusterTheme) {
        if (this.k) {
            this.f.clearNative();
            this.k = false;
        }
        this.f.a(this.e, ClusterThemeImpl.a(clusterTheme));
    }

    private synchronized void a(double d, boolean z) {
        this.e = new HashSet(this.i.a(this.h.values(), d, z));
        for (Cluster a : this.e) {
            a.a(this.h);
        }
    }

    public synchronized Set<Cluster> a() {
        return this.e;
    }

    public void a(ClusterTheme clusterTheme) {
        this.k = (this.g != clusterTheme ? 1 : 0) | this.k;
        this.g = clusterTheme;
        if (this.d.isEmpty()) {
            this.d.add(new a(a.CLUSTER));
        }
    }

    public void a(MapMarker mapMarker) {
        this.d.add(new a(a.ADD, mapMarker));
    }

    public void a(Collection<MapMarker> collection) {
        this.d.add(new a(a.ADD_BATCH, collection));
    }

    public void b(MapMarker mapMarker) {
        this.d.add(new a(a.REMOVE, mapMarker));
    }

    public void b(Collection<MapMarker> collection) {
        this.d.add(new a(a.REMOVE_BATCH, collection));
    }

    public void b() {
        if (this.d.isEmpty()) {
            this.d.add(new a(a.MOVE));
        }
    }
}

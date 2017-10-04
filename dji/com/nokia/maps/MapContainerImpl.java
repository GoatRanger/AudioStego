package com.nokia.maps;

import com.here.android.mpa.mapping.MapContainer;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapObject.Type;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Online
public class MapContainerImpl extends MapObjectImpl {
    private static String a = MapContainerImpl.class.getSimpleName();
    private static am<MapContainer, MapContainerImpl> d = null;
    private Hashtable<Integer, MapObject> e = new Hashtable();
    private List<Integer> f = new ArrayList();

    private native boolean addMapObjectNative(MapObjectImpl mapObjectImpl);

    private native void createNative();

    private native MapObject[] getAllMapObjectsNative();

    private native boolean isSupportedMapObject(MapObjectImpl mapObjectImpl);

    private native boolean removeAllMapObjectsNative();

    private native boolean removeMapObjectNative(MapObjectImpl mapObjectImpl);

    static {
        ce.a(MapContainer.class);
    }

    static MapContainer a(MapContainerImpl mapContainerImpl) {
        if (mapContainerImpl != null) {
            return (MapContainer) d.a(mapContainerImpl);
        }
        return null;
    }

    public static void a(am<MapContainer, MapContainerImpl> amVar) {
        d = amVar;
    }

    public MapContainerImpl() {
        createNative();
    }

    @OnlineNative
    MapContainerImpl(int i) {
        super(i);
    }

    public synchronized boolean a(MapObject mapObject) {
        boolean e;
        MapImpl p = p();
        if (p != null) {
            synchronized (p) {
                e = e(mapObject);
            }
        } else {
            e = e(mapObject);
        }
        return e;
    }

    private boolean e(MapObject mapObject) {
        MapObjectImpl d = MapObjectImpl.d(mapObject);
        if (a(d) || mapObject == null || !isSupportedMapObject(d)) {
            return false;
        }
        boolean z;
        synchronized (this.e) {
            boolean z2;
            if (this.e.contains(mapObject)) {
                bj.b(a, "WARNING: MapObject(0x%08x) already existed in the container.", new Object[]{Integer.valueOf(mapObject.hashCode())});
                z2 = false;
                z = true;
            } else {
                int i;
                MapImpl p = p();
                if (p != null) {
                    synchronized (p) {
                        z2 = addMapObjectNative(d);
                    }
                    d.a(p);
                } else {
                    z2 = addMapObjectNative(d);
                }
                if (z2) {
                    this.e.put(Integer.valueOf(d.hashCode()), mapObject);
                    this.f.add(Integer.valueOf(d.hashCode()));
                    i = 1;
                } else {
                    z = false;
                }
                int i2 = i;
                z = z2;
                int i3 = i2;
            }
        }
        if (!z) {
            bj.c(a, "ERROR: failed to add MapObject(0x%08x)", new Object[]{Integer.valueOf(mapObject.hashCode())});
        }
        if (i3 != 0) {
            o();
        }
        return z;
    }

    public synchronized boolean b(MapObject mapObject) {
        boolean f;
        MapImpl p = p();
        if (p != null) {
            synchronized (p) {
                f = f(mapObject);
            }
        } else {
            f = f(mapObject);
        }
        return f;
    }

    private boolean f(MapObject mapObject) {
        boolean z = false;
        if (mapObject != null) {
            MapObjectImpl d = MapObjectImpl.d(mapObject);
            if (!a(d)) {
                synchronized (this.e) {
                    if (this.e.get(Integer.valueOf(d.hashCode())) != null) {
                        z = b(d);
                        if (z) {
                            this.e.remove(Integer.valueOf(d.hashCode()));
                            this.f.remove(Integer.valueOf(d.hashCode()));
                        }
                    }
                }
            }
        }
        return z;
    }

    private static boolean a(MapObjectImpl mapObjectImpl) {
        return mapObjectImpl instanceof ca;
    }

    private boolean b(MapObjectImpl mapObjectImpl) {
        boolean z;
        int i;
        MapImpl p = p();
        if (p != null) {
            boolean removeMapObjectNative;
            synchronized (p) {
                removeMapObjectNative = removeMapObjectNative(mapObjectImpl);
            }
            z = removeMapObjectNative;
        } else {
            z = removeMapObjectNative(mapObjectImpl);
        }
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        if (!z) {
            bj.c(a, "ERROR: failed to remove MapObject(0x%08x)", new Object[]{Integer.valueOf(mapObjectImpl.hashCode())});
        }
        if (i != 0) {
            o();
        }
        return z;
    }

    public synchronized boolean a() {
        boolean d;
        MapImpl p = p();
        if (p != null) {
            synchronized (p) {
                d = d();
            }
        } else {
            d = d();
        }
        return d;
    }

    private boolean d() {
        boolean z;
        int i = 1;
        synchronized (this.e) {
            if (this.e.isEmpty()) {
                z = true;
                i = 0;
            } else {
                c();
                MapImpl p = p();
                if (p != null) {
                    synchronized (p) {
                        z = removeAllMapObjectsNative();
                    }
                } else {
                    z = removeAllMapObjectsNative();
                }
                this.e.clear();
                this.f.clear();
                if (!z) {
                    for (MapObject mapObject : getAllMapObjectsNative()) {
                        this.e.put(Integer.valueOf(MapObjectImpl.d(mapObject).hashCode()), mapObject);
                        this.f.add(Integer.valueOf(MapObjectImpl.d(mapObject).hashCode()));
                    }
                }
            }
        }
        if (!z) {
            bj.c(a, "ERROR: failed to remove all map objects", new Object[0]);
        }
        if (i != 0) {
            o();
        }
        return z;
    }

    public synchronized void a(MapImpl mapImpl) {
        super.a(mapImpl);
        synchronized (this.e) {
            for (MapObject d : this.e.values()) {
                MapObjectImpl.d(d).a(mapImpl);
            }
        }
    }

    public synchronized List<MapObject> b() {
        List<MapObject> arrayList;
        arrayList = new ArrayList();
        synchronized (this.e) {
            for (Integer num : this.f) {
                MapObject mapObject = (MapObject) this.e.get(num);
                if (mapObject != null) {
                    arrayList.add(mapObject);
                }
            }
        }
        return arrayList;
    }

    MapObject c(MapObject mapObject) {
        MapObject mapObject2;
        synchronized (this.e) {
            mapObject2 = (MapObject) this.e.get(Integer.valueOf(MapObjectImpl.d(mapObject).hashCode()));
        }
        return mapObject2;
    }

    void c() {
        b(this);
    }

    private static boolean b(MapContainerImpl mapContainerImpl) {
        synchronized (mapContainerImpl.e) {
            for (Integer num : mapContainerImpl.f) {
                MapObject mapObject = (MapObject) mapContainerImpl.e.get(num);
                if (mapObject.getType() == Type.MARKER) {
                    MapMarker mapMarker = (MapMarker) mapObject;
                    if (mapMarker.isInfoBubbleVisible()) {
                        mapMarker.hideInfoBubble();
                        return true;
                    }
                } else if (mapObject.getType() == Type.CONTAINER && b((MapContainerImpl) MapObjectImpl.d(mapObject))) {
                    return true;
                }
            }
            return false;
        }
    }
}

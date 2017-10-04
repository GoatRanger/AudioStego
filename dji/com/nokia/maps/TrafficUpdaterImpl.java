package com.nokia.maps;

import android.util.Pair;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.guidance.TrafficUpdater;
import com.here.android.mpa.guidance.TrafficUpdater.Error;
import com.here.android.mpa.guidance.TrafficUpdater.GetEventsListener;
import com.here.android.mpa.guidance.TrafficUpdater.Listener;
import com.here.android.mpa.guidance.TrafficUpdater.RequestInfo;
import com.here.android.mpa.guidance.TrafficUpdater.RequestState;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteElements;
import com.here.odnp.config.OdnpConfigStatic;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

@HybridPlus
public class TrafficUpdaterImpl extends BaseNativeObject {
    private static final String a = TrafficUpdaterImpl.class.getSimpleName();
    private static k<TrafficUpdater, TrafficUpdaterImpl> b = null;
    private static volatile TrafficUpdaterImpl c;
    private final Semaphore d = new Semaphore(0, true);
    private Map<Long, Pair<Listener, b>> e = new HashMap();
    private boolean f = false;
    private volatile boolean g = false;
    private List<a> h = new ArrayList();
    private List<WeakReference<MapImpl>> i = new ArrayList();
    private c j = null;

    private static class a extends Thread {
        private Route a;
        private RouteElement b;
        private RouteElements c;
        private List<RouteElement> d;
        private GetEventsListener e;

        public a(Route route, GetEventsListener getEventsListener) {
            a(route, null, null, null, getEventsListener);
        }

        public a(RouteElement routeElement, GetEventsListener getEventsListener) {
            a(null, routeElement, null, null, getEventsListener);
        }

        public a(RouteElements routeElements, GetEventsListener getEventsListener) {
            a(null, null, routeElements, null, getEventsListener);
        }

        public a(List<RouteElement> list, GetEventsListener getEventsListener) {
            a(null, null, null, list, getEventsListener);
        }

        private void a(Route route, RouteElement routeElement, RouteElements routeElements, List<RouteElement> list, GetEventsListener getEventsListener) {
            this.a = route;
            this.b = routeElement;
            this.c = routeElements;
            this.d = list;
            this.e = getEventsListener;
            setName("Traffic getEvents worker");
            setPriority(1);
        }

        public void run() {
            Error a;
            boolean z;
            Error error = Error.UNKNOWN;
            final List<TrafficEvent> arrayList = new ArrayList();
            if (this.a != null) {
                a = TrafficUpdaterImpl.a().a(this.a, (List) arrayList);
                z = true;
            } else if (this.b != null) {
                a = TrafficUpdaterImpl.a().a(this.b, (List) arrayList);
                z = false;
            } else if (this.c != null) {
                a = TrafficUpdaterImpl.a().a(this.c, (List) arrayList);
                z = false;
            } else if (this.d != null) {
                a = TrafficUpdaterImpl.a().a(this.d, (List) arrayList);
                z = false;
            } else {
                a = Error.INVALID_OPERATION;
                z = false;
            }
            final GetEventsListener getEventsListener = this.e;
            if (a == Error.NONE) {
                int i = 0;
                int i2 = 0;
                for (TrafficEvent trafficEvent : arrayList) {
                    int i3;
                    if (trafficEvent.isFlow()) {
                        i3 = i;
                        i = i2 + 1;
                    } else if (trafficEvent.isIncident()) {
                        i3 = i + 1;
                        i = i2;
                    } else {
                        i3 = i;
                        i = i2;
                    }
                    i2 = i;
                    i = i3;
                }
                if (z) {
                    l.a().a(true, i2, i);
                } else {
                    l.a().b(true, i2, i);
                }
            }
            ce.a(new Runnable(this) {
                final /* synthetic */ a d;

                public void run() {
                    getEventsListener.onComplete(arrayList, a);
                    TrafficUpdaterImpl.a().h.remove(this.d);
                }
            });
        }
    }

    private enum b {
        GEOCOORD,
        ROUTE,
        ROUTE_ELEMENTS;

        public static b[] a() {
            return (b[]) d.clone();
        }
    }

    private class c extends Thread {
        final /* synthetic */ TrafficUpdaterImpl a;

        public c(TrafficUpdaterImpl trafficUpdaterImpl) {
            this.a = trafficUpdaterImpl;
            setName("Traffic");
            setPriority(8);
        }

        public void run() {
            while (true) {
                try {
                    long j;
                    this.a.d.acquire();
                    this.a.d.drainPermits();
                    this.a.g = this.a.pollTraffic();
                    synchronized (this.a.e) {
                        if (this.a.g || !this.a.e.isEmpty()) {
                            j = 1000;
                        } else {
                            j = OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL;
                        }
                    }
                    sleep(j);
                    this.a.c();
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private native synchronized int activateTrafficSourceNative(ep epVar);

    private native boolean cancelRequestNative(long j);

    private native void createNative();

    private native synchronized ep getDefaultTrafficSourceNative();

    private native synchronized int getEventsForRoute(RouteImpl routeImpl, List<TrafficEventImpl> list);

    private native synchronized int getEventsForRouteElement(RouteElementImpl routeElementImpl, List<TrafficEventImpl> list);

    private native synchronized int getEventsForRouteElementList(List<RouteElementImpl> list, List<TrafficEventImpl> list2);

    private native synchronized int getEventsForRouteElements(RouteElementsImpl routeElementsImpl, List<TrafficEventImpl> list);

    private native synchronized int injectBinaryNative(ep epVar, byte[] bArr);

    private native synchronized boolean pollTraffic();

    private native int requestTrafficAtNative(GeoCoordinateImpl geoCoordinateImpl, int i, long j);

    private native int requestTrafficOnRouteElements(RouteElementsImpl routeElementsImpl, long j);

    private native int requestTrafficOnRouteNative(RouteImpl routeImpl, int i, long j);

    public native synchronized void clear();

    public native synchronized void reset();

    public native boolean setRefreshInterval(int i);

    public static void a(k<TrafficUpdater, TrafficUpdaterImpl> kVar) {
        b = kVar;
    }

    public static TrafficUpdaterImpl a() {
        if (c == null) {
            synchronized (TrafficUpdaterImpl.class) {
                if (c == null) {
                    c = new TrafficUpdaterImpl();
                }
            }
        }
        return c;
    }

    public TrafficUpdaterImpl() {
        BaseNativeObject.u();
        createNative();
        this.j = new c(this);
        this.j.start();
    }

    public void a(boolean z, MapImpl mapImpl) {
        if (this.f != z) {
            this.f = z;
            if (this.f) {
                this.d.release();
            }
        }
        if (mapImpl != null) {
            synchronized (this.i) {
                Object obj;
                for (WeakReference weakReference : this.i) {
                    MapImpl mapImpl2 = (MapImpl) weakReference.get();
                    if (mapImpl2 != null && mapImpl2 == mapImpl) {
                        if (!z) {
                            this.i.remove(weakReference);
                        }
                        obj = 1;
                        if (z && r0 == null) {
                            this.i.add(new WeakReference(mapImpl));
                        }
                    }
                }
                obj = null;
                this.i.add(new WeakReference(mapImpl));
            }
        }
    }

    public boolean b() {
        return this.f;
    }

    public RequestInfo a(GeoCoordinate geoCoordinate, Listener listener) {
        return a(geoCoordinate, 10, listener);
    }

    public synchronized RequestInfo a(GeoCoordinate geoCoordinate, int i, Listener listener) {
        Error error;
        long j;
        dy.a((Object) listener, "Listener is null");
        error = Error.NONE;
        j = 0;
        if (geoCoordinate == null || !geoCoordinate.isValid() || i <= 0) {
            error = Error.INVALID_PARAMETERS;
        } else if (this.f) {
            j = a(listener, b.GEOCOORD);
            error = b(requestTrafficAtNative(GeoCoordinateImpl.get(geoCoordinate), i, j));
            if (error == Error.NONE) {
                this.d.release();
            } else {
                b(j);
                bj.e(a, error.name(), new Object[0]);
            }
        }
        return eo.a(error, j);
    }

    public RequestInfo a(Route route, Listener listener) {
        return a(route, 10, listener);
    }

    public synchronized RequestInfo a(Route route, int i, Listener listener) {
        Error error;
        long j;
        dy.a((Object) listener, "Listener is null");
        error = Error.NONE;
        j = 0;
        if (route == null || i <= 0) {
            error = Error.INVALID_PARAMETERS;
        } else {
            RouteImpl a = RouteImpl.a(route);
            if (a(a.a())) {
                error = Error.UNSUPPORTED_ROUTE_MODE;
            } else if (this.f) {
                j = a(listener, b.ROUTE);
                error = b(requestTrafficOnRouteNative(a, i, j));
                if (error == Error.NONE) {
                    this.d.release();
                } else {
                    b(j);
                    bj.c(a, "request failed with: %s", new Object[]{error.name()});
                }
            }
        }
        return eo.a(error, j);
    }

    public synchronized RequestInfo a(RouteElements routeElements, Listener listener) {
        Error error;
        long j;
        dy.a((Object) listener, "Listener is null");
        error = Error.NONE;
        j = 0;
        if (routeElements == null) {
            error = Error.INVALID_PARAMETERS;
        } else if (a(RouteElementsImpl.a(routeElements).a())) {
            error = Error.UNSUPPORTED_ROUTE_MODE;
        } else if (this.f) {
            j = a(listener, b.ROUTE_ELEMENTS);
            error = b(requestTrafficOnRouteElements(RouteElementsImpl.a(routeElements), j));
            if (error == Error.NONE) {
                this.d.release();
            } else {
                b(j);
                bj.e(a, error.name(), new Object[0]);
            }
        }
        return eo.a(error, j);
    }

    public synchronized boolean a(long j) {
        boolean cancelRequestNative;
        cancelRequestNative = cancelRequestNative(j);
        if (cancelRequestNative) {
            b(j);
        }
        return cancelRequestNative;
    }

    public void a(Route route, final GetEventsListener getEventsListener) {
        if (a(RouteImpl.a(route).a())) {
            ce.a(new Runnable(this) {
                final /* synthetic */ TrafficUpdaterImpl b;

                public void run() {
                    getEventsListener.onComplete(new ArrayList(), Error.UNSUPPORTED_ROUTE_MODE);
                }
            });
            return;
        }
        a aVar = new a(route, getEventsListener);
        aVar.start();
        this.h.add(aVar);
    }

    public synchronized Error a(Route route, List<TrafficEvent> list) {
        Error error;
        dy.a((Object) list, "List<TrafficEvent> is null");
        error = Error.NONE;
        if (a(RouteImpl.a(route).a())) {
            error = Error.UNSUPPORTED_ROUTE_MODE;
        } else {
            List arrayList = new ArrayList();
            error = b(getEventsForRoute(RouteImpl.a(route), arrayList));
            b(arrayList, list);
        }
        return error;
    }

    public void a(RouteElement routeElement, final GetEventsListener getEventsListener) {
        if (a(RouteElementImpl.a(routeElement).a())) {
            ce.a(new Runnable(this) {
                final /* synthetic */ TrafficUpdaterImpl b;

                public void run() {
                    getEventsListener.onComplete(new ArrayList(), Error.UNSUPPORTED_ROUTE_MODE);
                }
            });
            return;
        }
        a aVar = new a(routeElement, getEventsListener);
        aVar.start();
        this.h.add(aVar);
    }

    public Error a(RouteElement routeElement, List<TrafficEvent> list) {
        dy.a((Object) list, "List<TrafficEvent> is null");
        Error error = Error.NONE;
        if (a(RouteElementImpl.a(routeElement).a())) {
            return Error.UNSUPPORTED_ROUTE_MODE;
        }
        List arrayList = new ArrayList();
        error = b(getEventsForRouteElement(RouteElementImpl.a(routeElement), arrayList));
        b(arrayList, list);
        return error;
    }

    public void a(RouteElements routeElements, final GetEventsListener getEventsListener) {
        if (a(RouteElementsImpl.a(routeElements).a())) {
            ce.a(new Runnable(this) {
                final /* synthetic */ TrafficUpdaterImpl b;

                public void run() {
                    getEventsListener.onComplete(new ArrayList(), Error.UNSUPPORTED_ROUTE_MODE);
                }
            });
            return;
        }
        a aVar = new a(routeElements, getEventsListener);
        aVar.start();
        this.h.add(aVar);
    }

    public Error a(RouteElements routeElements, List<TrafficEvent> list) {
        dy.a((Object) list, "List<TrafficEvent> is null");
        Error error = Error.NONE;
        if (a(RouteElementsImpl.a(routeElements).a())) {
            return Error.UNSUPPORTED_ROUTE_MODE;
        }
        List arrayList = new ArrayList();
        error = b(getEventsForRouteElements(RouteElementsImpl.a(routeElements), arrayList));
        b(arrayList, list);
        return error;
    }

    public void a(List<RouteElement> list, final GetEventsListener getEventsListener) {
        if (list.isEmpty() || !a(RouteElementImpl.a((RouteElement) list.get(0)).a())) {
            a aVar = new a((List) list, getEventsListener);
            aVar.start();
            this.h.add(aVar);
            return;
        }
        ce.a(new Runnable(this) {
            final /* synthetic */ TrafficUpdaterImpl b;

            public void run() {
                getEventsListener.onComplete(new ArrayList(), Error.UNSUPPORTED_ROUTE_MODE);
            }
        });
    }

    public Error a(List<RouteElement> list, List<TrafficEvent> list2) {
        dy.a((Object) list2, "List<TrafficEvent> is null");
        Error error = Error.NONE;
        if (!list.isEmpty() && a(RouteElementImpl.a((RouteElement) list.get(0)).a())) {
            return Error.UNSUPPORTED_ROUTE_MODE;
        }
        List arrayList = new ArrayList();
        error = b(getEventsForRouteElementList(RouteElementImpl.a((List) list), arrayList));
        b(arrayList, list2);
        return error;
    }

    private boolean a(com.nokia.maps.RouteImpl.a aVar) {
        return aVar == com.nokia.maps.RouteImpl.a.ENHANCED_TRANSIT_ROUTE || aVar == com.nokia.maps.RouteImpl.a.URBAN_MOBILITY_ROUTE;
    }

    private static void b(List<TrafficEventImpl> list, List<TrafficEvent> list2) {
        for (TrafficEventImpl a : list) {
            list2.add(TrafficEventImpl.a(a));
        }
    }

    @HybridPlusNative
    private synchronized void statusChanged(final long j, final int i) {
        ce.a(new Runnable(this) {
            final /* synthetic */ TrafficUpdaterImpl c;

            public void run() {
                synchronized (this.c.e) {
                    Pair pair = (Pair) this.c.e.remove(Long.valueOf(j));
                    Listener listener = (Listener) pair.first;
                    b bVar = (b) pair.second;
                }
                if (listener != null) {
                    RequestState a = TrafficUpdaterImpl.c(i);
                    listener.onStatusChanged(a);
                    if (bVar != null && a == RequestState.DONE) {
                        switch (bVar) {
                            case GEOCOORD:
                                l.a().f();
                                return;
                            case ROUTE:
                                l.a().a(false, 0, 0);
                                return;
                            case ROUTE_ELEMENTS:
                                l.a().b(false, 0, 0);
                                return;
                            default:
                                return;
                        }
                    }
                }
            }
        });
    }

    @HybridPlusNative
    private void redraw() {
        ce.a(new Runnable(this) {
            final /* synthetic */ TrafficUpdaterImpl a;

            {
                this.a = r1;
            }

            public void run() {
                synchronized (this.a.i) {
                    for (WeakReference weakReference : this.a.i) {
                        MapImpl mapImpl = (MapImpl) weakReference.get();
                        if (mapImpl != null) {
                            mapImpl.redraw();
                        }
                    }
                }
            }
        });
    }

    private void c() {
        if (this.f) {
            this.d.release();
        }
    }

    private long a(Listener listener, b bVar) {
        long currentTimeMillis;
        synchronized (this.e) {
            currentTimeMillis = System.currentTimeMillis();
            while (this.e.containsKey(Long.valueOf(currentTimeMillis))) {
                currentTimeMillis++;
            }
            this.e.put(Long.valueOf(currentTimeMillis), new Pair(listener, bVar));
        }
        return currentTimeMillis;
    }

    private void b(long j) {
        synchronized (this.e) {
            this.e.remove(Long.valueOf(j));
        }
    }

    private static Error b(int i) {
        switch (i) {
            case 0:
                return Error.NONE;
            case 1:
                return Error.INVALID_PARAMETERS;
            case 2:
                return Error.OUT_OF_MEMORY;
            case 3:
                return Error.INVALID_OPERATION;
            case 4:
                return Error.REQUEST_FAILED;
            case 5:
                return Error.INVALID_CREDENTIALS;
            case 7:
                return Error.UNSUPPORTED_ROUTE_MODE;
            case 8:
                return Error.OPERATION_NOT_ALLOWED;
            default:
                return Error.UNKNOWN;
        }
    }

    private static RequestState c(int i) {
        switch (i) {
            case 1:
                return RequestState.DONE;
            default:
                return RequestState.ERROR;
        }
    }
}

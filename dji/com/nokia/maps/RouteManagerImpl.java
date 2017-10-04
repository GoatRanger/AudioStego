package com.nokia.maps;

import com.here.android.mpa.routing.DynamicPenalty;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteManager.Error;
import com.here.android.mpa.routing.RouteManager.Listener;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.TimeType;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteResult.ViolatedOption;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

@Online
public class RouteManagerImpl extends BaseNativeObject {
    private static final String a = RouteManagerImpl.class.getSimpleName();
    private static final String e = RouteManagerImpl.class.getName();
    private static k<RouteManager, RouteManagerImpl> f = null;
    private boolean b = false;
    private ch c;
    private TransportMode d;
    private a g = a.AUTO;
    private a h = a.AUTO;
    private volatile Listener i = null;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] b = new int[com.here.android.mpa.routing.RouteManager.a.a().length];

        static {
            try {
                b[com.here.android.mpa.routing.RouteManager.a.c.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[com.here.android.mpa.routing.RouteManager.a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[com.here.android.mpa.routing.RouteManager.a.a.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            a = new int[a.a().length];
            try {
                a[a.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[a.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[a.ONLINE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private class TruckPermissionCheckListener implements ApplicationContext$c {
        final /* synthetic */ RouteManagerImpl a;
        private final RoutePlan b;
        private final Listener c;

        private TruckPermissionCheckListener(RouteManagerImpl routeManagerImpl, RoutePlan routePlan, Listener listener) {
            this.a = routeManagerImpl;
            this.b = routePlan;
            this.c = listener;
        }

        @HybridPlusNative
        public void a() {
            ez.a(new Runnable(this) {
                final /* synthetic */ TruckPermissionCheckListener a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.c.onCalculateRouteFinished(Error.OPERATION_NOT_ALLOWED, new ArrayList());
                }
            });
        }

        @HybridPlusNative
        public void b() {
            synchronized (this.a) {
                this.a.a(true);
            }
            this.a.a(this.b, this.c);
        }
    }

    @Internal
    public enum a {
        AUTO,
        ONLINE,
        OFFLINE;

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    private native void createRouteManagerNative();

    private native void destroyRouteManagerNative();

    private native synchronized DynamicPenaltyImpl getDynamicPenaltyNative();

    private native synchronized int getTrafficPenaltyModeNative();

    private native void native_calculateRoute(RoutePlanImpl routePlanImpl);

    private native synchronized void setDynamicPenaltyNative(DynamicPenaltyImpl dynamicPenaltyImpl);

    private native synchronized void setForceOnlineNative(boolean z);

    native synchronized boolean getForceOnline();

    public native synchronized void native_cancel();

    public native synchronized void setTrafficPenaltyMode(int i);

    static {
        ce.a(RouteManager.class);
    }

    public static void a(k<RouteManager, RouteManagerImpl> kVar) {
        f = kVar;
    }

    public static RouteManagerImpl a(RouteManager routeManager) {
        return (RouteManagerImpl) f.a(routeManager);
    }

    public RouteManagerImpl() {
        BaseNativeObject.u();
        createRouteManagerNative();
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRouteManagerNative();
        }
    }

    @OnlineNative
    synchronized void progress(final int i) {
        int i2 = 0;
        synchronized (this) {
            String str = e;
            String str2 = "progress - percentage=%d m_routeManagerListener=%d";
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i);
            if (this.i != null) {
                i2 = this.i.hashCode();
            }
            objArr[1] = Integer.valueOf(i2);
            bj.a(str, str2, objArr);
            if (this.i == null) {
                bj.c(e, "progress - m_routeManagerListener should not be NULL", new Object[0]);
            } else if (MapSettings.l()) {
                ez.a(new Runnable(this) {
                    final /* synthetic */ RouteManagerImpl b;

                    public void run() {
                        if (this.b.i != null) {
                            this.b.i.onProgress(i);
                        }
                    }
                });
            } else {
                this.i.onProgress(i);
            }
        }
    }

    static EnumSet<ViolatedOption> a(int[] iArr) {
        EnumSet<ViolatedOption> noneOf = EnumSet.noneOf(ViolatedOption.class);
        for (int i : iArr) {
            noneOf.add(ViolatedOption.values()[i]);
        }
        return noneOf;
    }

    @OnlineNative
    synchronized void calculateRouteFinished(int i, RouteImpl[] routeImplArr, List<int[]> list) {
        int i2;
        int length;
        RouteImpl a;
        String str = e;
        String str2 = "errorCode=%d m_routeManagerListener=%d";
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        if (this.i == null) {
            i2 = 0;
        } else {
            i2 = this.i.hashCode();
        }
        objArr[1] = Integer.valueOf(i2);
        bj.a(str, str2, objArr);
        Error error = Error.values()[i];
        ArrayList arrayList = new ArrayList();
        if (routeImplArr != null) {
            TrafficPenaltyMode c = c();
            for (int i3 = 0; i3 < routeImplArr.length; i3++) {
                if (routeImplArr[i3] != null) {
                    eh ehVar = new eh();
                    routeImplArr[i3].a(c);
                    ehVar.a(routeImplArr[i3]);
                    EnumSet a2 = a((int[]) list.get(i3));
                    if (a2 == EnumSet.noneOf(ViolatedOption.class)) {
                        ehVar.a(null);
                    } else {
                        ehVar.a(a2);
                    }
                    arrayList.add(eh.a(ehVar));
                }
            }
        }
        a(error, arrayList);
        if (error == Error.NONE && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                RouteResult routeResult = (RouteResult) it.next();
                EnumSet violatedOptions = routeResult.getViolatedOptions();
                if (violatedOptions != null) {
                    if (violatedOptions.isEmpty()) {
                    }
                }
                length = routeResult.getRoute().getLength();
                a = RouteImpl.a(routeResult.getRoute());
            }
        }
        length = 0;
        a = null;
        l.a().a(this.d, a, this.g);
        if (!(ck.b() || this.c == null)) {
            boolean z;
            RouteOptions routeOptions;
            String str3;
            boolean g = g();
            Object obj = (g && getForceOnline()) ? 1 : null;
            if (error == Error.NONE) {
                z = true;
            } else {
                z = false;
            }
            String str4 = "route";
            String str5 = obj != null ? "online" : "offline";
            if (a != null) {
                routeOptions = a.c().getRouteOptions();
            } else {
                routeOptions = null;
            }
            String a3 = cj.a(routeOptions);
            String str6 = g ? "mmonline" : "mmoffline";
            if (z) {
                str3 = null;
            } else {
                str3 = String.valueOf(i);
            }
            this.c.a(cj.a(str4, str5, a3, str6, str3), ((double) length) / 1000.0d, z);
        }
        this.b = false;
        a(this.h);
        bj.a(e, "<< calculateRouteFinished - errorCode=%d", new Object[]{Integer.valueOf(i)});
    }

    private void a(final Error error, final ArrayList<RouteResult> arrayList) {
        if (MapSettings.l()) {
            ez.a(new Runnable(this) {
                final /* synthetic */ RouteManagerImpl c;

                public void run() {
                    this.c.b(error, arrayList);
                }
            });
        } else {
            b(error, arrayList);
        }
    }

    private void b(Error error, ArrayList<RouteResult> arrayList) {
        if (this.i != null) {
            Listener listener = this.i;
            this.i = null;
            listener.onCalculateRouteFinished(error, arrayList);
            bj.a(e, "calculateRouteFinished - setting m_routeManagerListener(%s) to NULL", new Object[]{Integer.valueOf(listener.hashCode())});
            return;
        }
        bj.c(e, "calculateRouteFinished - m_routeManagerListener should not be NULL.", new Object[0]);
    }

    public synchronized Error a(RouteManager routeManager, RoutePlan routePlan, Listener listener) {
        Error error;
        int i = 0;
        synchronized (this) {
            if (!ck.b()) {
                this.c = new ch();
            }
            String str = e;
            String str2 = ">> calculateRoute - m_routeManagerListener=%d";
            Object[] objArr = new Object[1];
            if (this.i != null) {
                i = this.i.hashCode();
            }
            objArr[0] = Integer.valueOf(i);
            bj.a(str, str2, objArr);
            if (routePlan == null || listener == null || routePlan.getRouteOptions().getTime(null) != TimeType.DEPARTURE) {
                error = Error.INVALID_PARAMETERS;
            } else {
                synchronized (routePlan) {
                    RoutePlan routePlan2 = new RoutePlan(routePlan);
                    this.d = routePlan2.getRouteOptions() != null ? routePlan2.getRouteOptions().getTransportMode() : TransportMode.CAR;
                    if (routePlan2.getWaypointCount() > 2 && routePlan2.getRouteOptions().getRouteCount() > 1) {
                        RouteOptions routeOptions = routePlan2.getRouteOptions();
                        routeOptions.setRouteCount(1);
                        routePlan2.setRouteOptions(routeOptions);
                    }
                    this.h = e();
                    if (this.d == TransportMode.TRUCK) {
                        if (g()) {
                            ApplicationContext.b().check(10, new TruckPermissionCheckListener(routePlan2, listener));
                            error = Error.NONE;
                        } else {
                            error = Error.NO_CONNECTIVITY;
                        }
                    } else if (this.d == TransportMode.PUBLIC_TRANSPORT) {
                        error = b(routeManager, routePlan2, listener);
                    } else {
                        error = a(routePlan2, listener);
                    }
                }
            }
        }
        return error;
    }

    private boolean g() {
        boolean z = false;
        try {
            z = MapsEngine.c().isOnline();
        } catch (Exception e) {
        }
        return z;
    }

    public synchronized Error a(RoutePlan routePlan, Listener listener) {
        Error error;
        if (this.b) {
            bj.c(e, "calculateRouteAsync - route calculation in progress.", new Object[0]);
            error = Error.INVALID_OPERATION;
        } else if (this.i != null) {
            bj.c(e, "calculateRouteAsync - last route calculation has not finished reporting to caller.", new Object[0]);
            error = Error.INVALID_OPERATION;
        } else {
            this.i = listener;
            bj.a(e, "calculateRouteAsync - setting m_routeManagerListener to %s", new Object[]{Integer.valueOf(this.i.hashCode())});
            if (this.g == a.AUTO) {
                try {
                    if (g() && (a(routePlan) == TransportMode.PUBLIC_TRANSPORT || a(routePlan) == TransportMode.TRUCK || a(routePlan) == TransportMode.BICYCLE || c() != TrafficPenaltyMode.DISABLED)) {
                        setForceOnlineNative(true);
                    } else {
                        setForceOnlineNative(false);
                    }
                } catch (Exception e) {
                    bj.c(a, "calculateRouteAsync called before maps engine was initialized", new Object[0]);
                }
            }
            bj.e(a, "calculateRouteAsync able to start MOS calculateRoute", new Object[0]);
            this.b = true;
            final RoutePlanImpl a = RoutePlanImpl.a(routePlan);
            new Thread(new Runnable(this) {
                final /* synthetic */ RouteManagerImpl b;

                public void run() {
                    this.b.native_calculateRoute(a);
                }
            }).start();
            bj.e(a, "calculateRouteAsync called MOS calculateRoute", new Object[0]);
            if (getForceOnline() && this.g == a.OFFLINE) {
                this.g = a.ONLINE;
            }
            error = Error.NONE;
        }
        return error;
    }

    private TransportMode a(RoutePlanImpl routePlanImpl) {
        return routePlanImpl.a().getTransportMode();
    }

    private TransportMode a(RoutePlan routePlan) {
        return a(RoutePlanImpl.a(routePlan));
    }

    private Error b(RouteManager routeManager, RoutePlan routePlan, Listener listener) {
        return new et(routeManager).a(routePlan, listener);
    }

    public synchronized void a() {
        if (this.b) {
            native_cancel();
        }
    }

    public synchronized boolean b() {
        return this.b;
    }

    public TrafficPenaltyMode c() {
        return d().getTrafficPenaltyMode();
    }

    public void a(DynamicPenalty dynamicPenalty) {
        if (dynamicPenalty == null) {
            dynamicPenalty = new DynamicPenalty();
        }
        setDynamicPenaltyNative(DynamicPenaltyImpl.a(dynamicPenalty));
    }

    public DynamicPenalty d() {
        return DynamicPenaltyImpl.a(getDynamicPenaltyNative());
    }

    public synchronized void a(a aVar) {
        this.g = aVar;
        switch (this.g) {
            case OFFLINE:
                setForceOnlineNative(false);
                break;
            case ONLINE:
                setForceOnlineNative(true);
                break;
            default:
                setForceOnlineNative(false);
                break;
        }
    }

    synchronized void a(boolean z) {
        if (z) {
            this.g = a.ONLINE;
            setForceOnlineNative(true);
        } else {
            this.g = a.OFFLINE;
            setForceOnlineNative(false);
        }
    }

    public synchronized a e() {
        return this.g;
    }

    public synchronized com.here.android.mpa.routing.RouteManager.a f() {
        com.here.android.mpa.routing.RouteManager.a aVar;
        switch (e()) {
            case OFFLINE:
                aVar = com.here.android.mpa.routing.RouteManager.a.c;
                break;
            case ONLINE:
                aVar = com.here.android.mpa.routing.RouteManager.a.b;
                break;
            default:
                aVar = com.here.android.mpa.routing.RouteManager.a.a;
                break;
        }
        return aVar;
    }
}

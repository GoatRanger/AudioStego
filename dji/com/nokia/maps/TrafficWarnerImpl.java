package com.nokia.maps;

import com.here.android.mpa.guidance.TrafficNotification;
import com.here.android.mpa.guidance.TrafficWarner;
import com.here.android.mpa.guidance.TrafficWarner.Listener;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;

@HybridPlus
public class TrafficWarnerImpl extends BaseNativeObject {
    private static final String a = TrafficWarnerImpl.class.getName();
    private static k<TrafficWarner, TrafficWarnerImpl> c = null;
    private static am<TrafficWarner, TrafficWarnerImpl> d = null;
    private fc<Listener> b = new fc();

    private final native void createTrafficWarnerNative();

    private final native void destroyTrafficWarnerNative();

    private static final native TrafficNotificationImpl getNotificationsNative(NavigationManagerImpl navigationManagerImpl);

    private static final native TrafficNotificationImpl getNotificationsOnRouteNative(RouteImpl routeImpl);

    private final native boolean isAheadNative(TrafficNotificationImpl trafficNotificationImpl);

    private final native boolean isOnRouteNative(RouteImpl routeImpl, TrafficNotificationImpl trafficNotificationImpl);

    private final native boolean nativeInit(NavigationManagerImpl navigationManagerImpl);

    private final native boolean setAskAvoidOutputNative(TrafficNotificationImpl trafficNotificationImpl);

    private final native boolean setInformAvoidOutputNative(TrafficNotificationImpl trafficNotificationImpl);

    private final native void startNative();

    private final native void stopNative();

    public final native void clear();

    public final native boolean isValid();

    static {
        ce.a(TrafficWarner.class);
    }

    public static void a(k<TrafficWarner, TrafficWarnerImpl> kVar, am<TrafficWarner, TrafficWarnerImpl> amVar) {
        c = kVar;
        d = amVar;
    }

    static TrafficWarnerImpl a(TrafficWarner trafficWarner) {
        if (c != null) {
            return (TrafficWarnerImpl) c.a(trafficWarner);
        }
        return null;
    }

    static TrafficWarner a(TrafficWarnerImpl trafficWarnerImpl) {
        if (trafficWarnerImpl != null) {
            return (TrafficWarner) d.a(trafficWarnerImpl);
        }
        return null;
    }

    public TrafficWarnerImpl() {
        createTrafficWarnerNative();
    }

    protected void finalize() {
        destroyTrafficWarnerNative();
    }

    public boolean a() {
        NavigationManagerImpl a = NavigationManagerImpl.a();
        return a == null ? false : nativeInit(a);
    }

    public void b() {
        startNative();
    }

    public void c() {
        stopNative();
    }

    public boolean a(TrafficNotification trafficNotification) {
        if (trafficNotification != null) {
            return isAheadNative(TrafficNotificationImpl.a(trafficNotification));
        }
        return false;
    }

    public boolean a(Route route, TrafficNotification trafficNotification) {
        if (route == null || trafficNotification == null) {
            return false;
        }
        return isOnRouteNative(RouteImpl.a(route), TrafficNotificationImpl.a(trafficNotification));
    }

    public static TrafficNotification d() {
        return TrafficNotificationImpl.a(getNotificationsNative(NavigationManagerImpl.a()));
    }

    public static TrafficNotification a(Route route) {
        dy.a((Object) route, "Cannot pass a null route");
        RouteImpl a = RouteImpl.a(route);
        if (a.a() != a.MOS_ROUTE) {
            return null;
        }
        return TrafficNotificationImpl.a(getNotificationsOnRouteNative(a));
    }

    public void a(WeakReference<Listener> weakReference) {
        this.b.a(weakReference);
    }

    public void a(Listener listener) {
        this.b.b(listener);
    }

    @HybridPlusNative
    private void onTraffic(final TrafficNotificationImpl trafficNotificationImpl) {
        this.b.b(new fc$a<Listener>(this) {
            final /* synthetic */ TrafficWarnerImpl b;

            public void a(Listener listener) {
                listener.onTraffic(TrafficNotificationImpl.a(trafficNotificationImpl));
            }
        });
    }
}

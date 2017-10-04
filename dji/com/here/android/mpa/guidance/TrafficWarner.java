package com.here.android.mpa.guidance;

import com.here.android.mpa.routing.Route;
import com.nokia.maps.TrafficWarnerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.lang.ref.WeakReference;

@HybridPlus
public final class TrafficWarner {
    TrafficWarnerImpl a;

    @HybridPlus
    public static abstract class Listener {
        public abstract void onTraffic(TrafficNotification trafficNotification);
    }

    TrafficWarner(TrafficWarnerImpl trafficWarnerImpl) {
        this.a = trafficWarnerImpl;
    }

    public void addListener(WeakReference<Listener> weakReference) {
        this.a.a((WeakReference) weakReference);
    }

    public final void clear() {
        this.a.clear();
    }

    public boolean init() {
        return this.a.a();
    }

    public boolean isAhead(TrafficNotification trafficNotification) {
        return this.a.a(trafficNotification);
    }

    public boolean isOnRoute(Route route, TrafficNotification trafficNotification) {
        return this.a.a(route, trafficNotification);
    }

    public final boolean isValid() {
        return this.a.isValid();
    }

    public void removeListener(Listener listener) {
        this.a.a(listener);
    }

    public static TrafficNotification getNotificationOnRoute() {
        return TrafficWarnerImpl.d();
    }

    public static TrafficNotification getNotificationOnRoute(Route route) {
        return TrafficWarnerImpl.a(route);
    }

    public void start() {
        this.a.b();
    }

    public void stop() {
        this.a.c();
    }

    static {
        TrafficWarnerImpl.a(new k<TrafficWarner, TrafficWarnerImpl>() {
            public TrafficWarnerImpl a(TrafficWarner trafficWarner) {
                return trafficWarner.a;
            }
        }, new am<TrafficWarner, TrafficWarnerImpl>() {
            public TrafficWarner a(TrafficWarnerImpl trafficWarnerImpl) {
                return new TrafficWarner(trafficWarnerImpl);
            }
        });
    }
}

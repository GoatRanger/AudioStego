package com.here.android.mpa.guidance;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.TrafficEvent;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteElements;
import com.nokia.maps.TrafficUpdaterImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.eo;
import com.nokia.maps.eo.a;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class TrafficUpdater {
    private static volatile TrafficUpdater a = null;
    private TrafficUpdaterImpl b = TrafficUpdaterImpl.a();

    @HybridPlus
    public enum Error {
        NONE(0),
        INVALID_PARAMETERS(1),
        OUT_OF_MEMORY(2),
        INVALID_OPERATION(3),
        REQUEST_FAILED(4),
        INVALID_CREDENTIALS(5),
        UNKNOWN(6),
        UNSUPPORTED_ROUTE_MODE(7),
        OPERATION_NOT_ALLOWED(8);
        
        private int a;

        private Error(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @HybridPlus
    public interface GetEventsListener {
        void onComplete(List<TrafficEvent> list, Error error);
    }

    @HybridPlus
    public interface Listener {
        void onStatusChanged(RequestState requestState);
    }

    @HybridPlus
    public static final class RequestInfo {
        private final Error a;
        private final long b;

        public Error getError() {
            return this.a;
        }

        public long getRequestId() {
            return this.b;
        }

        private RequestInfo(Error error, long j) {
            this.a = error;
            this.b = j;
        }

        static {
            eo.a(new a() {
                public RequestInfo a(Error error, long j) {
                    return new RequestInfo(error, j);
                }
            });
        }
    }

    @HybridPlus
    public enum RequestState {
        ERROR(-1),
        DONE(1);
        
        private int a;

        private RequestState(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    static {
        TrafficUpdaterImpl.a(new k<TrafficUpdater, TrafficUpdaterImpl>() {
            public TrafficUpdaterImpl a(TrafficUpdater trafficUpdater) {
                return trafficUpdater.b;
            }
        });
    }

    public static TrafficUpdater getInstance() {
        if (a == null) {
            synchronized (TrafficUpdater.class) {
                if (a == null) {
                    a = new TrafficUpdater();
                }
            }
        }
        return a;
    }

    private TrafficUpdater() {
    }

    @HybridPlus
    public boolean setRefreshInterval(int i) {
        return this.b.setRefreshInterval(i);
    }

    public void clear() {
        this.b.clear();
    }

    public void enableUpdate(boolean z) {
        this.b.a(z, null);
    }

    public boolean isUpdateEnabled() {
        return this.b.b();
    }

    public RequestInfo request(GeoCoordinate geoCoordinate, Listener listener) {
        return this.b.a(geoCoordinate, listener);
    }

    public RequestInfo request(GeoCoordinate geoCoordinate, int i, Listener listener) {
        return this.b.a(geoCoordinate, i, listener);
    }

    public RequestInfo request(Route route, Listener listener) {
        return this.b.a(route, listener);
    }

    public RequestInfo request(Route route, int i, Listener listener) {
        return this.b.a(route, i, listener);
    }

    public RequestInfo request(RouteElements routeElements, Listener listener) {
        return this.b.a(routeElements, listener);
    }

    public void cancelRequest(long j) {
        this.b.a(j);
    }

    public void getEvents(Route route, GetEventsListener getEventsListener) {
        this.b.a(route, getEventsListener);
    }

    public void getEvents(RouteElement routeElement, GetEventsListener getEventsListener) {
        this.b.a(routeElement, getEventsListener);
    }

    public void getEvents(List<RouteElement> list, GetEventsListener getEventsListener) {
        this.b.a((List) list, getEventsListener);
    }

    public void getEvents(RouteElements routeElements, GetEventsListener getEventsListener) {
        this.b.a(routeElements, getEventsListener);
    }
}

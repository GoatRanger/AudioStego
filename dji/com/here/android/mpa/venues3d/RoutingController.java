package com.here.android.mpa.venues3d;

import com.here.android.mpa.routing.RouteOptions;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.fc;
import com.nokia.maps.fc$a;
import com.nokia.maps.l;
import java.lang.ref.WeakReference;
import java.security.AccessControlException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@HybridPlus
public class RoutingController extends BaseNativeObject {
    private final fc<RoutingControllerListener> a = new fc();
    private final ExecutorService b;
    private volatile boolean c = false;
    private final ApplicationContext$c d = new ApplicationContext$c(this) {
        final /* synthetic */ RoutingController a;

        {
            this.a = r1;
        }

        public void a() {
            throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
        }

        public void b() {
            this.a.c = true;
        }
    };

    @HybridPlus
    public interface RoutingControllerListener {
        void onCombinedRouteCompleted(CombinedRoute combinedRoute);
    }

    @HybridPlusNative
    private native void calculateRoute(BaseLocation baseLocation, BaseLocation baseLocation2, VenueRouteOptions venueRouteOptions);

    @HybridPlusNative
    private native void hideRouteNative();

    @HybridPlusNative
    private native void showRouteNative(CombinedRoute combinedRoute);

    @HybridPlusNative
    private RoutingController(int i) {
        super(true);
        ApplicationContext.b().check(7, this.d);
        this.b = Executors.newCachedThreadPool();
        this.nativeptr = i;
    }

    public void addListener(RoutingControllerListener routingControllerListener) {
        if (this.c && routingControllerListener != null) {
            this.a.b(routingControllerListener);
            this.a.a(new WeakReference(routingControllerListener));
        }
    }

    public void removeListener(RoutingControllerListener routingControllerListener) {
        if (this.c && routingControllerListener != null) {
            this.a.b(routingControllerListener);
        }
    }

    public void calculateCombinedRoute(final BaseLocation baseLocation, final BaseLocation baseLocation2, final VenueRouteOptions venueRouteOptions) {
        if (this.c) {
            this.b.execute(new Runnable(this) {
                final /* synthetic */ RoutingController d;

                public void run() {
                    this.d.calculateRoute(baseLocation, baseLocation2, venueRouteOptions);
                }
            });
        }
    }

    @HybridPlusNative
    private void onCombinedRouteCompletedSync(final CombinedRoute combinedRoute) {
        this.a.b(new fc$a<RoutingControllerListener>(this) {
            final /* synthetic */ RoutingController b;

            public void a(RoutingControllerListener routingControllerListener) {
                routingControllerListener.onCombinedRouteCompleted(combinedRoute);
            }
        });
        RouteOptions routeOptions = combinedRoute.getOptions().getRouteOptions();
        l.a().a(routeOptions.getTransportMode(), routeOptions.getRouteType(), combinedRoute.getRouteSections().size() == 0, combinedRoute.getCombinationType());
    }

    public void showRoute(CombinedRoute combinedRoute) {
        if (this.c) {
            showRouteNative(combinedRoute);
        }
    }

    public void hideRoute() {
        if (this.c) {
            hideRouteNative();
        }
    }
}

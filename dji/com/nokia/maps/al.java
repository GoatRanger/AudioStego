package com.nokia.maps;

import com.here.android.mpa.routing.CoreRouter;
import com.here.android.mpa.routing.DynamicPenalty;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteManager.Error;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.Router.Listener;
import com.here.android.mpa.routing.RoutingError;
import java.util.List;

public class al {
    private static k<CoreRouter, al> d = null;
    private final RouteManager a = new RouteManager();
    private final RouteManagerImpl b = RouteManagerImpl.a(this.a);
    private boolean c = false;

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[Error.values().length];

        static {
            try {
                a[Error.CANNOT_DO_PEDESTRIAN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Error.GRAPH_DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Error.GRAPH_DISCONNECTED_CHECK_OPTIONS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Error.INSUFFICIENT_MAP_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Error.INVALID_CREDENTIALS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[Error.INVALID_OPERATION.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[Error.INVALID_PARAMETERS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[Error.NONE.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[Error.NO_CONNECTIVITY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[Error.NO_END_POINT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[Error.NO_END_POINT_CHECK_OPTIONS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[Error.NO_START_POINT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[Error.OPERATION_NOT_ALLOWED.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[Error.OUT_OF_MEMORY.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[Error.REQUEST_TIMEOUT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[Error.ROUTE_CORRUPTED.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[Error.ROUTING_CANCELLED.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[Error.VIOLATES_OPTIONS.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[Error.UNKNOWN.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
        }
    }

    public synchronized void a(RoutePlan routePlan, final Listener<List<RouteResult>, RoutingError> listener) {
        if (this.c) {
            a((Listener) listener, RoutingError.INVALID_OPERATION);
        } else {
            RoutingError a = a(this.b.a(this.a, routePlan, new RouteManager.Listener(this) {
                final /* synthetic */ al b;

                public void onProgress(int i) {
                    listener.onProgress(i);
                }

                public void onCalculateRouteFinished(Error error, List<RouteResult> list) {
                    this.b.c = false;
                    listener.onCalculateRouteFinished(list, this.b.a(error));
                }
            }));
            if (a == RoutingError.NONE) {
                this.c = true;
            } else {
                a((Listener) listener, a);
            }
        }
    }

    public synchronized boolean a() {
        return this.c;
    }

    public synchronized void b() {
        this.b.a();
    }

    public void a(DynamicPenalty dynamicPenalty) {
        this.a.setDynamicPenalty(dynamicPenalty);
    }

    public DynamicPenalty c() {
        return this.a.getDynamicPenalty();
    }

    private void a(final Listener<List<RouteResult>, RoutingError> listener, final RoutingError routingError) {
        ez.a(new Runnable(this) {
            final /* synthetic */ al c;

            public void run() {
                listener.onCalculateRouteFinished(null, routingError);
            }
        });
    }

    private RoutingError a(Error error) {
        switch (AnonymousClass3.a[error.ordinal()]) {
            case 1:
                return RoutingError.CANNOT_DO_PEDESTRIAN;
            case 2:
                return RoutingError.GRAPH_DISCONNECTED;
            case 3:
                return RoutingError.GRAPH_DISCONNECTED_CHECK_OPTIONS;
            case 4:
                return RoutingError.INSUFFICIENT_MAP_DATA;
            case 5:
                return RoutingError.INVALID_CREDENTIALS;
            case 6:
                return RoutingError.INVALID_OPERATION;
            case 7:
                return RoutingError.INVALID_PARAMETERS;
            case 8:
                return RoutingError.NONE;
            case 9:
                return RoutingError.NO_CONNECTIVITY;
            case 10:
                return RoutingError.NO_END_POINT;
            case 11:
                return RoutingError.NO_END_POINT_CHECK_OPTIONS;
            case 12:
                return RoutingError.NO_START_POINT;
            case 13:
                return RoutingError.OPERATION_NOT_ALLOWED;
            case 14:
                return RoutingError.OUT_OF_MEMORY;
            case 15:
                return RoutingError.REQUEST_TIMEOUT;
            case 16:
                return RoutingError.ROUTE_CORRUPTED;
            case 17:
                return RoutingError.ROUTING_CANCELLED;
            case 18:
                return RoutingError.VIOLATES_OPTIONS;
            default:
                return RoutingError.UNKNOWN;
        }
    }

    static {
        ce.a(CoreRouter.class);
    }

    public static void a(k<CoreRouter, al> kVar) {
        d = kVar;
    }
}

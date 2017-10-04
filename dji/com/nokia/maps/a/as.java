package com.nokia.maps.a;

import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteManager.Error;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.Router.Listener;
import com.here.android.mpa.routing.UMCalculateResult;
import com.here.android.mpa.routing.UMRouter;
import com.here.android.mpa.routing.UMRouter.SubsequentRouteType;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.RouteManagerImpl;
import com.nokia.maps.ce;
import com.nokia.maps.ez;
import com.nokia.maps.k;
import java.util.List;

public final class as {
    private static k<UMRouter, as> h = null;
    private final RouteManager a = new RouteManager();
    private final RouteManagerImpl b = RouteManagerImpl.a(this.a);
    private RoutePlan c = null;
    private ao<?> d = null;
    private h e = null;
    private Listener<UMCalculateResult, ErrorCode> f = null;
    private boolean g = false;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[Error.values().length];

        static {
            try {
                a[Error.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Error.NO_END_POINT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Error.NO_START_POINT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Error.INVALID_PARAMETERS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[Error.GRAPH_DISCONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[Error.GRAPH_DISCONNECTED_CHECK_OPTIONS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[Error.NO_END_POINT_CHECK_OPTIONS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[Error.CANNOT_DO_PEDESTRIAN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[Error.ROUTING_CANCELLED.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[Error.ROUTE_CORRUPTED.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[Error.INVALID_CREDENTIALS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[Error.OPERATION_NOT_ALLOWED.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[Error.REQUEST_TIMEOUT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[Error.NO_CONNECTIVITY.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[Error.VIOLATES_OPTIONS.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[Error.INSUFFICIENT_MAP_DATA.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[Error.OUT_OF_MEMORY.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private class a implements RequestManager$ResponseListener<h> {
        final /* synthetic */ as a;

        private a(as asVar) {
            this.a = asVar;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((h) obj);
        }

        public void a(h hVar) {
            synchronized (this.a) {
                hVar.a(this.a.e.k());
                hVar.a(this.a.e.l());
                this.a.e = hVar;
                this.a.e();
            }
        }

        public void onError(ErrorCode errorCode, String str) {
            synchronized (this.a) {
                this.a.e.a(errorCode);
                this.a.e.a(str);
                this.a.e();
            }
        }
    }

    public synchronized void a(RoutePlan routePlan, Listener<UMCalculateResult, ErrorCode> listener) {
        if (this.g) {
            a((Listener) listener);
        } else {
            this.g = true;
            this.f = listener;
            this.c = new RoutePlan(routePlan);
            this.e = new h();
            this.e.a(this.c);
            this.e.a(this.a);
            com.here.android.mpa.routing.RouteManager.a a = this.a.a();
            if ((a == com.here.android.mpa.routing.RouteManager.a.a || a == com.here.android.mpa.routing.RouteManager.a.b) && MapsEngine.C() && this.c.getWaypointCount() == 2) {
                try {
                    this.d = new al().a(routePlan, new a());
                    this.d.d();
                } catch (IllegalArgumentException e) {
                    a(ErrorCode.INVALID_PARAMETERS);
                }
            } else {
                c();
            }
        }
    }

    public synchronized void a(UMCalculateResult uMCalculateResult, SubsequentRouteType subsequentRouteType, int i, Listener<UMCalculateResult, ErrorCode> listener) {
        if (this.g) {
            a((Listener) listener);
        } else {
            ErrorCode errorCode;
            this.g = true;
            this.f = listener;
            h a = h.a(uMCalculateResult);
            this.e = new h();
            this.e.a(a.k());
            this.e.a(a.l());
            ErrorCode errorCode2 = ErrorCode.NONE;
            com.here.android.mpa.routing.RouteManager.a a2 = a.l().a();
            if (a.k().getWaypointCount() != 2) {
                errorCode = ErrorCode.INVALID_PARAMETERS;
            } else if ((a2 == com.here.android.mpa.routing.RouteManager.a.a || a2 == com.here.android.mpa.routing.RouteManager.a.b) && MapsEngine.C()) {
                try {
                    this.d = new al().a(a.k(), a, subsequentRouteType, i, new a());
                    this.d.d();
                    errorCode = errorCode2;
                } catch (IllegalArgumentException e) {
                    errorCode = ErrorCode.INVALID_PARAMETERS;
                }
            } else {
                errorCode = ErrorCode.NETWORK_COMMUNICATION;
            }
            if (errorCode != ErrorCode.NONE) {
                a(errorCode);
            }
        }
    }

    public synchronized void a() {
        this.b.a();
        if (this.d != null) {
            this.d.cancel(true);
            this.e.a(ErrorCode.CANCELLED);
            e();
        }
    }

    public synchronized boolean b() {
        return this.g;
    }

    private void c() {
        new t(this, new int[]{26, 45}) {
            final /* synthetic */ as a;

            protected void c() {
                synchronized (this.a) {
                    this.a.d();
                }
            }

            protected void d() {
                synchronized (this.a) {
                    this.a.e.a(ErrorCode.OPERATION_NOT_ALLOWED);
                    this.a.e();
                }
            }
        }.e();
    }

    private void d() {
        RouteOptions routeOptions = this.c.getRouteOptions();
        routeOptions.setTransportMode(TransportMode.PUBLIC_TRANSPORT);
        this.c.setRouteOptions(routeOptions);
        ErrorCode a = a(this.b.a(this.a, this.c, new RouteManager.Listener(this) {
            final /* synthetic */ as a;

            {
                this.a = r1;
            }

            public void onProgress(int i) {
                synchronized (this.a) {
                    if (this.a.f != null) {
                        this.a.f.onProgress(i);
                    }
                }
            }

            public void onCalculateRouteFinished(Error error, List<RouteResult> list) {
                synchronized (this.a) {
                    this.a.e.a(this.a.a(error));
                    if (list != null) {
                        for (RouteResult aqVar : list) {
                            this.a.e.a(aq.a(new aq(aqVar)));
                        }
                    }
                    this.a.e();
                }
            }
        }));
        if (a != ErrorCode.NONE) {
            this.e.a(a);
            e();
        }
    }

    private static void a(final Listener<UMCalculateResult, ErrorCode> listener) {
        if (listener != null) {
            ez.a(new Runnable() {
                public void run() {
                    h hVar = new h();
                    hVar.a(ErrorCode.INVALID_OPERATION);
                    UMCalculateResult a = h.a(hVar);
                    listener.onCalculateRouteFinished(a, a.getError());
                }
            });
        }
    }

    private void a(final ErrorCode errorCode) {
        ez.a(new Runnable(this) {
            final /* synthetic */ as b;

            public void run() {
                synchronized (this.b) {
                    this.b.e.a(errorCode);
                    this.b.e();
                }
            }
        });
    }

    private void e() {
        this.d = null;
        this.g = false;
        if (this.f != null) {
            UMCalculateResult a = h.a(this.e);
            this.f.onCalculateRouteFinished(a, a.getError());
        }
    }

    private ErrorCode a(Error error) {
        switch (AnonymousClass5.a[error.ordinal()]) {
            case 1:
                return ErrorCode.NONE;
            case 2:
            case 3:
            case 4:
                return ErrorCode.INVALID_PARAMETERS;
            case 5:
            case 6:
            case 7:
            case 8:
                return ErrorCode.ROUTING_NOT_POSSIBLE;
            case 9:
                return ErrorCode.CANCELLED;
            case 10:
                return ErrorCode.INVALID_RESPONSE;
            case 11:
            case 12:
                return ErrorCode.INVALID_CREDENTIALS;
            case 13:
            case 14:
                return ErrorCode.NETWORK_COMMUNICATION;
            case 15:
                return ErrorCode.VIOLATES_OPTIONS;
            case 16:
                return ErrorCode.INSUFFICIENT_MAP_DATA;
            case 17:
                return ErrorCode.OUT_OF_MEMORY;
            default:
                return ErrorCode.UNKNOWN;
        }
    }

    static {
        ce.a(UMRouter.class);
    }

    public static void a(k<UMRouter, as> kVar) {
        h = kVar;
    }
}

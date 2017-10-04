package com.here.android.mpa.customlocation;

import android.util.Log;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.routing.Route;
import com.nokia.maps.ApplicationContext;
import com.nokia.maps.ApplicationContext$c;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.ez;
import com.nokia.maps.l;
import java.util.List;
import javax.security.cert.CertificateException;

public class Request {
    private static final String a = Request.class.getName();
    private List<GeoCoordinate> b;
    private GeoBoundingBox c;
    private int d;
    private GeoCoordinate e;
    private int f;
    private a g;
    private int h;
    private String i;
    private int j;
    private String k;
    private ResultListener l;
    private b m;
    private f n;
    private ApplicationContext$c o;

    @HybridPlus
    public interface ResultListener {
        void onCompleted(Result result, Error error);
    }

    @HybridPlus
    public enum Error {
        NONE,
        GENERAL,
        NOT_INITIALIZED,
        BAD_REQUEST,
        INCOMPLETE,
        NOT_FOUND,
        FORBIDDEN,
        UNKNOWN,
        BUSY,
        CANCELLED,
        SERVER_INTERNAL,
        NO_CONTENT,
        INVALID_PARAMETER,
        NETWORK_COMMUNICATION,
        INVALID_CREDENTIALS,
        UNAUTHORIZED,
        SERVICE_UNAVAILABLE,
        OPERATION_NOT_ALLOWED
    }

    enum a {
        LOCATION_AREA,
        LOCATION_PROXIMITY,
        LOCATION_CORRIDOR,
        LOCATION_ATTRIBUTE,
        GEOMETRY_AREA,
        GEOMETRY_POINT,
        GEOMETRY_ID;

        public static a[] a() {
            return (a[]) h.clone();
        }
    }

    private Request(int i, a aVar) {
        this.j = -1;
        this.n = new f(this) {
            final /* synthetic */ Request a;

            {
                this.a = r1;
            }

            public void a(CLEResponse cLEResponse, Error error) {
                if (error == Error.NONE) {
                    error = this.a.a(cLEResponse);
                }
                Result result = null;
                if (error == Error.NONE) {
                    result = cLEResponse.a();
                }
                ez.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass6 c;

                    public void run() {
                        try {
                            this.c.a.l.onCompleted(result, error);
                        } catch (Throwable th) {
                            Log.e(Request.a, th.getLocalizedMessage());
                        }
                        this.c.a.l = null;
                    }
                });
            }
        };
        this.o = new ApplicationContext$c(this) {
            final /* synthetic */ Request a;

            {
                this.a = r1;
            }

            @HybridPlusNative
            public void a() {
                ez.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass7 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.l.onCompleted(null, Error.OPERATION_NOT_ALLOWED);
                    }
                });
            }

            @HybridPlusNative
            public void b() {
                this.a.b();
            }
        };
        if (i <= 0) {
            throw new IllegalArgumentException("Layer Id should be > 0");
        }
        this.h = i;
        this.g = aVar;
        this.m = new b();
    }

    Request(int i, GeoBoundingBox geoBoundingBox, boolean z) {
        this(i, z ? a.GEOMETRY_AREA : a.LOCATION_AREA);
        if (geoBoundingBox == null || geoBoundingBox.isEmpty()) {
            throw new IllegalArgumentException("Area specified should not be empty");
        }
        this.c = geoBoundingBox;
    }

    Request(int i, QueryBuilder queryBuilder) {
        this(i, a.LOCATION_ATTRIBUTE);
        this.k = queryBuilder.a();
        if (this.k.isEmpty()) {
            throw new IllegalArgumentException("Query is empty");
        }
    }

    Request(int i, CustomQueryBuilder customQueryBuilder) {
        this(i, a.LOCATION_ATTRIBUTE);
        this.i = customQueryBuilder.a();
        if (this.i.isEmpty()) {
            throw new IllegalArgumentException("Custom attribute query is empty");
        }
    }

    Request(int i, GeoCoordinate geoCoordinate, int i2) {
        this(i, a.LOCATION_PROXIMITY);
        if (!geoCoordinate.isValid()) {
            throw new IllegalArgumentException("Center is invalid");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("Radius should be > 0");
        } else {
            this.e = geoCoordinate;
            this.d = i2;
        }
    }

    Request(int i, GeoCoordinate geoCoordinate) {
        this(i, a.GEOMETRY_POINT);
        if (geoCoordinate.isValid()) {
            this.e = geoCoordinate;
            return;
        }
        throw new IllegalArgumentException("Center is invalid");
    }

    Request(int i, int i2) {
        this(i, a.GEOMETRY_ID);
        if (i2 < 0) {
            throw new IllegalArgumentException("Geometry ID should be greater or equal to 0");
        }
        this.f = i2;
    }

    Request(int i, List<GeoCoordinate> list, int i2) {
        this(i, a.LOCATION_CORRIDOR);
        if (list.size() <= 1) {
            throw new IllegalArgumentException("Route must be represented by a list of > 1 GeoCoordinate");
        } else if (i2 <= 0) {
            throw new IllegalArgumentException("Radius should be > 0");
        } else {
            this.b = list;
            this.d = i2;
        }
    }

    Request(int i, Route route, int i2) {
        this(i, route.getRouteGeometry(), i2);
    }

    @HybridPlus
    public Error execute(final ResultListener resultListener) {
        if (resultListener == null) {
            return Error.INVALID_PARAMETER;
        }
        if (this.l != null) {
            return Error.BUSY;
        }
        if (!MapEngine.isOnlineEnabled()) {
            return Error.NETWORK_COMMUNICATION;
        }
        this.l = new ResultListener(this) {
            final /* synthetic */ Request b;

            public void onCompleted(Result result, Error error) {
                boolean z = true;
                boolean z2 = error != Error.NONE;
                List locations = result == null ? null : result.getLocations();
                if (locations == null || locations.isEmpty()) {
                    z = false;
                }
                l.a().a(z2, z);
                resultListener.onCompleted(result, error);
            }
        };
        j();
        return Error.NONE;
    }

    private void b() {
        try {
            switch (this.g) {
                case LOCATION_AREA:
                    c();
                    return;
                case LOCATION_ATTRIBUTE:
                    d();
                    return;
                case LOCATION_PROXIMITY:
                    e();
                    return;
                case LOCATION_CORRIDOR:
                    f();
                    return;
                case GEOMETRY_AREA:
                    g();
                    return;
                case GEOMETRY_POINT:
                    h();
                    return;
                case GEOMETRY_ID:
                    i();
                    return;
                default:
                    ez.a(new Runnable(this) {
                        final /* synthetic */ Request a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.l.onCompleted(null, Error.UNKNOWN);
                        }
                    });
                    return;
            }
        } catch (IllegalArgumentException e) {
            ez.a(new Runnable(this) {
                final /* synthetic */ Request a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.l.onCompleted(null, Error.INVALID_PARAMETER);
                }
            });
        } catch (CertificateException e2) {
            ez.a(new Runnable(this) {
                final /* synthetic */ Request a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.l.onCompleted(null, Error.INVALID_CREDENTIALS);
                }
            });
        } catch (Exception e3) {
            ez.a(new Runnable(this) {
                final /* synthetic */ Request a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.l.onCompleted(null, Error.UNKNOWN);
                }
            });
        }
    }

    @HybridPlus
    public void cancel() {
        this.m.a();
    }

    @HybridPlus
    public void setCustomAttributeQuery(CustomQueryBuilder customQueryBuilder) {
        this.i = customQueryBuilder.a();
        if (this.i.isEmpty()) {
            throw new IllegalArgumentException("Query specified is empty");
        }
    }

    @HybridPlus
    public void setQuery(QueryBuilder queryBuilder) {
        this.k = queryBuilder.a();
        if (this.k.isEmpty()) {
            throw new IllegalArgumentException("Query specified is empty");
        }
    }

    @HybridPlus
    public void setLimit(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Limit should be > 0");
        } else if (i > 500) {
            throw new IllegalArgumentException(String.format("Limit has a maximum of %d", new Object[]{Integer.valueOf(500)}));
        } else {
            this.j = i;
        }
    }

    private void c() throws Exception {
        this.m.a(this.h, this.c, this.j, this.k, this.i, this.n);
    }

    private void d() throws Exception {
        this.m.a(this.h, this.j, this.k, this.i, this.n);
    }

    private void e() throws Exception {
        this.m.a(this.h, this.e, this.d, this.j, this.k, this.i, this.n);
    }

    private void f() throws Exception {
        this.m.a(this.h, this.b, this.d, this.j, this.k, this.i, this.n);
    }

    private void g() throws Exception {
        this.m.b(this.h, this.c, this.j, this.k, this.i, this.n);
    }

    private void h() throws Exception {
        this.m.a(this.h, this.e, this.j, this.k, this.i, this.n);
    }

    private void i() throws Exception {
        this.m.a(this.h, this.f, this.j, this.k, this.i, this.n);
    }

    private Error a(CLEResponse cLEResponse) {
        if (cLEResponse == null || cLEResponse.status == null) {
            return Error.INCOMPLETE;
        }
        String toLowerCase = cLEResponse.status.toLowerCase();
        if (toLowerCase.contains("OK".toLowerCase())) {
            return Error.NONE;
        }
        if (toLowerCase.contains("Bad Request")) {
            return Error.BAD_REQUEST;
        }
        if (toLowerCase.contains("Unauthorized")) {
            return Error.UNAUTHORIZED;
        }
        return Error.UNKNOWN;
    }

    private synchronized void j() {
        ApplicationContext.b().check(11, this.o);
    }
}

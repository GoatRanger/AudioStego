package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.GeocodeRequest;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.ResultListener;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Online
public class di extends PlacesBaseRequest<List<Location>> {
    private static final String j = di.class.getName();
    private static k<GeocodeRequest, di> v;
    private static am<GeocodeRequest, di> w;
    private ResultListener<List<Location>> k;
    private bi l;
    private PlacesDiscoveryRequest m;
    private AtomicBoolean n;
    private a o;
    private String p;
    private Address q;
    private GeoCoordinate r;
    private int s;
    private GeoBoundingBox t;
    private p u;

    enum a {
        ADDRESS_GEOCODE,
        ONE_BOX_GEOCODE,
        UNKNOWN;

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    static {
        ce.a(GeocodeRequest.class);
    }

    public static void a(k<GeocodeRequest, di> kVar, am<GeocodeRequest, di> amVar) {
        v = kVar;
        w = amVar;
    }

    di(String str, GeoCoordinate geoCoordinate) {
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = new AtomicBoolean(false);
        this.o = a.UNKNOWN;
        this.r = null;
        this.s = 0;
        this.t = null;
        this.u = l.a();
        this.i = c.GEOCODE;
        this.o = a.ONE_BOX_GEOCODE;
        this.p = str;
        this.r = geoCoordinate;
    }

    public void b(String str) {
        this.p = str;
    }

    public void a(GeoCoordinate geoCoordinate, int i) {
        this.r = geoCoordinate;
        this.s = i;
    }

    public void b(GeoBoundingBox geoBoundingBox) {
        this.t = geoBoundingBox;
    }

    public void c(GeoBoundingBox geoBoundingBox) {
        this.e = geoBoundingBox;
    }

    public ErrorCode a(ResultListener<List<Location>> resultListener) {
        ErrorCode errorCode = ErrorCode.NONE;
        if (resultListener == null) {
            return ErrorCode.INVALID_PARAMETER;
        }
        boolean isOnline;
        this.k = resultListener;
        try {
            isOnline = MapsEngine.c().isOnline();
        } catch (Exception e) {
            isOnline = false;
        }
        if (isOnline) {
            errorCode = g();
            if (errorCode == ErrorCode.NONE) {
                ez.a(new Runnable(this) {
                    final /* synthetic */ di a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b(this.a.k);
                    }
                });
            }
        } else {
            errorCode = super.a(this.k);
        }
        if (errorCode == ErrorCode.NONE) {
            return errorCode;
        }
        this.u.a(this.o, true, false);
        return errorCode;
    }

    public void c() {
        this.k = null;
        if (this.l != null) {
            this.l.cancel(true);
        } else if (this.m != null) {
            this.m.c();
        }
        this.n.set(true);
    }

    protected ErrorCode d() {
        switch (this.o) {
            case ADDRESS_GEOCODE:
                this.m = PlacesApi.a().a(com.nokia.maps.dd.a.OFFLINE, this.q.toString());
                break;
            case ONE_BOX_GEOCODE:
                this.m = PlacesApi.a().a(com.nokia.maps.dd.a.OFFLINE, this.p);
                break;
            default:
                return ErrorCode.BAD_REQUEST;
        }
        if (this.m == null) {
            return ErrorCode.BAD_REQUEST;
        }
        if (this.r != null) {
            this.m.a("at", this.r.getLatitude() + "," + this.r.getLongitude());
        } else if (this.t != null) {
            this.m.a("at", this.t.getCenter().getLatitude() + "," + this.t.getCenter().getLongitude());
        }
        if (this.e != null) {
            this.m.a(this.e);
        }
        synchronized (this) {
            if (this.d != 20) {
                this.m.a(this.d);
            }
        }
        if (this.a != dd.b) {
            this.m.a(this.a);
        }
        return this.m.a(new ResultListener<DiscoveryResultPage>(this) {
            final /* synthetic */ di a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                a((DiscoveryResultPage) obj, errorCode);
            }

            public void a(final DiscoveryResultPage discoveryResultPage, final ErrorCode errorCode) {
                this.a.m = null;
                if (errorCode != ErrorCode.NONE) {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            if (this.b.a.k != null) {
                                this.b.a.k.onCompleted(new ArrayList(), errorCode);
                            }
                        }
                    });
                } else {
                    new Thread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            this.b.a.a(discoveryResultPage);
                        }
                    }).start();
                }
            }
        });
    }

    private void a(DiscoveryResultPage discoveryResultPage) {
        synchronized (this) {
            this.g = new ArrayList();
        }
        if (!(discoveryResultPage == null || discoveryResultPage.getPlaceLinks().isEmpty())) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            Iterator it = discoveryResultPage.getPlaceLinks().iterator();
            while (it.hasNext() && this.f == ErrorCode.NONE) {
                PlaceLink placeLink = (PlaceLink) it.next();
                atomicBoolean.set(false);
                placeLink.getDetailsRequest().execute(new ResultListener<Place>(this) {
                    final /* synthetic */ di b;

                    public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                        a((Place) obj, errorCode);
                    }

                    public void a(Place place, ErrorCode errorCode) {
                        this.b.f = errorCode;
                        if (this.b.f == ErrorCode.NONE) {
                            ((List) this.b.g).add(place.getLocation());
                        }
                        atomicBoolean.set(true);
                    }
                });
                while (!atomicBoolean.get()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        ez.a(new Runnable(this) {
            final /* synthetic */ di a;

            {
                this.a = r1;
            }

            public void run() {
                boolean z;
                if (this.a.k != null) {
                    this.a.k.onCompleted(this.a.g, this.a.f);
                }
                p c = this.a.u;
                a b = this.a.o;
                if (((List) this.a.g).isEmpty()) {
                    z = false;
                } else {
                    z = true;
                }
                c.a(b, false, z);
            }
        });
    }

    private ErrorCode g() {
        ErrorCode errorCode = ErrorCode.NONE;
        switch (this.o) {
            case ADDRESS_GEOCODE:
                if (this.q == null) {
                    return ErrorCode.QUERY_ADDRESS_MISSING;
                }
                return errorCode;
            case ONE_BOX_GEOCODE:
                if (this.p == null) {
                    return ErrorCode.QUERY_TEXT_MISSING;
                }
                if (this.r == null && this.t == null && this.e == null) {
                    return ErrorCode.QUERY_LOCATION_CONTEXT_INVALID;
                }
                return errorCode;
            default:
                return ErrorCode.BAD_REQUEST;
        }
    }

    private void b(final ResultListener<List<Location>> resultListener) {
        if (!this.n.get()) {
            this.l = new bi(this, this.i) {
                final /* synthetic */ di b;

                protected void a(List<Location> list) {
                    this.b.a(resultListener, list, ErrorCode.NONE);
                }

                protected void a(ErrorCode errorCode) {
                    this.b.a(resultListener, null, errorCode);
                }
            };
            this.l.a(this.q);
            this.l.a(e());
            this.l.b(this.e);
            this.l.a(this.p);
            this.l.a(this.t);
            this.l.a(this.r, this.s);
            this.l.a();
        }
    }

    private void a(ResultListener<List<Location>> resultListener, List<Location> list, ErrorCode errorCode) {
        boolean z = true;
        p pVar = this.u;
        a aVar = this.o;
        boolean z2 = errorCode != ErrorCode.NONE;
        if (list == null || list.isEmpty()) {
            z = false;
        }
        pVar.a(aVar, z2, z);
        if (resultListener != null) {
            resultListener.onCompleted(list, errorCode);
        }
    }
}

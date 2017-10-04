package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.ReverseGeocodeRequest2;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.dd.c;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

@Online
public class dp extends PlacesBaseRequest<Location> {
    private static k<ReverseGeocodeRequest2, dp> q;
    private static am<ReverseGeocodeRequest2, dp> r;
    private static int s = 10000;
    private ResultListener<Location> j;
    private bi k;
    private PlacesDiscoveryRequest l;
    private GeoCoordinate m;
    private Locale n;
    private p o;
    private boolean p;

    public static void a(k<ReverseGeocodeRequest2, dp> kVar, am<ReverseGeocodeRequest2, dp> amVar) {
        q = kVar;
        r = amVar;
    }

    static {
        ce.a(ReverseGeocodeRequest2.class);
    }

    dp(GeoCoordinate geoCoordinate, Locale locale) {
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = null;
        this.o = l.a();
        this.p = false;
        this.i = c.REVERSE_GEOCODE;
        this.m = geoCoordinate;
        this.n = locale;
    }

    public ErrorCode a(ResultListener<Location> resultListener) {
        boolean isOnline;
        ErrorCode errorCode = ErrorCode.NONE;
        if (resultListener == null) {
            return ErrorCode.INVALID_PARAMETER;
        }
        this.j = resultListener;
        try {
            isOnline = MapsEngine.c().isOnline();
        } catch (Exception e) {
            isOnline = false;
        }
        if (this.p || !r0) {
            errorCode = super.a(this.j);
        } else {
            errorCode = g();
            if (errorCode == ErrorCode.NONE) {
                ez.a(new Runnable(this) {
                    final /* synthetic */ dp a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b(this.a.j);
                    }
                });
            }
        }
        if (errorCode == ErrorCode.NONE) {
            return errorCode;
        }
        this.o.a(this.i, true, false);
        return errorCode;
    }

    public void c() {
        this.j = null;
        if (this.k != null) {
            this.k.cancel(true);
        } else if (this.l != null) {
            this.l.c();
        }
    }

    protected Locale b() {
        return this.n;
    }

    protected ErrorCode d() {
        String str = this.m.getLatitude() + "," + this.m.getLongitude();
        this.l = PlacesApi.a().a(a.OFFLINE, str);
        if (this.l == null) {
            return ErrorCode.BAD_REQUEST;
        }
        this.l.a("at", str);
        if (this.a != dd.b) {
            this.l.a(this.a);
        }
        return this.l.a(new ResultListener<DiscoveryResultPage>(this) {
            final /* synthetic */ dp a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                a((DiscoveryResultPage) obj, errorCode);
            }

            public void a(final DiscoveryResultPage discoveryResultPage, final ErrorCode errorCode) {
                this.a.l = null;
                if (errorCode != ErrorCode.NONE) {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void run() {
                            if (this.b.a.j != null) {
                                this.b.a.j.onCompleted(null, errorCode);
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
        if (!(discoveryResultPage == null || discoveryResultPage.getPlaceLinks().isEmpty())) {
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            ((PlaceLink) discoveryResultPage.getPlaceLinks().get(0)).getDetailsRequest().execute(new ResultListener<Place>(this) {
                final /* synthetic */ dp b;

                public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                    a((Place) obj, errorCode);
                }

                public void a(Place place, ErrorCode errorCode) {
                    if (errorCode == ErrorCode.NONE) {
                        place.getLocation().getAddress().setText(place.getLocation().getAddress().getText().replaceAll("<br/>", ", "));
                        this.b.g = place.getLocation();
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
        ez.a(new Runnable(this) {
            final /* synthetic */ dp a;

            {
                this.a = r1;
            }

            public void run() {
                boolean z;
                if (this.a.j != null) {
                    this.a.j.onCompleted(this.a.g, this.a.f);
                }
                p b = this.a.o;
                c cVar = this.a.i;
                if (this.a.g != null) {
                    z = true;
                } else {
                    z = false;
                }
                b.a(cVar, false, z);
            }
        });
    }

    private ErrorCode g() {
        ErrorCode errorCode = ErrorCode.NONE;
        if (this.m == null) {
            return ErrorCode.QUERY_LOCATION_CONTEXT_MISSING;
        }
        return errorCode;
    }

    private void b(final ResultListener<Location> resultListener) {
        this.k = new bi(this, this.i) {
            final /* synthetic */ dp b;

            protected void a(List<Location> list) {
                if (resultListener != null) {
                    final Location location = list.size() > 0 ? (Location) list.get(0) : null;
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 b;

                        public void run() {
                            this.b.b.a(resultListener, location, ErrorCode.NONE);
                        }
                    });
                }
            }

            protected void a(final ErrorCode errorCode) {
                if (resultListener != null) {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 b;

                        public void run() {
                            this.b.b.a(resultListener, null, errorCode);
                        }
                    });
                }
            }
        };
        this.k.a(this.m, s);
        this.k.a(this.n);
        this.k.b();
    }

    private void a(ResultListener<Location> resultListener, Location location, ErrorCode errorCode) {
        boolean z;
        boolean z2 = true;
        p pVar = this.o;
        c cVar = this.i;
        if (errorCode != ErrorCode.NONE) {
            z = true;
        } else {
            z = false;
        }
        if (location == null) {
            z2 = false;
        }
        pVar.a(cVar, z, z2);
        if (resultListener != null) {
            resultListener.onCompleted(location, errorCode);
        }
    }
}

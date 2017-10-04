package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.PlaceLink;
import com.here.android.mpa.search.ResultListener;
import com.here.android.mpa.search.ReverseGeocodeRequest;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dd.a;
import com.nokia.maps.dd.c;
import java.util.concurrent.atomic.AtomicBoolean;

@Online
@Deprecated
public class do extends PlacesBaseRequest<Address> {
    private static k<ReverseGeocodeRequest, do> p;
    private static am<ReverseGeocodeRequest, do> q;
    private static int r = 10000;
    private ResultListener<Address> j;
    private bi k;
    private PlacesDiscoveryRequest l;
    private GeoCoordinate m;
    private p n;
    private boolean o;

    public static void a(k<ReverseGeocodeRequest, do> kVar, am<ReverseGeocodeRequest, do> amVar) {
        p = kVar;
        q = amVar;
    }

    static {
        ce.a(ReverseGeocodeRequest.class);
    }

    do(GeoCoordinate geoCoordinate) {
        this.j = null;
        this.k = null;
        this.l = null;
        this.n = l.a();
        this.o = false;
        this.i = c.r;
        this.m = geoCoordinate;
    }

    public ErrorCode a(ResultListener<Address> resultListener) {
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
        if (this.o || !r0) {
            errorCode = super.a(this.j);
        } else {
            errorCode = g();
            if (errorCode == ErrorCode.NONE) {
                ez.a(new 1(this));
            }
        }
        if (errorCode == ErrorCode.NONE) {
            return errorCode;
        }
        this.n.a(this.i, true, false);
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

    protected ErrorCode d() {
        String str = this.m.getLatitude() + "," + this.m.getLongitude();
        this.l = PlacesApi.a().a(a.a, str);
        if (this.l == null) {
            return ErrorCode.BAD_REQUEST;
        }
        this.l.a("at", str);
        if (this.a != dd.b) {
            this.l.a(this.a);
        }
        return this.l.a(new 2(this));
    }

    private void a(DiscoveryResultPage discoveryResultPage) {
        if (!(discoveryResultPage == null || discoveryResultPage.getPlaceLinks().isEmpty())) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            ((PlaceLink) discoveryResultPage.getPlaceLinks().get(0)).getDetailsRequest().execute(new 3(this, atomicBoolean));
            while (!atomicBoolean.get()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
        ez.a(new 4(this));
    }

    private ErrorCode g() {
        ErrorCode errorCode = ErrorCode.NONE;
        if (this.m == null) {
            return ErrorCode.QUERY_LOCATION_CONTEXT_MISSING;
        }
        return errorCode;
    }

    private void b(ResultListener<Address> resultListener) {
        this.k = new 5(this, this.i, resultListener);
        this.k.a(this.m, r);
        this.k.b();
    }

    private void a(ResultListener<Address> resultListener, Address address, ErrorCode errorCode) {
        boolean z;
        boolean z2 = true;
        p pVar = this.n;
        c cVar = this.i;
        if (errorCode != ErrorCode.NONE) {
            z = true;
        } else {
            z = false;
        }
        if (address == null) {
            z2 = false;
        }
        pVar.a(cVar, z, z2);
        if (resultListener != null) {
            resultListener.onCompleted(address, errorCode);
        }
    }
}

package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesApi;
import com.nokia.maps.PlacesBaseRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.di;
import com.nokia.maps.dy;
import com.nokia.maps.k;
import java.util.List;

public class GeocodeRequest extends Request<List<Location>> {
    private String a;
    private GeoCoordinate b;
    private int c;
    private GeoBoundingBox d;

    @Online
    public GeocodeRequest(String str) throws IllegalArgumentException {
        boolean z = false;
        this.b = null;
        this.c = 0;
        this.d = null;
        dy.a((Object) str, "Query text is null");
        if (!str.isEmpty()) {
            z = true;
        }
        dy.a(Boolean.valueOf(z), "Query text is empty");
        this.a = str;
    }

    private GeocodeRequest(di diVar) {
        super(diVar);
        this.b = null;
        this.c = 0;
        this.d = null;
    }

    @Online
    public GeocodeRequest setSearchArea(GeoCoordinate geoCoordinate, int i) {
        dy.a((Object) geoCoordinate, "Search center coordinate is null");
        dy.a(geoCoordinate.isValid(), "Search center coordinate is invalid");
        dy.a(i > 0, "Search radius must be greater than 0");
        this.b = geoCoordinate;
        this.c = i;
        return this;
    }

    @Online
    public GeocodeRequest setSearchArea(GeoBoundingBox geoBoundingBox) {
        dy.a((Object) geoBoundingBox, "Search area bounding box is null");
        this.d = geoBoundingBox;
        return this;
    }

    @Online
    public int getCollectionSize() {
        return super.getCollectionSize();
    }

    @Online
    public GeocodeRequest setCollectionSize(int i) {
        return (GeocodeRequest) super.setCollectionSize(i);
    }

    @Online
    public GeocodeRequest setMapViewport(GeoBoundingBox geoBoundingBox) {
        return (GeocodeRequest) super.setMapViewport(geoBoundingBox);
    }

    @Online
    public ErrorCode execute(ResultListener<List<Location>> resultListener) {
        a();
        PlacesBaseRequest placesBaseRequest = null;
        if (this.a != null) {
            GeoCoordinate geoCoordinate;
            if (this.b != null) {
                geoCoordinate = this.b;
            } else if (this.d != null) {
                geoCoordinate = this.d.getCenter();
            } else {
                geoCoordinate = this.i.getCenter();
            }
            placesBaseRequest = PlacesApi.a().a(geoCoordinate, this.a);
            placesBaseRequest.b(this.a);
            placesBaseRequest.a(this.b, this.c);
            placesBaseRequest.b(this.d);
            placesBaseRequest.c(this.i);
        }
        this.f = placesBaseRequest;
        return super.execute(resultListener);
    }

    static {
        di.a(new k<GeocodeRequest, di>() {
            public di a(GeocodeRequest geocodeRequest) {
                return (di) geocodeRequest.f;
            }
        }, new am<GeocodeRequest, di>() {
            public GeocodeRequest a(di diVar) {
                return diVar != null ? new GeocodeRequest(diVar) : null;
            }
        });
    }
}

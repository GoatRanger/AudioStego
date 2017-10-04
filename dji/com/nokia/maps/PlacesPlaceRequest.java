package com.nokia.maps;

import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.PlaceRequest;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesPlaceRequest extends PlacesBaseRequest<Place> {
    private static k<PlaceRequest, PlacesPlaceRequest> j;
    private static am<PlaceRequest, PlacesPlaceRequest> k;

    public static void a(k<PlaceRequest, PlacesPlaceRequest> kVar, am<PlaceRequest, PlacesPlaceRequest> amVar) {
        j = kVar;
        k = amVar;
    }

    static PlaceRequest a(PlacesPlaceRequest placesPlaceRequest) {
        if (placesPlaceRequest != null) {
            return (PlaceRequest) k.a(placesPlaceRequest);
        }
        return null;
    }

    static {
        ce.a(PlaceRequest.class);
    }

    @OnlineNative
    private PlacesPlaceRequest(int i) {
        super(i);
        this.i = c.p;
    }
}

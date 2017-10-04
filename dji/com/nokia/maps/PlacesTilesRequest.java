package com.nokia.maps;

import com.here.android.mpa.search.e;
import com.here.android.mpa.search.f;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesTilesRequest extends PlacesBaseRequest<e> {
    private static k<f, PlacesTilesRequest> j;
    private static am<f, PlacesTilesRequest> k;

    public static void a(k<f, PlacesTilesRequest> kVar, am<f, PlacesTilesRequest> amVar) {
        j = kVar;
        k = amVar;
    }

    static {
        ce.a(f.class);
    }

    @OnlineNative
    private PlacesTilesRequest(int i) {
        super(i);
        this.i = c.TILES;
    }
}

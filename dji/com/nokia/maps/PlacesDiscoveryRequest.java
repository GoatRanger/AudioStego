package com.nokia.maps;

import com.here.android.mpa.search.DiscoveryRequest;
import com.here.android.mpa.search.DiscoveryResultPage;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesDiscoveryRequest extends PlacesBaseRequest<DiscoveryResultPage> {
    private static k<DiscoveryRequest, PlacesDiscoveryRequest> j;
    private static am<DiscoveryRequest, PlacesDiscoveryRequest> k;

    public static void a(k<DiscoveryRequest, PlacesDiscoveryRequest> kVar, am<DiscoveryRequest, PlacesDiscoveryRequest> amVar) {
        j = kVar;
        k = amVar;
    }

    static DiscoveryRequest a(PlacesDiscoveryRequest placesDiscoveryRequest) {
        if (placesDiscoveryRequest != null) {
            return (DiscoveryRequest) k.a(placesDiscoveryRequest);
        }
        return null;
    }

    static {
        ce.a(DiscoveryRequest.class);
    }

    @OnlineNative
    private PlacesDiscoveryRequest(int i) {
        super(i);
        this.i = c.DISCOVER;
    }
}

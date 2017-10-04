package com.nokia.maps;

import com.here.android.mpa.search.TransitSchedulePage;
import com.here.android.mpa.search.TransitSchedulePageRequest;
import com.nokia.maps.annotation.OnlineNative;
import com.nokia.maps.dd.c;

public class PlacesTransitSchedulePageRequest extends PlacesBaseRequest<TransitSchedulePage> {
    private static k<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest> j;
    private static am<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest> k;

    public static void a(k<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest> kVar, am<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest> amVar) {
        j = kVar;
        k = amVar;
    }

    static TransitSchedulePageRequest a(PlacesTransitSchedulePageRequest placesTransitSchedulePageRequest) {
        if (placesTransitSchedulePageRequest != null) {
            return (TransitSchedulePageRequest) k.a(placesTransitSchedulePageRequest);
        }
        return null;
    }

    static {
        ce.a(TransitSchedulePageRequest.class);
    }

    @OnlineNative
    private PlacesTransitSchedulePageRequest(int i) {
        super(i);
        this.i = c.TRANSIT_SCHEDULE_PAGE;
    }
}

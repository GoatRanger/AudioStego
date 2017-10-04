package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitSchedulePageRequest;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

public class TransitSchedulePageRequest extends Request<TransitSchedulePage> {
    private PlacesTransitSchedulePageRequest a;

    @Online
    TransitSchedulePageRequest(PlacesTransitSchedulePageRequest placesTransitSchedulePageRequest) {
        super(placesTransitSchedulePageRequest);
        this.a = placesTransitSchedulePageRequest;
    }

    @Online
    public ErrorCode execute(ResultListener<TransitSchedulePage> resultListener) {
        return super.execute(resultListener);
    }

    public int hashCode() {
        return (this.f == null ? 0 : this.f.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f.equals(obj);
    }

    static {
        PlacesTransitSchedulePageRequest.a(new k<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest>() {
            public PlacesTransitSchedulePageRequest a(TransitSchedulePageRequest transitSchedulePageRequest) {
                return transitSchedulePageRequest.a;
            }
        }, new am<TransitSchedulePageRequest, PlacesTransitSchedulePageRequest>() {
            public TransitSchedulePageRequest a(PlacesTransitSchedulePageRequest placesTransitSchedulePageRequest) {
                return placesTransitSchedulePageRequest != null ? new TransitSchedulePageRequest(placesTransitSchedulePageRequest) : null;
            }
        });
    }
}

package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitDeparture;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;
import java.util.Map;

@Online
public class TransitDeparture {
    public static final String DEPARTURE_PLATFORM_KEY_NAME = "platform";
    public static final String DEPARTURE_TIME_KEY_NAME = "departure";
    public static final String EXCEPTION_EVENT_ADDITIONAL = "additional";
    public static final String EXCEPTION_EVENT_CANCELLED = "cancelled";
    public static final String EXCEPTION_EVENT_REDIRECTED = "redirected";
    public static final String EXCEPTION_EVENT_REPLACED = "replaced";
    protected PlacesTransitDeparture a;

    TransitDeparture(PlacesTransitDeparture placesTransitDeparture) {
        this.a = placesTransitDeparture;
    }

    public String getLine() {
        return this.a.a();
    }

    public Map<String, String> getScheduledTimeInformation() {
        return this.a.b();
    }

    public Map<String, String> getRealTimeInformation() {
        return this.a.c();
    }

    public String getDirection() {
        return this.a.d();
    }

    public String getException() {
        return this.a.e();
    }

    public String getOperator() {
        return this.a.f();
    }

    public List<ExtendedAttribute> getExtendedAttributes() {
        return this.a.g();
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(obj);
    }

    static {
        PlacesTransitDeparture.a(new k<TransitDeparture, PlacesTransitDeparture>() {
            public PlacesTransitDeparture a(TransitDeparture transitDeparture) {
                return transitDeparture != null ? transitDeparture.a : null;
            }
        }, new am<TransitDeparture, PlacesTransitDeparture>() {
            public TransitDeparture a(PlacesTransitDeparture placesTransitDeparture) {
                return placesTransitDeparture != null ? new TransitDeparture(placesTransitDeparture) : null;
            }
        });
    }
}

package com.here.android.mpa.search;

import com.nokia.maps.PlacesTransitSchedulePage;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;
import java.util.Map;

@Online
public class TransitSchedulePage {
    protected PlacesTransitSchedulePage a;

    TransitSchedulePage(PlacesTransitSchedulePage placesTransitSchedulePage) {
        this.a = placesTransitSchedulePage;
    }

    public int getOffsetCount() {
        return this.a.a();
    }

    public TransitSchedulePageRequest getNextPageRequest() {
        return this.a.b();
    }

    public TransitSchedulePageRequest getPreviousPageRequest() {
        return this.a.c();
    }

    public List<TransitDeparture> getItems() {
        return this.a.d();
    }

    public Map<String, TransitLine> getLines() {
        return this.a.e();
    }

    public Map<String, TransitOperator> getOperators() {
        return this.a.f();
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
        PlacesTransitSchedulePage.a(new k<TransitSchedulePage, PlacesTransitSchedulePage>() {
            public PlacesTransitSchedulePage a(TransitSchedulePage transitSchedulePage) {
                return transitSchedulePage != null ? transitSchedulePage.a : null;
            }
        }, new am<TransitSchedulePage, PlacesTransitSchedulePage>() {
            public TransitSchedulePage a(PlacesTransitSchedulePage placesTransitSchedulePage) {
                return placesTransitSchedulePage != null ? new TransitSchedulePage(placesTransitSchedulePage) : null;
            }
        });
    }
}

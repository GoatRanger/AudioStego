package com.here.android.mpa.search;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PlacesAutoSuggest;
import com.nokia.maps.annotation.Online;

public class AutoSuggestPlace extends AutoSuggest {
    AutoSuggestPlace(PlacesAutoSuggest placesAutoSuggest) {
        super(placesAutoSuggest);
    }

    @Online
    public String getVicinity() {
        return this.m_pimpl.c();
    }

    @Online
    public GeoCoordinate getPosition() {
        return this.m_pimpl.d();
    }

    @Online
    public String getCategory() {
        return this.m_pimpl.e();
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.m_pimpl.f();
    }

    @Online
    public PlaceRequest getPlaceDetailsRequest() {
        return this.m_pimpl.g();
    }

    @Online
    public int hashCode() {
        return (this.m_pimpl == null ? 0 : this.m_pimpl.hashCode()) + 31;
    }

    @Online
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.m_pimpl.equals(obj);
    }
}

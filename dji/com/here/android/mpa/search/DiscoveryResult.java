package com.here.android.mpa.search;

import com.nokia.maps.PlacesLink;
import com.nokia.maps.annotation.Online;

@Online
public class DiscoveryResult extends Link {

    @Online
    public enum ResultType {
        UNKNOWN,
        PLACE,
        DISCOVERY
    }

    protected DiscoveryResult(PlacesLink placesLink) {
        super(placesLink);
    }

    public ResultType getResultType() {
        return this.a.g();
    }

    public String getTitle() {
        return super.getTitle();
    }

    public String getIconUrl() {
        return super.getIconUrl();
    }

    public String getId() {
        return super.getId();
    }

    public String getVicinity() {
        return this.a.m();
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
}

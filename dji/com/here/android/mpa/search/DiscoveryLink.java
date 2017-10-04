package com.here.android.mpa.search;

import com.nokia.maps.PlacesLink;
import com.nokia.maps.annotation.Online;

public class DiscoveryLink extends DiscoveryResult {
    DiscoveryLink(PlacesLink placesLink) {
        super(placesLink);
    }

    @Online
    public String getTitle() {
        return super.getTitle();
    }

    @Online
    public String getIconUrl() {
        return super.getIconUrl();
    }

    @Online
    public String getId() {
        return super.getId();
    }

    @Online
    public DiscoveryRequest getRequest() {
        return this.a.p();
    }

    @Online
    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    @Online
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

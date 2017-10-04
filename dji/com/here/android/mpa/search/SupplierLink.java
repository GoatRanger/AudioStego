package com.here.android.mpa.search;

import com.nokia.maps.PlacesLink;
import com.nokia.maps.annotation.Online;

public class SupplierLink extends Link {
    SupplierLink(PlacesLink placesLink) {
        super(placesLink);
    }

    @Online
    public String getId() {
        return this.a.d();
    }

    @Online
    public String getName() {
        return this.a.f();
    }

    @Online
    public String getIconUrl() {
        return super.getIconUrl();
    }

    @Online
    public String getUrl() {
        return this.a.a();
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
